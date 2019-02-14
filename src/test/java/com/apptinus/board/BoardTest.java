package com.apptinus.board;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class BoardTest {

  @Test
  public void testFenInAndOut() {
    String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

    Board board = new Board();
    board.setFen(fen);

    assertEquals(0b0000000000000000000000000000000000000000000000001111111100000000L, board.wP);
    assertEquals(0b0000000000000000000000000000000000000000000000000000000001000010L, board.wN);
    assertEquals(0b0000000000000000000000000000000000000000000000000000000000100100L, board.wB);
    assertEquals(0b0000000000000000000000000000000000000000000000000000000010000001L, board.wR);
    assertEquals(0b0000000000000000000000000000000000000000000000000000000000001000L, board.wQ);
    assertEquals(0b0000000000000000000000000000000000000000000000000000000000010000L, board.wK);
    assertEquals(0b0000000011111111000000000000000000000000000000000000000000000000L, board.bP);
    assertEquals(0b0100001000000000000000000000000000000000000000000000000000000000L, board.bN);
    assertEquals(0b0010010000000000000000000000000000000000000000000000000000000000L, board.bB);
    assertEquals(0b1000000100000000000000000000000000000000000000000000000000000000L, board.bR);
    assertEquals(0b0000100000000000000000000000000000000000000000000000000000000000L, board.bQ);
    assertEquals(0b0001000000000000000000000000000000000000000000000000000000000000L, board.bK);

    assertEquals(
        0b0000000000000000000000000000000000000000000000001111111111111111L, board.wPieces);
    assertEquals(
        0b1111111111111111000000000000000000000000000000000000000000000000L, board.bPieces);
    assertEquals(
        0b1111111111111111000000000000000000000000000000001111111111111111L, board.allPieces);

    assertEquals(0b0, board.toMove);
    assertEquals(0b11, board.wCastle);
    assertEquals(0b11, board.bCastle);
    assertEquals(0, board.fPly);
    assertEquals(0, board.ply);
    assertEquals(-1, board.ep);

    assertEquals(fen, board.getFen());
  }

  @Test
  public void testFileAndRankToSquare() {
    assertEquals(0, Board.fileAndRankToSquare(0, 0));
    assertEquals(56, Board.fileAndRankToSquare(0, 7));
    assertEquals(7, Board.fileAndRankToSquare(7, 0));
    assertEquals(63, Board.fileAndRankToSquare(7, 7));
    assertEquals(34, Board.fileAndRankToSquare(2, 4));
  }

  @Test
  public void testNotationToSquare() {
    Map<Integer, String> squareToNotation = new HashMap<>();
    squareToNotation.put(0, "a1");
    squareToNotation.put(1, "b1");
    squareToNotation.put(2, "c1");
    squareToNotation.put(3, "d1");
    squareToNotation.put(4, "e1");
    squareToNotation.put(5, "f1");
    squareToNotation.put(6, "g1");
    squareToNotation.put(7, "h1");
    squareToNotation.put(8, "a2");
    squareToNotation.put(9, "b2");
    squareToNotation.put(10, "c2");
    squareToNotation.put(11, "d2");
    squareToNotation.put(12, "e2");
    squareToNotation.put(13, "f2");
    squareToNotation.put(14, "g2");
    squareToNotation.put(15, "h2");
    squareToNotation.put(16, "a3");
    squareToNotation.put(17, "b3");
    squareToNotation.put(18, "c3");
    squareToNotation.put(19, "d3");
    squareToNotation.put(20, "e3");
    squareToNotation.put(21, "f3");
    squareToNotation.put(22, "g3");
    squareToNotation.put(23, "h3");
    squareToNotation.put(24, "a4");
    squareToNotation.put(25, "b4");
    squareToNotation.put(26, "c4");
    squareToNotation.put(27, "d4");
    squareToNotation.put(28, "e4");
    squareToNotation.put(29, "f4");
    squareToNotation.put(30, "g4");
    squareToNotation.put(31, "h4");
    squareToNotation.put(32, "a5");
    squareToNotation.put(33, "b5");
    squareToNotation.put(34, "c5");
    squareToNotation.put(35, "d5");
    squareToNotation.put(36, "e5");
    squareToNotation.put(37, "f5");
    squareToNotation.put(38, "g5");
    squareToNotation.put(39, "h5");
    squareToNotation.put(40, "a6");
    squareToNotation.put(41, "b6");
    squareToNotation.put(42, "c6");
    squareToNotation.put(43, "d6");
    squareToNotation.put(44, "e6");
    squareToNotation.put(45, "f6");
    squareToNotation.put(46, "g6");
    squareToNotation.put(47, "h6");
    squareToNotation.put(48, "a7");
    squareToNotation.put(49, "b7");
    squareToNotation.put(50, "c7");
    squareToNotation.put(51, "d7");
    squareToNotation.put(52, "e7");
    squareToNotation.put(53, "f7");
    squareToNotation.put(54, "g7");
    squareToNotation.put(55, "h7");
    squareToNotation.put(56, "a8");
    squareToNotation.put(57, "b8");
    squareToNotation.put(58, "c8");
    squareToNotation.put(59, "d8");
    squareToNotation.put(60, "e8");
    squareToNotation.put(61, "f8");
    squareToNotation.put(62, "g8");
    squareToNotation.put(63, "h8");

    for (Map.Entry<Integer, String> entry : squareToNotation.entrySet()) {
      assertEquals(entry.getKey().intValue(), Board.notationToSquare(entry.getValue()));
      assertEquals(entry.getValue(), Board.squareToNotation(entry.getKey()));
    }
  }
}
