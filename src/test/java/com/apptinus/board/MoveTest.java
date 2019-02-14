package com.apptinus.board;

import static com.apptinus.board.Move.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MoveTest {

  @Test
  public void testMoveRa1A8() {
    int expectedMove = 0b0000111000000000; // Ra1-a8
    int actualMove = m(0, 56, 0, 0);

    assertEquals(expectedMove, actualMove);
    assertEquals(0, from(actualMove));
    assertEquals(56, to(actualMove));
    assertEquals(0, special(actualMove));
    assertEquals(0, promotion(actualMove));
  }

  @Test
  public void testMove00() {
    int expectedMove = 0b0011111110111100; // black 0-0
    int actualMove = m(60, 62, 3, 0);

    assertEquals(expectedMove, actualMove);
    assertEquals(60, from(actualMove));
    assertEquals(62, to(actualMove));
    assertEquals(3, special(actualMove));
    assertEquals(0, promotion(actualMove));
  }

  @Test
  public void testMove000() {
    int expectedMove = 0b0011000000000100; // white 0-0-0
    int actualMove = m(4, 0, 3, 0);

    assertEquals(expectedMove, actualMove);
    assertEquals(4, from(actualMove));
    assertEquals(0, to(actualMove));
    assertEquals(3, special(actualMove));
    assertEquals(0, promotion(actualMove));
  }

  @Test
  public void testMoveEp() {
    int expectedMove = 0b0010101001100000; // a5xb6e.p.
    int actualMove = m(32, 41, 2, 0);

    assertEquals(expectedMove, actualMove);
    assertEquals(32, from(actualMove));
    assertEquals(41, to(actualMove));
    assertEquals(2, special(actualMove));
    assertEquals(0, promotion(actualMove));
  }

  @Test
  public void testMovePromotion() {
    int expectedMove = 0b1101111000110000; // a7-a8Q
    int actualMove = m(48, 56, 1, 3);

    assertEquals(expectedMove, actualMove);
    assertEquals(48, from(actualMove));
    assertEquals(56, to(actualMove));
    assertEquals(1, special(actualMove));
    assertEquals(3, promotion(actualMove));
  }
}
