package com.apptinus.sagan.board;

import static org.junit.Assert.assertEquals;

import com.apptinus.sagan.util.BoardUtil;
import org.junit.Test;

public class BoardUtilTest {

  @Test
  public void testSquareToRank() {
    assertEquals(0, BoardUtil.squareToRank(0));
    assertEquals(0, BoardUtil.squareToRank(7));
    assertEquals(4, BoardUtil.squareToRank(35));
    assertEquals(7, BoardUtil.squareToRank(56));
    assertEquals(7, BoardUtil.squareToRank(63));
  }

  @Test
  public void testSquareToFile() {
    assertEquals(0, BoardUtil.squareToFile(0));
    assertEquals(7, BoardUtil.squareToFile(7));
    assertEquals(3, BoardUtil.squareToFile(35));
    assertEquals(0, BoardUtil.squareToFile(56));
    assertEquals(7, BoardUtil.squareToFile(63));
  }
}
