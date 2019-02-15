package com.apptinus.sagan.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class BoardTest {

  // Bitboard reference
  // hgfedcbahgfedcbahgfedcbahgfedcbahgfedcbahgfedcbahgfedcbahgfedcba
  // 8888888877777777666666665555555544444444333333332222222211111111

  @Test
  public void testMakeMove() {
    String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

    Board board = new Board();
    board.setFen(fen);

    board.make(Move.m(Move.E2, Move.E4, 0, 0));
    assertEquals("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1", board.getFen());
    board.make(Move.m(Move.E7, Move.E5, 0, 0));
    assertEquals("rnbqkbnr/pppp1ppp/8/4p3/4P3/8/PPPP1PPP/RNBQKBNR w KQkq e6 0 2", board.getFen());
    board.make(Move.m(Move.G1, Move.F3, 0, 0));
    assertEquals("rnbqkbnr/pppp1ppp/8/4p3/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2", board.getFen());

    board.setFen("rnbqk1nr/ppp1bppp/3p4/4p3/4P3/5N2/PPPPBPPP/RNBQK2R w KQkq -");
    board.make(Move.m(Move.E1, Move.G1, Move.SPECIAL_CASTLE, 0));
    assertEquals("rnbqk1nr/ppp1bppp/3p4/4p3/4P3/5N2/PPPPBPPP/RNBQ1RK1 b kq - 1 1", board.getFen());
    board.setFen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R3KBNR w KQkq -");
    board.make(Move.m(Move.E1, Move.C1, Move.SPECIAL_CASTLE, 0));
    assertEquals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/2KR1BNR b kq - 1 1", board.getFen());
    board.setFen("r3kbnr/pppppppp/8/8/8/8/PPPPPPPP/2KR1BNR b kq -");
    board.make(Move.m(Move.E8, Move.C8, Move.SPECIAL_CASTLE, 0));
    assertEquals("2kr1bnr/pppppppp/8/8/8/8/PPPPPPPP/2KR1BNR w - - 1 2", board.getFen());
    board.setFen("r3k2r/pppppppp/8/8/8/8/PPPPPPPP/2KR1BNR b kq -");
    board.make(Move.m(Move.E8, Move.G8, Move.SPECIAL_CASTLE, 0));
    assertEquals("r4rk1/pppppppp/8/8/8/8/PPPPPPPP/2KR1BNR w - - 1 2", board.getFen());

    board.setFen("4k3/8/8/8/Pp6/8/8/4K3 b a3 -");
    board.make(Move.m(Move.B4, Move.A3, Move.SPECIAL_EP, 0));
    assertEquals("4k3/8/8/8/8/p7/8/4K3 w - - 0 2", board.getFen());
    board.setFen("4k3/8/8/6Pp/8/8/8/4K3 w - h6");
    board.make(Move.m(Move.G5, Move.H6, Move.SPECIAL_EP, 0));
    assertEquals("4k3/8/7P/8/8/8/8/4K3 b - - 0 1", board.getFen());

    board.setFen("4k3/7P/8/8/8/8/8/4K3 w - -");
    board.make(Move.m(Move.H7, Move.H8, Move.SPECIAL_PROMO, Move.PROMO_N));
    assertEquals("4k2N/8/8/8/8/8/8/4K3 b - - 0 1", board.getFen());
    board.setFen("4k3/7P/8/8/8/8/8/4K3 w - -");
    board.make(Move.m(Move.H7, Move.H8, Move.SPECIAL_PROMO, Move.PROMO_B));
    assertEquals("4k2B/8/8/8/8/8/8/4K3 b - - 0 1", board.getFen());
    board.setFen("4k3/7P/8/8/8/8/8/4K3 w - -");
    board.make(Move.m(Move.H7, Move.H8, Move.SPECIAL_PROMO, Move.PROMO_R));
    assertEquals("4k2R/8/8/8/8/8/8/4K3 b - - 0 1", board.getFen());
    board.setFen("4k3/7P/8/8/8/8/8/4K3 w - -");
    board.make(Move.m(Move.H7, Move.H8, Move.SPECIAL_PROMO, Move.PROMO_Q));
    assertEquals("4k2Q/8/8/8/8/8/8/4K3 b - - 0 1", board.getFen());

    board.setFen("4k3/8/8/8/8/8/p7/4K3 b - -");
    board.make(Move.m(Move.A2, Move.A1, Move.SPECIAL_PROMO, Move.PROMO_N));
    assertEquals("4k3/8/8/8/8/8/8/n3K3 w - - 0 2", board.getFen());
    board.setFen("4k3/8/8/8/8/8/p7/4K3 b - -");
    board.make(Move.m(Move.A2, Move.A1, Move.SPECIAL_PROMO, Move.PROMO_B));
    assertEquals("4k3/8/8/8/8/8/8/b3K3 w - - 0 2", board.getFen());
    board.setFen("4k3/8/8/8/8/8/p7/4K3 b - -");
    board.make(Move.m(Move.A2, Move.A1, Move.SPECIAL_PROMO, Move.PROMO_R));
    assertEquals("4k3/8/8/8/8/8/8/r3K3 w - - 0 2", board.getFen());
    board.setFen("4k3/8/8/8/8/8/p7/4K3 b - -");
    board.make(Move.m(Move.A2, Move.A1, Move.SPECIAL_PROMO, Move.PROMO_Q));
    assertEquals("4k3/8/8/8/8/8/8/q3K3 w - - 0 2", board.getFen());

    board.setFen("r3k3/1P6/8/8/8/8/8/4K3 w - -");
    board.make(Move.m(Move.B7, Move.A8, Move.SPECIAL_PROMO, Move.PROMO_N));
    assertEquals("N3k3/8/8/8/8/8/8/4K3 b - - 0 1", board.getFen());
    board.setFen("2r1k3/1P6/8/8/8/8/8/4K3 w - -");
    board.make(Move.m(Move.B7, Move.C8, Move.SPECIAL_PROMO, Move.PROMO_B));
    assertEquals("2B1k3/8/8/8/8/8/8/4K3 b - - 0 1", board.getFen());

    board.setFen("4k3/8/8/8/8/8/6p1/4K2R b - -");
    board.make(Move.m(Move.G2, Move.H1, Move.SPECIAL_PROMO, Move.PROMO_R));
    assertEquals("4k3/8/8/8/8/8/8/4K2r w - - 0 2", board.getFen());
    board.setFen("4k3/8/8/8/8/8/6p1/4KR2 b - -");
    board.make(Move.m(Move.G2, Move.F1, Move.SPECIAL_PROMO, Move.PROMO_Q));
    assertEquals("4k3/8/8/8/8/8/8/4Kq2 w - - 0 2", board.getFen());
  }

  @Test
  public void testFenInAndOut1() {
    String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

    Board board = new Board();
    board.setFen(fen);

    assertEquals(
        0b0000000000000000000000000000000000000000000000001111111100000000L,
        board.pieces[Board.WP]);
    assertEquals(
        0b0000000000000000000000000000000000000000000000000000000001000010L,
        board.pieces[Board.WN]);
    assertEquals(
        0b0000000000000000000000000000000000000000000000000000000000100100L,
        board.pieces[Board.WB]);
    assertEquals(
        0b0000000000000000000000000000000000000000000000000000000010000001L,
        board.pieces[Board.WR]);
    assertEquals(
        0b0000000000000000000000000000000000000000000000000000000000001000L,
        board.pieces[Board.WQ]);
    assertEquals(
        0b0000000000000000000000000000000000000000000000000000000000010000L,
        board.pieces[Board.WK]);
    assertEquals(
        0b0000000011111111000000000000000000000000000000000000000000000000L,
        board.pieces[Board.BP]);
    assertEquals(
        0b0100001000000000000000000000000000000000000000000000000000000000L,
        board.pieces[Board.BN]);
    assertEquals(
        0b0010010000000000000000000000000000000000000000000000000000000000L,
        board.pieces[Board.BB]);
    assertEquals(
        0b1000000100000000000000000000000000000000000000000000000000000000L,
        board.pieces[Board.BR]);
    assertEquals(
        0b0000100000000000000000000000000000000000000000000000000000000000L,
        board.pieces[Board.BQ]);
    assertEquals(
        0b0001000000000000000000000000000000000000000000000000000000000000L,
        board.pieces[Board.BK]);

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

    assertTrue(
        Arrays.equals(
            new int[] {
              2, 4, 3, 1, 0, 3, 4, 2, 5, 5, 5, 5, 5, 5, 5, 5, 12, 12, 12, 12, 12, 12, 12, 12, 12,
              12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12,
              12, 12, 11, 11, 11, 11, 11, 11, 11, 11, 8, 10, 9, 7, 6, 9, 10, 8
            },
            board.board));

    assertEquals(fen, board.getFen());
    assertEquals(
        "r n b q k b n r \n"
            + "p p p p p p p p \n"
            + ". . . . . . . . \n"
            + ". . . . . . . . \n"
            + ". . . . . . . . \n"
            + ". . . . . . . . \n"
            + "P P P P P P P P \n"
            + "R N B Q K B N R \n",
        board.prettyPrintBoard());
  }

  @Test
  public void testFenInAndOut2() {
    String fen = "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1";

    Board board = new Board();
    board.setFen(fen);

    assertEquals(
        0b0000000000000000000000000000100000010000000000001110011100000000L,
        board.pieces[Board.WP]);
    assertEquals(
        0b0000000000000000000000000001000000000000000001000000000000000000L,
        board.pieces[Board.WN]);
    assertEquals(
        0b0000000000000000000000000000000000000000000000000001100000000000L,
        board.pieces[Board.WB]);
    assertEquals(
        0b0000000000000000000000000000000000000000000000000000000010000001L,
        board.pieces[Board.WR]);
    assertEquals(
        0b0000000000000000000000000000000000000000001000000000000000000000L,
        board.pieces[Board.WQ]);
    assertEquals(
        0b0000000000000000000000000000000000000000000000000000000000010000L,
        board.pieces[Board.WK]);
    assertEquals(
        0b0000000000101101010100000000000000000010100000000000000000000000L,
        board.pieces[Board.BP]);
    assertEquals(
        0b0000000000000000001000100000000000000000000000000000000000000000L,
        board.pieces[Board.BN]);
    assertEquals(
        0b0000000001000000000000010000000000000000000000000000000000000000L,
        board.pieces[Board.BB]);
    assertEquals(
        0b1000000100000000000000000000000000000000000000000000000000000000L,
        board.pieces[Board.BR]);
    assertEquals(
        0b0000000000010000000000000000000000000000000000000000000000000000L,
        board.pieces[Board.BQ]);
    assertEquals(
        0b0001000000000000000000000000000000000000000000000000000000000000L,
        board.pieces[Board.BK]);

    assertEquals(
        0b0000000000000000000000000001100000010000001001001111111110010001L, board.wPieces);
    assertEquals(
        0b1001000101111101011100110000000000000010100000000000000000000000L, board.bPieces);
    assertEquals(
        0b1001000101111101011100110001100000010010101001001111111110010001L, board.allPieces);

    assertEquals(0b0, board.toMove);
    assertEquals(0b11, board.wCastle);
    assertEquals(0b11, board.bCastle);
    assertEquals(0, board.fPly);
    assertEquals(0, board.ply);
    assertEquals(-1, board.ep);

    assertTrue(
        Arrays.equals(
            new int[] {
              2, 12, 12, 12, 0, 12, 12, 2, 5, 5, 5, 3, 3, 5, 5, 5, 12, 12, 4, 12, 12, 1, 12, 11, 12,
              11, 12, 12, 5, 12, 12, 12, 12, 12, 12, 5, 4, 12, 12, 12, 9, 10, 12, 12, 11, 10, 11,
              12, 11, 12, 11, 11, 7, 11, 9, 12, 8, 12, 12, 12, 6, 12, 12, 8
            },
            board.board));

    assertEquals(fen, board.getFen());
    assertEquals(
        "r . . . k . . r \n"
            + "p . p p q p b . \n"
            + "b n . . p n p . \n"
            + ". . . P N . . . \n"
            + ". p . . P . . . \n"
            + ". . N . . Q . p \n"
            + "P P P B B P P P \n"
            + "R . . . K . . R \n",
        board.prettyPrintBoard());
  }

  @Test
  public void testFenInAndOut3() {
    Board board = new Board();
    board.setFen("rnbq1k1r/pp1Pbppp/2p5/8/2B5/8/PPP1NnPP/RNBQK2R w KQ - 1 8");
    assertEquals("rnbq1k1r/pp1Pbppp/2p5/8/2B5/8/PPP1NnPP/RNBQK2R w KQ - 1 8", board.getFen());

    board.setFen("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1");
    assertEquals("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1", board.getFen());

    board.setFen("rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2");
    assertEquals("rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2", board.getFen());

    board.setFen("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2");
    assertEquals("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2", board.getFen());

    board.setFen("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB2 b KQkq - 1 2");
    assertEquals("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB2 b KQkq - 1 2", board.getFen());

    board.setFen("1nbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB2 b KQkq - 1 2");
    assertEquals("1nbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB2 b KQkq - 1 2", board.getFen());
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
