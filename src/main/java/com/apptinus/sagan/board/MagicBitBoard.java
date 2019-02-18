package com.apptinus.sagan.board;

import static com.apptinus.sagan.util.Bitops.next;
import static com.apptinus.sagan.util.Bitops.setBit;
import static com.apptinus.sagan.util.Bitops.unset;

import java.util.Random;

/**
 * This class borrows some (ported) code from
 * https://github.com/domibu/chess/blob/master/BitBoardMagic.c and
 * http://www.rivalchess.com/magic-bitboards (can now be found at
 * https://web.archive.org/web/20170212100521/http://www.rivalchess.com/magic-bitboards)
 */
public class MagicBitBoard {

  private static long[] magicNumberRook = {
    0x8080009040018020L,
    0x40001001c02000L,
    0x280100060008008L,
    0x60010049a004020L,
    0x200086044106200L,
    0x80030400802200L,
    0x2100490000840200L,
    0x8480008000442300L,
    0x801040008024L,
    0xc00400050002000L,
    0x2401001100402000L,
    0xa823000821001000L,
    0x831000c30080100L,
    0x8308808084000200L,
    0x82003402000d08L,
    0x2082000e06488104L,
    0x2800218000844000L,
    0x1b004402000c008L,
    0x228b808020099000L,
    0x108008008801000L,
    0x43010050080004L,
    0xc80a008002800400L,
    0x26004080010040L,
    0x4ca00030040acL,
    0xa08040008000822eL,
    0x40aa00880400081L,
    0x90048080302000L,
    0x8000100080800800L,
    0x4004000808008040L,
    0x110208801100440L,
    0x802000200110408L,
    0x110028200010064L,
    0x8060804000801120L,
    0x401400080802010L,
    0x1308110041002000L,
    0x2000100481800800L,
    0x8000400800882L,
    0x808400800200L,
    0x802009402000108L,
    0x84200400a2000041L,
    0x109004480090020L,
    0x102500020044000L,
    0x40402001010010L,
    0x50040008004040L,
    0xa400048801010010L,
    0x82400801200800cL,
    0x8024100200040208L,
    0x8a2f000481410002L,
    0x8840a104801100L,
    0x4015004000208100L,
    0x811000200080L,
    0x8210090021500100L,
    0x8008c02004040L,
    0x1000400580300L,
    0x5109305806810c00L,
    0x80008000d1000480L,
    0x21012080004811L,
    0x51001080c00861L,
    0x4002814012000962L,
    0x80890030004421L,
    0x21000208001005L,
    0x80d0008040002a1L,
    0x20200080401288aL,
    0x2808041041042082L
  };

  private static long[] magicNumberShiftsRook = {
    52, 53, 53, 53, 53, 53, 53, 52, 53, 54, 54, 54, 54, 54, 54, 53, 53, 54, 54, 54, 54, 54, 54, 53,
    53, 54, 54, 54, 54, 54, 54, 53, 53, 54, 54, 54, 54, 54, 54, 53, 53, 54, 54, 54, 54, 54, 54, 53,
    53, 54, 54, 54, 54, 54, 54, 53, 52, 53, 53, 53, 53, 53, 53, 52
  };

