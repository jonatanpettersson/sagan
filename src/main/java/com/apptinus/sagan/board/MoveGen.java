package com.apptinus.sagan.board;

import static com.apptinus.sagan.board.Board.BK;
import static com.apptinus.sagan.board.Board.BP;
import static com.apptinus.sagan.board.Board.WK;
import static com.apptinus.sagan.board.Board.WP;
import static com.apptinus.sagan.board.Move.BLACK;
import static com.apptinus.sagan.board.Move.C1;
import static com.apptinus.sagan.board.Move.C8;
import static com.apptinus.sagan.board.Move.D1;
import static com.apptinus.sagan.board.Move.D8;
import static com.apptinus.sagan.board.Move.E1;
import static com.apptinus.sagan.board.Move.E8;
import static com.apptinus.sagan.board.Move.F1;
import static com.apptinus.sagan.board.Move.F8;
import static com.apptinus.sagan.board.Move.G1;
import static com.apptinus.sagan.board.Move.G8;
import static com.apptinus.sagan.board.Move.WHITE;
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
import static com.apptinus.sagan.util.Bitops.next;
import static com.apptinus.sagan.util.Bitops.setBit;
import static com.apptinus.sagan.util.Bitops.unset;

import com.apptinus.sagan.util.Bitops;

public class MoveGen {

  public static long[] bishopDeltas;
  public static long[] rookDeltas;
  public static long[] queenDeltas;
  public static long[] knightDeltas;
  public static long[] kingDeltas;
  public static long[] pawnWhiteCaptureDeltas;
  public static long[] pawnBlackCaptureDeltas;

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

      pawnWhiteCaptureDeltas[square] = noEa(bSquare) | noWe(bSquare);
      pawnBlackCaptureDeltas[square] = soEa(bSquare) | soWe(bSquare);

      rookDeltas[square] = rookAttackMask(square);
      bishopDeltas[square] = bishopAttackMask(square);

