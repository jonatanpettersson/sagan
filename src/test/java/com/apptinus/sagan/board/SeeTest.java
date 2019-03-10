package com.apptinus.sagan.board;

import static org.junit.Assert.assertEquals;

import com.apptinus.sagan.util.BoardUtil;
import org.junit.Test;

public class SeeTest {

  @Test
  public void testSee() {
    MoveGen.init();
    Board board;

    board = BoardUtil.createBoard("1k1r3q/1ppn3p/p4b2/4p3/8/P2N2P1/1PP1R1BP/2K1Q3 w - -");
    assertEquals(-200, Evaluation.getSee(board, Move.m(19, 36, Move.SPECIAL_NONE, 0)));

    board = BoardUtil.createBoard("8/8/3k4/8/3P2r1/8/8/B1K5 b - -");
    assertEquals(-400, Evaluation.getSee(board, Move.m(30, 27, Move.SPECIAL_NONE, 0)));

    board = BoardUtil.createBoard("8/3k4/8/3P2r1/8/8/B7/2K5 b - -");
    assertEquals(-400, Evaluation.getSee(board, Move.m(38, 35, Move.SPECIAL_NONE, 0)));

    board = BoardUtil.createBoard("2r5/1P4pk/p2p1b1p/5b1n/BB3p2/2R2p2/P1P2P2/4RK2 w - -");
    assertEquals(300, Evaluation.getSee(board, Move.m(18, 58, Move.SPECIAL_NONE, 0)));

    board = BoardUtil.createBoard("1k1r4/1ppn3p/p4b2/4p3/8/P2N2P1/1PP1R1BP/2K5 w - -");
    assertEquals(-200, Evaluation.getSee(board, Move.m(19, 36, Move.SPECIAL_NONE, 0)));

    board = BoardUtil.createBoard("k6r/7q/7r/7p/8/7R/7Q/K6R w - -");
    assertEquals(-400, Evaluation.getSee(board, Move.m(23, 39, Move.SPECIAL_NONE, 0)));

    board = BoardUtil.createBoard("8/pp6/2pkp3/4bp2/2R3b1/2P5/PP4B1/1K6 w - -");
    assertEquals(100 - 300, Evaluation.getSee(board, Move.m(14, 42, Move.SPECIAL_NONE, 0)));

    board = BoardUtil.createBoard("4R3/2r3p1/5bk1/1p1r3p/p2PR1P1/P1BK1P2/1P6/8 b - -");
    assertEquals(0, Evaluation.getSee(board, Move.m(39, 30, Move.SPECIAL_NONE, 0)));

    board = BoardUtil.createBoard("1k1r4/1pp4p/p7/4p3/8/P5P1/1PP4P/2K1R3 w - -");
    assertEquals(100, Evaluation.getSee(board, Move.m(4, 36, Move.SPECIAL_NONE, 0)));

    board = BoardUtil.createBoard("6k1/8/1R6/2N5/8/1p2r3/1K6/3b4 w - -");
    assertEquals(100, Evaluation.getSee(board, Move.m(34, 17, Move.SPECIAL_NONE, 0)));

    board = BoardUtil.createBoard("4R3/2r3p1/5bk1/1p1r1p1p/p2PR1P1/P1BK1P2/1P6/8 b - -");
    assertEquals(0, Evaluation.getSee(board, Move.m(39, 30, Move.SPECIAL_NONE, 0)));
    board = BoardUtil.createBoard("4r1k1/5pp1/nbp4p/1p2p2q/1P2P1b1/1BP2N1P/1B2QPPK/3R4 b - -");
    assertEquals(0, Evaluation.getSee(board, Move.m(30, 21, Move.SPECIAL_NONE, 0)));
    board =
        BoardUtil.createBoard("2r1r1k1/pp1bppbp/3p1np1/q3P3/2P2P2/1P2B3/P1N1B1PP/2RQ1RK1 b - -");
    assertEquals(100, Evaluation.getSee(board, Move.m(43, 36, Move.SPECIAL_NONE, 0)));
    board = BoardUtil.createBoard("8/4kp2/2npp3/1Nn5/1p2PQP1/7q/1PP1B3/4KR1r b - -");
    assertEquals(0, Evaluation.getSee(board, Move.m(7, 5, Move.SPECIAL_NONE, 0)));
    board = BoardUtil.createBoard("8/4kp2/2npp3/1Nn5/1p2P1P1/7q/1PP1B3/4KR1r b - -");
    assertEquals(0, Evaluation.getSee(board, Move.m(7, 5, Move.SPECIAL_NONE, 0)));
    board = BoardUtil.createBoard("2r2r1k/6bp/p7/2q2p1Q/3PpP2/1B6/P5PP/2RR3K b - -");
    assertEquals(2 * 500 - 900, Evaluation.getSee(board, Move.m(34, 2, Move.SPECIAL_NONE, 0)));
    board = BoardUtil.createBoard("r2qk1nr/pp2ppbp/2b3p1/2p1p3/8/2N2N2/PPPP1PPP/R1BQR1K1 w kq -");
    assertEquals(100, Evaluation.getSee(board, Move.m(21, 36, Move.SPECIAL_NONE, 0)));
    board = BoardUtil.createBoard("6r1/4kq2/b2p1p2/p1pPb3/p1P2B1Q/2P4P/2B1R1P1/6K1 w - -");
    assertEquals(0, Evaluation.getSee(board, Move.m(29, 36, Move.SPECIAL_NONE, 0)));
    board = BoardUtil.createBoard("3q2nk/pb1r1p2/np6/3P2Pp/2p1P3/2R4B/PQ3P1P/3R2K1 w - h6");
    assertEquals(0, Evaluation.getSee(board, Move.m(38, 47, Move.SPECIAL_EP, 0)));
    board = BoardUtil.createBoard("3q2nk/pb1r1p2/np6/3P2Pp/2p1P3/2R1B2B/PQ3P1P/3R2K1 w - h6");
    assertEquals(100, Evaluation.getSee(board, Move.m(38, 47, Move.SPECIAL_EP, 0)));
    board = BoardUtil.createBoard("2r4r/1P4pk/p2p1b1p/7n/BB3p2/2R2p2/P1P2P2/4RK2 w - -");
    assertEquals(500, Evaluation.getSee(board, Move.m(18, 58, Move.SPECIAL_NONE, 0)));
    board = BoardUtil.createBoard("2r4k/2r4p/p7/2b2p1b/4pP2/1BR5/P1R3PP/2Q4K w - -");
    assertEquals(300, Evaluation.getSee(board, Move.m(18, 34, Move.SPECIAL_NONE, 0)));

    board = BoardUtil.createBoard("4q3/1p1pr1k1/1B2rp2/6p1/p3PP2/P3R1P1/1P2R1K1/4Q3 b - -");
    assertEquals(100 - 500, Evaluation.getSee(board, Move.m(44, 28, Move.SPECIAL_NONE, 0)));
    board = BoardUtil.createBoard("4q3/1p1pr1kb/1B2rp2/6p1/p3PP2/P3R1P1/1P2R1K1/4Q3 b - -");
    assertEquals(100, Evaluation.getSee(board, Move.m(55, 28, Move.SPECIAL_NONE, 0)));
  }

