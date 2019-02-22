package com.apptinus.sagan.util;

public class Bitops {

  public static long setBit(int square) {
    return 1L << square;
  }

  public static long unsetBit(int square) {
    return ~(setBit(square));
  }

  public static long set(long bb, int square) {
    return bb | setBit(square);
  }

  public static long unset(long bb, int square) {
    return bb & unsetBit(square);
  }

  public static int unset(int bb, int square) {
    return bb & (int) unsetBit(square);
  }

  public static boolean isSet(long bb, int square) {
    return (bb & setBit(square)) != 0;
  }

  public static boolean isSet(int bb, int square) {
    return (bb & setBit(square)) != 0;
  }

  public static int next(long bb) {
    int square = Long.numberOfTrailingZeros(bb);
    return square == 64 ? -1 : square;
  }

  public static int population(long bb) {
    int n;
    int count = 0;
    while ((n = next(bb)) != -1) {
      count++;
      bb = unset(bb, n);
    }
    return count;
  }
}
