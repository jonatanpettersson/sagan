package com.apptinus.sagan.board;

import com.apptinus.sagan.util.Bitops;

public class Evaluation {
  private static final int[] PIECE_VALUE = {
    0,900,500,300,300,100,0,900,500,300,300,100
  };

  public static int evaluate(Board board) {
    int eval = 0;
    for (int i = 0; i <= 11; i++) {
      eval += PIECE_VALUE[i] * Bitops.population(board.pieces[i]);
    }

    return eval;
  }
}