  private static long[] magicNumberBishop = {
    0x2910054208004104L,
    0x2100630a7020180L,
    0x5822022042000000L,
    0x2ca804a100200020L,
    0x204042200000900L,
    0x2002121024000002L,
    0x80404104202000e8L,
    0x812a020205010840L,
    0x8005181184080048L,
    0x1001c20208010101L,
    0x1001080204002100L,
    0x1810080489021800L,
    0x62040420010a00L,
    0x5028043004300020L,
    0xc0080a4402605002L,
    0x8a00a0104220200L,
    0x940000410821212L,
    0x1808024a280210L,
    0x40c0422080a0598L,
    0x4228020082004050L,
    0x200800400e00100L,
    0x20b001230021040L,
    0x90a0201900c00L,
    0x4940120a0a0108L,
    0x20208050a42180L,
    0x1004804b280200L,
    0x2048020024040010L,
    0x102c04004010200L,
    0x20408204c002010L,
    0x2411100020080c1L,
    0x102a008084042100L,
    0x941030000a09846L,
    0x244100800400200L,
    0x4000901010080696L,
    0x280404180020L,
    0x800042008240100L,
    0x220008400088020L,
    0x4020182000904c9L,
    0x23010400020600L,
    0x41040020110302L,
    0x412101004020818L,
    0x8022080a09404208L,
    0x1401210240484800L,
    0x22244208010080L,
    0x1105040104000210L,
    0x2040088800c40081L,
    0x8184810252000400L,
    0x4004610041002200L,
    0x40201a444400810L,
    0x4611010802020008L,
    0x80000b0401040402L,
    0x20004821880a00L,
    0x8200002022440100L,
    0x9431801010068L,
    0x1040c20806108040L,
    0x804901403022a40L,
    0x2400202602104000L,
    0x208520209440204L,
    0x40c000022013020L,
    0x2000104000420600L,
    0x400000260142410L,
    0x800633408100500L,
    0x2404080a1410L,
    0x138200122002900L
  };

  private static long[] magicNumberShiftsBishop = {
    58, 59, 59, 59, 59, 59, 59, 58, 59, 59, 59, 59, 59, 59, 59, 59,
    59, 59, 57, 57, 57, 57, 59, 59, 59, 59, 57, 55, 55, 57, 59, 59,
    59, 59, 57, 55, 55, 57, 59, 59, 59, 59, 57, 57, 57, 57, 59, 59,
    59, 59, 59, 59, 59, 59, 59, 59, 58, 59, 59, 59, 59, 59, 59, 58
  };

  public static long[][] generateMagicRookMoves(long[] rookDeltas) {
    long[][] occupancyVariations = generateOccupancyVariations(rookDeltas);
    return generateMoveDatabase(
        true, rookDeltas, occupancyVariations, magicNumberRook, magicNumberShiftsRook);
  }

  public static long[][] generateMagiBishopMoves(long[] bishopDeltas) {
    long[][] occupancyVariations = generateOccupancyVariations(bishopDeltas);
    return generateMoveDatabase(
        true, bishopDeltas, occupancyVariations, magicNumberBishop, magicNumberShiftsBishop);
  }

  public static int magicIndex(long allPieces, long mask, int square) {
    return (int) (((allPieces & mask) * magicNumberRook[square]) >>> magicNumberShiftsRook[square]);
  }

  /**
   * Populates all possible combinations of blocking squares per square. E.g.
   * occupancyVariationRook[0][0] = 0 (no blocking pieces for rook on A1)
   * occupancyVariationRook[0][1] = 0b10 (a blocking piece on B1 for rook on A1
   * occupancyVariationRook[0][4096] = 0b0000000100000001...1111110 (all squares for rook on A1
   * blocked) Basically ported from https://github.com/domibu/chess/blob/master/BitBoardMagic.c
   */
  private static long[][] generateOccupancyVariations(long[] deltas) {
    long[][] occupancyVariation = new long[64][4096];
    long mask;
    int[] setBitsInMask = new int[13];
    int[] setBitsInIndex = new int[13];

    for (int square = 0; square <= 63; square++) {
      mask = deltas[square];
      getsetBits(mask, setBitsInMask);
      for (int i = 0; i < setBit(population(mask)); i++) {
        getsetBits(i, setBitsInIndex);
        for (int j = 0; setBitsInIndex[j] != -1; j++) {
          occupancyVariation[square][i] |= 1L << setBitsInMask[setBitsInIndex[j]];
        }
      }
    }

    return occupancyVariation;
  }

  private static int population(long bb) {
    int n;
    int count = 0;
    while ((n = next(bb)) != -1) {
      count++;
      bb = unset(bb, n);
    }
    return count;
  }

  private static void getsetBits(long a, int[] p) {
    int i, j = 0;
    long c;
    for (i = 0; i < 64; i++) {
      c = 1L & a;
      if (c != 0) {
        p[j] = i;
        j++;
      }
      a >>>= 1;
    }
    p[j] = -1;
  }

