package com.apptinus.sagan.util;

import com.apptinus.sagan.board.Board;
import com.apptinus.sagan.board.Move;
import java.util.Random;

/**
 * Handles the creation and updating of zobrist keys
 *
 * @author Jonatan Pettersson (mediocrechess@gmail.com)
 */
public class Zobrist {
  public static final long[][][] PIECES;
  public static final long[] W_CASTLING_RIGHTS;
  public static final long[] B_CASTLING_RIGHTS;
  public static final long[] EN_PASSANT;
  public static final long SIDE;

  /**
   * Creates a zobrist key from scratch using the given board
   *
   * @param board The board to create a zobrist key from
   * @return long The zobrist key for the board
   */
  public static long getZobristKey(Board board) {
    long zobristKey = 0L;

    for (int index = 0; index <= 63; index++) {
      int piece = board.board[index];

      if (piece <= 5) {
        zobristKey ^= PIECES[piece][0][index];
      } else if (piece != 12) {
        zobristKey ^= PIECES[piece - 6][1][index];
      }
    }

    zobristKey ^= W_CASTLING_RIGHTS[board.wCastle];
    zobristKey ^= B_CASTLING_RIGHTS[board.bCastle];

    if (board.ep != -1) zobristKey ^= EN_PASSANT[board.ep];

    if (board.toMove == Move.BLACK) zobristKey ^= SIDE;

    return zobristKey;
  }

  /** The following lines creates the random numbers used for zobrist key creation */
  static {
    Random rnd = new Random(17L);
    SIDE = Math.abs(rnd.nextLong());

    W_CASTLING_RIGHTS = new long[4];
    B_CASTLING_RIGHTS = new long[4];

    for (int i = 0; i < 4; i++) {
      W_CASTLING_RIGHTS[i] = Math.abs(rnd.nextLong());
      B_CASTLING_RIGHTS[i] = Math.abs(rnd.nextLong());
    }

    EN_PASSANT = new long[64];
    PIECES = new long[6][2][64]; // piece, side to move, square

    for (int square = 0; square < 64; square++) {
      EN_PASSANT[square] = Math.abs(rnd.nextLong());
      for (int piece = 0; piece < 6; piece++) {
        PIECES[piece][0][square] = Math.abs(rnd.nextLong());
        PIECES[piece][1][square] = Math.abs(rnd.nextLong());
      }
    }
  }
}
