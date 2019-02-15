package com.apptinus.sagan.util;

public class Bitops {

  public static long add(long bb, int square) {
    return bb |= 1L << square;
  }

  public static boolean isSet(long bb, int square) {
    return (bb & 1L << square) != 0;
  }

  public static int nextSet(long bb) {

  }
}
