package com.apptinus.board;

public class Move {
  /*
  0000000000000000[00|00|000000|000000
  First 16 bits not used for now
  Promotion type (0 - Knight, 1 - Bishop, 2 - Rook, 3 - Queen)
  Special move (0 - None, 1 - Promotion, 2 - En passant, 3 - Castle
  To square (0-63)
  From square (0-63)
   */

  public static int m(int from, int to, int special, int promotion) {
    return (0 | from | to << 6 | special << 12 | promotion << 14);
  }

  public static int from(int move) {
    return move & 63;
  }

  public static int to(int move) {
    return move >>> 6 & 63;
  }

  public static int special(int move) {
    return move >>> 12 & 3;
  }

  public static int promotion(int move) {
    return move >>> 14 & 3;
  }
}
