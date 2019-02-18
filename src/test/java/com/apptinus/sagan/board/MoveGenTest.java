package com.apptinus.sagan.board;

import org.junit.Test;

public class MoveGenTest {

  @Test
  public void testGen() {
    Board board = new Board();
    //    board.setFen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
    //    board.setFen("1N2k3/8/8/8/8/8/8/4K3 w - -");
//    board.setFen("k7/8/8/5Pp1/8/8/8/K7 w - g6");
//    board.setFen("k7/8/8/6p1/P7/8/8/R3K2R w KQ -");
    board.setFen("7k/8/8/8/8/8/8/R1K5 w - -");
//    board.setFen("7k/8/8/8/6r1/8/8/3B3K w - -");

    MoveGen.genMoves(board);

    for (int moveIdx = 0; moveIdx < MoveGen.allMovesIdx; moveIdx++) {
      int move = MoveGen.allMoves[moveIdx];
      System.out.println(
          Board.squareToNotation(Move.from(move)) + "" + Board.squareToNotation(Move.to(move)));
    }

    System.out.println("tjo");
  }
}
