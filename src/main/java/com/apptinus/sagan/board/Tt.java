package com.apptinus.sagan.board;

import com.apptinus.sagan.util.BoardUtil;

public class Tt {

  public static final int EXACT = 0;
  public static final int LOWER_BOUND = 1;
  public static final int UPPER_BOUND = 2;

  private int size;
  private long[] posZobrist;
  private long[] posInfo;

  private int probedEval;
  private int probedEvalType;
  private int probedDepth;
  private int probedMove;

  public Tt(int sizeMb) {
    // Probably breaks if > ~32000mb (can't currently make java arrays that big anyway)
    this.size = sizeMb * 1024 * 1024 * 8 / 64 / 2;
    this.posZobrist = new long[size];
    this.posInfo = new long[size];
  }

  public void set(long zobrist, int depth, int evalType, int eval, int move) {
    // Always replace scheme

    int hashKey = (int) (zobrist % size);

    posZobrist[hashKey] = zobrist;

    posInfo[hashKey] =
        (eval + Search.INFINITY) | (evalType << 15) | (depth << 19) | ((long) move << 25);
  }

  public boolean probe(long zobrist) {
    int hashKey = (int) (zobrist % size);
    if (posZobrist[hashKey] != zobrist) return false;

    probedEval = (int) ((posInfo[hashKey] & 0xFFFF) - Search.INFINITY);
    probedEvalType = (int) (posInfo[hashKey] >>> 15 & 0x3);
    probedDepth = (int) (posInfo[hashKey] >>> 19 & 0x3F);
    probedMove = (int) (posInfo[hashKey] >>> 25);

    return true;
  }

  public static boolean isLegalMove(Board board, int move) {
    Move[] moves = new Move[256];
    for (int i = 0; i < 256; i++) moves[i] = new Move();
    int totalMoves = MoveGen.genAllLegalMoves(board, moves, 0);
    for (int i = 0; i < totalMoves; i++) {
      if (moves[i].move == move) return true;
    }

    return false;
  }

  public int[] collectPV(Board board, int bestMove) {
    int[] arrayPV = new int[128];

    arrayPV[0] = bestMove;
    board.make(bestMove);

    probe(board.zobrist);
    int move = probedMove;

    int i = 20;
    int index = 1;
    while (i > 0) {
      if (move == 0 || !isLegalMove(board, move)) break;
      arrayPV[index] = move;
      board.make(move);
      probe(board.zobrist);
      move = probedMove;
      i--;
      index++;
    }

    // Unmake the moves
    for (i = index - 1; i >= 0; i--) {
      board.unmake(arrayPV[i]);
    }
    return arrayPV;
  }

  public int getProbedEval() {
    return probedEval;
  }

  public int getProbedEvalType() {
    return probedEvalType;
  }

  public int getProbedDepth() {
    return probedDepth;
  }

  public int getProbedMove() {
    return probedMove;
  }
}
