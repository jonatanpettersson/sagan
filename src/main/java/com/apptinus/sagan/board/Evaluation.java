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

}
