package com.apptinus.sagan.board;

public class Move {
  /*
  Move
  0000000000000000|00|00|000000|000000
  First 16 bits not used for now
  Promotion type (0 - Knight, 1 - Bishop, 2 - Rook, 3 - Queen)
  Special move (0 - None, 1 - Promotion, 2 - En passant, 3 - Castle
  To square (0-63)
  From square (0-63)
   */

  /*
  History
  00000000000|0000|000000|000000|0|00|00
  First not used
  Captured piece
  Half move count
  Ep square
  Has ep
  Black castle
  White castle
   */

  public static final int PROMO_N = 0;
  public static final int PROMO_B = 1;
  public static final int PROMO_R = 2;
  public static final int PROMO_Q = 3;

  public static final int SPECIAL_NONE = 0;
  public static final int SPECIAL_PROMO = 1;
  public static final int SPECIAL_EP = 2;
  public static final int SPECIAL_CASTLE = 3;

  public static int m(int from, int to, int special, int promotion) {
    return (from | to << 6 | special << 12 | promotion << 14);
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

  public static int h(
      int wCastle, int bCastle, int hasEp, int epSquare, int fPly, int capturedPiece) {
    return (wCastle
            | bCastle << 2
            | hasEp << 4
            | epSquare << 5
            | fPly << 11
            | capturedPiece << 17);
  }

  public static int wCastle(int history) {
    return history & 3;
  }

  public static int bCastle(int history) {
    return history >>> 2 & 3;
  }

  public static int hasEp(int history) {
    return history >>> 4 & 1;
  }

  public static int epSquare(int history) {
    return hasEp(history) == 1 ? history >>> 5 & 63 : -1;
  }

  public static int fPly(int history) {
    return history >>> 11 & 63;
  }

  public static int capturedPiece(int history) {
    return history >>> 17 & 15;
  }

  public static final int A1 = 0;
  public static final int B1 = 1;
  public static final int C1 = 2;
  public static final int D1 = 3;
  public static final int E1 = 4;
  public static final int F1 = 5;
  public static final int G1 = 6;
  public static final int H1 = 7;
  public static final int A2 = 8;
  public static final int B2 = 9;
  public static final int C2 = 10;
  public static final int D2 = 11;
  public static final int E2 = 12;
  public static final int F2 = 13;
  public static final int G2 = 14;
  public static final int H2 = 15;
  public static final int A3 = 16;
  public static final int B3 = 17;
  public static final int C3 = 18;
  public static final int D3 = 19;
  public static final int E3 = 20;
  public static final int F3 = 21;
  public static final int G3 = 22;
  public static final int H3 = 23;
  public static final int A4 = 24;
  public static final int B4 = 25;
  public static final int C4 = 26;
  public static final int D4 = 27;
  public static final int E4 = 28;
  public static final int F4 = 29;
  public static final int G4 = 30;
  public static final int H4 = 31;
  public static final int A5 = 32;
  public static final int B5 = 33;
  public static final int C5 = 34;
  public static final int D5 = 35;
  public static final int E5 = 36;
  public static final int F5 = 37;
  public static final int G5 = 38;
  public static final int H5 = 39;
  public static final int A6 = 40;
  public static final int B6 = 41;
  public static final int C6 = 42;
  public static final int D6 = 43;
  public static final int E6 = 44;
  public static final int F6 = 45;
  public static final int G6 = 46;
  public static final int H6 = 47;
  public static final int A7 = 48;
  public static final int B7 = 49;
  public static final int C7 = 50;
  public static final int D7 = 51;
  public static final int E7 = 52;
  public static final int F7 = 53;
  public static final int G7 = 54;
  public static final int H7 = 55;
  public static final int A8 = 56;
  public static final int B8 = 57;
  public static final int C8 = 58;
  public static final int D8 = 59;
  public static final int E8 = 60;
  public static final int F8 = 61;
  public static final int G8 = 62;
  public static final int H8 = 63;

  public static final int N = 8;
  public static final int E = 1;
  public static final int W = -1;
  public static final int S = -8;
  public static final int NN = 16;
  public static final int NE = 9;
  public static final int NW = 7;
  public static final int SS = -16;
  public static final int SE = -7;
  public static final int SW = -9;

  public static int dir(int square, int d) {
    return square += d;
  }
}
