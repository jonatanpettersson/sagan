package com.apptinus.sagan.board;

import static org.junit.Assert.assertEquals;

import com.apptinus.sagan.util.BoardUtil;
import org.junit.Test;

public class MoveGenTest {

  //  @Test
  public void testGenPrint() {
    Board board = BoardUtil.createBoard("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
    //    BoardUtil.setFen(board, "8/6k1/3R4/8/8/2K5/8/8 w - -");

    Move[] moves = new Move[256];
    for (int i = 0; i < 256; i++) moves[i] = new Move();
    int movesTotal = MoveGen.genMoves(board, moves, 0);

    System.out.println("Total moves: " + movesTotal);
    for (int moveIdx = 0; moveIdx < movesTotal; moveIdx++) {
      int move = moves[moveIdx].move;
      System.out.println(
          BoardUtil.squareToNotation(Move.from(move)) + "" + BoardUtil.squareToNotation(Move.to(move)));
    }
  }

  @Test
  public void testGen() {
    Board board;
    MoveGen.init();

    Move[] moves = new Move[256];
    for (int i = 0; i < 256; i++) moves[i] = new Move();

    board = BoardUtil.createBoard("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
    assertEquals(20, MoveGen.genMoves(board, moves, 0));
    assertEquals(0, MoveGen.genPseudoLegalCaptures(board, moves, 0));

    board = BoardUtil.createBoard("8/6k1/3R4/8/8/2K5/8/8 w - -");
    assertEquals(22, MoveGen.genMoves(board, moves, 0));
    assertEquals(0, MoveGen.genPseudoLegalCaptures(board, moves, 0));

    board = BoardUtil.createBoard("8/6k1/3R1r2/8/8/2K5/3r4/8 w - -");
    assertEquals(15, MoveGen.genMoves(board, moves, 0));
    assertEquals(3, MoveGen.genPseudoLegalCaptures(board, moves, 0));
  }

  @Test
  public void testRookMask() {
    assertEquals(0x101010101017eL, MoveGen.rookAttackMask(0));
    assertEquals(0x1010101010106eL, MoveGen.rookAttackMask(4));
    assertEquals(0x8080808080807eL, MoveGen.rookAttackMask(7));
    assertEquals(0x101017e010100L, MoveGen.rookAttackMask(24));
    assertEquals(0x80807e80808000L, MoveGen.rookAttackMask(39));
    assertEquals(0x7e01010101010100L, MoveGen.rookAttackMask(56));
    assertEquals(0x7e80808080808000L, MoveGen.rookAttackMask(63));
  }

  @Test
  public void testBishopMask() {
    assertEquals(0x40201008040200L, MoveGen.bishopAttackMask(0));
    assertEquals(0x2442800L, MoveGen.bishopAttackMask(4));
    assertEquals(0x2040810204000L, MoveGen.bishopAttackMask(7));
    assertEquals(0x8040200020400L, MoveGen.bishopAttackMask(24));
    assertEquals(0x20400040201000L, MoveGen.bishopAttackMask(39));
    assertEquals(0x2040810204000L, MoveGen.bishopAttackMask(56));
    assertEquals(0x40201008040200L, MoveGen.bishopAttackMask(63));
  }

  @Test
  public void testGenMovesUntilBlockerRook() {
    assertEquals(0x101010101010106L, MagicBitBoard.getRookMovesUntilBlocker(0, 0x4L));
    assertEquals(0x7f80800000000000L, MagicBitBoard.getRookMovesUntilBlocker(63, 0x800000000000L));
    assertEquals(0x10106c10100000L, MagicBitBoard.getRookMovesUntilBlocker(36, 0x10004400100000L));
  }

  @Test
  public void testGenMovesUntilBlockerBishop() {
    assertEquals(0x40200L, MagicBitBoard.getBishopMovesUntilBlocker(0, 0x40000L));
    assertEquals(0x40201000000000L, MagicBitBoard.getBishopMovesUntilBlocker(63, 0x1000000000L));
    assertEquals(
        0x44280028440000L, MagicBitBoard.getBishopMovesUntilBlocker(36, 0x44000000440000L));
  }
}
