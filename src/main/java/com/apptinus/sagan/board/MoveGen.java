package com.apptinus.sagan.board;

import static com.apptinus.sagan.board.Board.WP;
import static com.apptinus.sagan.board.Move.C1;
import static com.apptinus.sagan.board.Move.E1;
import static com.apptinus.sagan.board.Move.G1;
import static com.apptinus.sagan.board.Move.ea;
import static com.apptinus.sagan.board.Move.m;
import static com.apptinus.sagan.board.Move.no;
import static com.apptinus.sagan.board.Move.noEa;
import static com.apptinus.sagan.board.Move.noEaEa;
import static com.apptinus.sagan.board.Move.noNoEa;
import static com.apptinus.sagan.board.Move.noNoWe;
import static com.apptinus.sagan.board.Move.noWe;
import static com.apptinus.sagan.board.Move.noWeWe;
import static com.apptinus.sagan.board.Move.so;
import static com.apptinus.sagan.board.Move.soEa;
import static com.apptinus.sagan.board.Move.soEaEa;
import static com.apptinus.sagan.board.Move.soSoEa;
import static com.apptinus.sagan.board.Move.soSoWe;
import static com.apptinus.sagan.board.Move.soWe;
import static com.apptinus.sagan.board.Move.soWeWe;
import static com.apptinus.sagan.board.Move.we;
import static com.apptinus.sagan.util.Bitops.isSet;
import static com.apptinus.sagan.util.Bitops.next;
import static com.apptinus.sagan.util.Bitops.setBit;
import static com.apptinus.sagan.util.Bitops.unset;

