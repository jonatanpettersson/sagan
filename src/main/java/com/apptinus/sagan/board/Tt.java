package com.apptinus.sagan.board;

public class Tt {

  private int size;
  private long[] posZobrist;
  private long[] posInfo;

  private int probedEval;
  private int probedFlag;
  private int probedDepth;
  private int probedMove;

  public Tt(int sizeMb) {
    // Probably breaks if > ~32000mb (can't currently make java arrays that big anyway)
    this.size = sizeMb * 1024 * 1024 * 8 / 64 / 2;
    this.posZobrist = new long[size];
    this.posInfo = new long[size];
  }

  public void set(long zobrist, int depth, int flag, int eval, int move) {
    // Always replace scheme

    int hashKey = (int) (zobrist % size);

    posZobrist[hashKey] = zobrist;

    posInfo[hashKey] =
      (eval + Search.INFINITY) | (flag << 15) | (depth << 19) | ((long)move << 25);
  }

  public boolean probe(long zobrist) {
    int hashKey = (int) (zobrist % size);
    if (posZobrist[hashKey] != zobrist) return false;

    probedEval = (int) ((posInfo[hashKey] & 0xFFFF) - Search.INFINITY);
    probedFlag = (int) (posInfo[hashKey] >>> 15 & 0x3);
    probedDepth = (int) (posInfo[hashKey] >>> 19 & 0x3F);
    probedMove = (int) (posInfo[hashKey] >>> 25);

    return true;
  }

  public int getProbedEval() {
    return probedEval;
  }

  public int getProbedFlag() {
    return probedFlag;
  }

  public int getProbedDepth() {
    return probedDepth;
  }

  public int getProbedMove() {
    return probedMove;
  }
}
