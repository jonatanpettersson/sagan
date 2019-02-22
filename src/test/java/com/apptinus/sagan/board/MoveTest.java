package com.apptinus.sagan.board;

import static com.apptinus.sagan.board.Move.bCastle;
import static com.apptinus.sagan.board.Move.capturedPiece;
import static com.apptinus.sagan.board.Move.epSquare;
import static com.apptinus.sagan.board.Move.fPly;
import static com.apptinus.sagan.board.Move.from;
import static com.apptinus.sagan.board.Move.h;
import static com.apptinus.sagan.board.Move.hasEp;
import static com.apptinus.sagan.board.Move.m;
import static com.apptinus.sagan.board.Move.promotion;
import static com.apptinus.sagan.board.Move.special;
import static com.apptinus.sagan.board.Move.to;
import static com.apptinus.sagan.board.Move.wCastle;
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

  @Test
  public void testHistoryWithEp() {
    int expectedHistory = 0b110000001001000010110;
    int actualHistory = h(2, 1, 1, 16, 2, 12);

    assertEquals(expectedHistory, actualHistory);
    assertEquals(2, wCastle(actualHistory));
    assertEquals(1, bCastle(actualHistory));
    assertEquals(1, hasEp(actualHistory));
    assertEquals(16, epSquare(actualHistory));
    assertEquals(2, fPly(actualHistory));
    assertEquals(12, capturedPiece(actualHistory));
  }

  @Test
  public void testHistoryWithoutEp() {
    int expectedHistory = 0b000000001001000000000;
    int actualHistory = h(0, 0, 0, 16, 2, 0);

    assertEquals(expectedHistory, actualHistory);
    assertEquals(0, wCastle(actualHistory));
    assertEquals(0, bCastle(actualHistory));
    assertEquals(0, hasEp(actualHistory));
    assertEquals(-1, epSquare(actualHistory));
    assertEquals(2, fPly(actualHistory));
    assertEquals(0, capturedPiece(actualHistory));
  }
}
