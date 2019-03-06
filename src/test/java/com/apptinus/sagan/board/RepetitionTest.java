package com.apptinus.sagan.board;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.apptinus.sagan.util.BoardUtil;
import org.junit.Test;

public class RepetitionTest {

  @Test
  public void repetitionTest() {
    Board board = BoardUtil.createStartPosBoard();

    assertFalse(board.isDraw());
    board.make(Move.m(1,16,0,0));
    assertFalse(board.isDraw());
    board.make(Move.m(57,40,0,0));
    assertFalse(board.isDraw());
    board.make(Move.m(16,1,0,0));
    assertFalse(board.isDraw());
    board.make(Move.m(40,57,0,0));
    assertFalse(board.isDraw());
    board.make(Move.m(1,16,0,0));
    assertFalse(board.isDraw());
    board.make(Move.m(57,40,0,0));
    assertFalse(board.isDraw());
    board.make(Move.m(16,1,0,0));
    assertFalse(board.isDraw());
    board.make(Move.m(40,57,0,0));
    assertTrue(board.isDraw());
  }
}