import com.apptinus.sagan.util.Bitops;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class MoveGen {

  public static int[] allMoves = new int[256];
  public static int allMovesIdx = 0;

  public static long[] bishopDeltas;
  public static long[] rookDeltas;
  private static long[] queenDeltas;
  private static long[] knightDeltas;
  private static long[] kingDeltas;
  private static long[] pawnWhiteCaptureDeltas;
  private static long[] pawnBlackCaptureDeltas;

  public static long[][] magicBishopMoves;
  public static long[][] magicRookMoves;

  static {
    bishopDeltas = new long[64];
    rookDeltas = new long[64];
    queenDeltas = new long[64];
    knightDeltas = new long[64];
    kingDeltas = new long[64];
    pawnWhiteCaptureDeltas = new long[64];
    pawnBlackCaptureDeltas = new long[64];

    magicBishopMoves = new long[64][4096];
    magicRookMoves = new long[64][4096];

    for (int square = 0; square <= 63; square++) {
      long bSquare = Bitops.setBit(square);

      knightDeltas[square] =
          noNoEa(bSquare)
              | noEaEa(bSquare)
              | soEaEa(bSquare)
              | soSoEa(bSquare)
              | noNoWe(bSquare)
              | noWeWe(bSquare)
              | soWeWe(bSquare)
              | soSoWe(bSquare);

      kingDeltas[square] = ea(bSquare) | we(bSquare);
      long bSquareWithKing = bSquare | kingDeltas[square];
      kingDeltas[square] |= no(bSquareWithKing) | so(bSquareWithKing);

      pawnWhiteCaptureDeltas[square] = noEa(square) | noWe(square);
      pawnBlackCaptureDeltas[square] = soEa(square) | soWe(square);

      rookDeltas[square] = rookAttackMask(square);
      bishopDeltas[square] = bishopAttackMask(square);

      queenDeltas[square] = rookDeltas[square] ^ bishopDeltas[square];
    }

    magicRookMoves = MagicBitBoard.generateMagicRookMoves(rookDeltas);
    magicBishopMoves = MagicBitBoard.generateMagiBishopMoves(bishopDeltas);
  }

  public static void genMoves(Board board) {
    allMovesIdx = 0;
    if (board.toMove == 0) {
      // Statics
      genTargets(board.pieces[Board.WN], knightDeltas, ~board.wPieces);
      genTargets(board.pieces[Board.WK], kingDeltas, ~board.wPieces);

      // Sliding
      genTargetsRooks(board.pieces[Board.WR], board.allPieces, ~board.wPieces);
      genTargetsBishops(board.pieces[Board.WB], board.allPieces, ~board.wPieces);

      // Pawns
      long pawnsAbleToPush = so(~board.allPieces) & board.pieces[WP];
      long pawnsAbleToPromote = pawnsAbleToPush & 0xFF000000000000L;
      pawnsAbleToPush &= 0xFFFFFFFFFFFFL;
      long pawsAbleToPushPush =
          so(~board.allPieces & so(~board.allPieces & 0xFF000000)) & board.pieces[WP] & 0xFF00;

      genTargets(pawnsAbleToPush, 8);
      genTargets(pawsAbleToPushPush, 16);
      genPawnPromotions(pawnsAbleToPromote, 8);
      genTargets(board.pieces[WP], pawnWhiteCaptureDeltas, board.bPieces);

      if (board.ep != -1) {
        genEp(board.pieces[WP] & (soEa(setBit(board.ep)) | soWe(setBit(board.ep))), board.ep);
      }

      // Castling
      if (isSet(board.wCastle, 1) && (board.allPieces & 0b1100000) == 0) {
        allMoves[allMovesIdx++] = m(E1, G1, 3, 0);
      }
      if (isSet(board.wCastle, 1) && (board.allPieces & 0b1110) == 0) {
        allMoves[allMovesIdx++] = m(E1, C1, 3, 0);
      }
    } else {
      throw new NotImplementedException();
    }
  }

  private static void genTargetsRooks(long pieces, long allPieces, long friendlies) {
    int from;
    while ((from = next(pieces)) >= 0) {
      int to;
      int magicIndex = MagicBitBoard.magicIndexRook(allPieces, MoveGen.rookDeltas[from], from);
      long delta = magicRookMoves[from][magicIndex] &= friendlies;
      while ((to = next(delta)) >= 0) {
        allMoves[allMovesIdx++] = m(from, to, 0, 0);
        delta = unset(delta, to);
      }
      pieces = unset(pieces, from);
    }
  }

  private static void genTargetsBishops(long pieces, long allPieces, long friendlies) {
    int from;
    while ((from = next(pieces)) >= 0) {
      int to;
      int magicIndex = MagicBitBoard.magicIndexBishop(allPieces, MoveGen.bishopDeltas[from], from);
      long delta = magicBishopMoves[from][magicIndex] &= friendlies;
      while ((to = next(delta)) >= 0) {
        allMoves[allMovesIdx++] = m(from, to, 0, 0);
        delta = unset(delta, to);
      }
      pieces = unset(pieces, from);
    }
  }

  private static void genTargets(long pieces, long[] deltas, long friendlies) {
    int n;
    while ((n = next(pieces)) >= 0) {
      int na;
      long delta = deltas[n] &= friendlies;
      while ((na = next(delta)) >= 0) {
        allMoves[allMovesIdx++] = m(n, na, 0, 0);
        delta = unset(delta, na);
      }
      pieces = unset(pieces, n);
    }
  }

  private static void genTargets(long pieces, int delta) {
    int n;
    while ((n = next(pieces)) >= 0) {
      allMoves[allMovesIdx++] = m(n, n + delta, 0, 0);
      pieces = unset(pieces, n);
    }
  }

  private static void genPawnPromotions(long pieces, int delta) {
    int n;
    while ((n = next(pieces)) >= 0) {
      allMoves[allMovesIdx++] = m(n, n + delta, 1, 0);
      allMoves[allMovesIdx++] = m(n, n + delta, 1, 1);
      allMoves[allMovesIdx++] = m(n, n + delta, 1, 2);
      allMoves[allMovesIdx++] = m(n, n + delta, 1, 3);
      pieces = unset(pieces, n);
    }
  }

  private static void genEp(long pieces, int target) {
    int n;
    while ((n = next(pieces)) >= 0) {
      allMoves[allMovesIdx++] = m(n, target, 2, 0);
      pieces = unset(pieces, n);
    }
  }

  public static long bishopAttackMask(int square) {
    return (diagonalMask(square) ^ antiDiagMask(square)) & 0x7e7e7e7e7e7e00L;
  }

  public static long rookAttackMask(int square) {
    long mask = rankMask(square) ^ fileMask(square);

    long bSquare = Bitops.setBit(square);

    // Mask out border bit (unless the rook is placed on that border)
    // A-file
    if ((bSquare & 0x101010101010101L) == 0) {
      mask &= ~0x101010101010101L;
    }
    // 1-rank
    if ((bSquare & 0xffL) == 0) {
      mask &= ~0xffL;
    }
    // H-file
    if ((bSquare & 0x8080808080808080L) == 0) {
      mask &= ~0x8080808080808080L;
    }
    // 8-rank
    if ((bSquare & 0xff00000000000000L) == 0) {
      mask &= ~0xff00000000000000L;
    }

    return mask;
  }

  // The following four methods are ported from https://www.chessprogramming.org/On_an_empty_Board
  private static long rankMask(int sq) {
    return 0xffL << (sq & 56L);
  }

  private static long fileMask(int sq) {
    return 0x0101010101010101L << (sq & 7L);
  }

  private static long diagonalMask(int sq) {
    long maindia = 0x8040201008040201L;
    long diag = 8L * (sq & 7L) - (sq & 56L);
    long nort = -diag & (diag >>> 31L);
    long sout = diag & (-diag >>> 31L);
    return (maindia >>> sout) << nort;
  }

  private static long antiDiagMask(int sq) {
    long maindia = 0x0102040810204080L;
    long diag = 56L - 8L * (sq & 7L) - (sq & 56L);
    long nort = -diag & (diag >>> 31L);
    long sout = diag & (-diag >>> 31L);
    return (maindia >>> sout) << nort;
  }
}