  private static long getRookMovesUntilBlocker(int square, long occupancyVariation) {
    long validMoves = 0L;
    for (int j = square + 8; j <= 63; j += 8) {
      validMoves |= (1L << j);
      if ((occupancyVariation & (1L << j)) != 0) break;
    }
    for (int j = square - 8; j >= 0; j -= 8) {
      validMoves |= (1L << j);
      if ((occupancyVariation & (1L << j)) != 0) break;
    }
    for (int j = square + 1; j % 8 != 0; j++) {
      validMoves |= (1L << j);
      if ((occupancyVariation & (1L << j)) != 0) break;
    }
    for (int j = square - 1; j % 8 != 7 && j >= 0; j--) {
      validMoves |= (1L << j);
      if ((occupancyVariation & (1L << j)) != 0) break;
    }
    return validMoves;
  }

  private static long getBishopMovesUntilBlocker(int square, long occupancyVariation) {
    long validMoves = 0L;
    for (int j = square + 9; j % 8 != 0 && j <= 63; j += 9) {
      validMoves |= (1L << j);
      if ((occupancyVariation & (1L << j)) != 0) break;
    }
    for (int j = square - 9; j % 8 != 7 && j >= 0; j -= 9) {
      validMoves |= (1L << j);
      if ((occupancyVariation & (1L << j)) != 0) break;
    }
    for (int j = square + 7; j % 8 != 7 && j <= 63; j += 7) {
      validMoves |= (1L << j);
      if ((occupancyVariation & (1L << j)) != 0) break;
    }
    for (int j = square - 7; j % 8 != 0 && j >= 0; j -= 7) {
      validMoves |= (1L << j);
      if ((occupancyVariation & (1L << j)) != 0) break;
    }
    return validMoves;
  }

  private static long[][] generateMoveDatabase(
      boolean isRook,
      long[] deltas,
      long[][] occupancyVariations,
      long[] magicNumbers,
      long[] magicNumberShifts) {
    long[][] magicMoves = new long[64][4096];
    int magicIndex;

    for (int square = 0; square <= 63; square++) {
      long bitCount = setBit(population(deltas[square]));

      for (int i = 0; i < bitCount; i++) {
        magicIndex =
            (int)
                ((occupancyVariations[square][i] * magicNumbers[square])
                    >>> magicNumberShifts[square]);
        if (isRook) {
          magicMoves[square][magicIndex] =
              getRookMovesUntilBlocker(square, occupancyVariations[square][i]);
        } else {
          magicMoves[square][magicIndex] =
              getBishopMovesUntilBlocker(square, occupancyVariations[square][i]);
        }
      }
    }

    return magicMoves;
  }

  public static long[][] generateMagicNumbers(
      boolean isRook, long[] deltas, long[][] occupancyVariation) {
    long[][] result = new long[2][64]; // Magic numbers and magic shifts
    int i, j, square, variationCount;

    Random r = new Random();
    long magicNumber = 0;
    int index;

    for (square = 0; square <= 63; square++) {
      System.out.println("Calculating " + Board.squareToNotation(square));
      int bitCount = population(deltas[square]);
      variationCount = (int) (1L << bitCount);
      boolean fail;
      long usedBy[] = new long[(int) (1L << bitCount)];

      do {
        magicNumber =
            r.nextLong()
                & r.nextLong()
                & r.nextLong(); // generate a random number with not many bits set
        for (j = 0; j < variationCount; j++) usedBy[j] = 0;
        for (i = 0, fail = false; i < variationCount && !fail; i++) {
          index = (int) ((occupancyVariation[square][i] * magicNumber) >>> (64 - bitCount));

          long untilBlocker =
              isRook
                  ? getRookMovesUntilBlocker(square, occupancyVariation[square][i])
                  : getBishopMovesUntilBlocker(square, occupancyVariation[square][i]);

          if (usedBy[index] == 0) {
            usedBy[index] = untilBlocker;
          } else {
            fail = usedBy[index] != untilBlocker;
          }
        }
      } while (fail);

      result[0][square] = magicNumber;
      result[0][square] = (64 - bitCount);
    }

    return result;
  }
}
