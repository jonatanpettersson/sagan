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
import static com.apptinus.sagan.board.Move.WHITE;

import com.apptinus.sagan.util.Bitops;

public class Evaluation {
  public static final int[] PIECE_VALUE = {
    0, 900, 500, 300, 300, 100, 0, -900, -500, -300, -300, -100
  };

  public static final int[] PIECE_ABS_VALUE = {
    0, 900, 500, 300, 300, 100, 0, 900, 500, 300, 300, 100
  };

  public static int evaluate(Board board) {
    if (drawByMaterial(board)) return 0;

    int eval = 0;
    for (int i = 0; i <= 11; i++) {
      eval += PIECE_VALUE[i] * Bitops.population(board.pieces[i]);
    }

    return eval;
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

  public static final boolean drawByMaterial(Board board) {
    int wBishops = Bitops.population(board.pieces[WB]);
    int wKnights = Bitops.population(board.pieces[WN]);
    int bBishops = Bitops.population(board.pieces[BB]);
    int bKnights = Bitops.population(board.pieces[BN]);
    if (Bitops.population(board.pieces[WP]) != 0
        || Bitops.population(board.pieces[WR]) != 0
        || Bitops.population(board.pieces[WQ]) != 0
        || wBishops > 1
        || wKnights > 2
        || Bitops.population(board.pieces[BP]) != 0
        || Bitops.population(board.pieces[BR]) != 0
        || Bitops.population(board.pieces[BQ]) != 0
        || bBishops > 1
        || bKnights > 2) {
      return false;
    }
    if ((wBishops > 0 && wKnights > 0) || (bBishops > 0 && bKnights > 0)) {
      return false;
    }

    return true;
  }

  public static String getEvalInfo(Board board) {
    String evalString = "";
    evalString += evaluate(board);
    return evalString;
  }
}