  /*
  SeeData("4R3/2r3p1/5bk1/1p1r3p/p2PR1P1/P1BK1P2/1P6/8 b - -","hxg4",0),
       SeeData("4R3/2r3p1/5bk1/1p1r1p1p/p2PR1P1/P1BK1P2/1P6/8 b - -","hxg4",0),
       SeeData("4r1k1/5pp1/nbp4p/1p2p2q/1P2P1b1/1BP2N1P/1B2QPPK/3R4 b - -","Bxf3",0),
       SeeData("2r1r1k1/pp1bppbp/3p1np1/q3P3/2P2P2/1P2B3/P1N1B1PP/2RQ1RK1 b - -","dxe5",Params::PAWN_VALUE),
       SeeData("7r/5qpk/p1Qp1b1p/3r3n/BB3p2/5p2/P1P2P2/4RK1R w - -","Re8",0),
       SeeData("6rr/6pk/p1Qp1b1p/2n5/1B3p2/5p2/P1P2P2/4RK1R w - -","Re8",-Params::ROOK_VALUE),
       SeeData("7r/5qpk/2Qp1b1p/1N1r3n/BB3p2/5p2/P1P2P2/4RK1R w - -","Re8",-Params::ROOK_VALUE),
       SeeData("6RR/4bP2/8/8/5r2/3K4/5p2/4k3 w - -","f8=Q",Params::BISHOP_VALUE-Params::PAWN_VALUE),
       SeeData("6RR/4bP2/8/8/5r2/3K4/5p2/4k3 w - -","f8=N",Params::KNIGHT_VALUE-Params::PAWN_VALUE),
       SeeData("7R/5P2/8/8/8/3K2r1/5p2/4k3 w - -","f8=Q",Params::QUEEN_VALUE-Params::PAWN_VALUE),
       SeeData("7R/5P2/8/8/8/3K2r1/5p2/4k3 w - -","f8=B",Params::BISHOP_VALUE-Params::PAWN_VALUE),
       SeeData("7R/4bP2/8/8/1q6/3K4/5p2/4k3 w - -","f8=R",-Params::PAWN_VALUE),
       SeeData("8/4kp2/2npp3/1Nn5/1p2PQP1/7q/1PP1B3/4KR1r b - -","Rxf1+",0),
       SeeData("8/4kp2/2npp3/1Nn5/1p2P1P1/7q/1PP1B3/4KR1r b - -","Rxf1+", 0),
       SeeData("2r2r1k/6bp/p7/2q2p1Q/3PpP2/1B6/P5PP/2RR3K b - -","Qxc1",2*Params::ROOK_VALUE-Params::QUEEN_VALUE),
       SeeData("r2qk1nr/pp2ppbp/2b3p1/2p1p3/8/2N2N2/PPPP1PPP/R1BQR1K1 w kq -","Nxe5",Params::PAWN_VALUE),
       SeeData("6r1/4kq2/b2p1p2/p1pPb3/p1P2B1Q/2P4P/2B1R1P1/6K1 w - -","Bxe5",0),
       SeeData("3q2nk/pb1r1p2/np6/3P2Pp/2p1P3/2R4B/PQ3P1P/3R2K1 w - h6","gxh6",0),
       SeeData("3q2nk/pb1r1p2/np6/3P2Pp/2p1P3/2R1B2B/PQ3P1P/3R2K1 w - h6","gxh6",Params::PAWN_VALUE),
       SeeData("2r4r/1P4pk/p2p1b1p/7n/BB3p2/2R2p2/P1P2P2/4RK2 w - -","Rxc8",Params::ROOK_VALUE),
       SeeData("2r5/1P4pk/p2p1b1p/5b1n/BB3p2/2R2p2/P1P2P2/4RK2 w - -","Rxc8",Params::ROOK_VALUE),
       SeeData("2r4k/2r4p/p7/2b2p1b/4pP2/1BR5/P1R3PP/2Q4K w - -","Rxc5",Params::BISHOP_VALUE),
       SeeData("8/pp6/2pkp3/4bp2/2R3b1/2P5/PP4B1/1K6 w - -","Bxc6",Params::PAWN_VALUE-Params::BISHOP_VALUE),
       SeeData("4q3/1p1pr1k1/1B2rp2/6p1/p3PP2/P3R1P1/1P2R1K1/4Q3 b - -","Rxe4",Params::PAWN_VALUE-Params::ROOK_VALUE),
       SeeData("4q3/1p1pr1kb/1B2rp2/6p1/p3PP2/P3R1P1/1P2R1K1/4Q3 b - -","Bxe4",Params::PAWN_VALUE)
   */
}