      queenDeltas[square] = rookDeltas[square] ^ bishopDeltas[square];
    }

    magicRookMoves = MagicBitBoard.generateMagicRookMoves(rookDeltas);
    magicBishopMoves = MagicBitBoard.generateMagiBishopMoves(bishopDeltas);
  }

  public static int genMoves(Board board, int[] moves, int startIndex) {
    int totalPseudoMoves = genPseudoLegal(board, moves);
    int totalLegalMoves = 0;
    for (int i = 0; i < totalPseudoMoves + startIndex; i++) {
      int move = moves[i];
      //      System.out.println(Board.moveToNotation(move));
      board.make(move);
      int checkSquare =
          board.toMove == WHITE ? Bitops.next(board.pieces[BK]) : Bitops.next(board.pieces[WK]);
      if (!board.isAttacked(checkSquare, board.toMove)) {
        moves[startIndex + totalLegalMoves] = move;
        totalLegalMoves++;
      }
      // Check castled over checked square
      board.unmake(move);
    }

    return totalLegalMoves;
  }

  public static int genPseudoLegal(Board board, int[] moves) {
    int movesIdx = 0;
    if (board.toMove == WHITE) {
      // Statics
      movesIdx = genTargets(board.pieces[Board.WN], knightDeltas, ~board.wPieces, moves, movesIdx);
      movesIdx = genTargets(board.pieces[Board.WK], kingDeltas, ~board.wPieces, moves, movesIdx);

      // Sliding
      movesIdx =
          genTargetsRooks(board.pieces[Board.WR], board.allPieces, ~board.wPieces, moves, movesIdx);
      movesIdx =
          genTargetsBishops(
              board.pieces[Board.WB], board.allPieces, ~board.wPieces, moves, movesIdx);
      movesIdx =
          genTargetsRooks(board.pieces[Board.WQ], board.allPieces, ~board.wPieces, moves, movesIdx);
      movesIdx =
          genTargetsBishops(
              board.pieces[Board.WQ], board.allPieces, ~board.wPieces, moves, movesIdx);

      // Pawns
      long pawnsAbleToPush = so(~board.allPieces) & board.pieces[WP];
      long pawnsAbleToPromote = pawnsAbleToPush & 0xFF000000000000L;
      pawnsAbleToPush &= 0xFFFFFFFFFFFFL;
      long pawsAbleToPushPush =
          so(~board.allPieces & so(~board.allPieces & 0xFF000000)) & board.pieces[WP] & 0xFF00;

      movesIdx = genTargets(pawnsAbleToPush, 8, moves, movesIdx);
      movesIdx = genTargets(pawsAbleToPushPush, 16, moves, movesIdx);
      movesIdx = genPawnPromotions(pawnsAbleToPromote, 8, moves, movesIdx);
      movesIdx =
          genTargets(board.pieces[WP], pawnWhiteCaptureDeltas, board.bPieces, moves, movesIdx);

      if (board.ep != -1) {
        movesIdx =
            genEp(
                board.pieces[WP] & (soEa(setBit(board.ep)) | soWe(setBit(board.ep))),
                board.ep,
                moves,
                movesIdx);
      }

      // Castling
      if ((board.wCastle & 0b01L) != 0
          && (board.allPieces & 0b1100000) == 0
          && !board.isAttacked(F1, BLACK)) {
        moves[movesIdx++] = m(E1, G1, 3, 0);
      }
      if ((board.wCastle & 0b10L) != 0
          && (board.allPieces & 0b1110) == 0
          && !board.isAttacked(D1, BLACK)) {
        moves[movesIdx++] = m(E1, C1, 3, 0);
      }
    } else {
      // Statics
      movesIdx = genTargets(board.pieces[Board.BN], knightDeltas, ~board.bPieces, moves, movesIdx);
      movesIdx = genTargets(board.pieces[Board.BK], kingDeltas, ~board.bPieces, moves, movesIdx);

      // Sliding
      movesIdx =
          genTargetsRooks(board.pieces[Board.BR], board.allPieces, ~board.bPieces, moves, movesIdx);
      movesIdx =
          genTargetsBishops(
              board.pieces[Board.BB], board.allPieces, ~board.bPieces, moves, movesIdx);
      movesIdx =
          genTargetsRooks(board.pieces[Board.BQ], board.allPieces, ~board.bPieces, moves, movesIdx);
      movesIdx =
          genTargetsBishops(
              board.pieces[Board.BQ], board.allPieces, ~board.bPieces, moves, movesIdx);

      // Pawns
      long pawnsAbleToPush = no(~board.allPieces) & board.pieces[BP];
      long pawnsAbleToPromote = pawnsAbleToPush & 0xFF00L;
      pawnsAbleToPush &= 0xFFFFFFFFFFFF0000L;
      long pawsAbleToPushPush =
          no(~board.allPieces & no(~board.allPieces & 0xFF00000000L))
              & board.pieces[BP]
              & 0xFF000000000000L;

      movesIdx = genTargets(pawnsAbleToPush, -8, moves, movesIdx);
      movesIdx = genTargets(pawsAbleToPushPush, -16, moves, movesIdx);
      movesIdx = genPawnPromotions(pawnsAbleToPromote, -8, moves, movesIdx);
      movesIdx =
          genTargets(board.pieces[BP], pawnBlackCaptureDeltas, board.wPieces, moves, movesIdx);

      if (board.ep != -1) {
        movesIdx =
            genEp(
                board.pieces[BP] & (noEa(setBit(board.ep)) | noWe(setBit(board.ep))),
                board.ep,
                moves,
                movesIdx);
      }

      // Castling
      if ((board.bCastle & 0b01L) != 0
          && (board.allPieces & 0x6000000000000000L) == 0
          && !board.isAttacked(F8, WHITE)) {
        moves[movesIdx++] = m(E8, G8, 3, 0);
      }
      if ((board.bCastle & 0b10L) != 0
          && (board.allPieces & 0xe00000000000000L) == 0
          && !board.isAttacked(D8, WHITE)) {
        moves[movesIdx++] = m(E8, C8, 3, 0);
      }
    }

    return movesIdx;
  }

  private static int genTargetsRooks(
      long pieces, long allPieces, long antiFriendlies, int[] moves, int movesIdx) {
    int from;
    while ((from = next(pieces)) >= 0) {
      int to;
      int magicIndex = MagicBitBoard.magicIndexRook(allPieces, MoveGen.rookDeltas[from], from);
      long delta = magicRookMoves[from][magicIndex] & antiFriendlies;
      while ((to = next(delta)) >= 0) {
        moves[movesIdx++] = m(from, to, 0, 0);
        delta = unset(delta, to);
      }
      pieces = unset(pieces, from);
    }
    return movesIdx;
  }

  public static long genTargetsRooks(int from, long allPieces, long antiFriendlies) {
    long attacks = 0L;
    int magicIndex = MagicBitBoard.magicIndexRook(allPieces, MoveGen.rookDeltas[from], from);
    long delta = magicRookMoves[from][magicIndex] & antiFriendlies;
    int to;
    while ((to = next(delta)) >= 0) {
      attacks |= Bitops.setBit(to);
      delta = unset(delta, to);
    }
    return attacks;
  }

  private static int genTargetsBishops(
      long pieces, long allPieces, long antiFriendlies, int[] moves, int movesIdx) {
    int from;
    while ((from = next(pieces)) >= 0) {
      int to;
      int magicIndex = MagicBitBoard.magicIndexBishop(allPieces, MoveGen.bishopDeltas[from], from);
      long delta = magicBishopMoves[from][magicIndex] & antiFriendlies;
      while ((to = next(delta)) >= 0) {
        moves[movesIdx++] = m(from, to, 0, 0);
        delta = unset(delta, to);
      }
      pieces = unset(pieces, from);
    }

    return movesIdx;
  }

  public static long genTargetsBishops(int from, long allPieces, long antiFriendlies) {
    long attacks = 0L;
    int magicIndex = MagicBitBoard.magicIndexBishop(allPieces, MoveGen.bishopDeltas[from], from);
    long delta = magicBishopMoves[from][magicIndex] & antiFriendlies;
    int to;
    while ((to = next(delta)) >= 0) {
      attacks |= Bitops.setBit(to);
      delta = unset(delta, to);
    }

    return attacks;
  }

  private static int genTargets(
      long pieces, long[] deltas, long friendlies, int[] moves, int movesIdx) {
    int n;
    while ((n = next(pieces)) >= 0) {
      int na;
      long delta = deltas[n] & friendlies;
      while ((na = next(delta)) >= 0) {
        moves[movesIdx++] = m(n, na, 0, 0);
        delta = unset(delta, na);
      }
      pieces = unset(pieces, n);
    }

    return movesIdx;
  }

  private static int genTargets(long pieces, int delta, int[] moves, int movesIdx) {
    int n;
    while ((n = next(pieces)) >= 0) {
      moves[movesIdx++] = m(n, n + delta, 0, 0);
      pieces = unset(pieces, n);
    }

    return movesIdx;
  }

  private static int genPawnPromotions(long pieces, int delta, int[] moves, int movesIdx) {
    int n;
    while ((n = next(pieces)) >= 0) {
      moves[movesIdx++] = m(n, n + delta, 1, 0);
      moves[movesIdx++] = m(n, n + delta, 1, 1);
      moves[movesIdx++] = m(n, n + delta, 1, 2);
      moves[movesIdx++] = m(n, n + delta, 1, 3);
      pieces = unset(pieces, n);
    }

    return movesIdx;
  }

  private static int genEp(long pieces, int target, int[] moves, int movesIdx) {
    int n;
    while ((n = next(pieces)) >= 0) {
      moves[movesIdx++] = m(n, target, 2, 0);
      pieces = unset(pieces, n);
    }

    return movesIdx;
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
