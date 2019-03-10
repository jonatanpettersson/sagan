package com.apptinus.sagan.board;

import static com.apptinus.sagan.board.Board.BB;
import static com.apptinus.sagan.board.Board.BN;
import static com.apptinus.sagan.board.Board.BP;
import static com.apptinus.sagan.board.Board.BQ;
import static com.apptinus.sagan.board.Board.BR;
import static com.apptinus.sagan.board.Board.WB;
import static com.apptinus.sagan.board.Board.WN;
import static com.apptinus.sagan.board.Board.WP;
import static com.apptinus.sagan.board.Board.WQ;
import static com.apptinus.sagan.board.Board.WR;
import static com.apptinus.sagan.board.Move.BLACK;
import static com.apptinus.sagan.board.Move.SPECIAL_EP;
import static com.apptinus.sagan.board.Move.WHITE;
import static com.apptinus.sagan.util.Bitops.unset;

import com.apptinus.sagan.util.Bitops;

public class Evaluation {
  public static final int[] PIECE_VALUE = {
    0, 900, 500, 300, 300, 100, 0, -900, -500, -300, -300, -100
  };

  public static final int[] PIECE_ABS_VALUE = {
    0, 900, 500, 300, 300, 100, 0, 900, 500, 300, 300, 100
  };

  public static int evaluate(Board board) {
    int eval = 0;
    for (int i = 0; i <= 11; i++) {
      eval += PIECE_VALUE[i] * Bitops.population(board.pieces[i]);
    }

    return board.toMove == WHITE ? eval : -1 * eval;
  }

  public static final boolean sideCantWin(Board board, int side) {
    if (side == WHITE) {
      int bishops = Bitops.population(board.pieces[WB]);
      int knights = Bitops.population(board.pieces[WN]);
      if (Bitops.population(board.pieces[WP]) != 0
          || Bitops.population(board.pieces[WR]) != 0
          || Bitops.population(board.pieces[WQ]) != 0
          || bishops > 1
          || knights > 2) {
        return false;
      }
      if ((bishops > 0 && knights > 0)) {
        return false;
      }

      return true;
    } else {
      int bishops = Bitops.population(board.pieces[BB]);
      int knights = Bitops.population(board.pieces[BN]);
      if (Bitops.population(board.pieces[BP]) != 0
          || Bitops.population(board.pieces[BR]) != 0
          || Bitops.population(board.pieces[BQ]) != 0
          || bishops > 1
          || knights > 2) {
        return false;
      }
      if ((bishops > 0 && knights > 0)) {
        return false;
      }

      return true;
    }
  }

  public static boolean isPawnEnding(Board board) {
    return Bitops.population(board.pieces[WN]) == 0
        && Bitops.population(board.pieces[WB]) == 0
        && Bitops.population(board.pieces[WR]) == 0
        && Bitops.population(board.pieces[WQ]) == 0
        && Bitops.population(board.pieces[BN]) == 0
        && Bitops.population(board.pieces[BB]) == 0
        && Bitops.population(board.pieces[BR]) == 0
        && Bitops.population(board.pieces[BQ]) == 0;
  }

  public static String getEvalInfo(Board board) {
    String evalString = "";
    evalString += evaluate(board);
    return evalString;
  }

  public static int getMvvLva(final Board board, final int move) {
    if (Move.special(move) == SPECIAL_EP) {
      // If the move is en passant we know the captured pieces and capturer are both pawns, so
      // just take the pawn value on both (doesn't matter which color since the value is the same)
      return 256 * Evaluation.PIECE_ABS_VALUE[WP] - Evaluation.PIECE_ABS_VALUE[WP];
    } else {
      return 256 * Evaluation.PIECE_ABS_VALUE[board.board[Move.to(move)]]
          - Evaluation.PIECE_ABS_VALUE[board.board[Move.from(move)]];
    }
  }

  public static int getSee(final Board board, final int move) {
    int from = Move.from(move);
    int to = Move.to(move);

    // Get a bitboard with all pieces that can attack the to square (including xray)
    long potentialBlockers = board.allPieces;
    long attackers = board.attackers(to, potentialBlockers, 0xffffffffffffffffL);
    long usedAttackers = 0L;

    int[] score = new int[32];

    if (Move.special(move) == SPECIAL_EP) {
      score[0] = PIECE_ABS_VALUE[WP];
    } else {
      score[0] = PIECE_ABS_VALUE[board.board[to]];
    }

    // "Make" the first capture
//    attackers ^= Bitops.setBit(from);
//    if (attackers == 0) {
//      return score[0];
//    }
//    usedAttackers |= Bitops.setBit(from);
//    int toMove = board.toMove == WHITE ? BLACK : WHITE;
//    score[1] = PIECE_ABS_VALUE[board.board[from]] - score[0];

    int toMove = board.toMove;

    int depth = 0;
    do {
      depth++;
      attackers ^= Bitops.setBit(from);
      usedAttackers |= Bitops.setBit(from);
      toMove = toMove == WHITE ? BLACK : WHITE;
      score[depth] = PIECE_ABS_VALUE[board.board[from]] - score[depth - 1];

      // Add potential xray piece behind attacker
      if ((Bitops.setBit(from) & (MoveGen.rankMask(to) ^ MoveGen.fileMask(to)))
          != 0L) {
        attackers |=
            board.attackers(
                to,
                board.allPieces & ~usedAttackers,
                (MoveGen.rankMask(to) ^ MoveGen.fileMask(to))
                    & ~usedAttackers
                    & (board.pieces[WR] | board.pieces[BR] | board.pieces[BQ] | board.pieces[WQ]));
      } else if ((Bitops.setBit(from)
              & (MoveGen.diagonalMask(to) ^ MoveGen.antiDiagMask(to)))
          != 0L) {
        attackers |=
            board.attackers(
                to,
                board.allPieces & ~usedAttackers,
                (MoveGen.diagonalMask(to) ^ MoveGen.antiDiagMask(to))
                    & ~usedAttackers
                    & (board.pieces[WB] | board.pieces[BB] | board.pieces[WQ] | board.pieces[BQ]));
      }

      long toMoveAttackers = attackers & (toMove == WHITE ? board.wPieces : board.bPieces);

//      if (toMoveAttackers == 0) break;


      int nextAttackerValue = 10000;
      int thisAttackerSquare;
      from = -1;
      while ((thisAttackerSquare = Bitops.next(toMoveAttackers)) != -1) {
        toMoveAttackers = unset(toMoveAttackers, thisAttackerSquare);
        if (nextAttackerValue > PIECE_ABS_VALUE[board.board[thisAttackerSquare]]) {
          nextAttackerValue = PIECE_ABS_VALUE[board.board[thisAttackerSquare]];
          from = thisAttackerSquare;
        }
      }
    } while(from >= 0);

//    depth--;
    while (depth > 1) {
//      gain[d-1]= -max (-gain[d-1], gain[d])
      depth--;
      score[depth - 1] = -Math.max(-score[depth-1], score[depth]);
//      if (score[depth - 1] > -score[depth]) {
//        score[depth - 1] = -score[depth];
//      }
    }

    return score[0];
  }
}
