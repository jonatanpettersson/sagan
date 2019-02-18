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

public class MoveGen {

  public static int[] allMoves = new int[256];
  public static int allMovesIdx = 0;

  private static long[] bishopDeltas;
  private static long[] rookDeltas;
  private static long[] queenDeltas;
  private static long[] knightDeltas;
  private static long[] kingDeltas;
  private static long[] pawnWhiteCaptureDeltas;
  private static long[] pawnBlackCaptureDeltas;

  private static long[][] magicBishopMoves;
  private static long[][] magicRookMoves;

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

      rookDeltas[square] = rankMask(square) ^ fileMask(square);
      // Mask out border bit (unless the rook is placed on that border)
      // A-file
      if ((bSquare & 0x101010101010101L) == 0) {
        rookDeltas[square] &= ~0x101010101010101L;
      }
      // 1-rank
      if ((bSquare & 0xffL) == 0) {
        rookDeltas[square] &= ~0xffL;
      }
      // H-file
      if ((bSquare & 0x8080808080808080L) == 0) {
        rookDeltas[square] &= ~0x8080808080808080L;
      }
      // 8-rank
      if ((bSquare & 0xff00000000000000L) == 0) {
        rookDeltas[square] &= ~0xff00000000000000L;
      }

      bishopDeltas[square] = (diagonalMask(square) ^ antiDiagMask(square)) & 0x7e7e7e7e7e7e00L;
      queenDeltas[square] = rookDeltas[square] ^ bishopDeltas[square];
    }

    magicRookMoves = MagicBitBoard.generateMagicRookMoves(rookDeltas);
    magicBishopMoves = MagicBitBoard.generateMagiBishopMoves(bishopDeltas);
  }

  public static void genMoves(Board board) {
    allMovesIdx = 0;
    if (board.toMove == 0) {
      genTargets(board.pieces[Board.WN], knightDeltas, ~board.wPieces);
      genTargets(board.pieces[Board.WK], kingDeltas, ~board.wPieces);

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

      if (isSet(board.wCastle, 1) && (board.allPieces & 0b1100000) == 0) {
        allMoves[allMovesIdx++] = m(E1, G1, 3, 0);
      }
      if (isSet(board.wCastle, 1) && (board.allPieces & 0b1110) == 0) {
        allMoves[allMovesIdx++] = m(E1, C1, 3, 0);
      }

      for (int i = 0; i < 16; i++) {
        System.out.println(
            Board.prettyPrintBitBoard(
                magicRookMoves[i][MagicBitBoard.magicIndex(board.allPieces, rookDeltas[i], i)]));
      }
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

  // The following four methods are ported from https://www.chessprogramming.org/On_an_empty_Board
  private static long rankMask(int sq) {
    return 0xffL << (sq & 56L);
  }

  private static long fileMask(int sq) {
    return 0x0101010101010101L << (sq & 7);
  }

  private static long diagonalMask(int sq) {
    long maindia = 0x8040201008040201L;
    int diag = 8 * (sq & 7) - (sq & 56);
    int nort = -diag & (diag >>> 31);
    int sout = diag & (-diag >>> 31);
    return (maindia >> sout) << nort;
  }

  private static long antiDiagMask(int sq) {
    long maindia = 0x0102040810204080L;
    int diag = 56 - 8 * (sq & 7) - (sq & 56);
    int nort = -diag & (diag >>> 31);
    int sout = diag & (-diag >>> 31);
    return (maindia >> sout) << nort;
  }
}
