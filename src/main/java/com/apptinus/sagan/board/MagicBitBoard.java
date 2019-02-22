package com.apptinus.sagan.board;

import static com.apptinus.sagan.util.Bitops.next;
import static com.apptinus.sagan.util.Bitops.setBit;
import static com.apptinus.sagan.util.Bitops.unset;

import com.apptinus.sagan.util.Bitops;
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

  public static long[] magicNumberShiftsRook = {
    52, 53, 53, 53, 53, 53, 53, 52, 53, 54, 54, 54, 54, 54, 54, 53, 53, 54, 54, 54, 54, 54, 54, 53,
    53, 54, 54, 54, 54, 54, 54, 53, 53, 54, 54, 54, 54, 54, 54, 53, 53, 54, 54, 54, 54, 54, 54, 53,
    53, 54, 54, 54, 54, 54, 54, 53, 52, 53, 53, 53, 53, 53, 53, 52
  };

  private static long[] magicNumberBishop = {
    0x2200810890246L,
    0x2121040482004040L,
    0x10408e20405800L,
    0xc080a0028200818L,
    0x8002021000080004L,
    0x8820080484caL,
    0x80808808085c0001L,
    0x8828c02a10022002L,
    0xa0a04a7021030aL,
    0x140882040920L,
    0x14100090a10100L,
    0xc850042400801000L,
    0x2210240420040010L,
    0x6000063004200021L,
    0xc00881808a0L,
    0x28129082208a2012L,
    0x80281010200804c0L,
    0x10000530420040L,
    0x100c8901012500L,
    0x1090800802004400L,
    0x1000820280800L,
    0x42004262032000L,
    0x44280203822800L,
    0x100401284040120L,
    0x403110000820c108L,
    0xa020210908201L,
    0x4020280010024044L,
    0x100400400c030012L,
    0xf88100c084044000L,
    0x8010008401004100L,
    0x82040400006a0208L,
    0xc002026804808816L,
    0xd002101003b42020L,
    0x2024200103010L,
    0x20c006414ca1400L,
    0x3060080080080L,
    0x10c008400020102L,
    0x2020108100808050L,
    0x5012100020804L,
    0x44c042840002104L,
    0x2800904422001000L,
    0x420805202040L,
    0x4101a0613000080cL,
    0x1201482024200800L,
    0x1201200c300L,
    0x2020020408180240L,
    0x400202b404082100L,
    0x24480050408101L,
    0x24008809a80010L,
    0x4808220202205001L,
    0x494142884100011L,
    0x84084040101L,
    0x1801002021402L,
    0xa300112001170020L,
    0x4101021090480L,
    0x44080201420405L,
    0x2881410800822040L,
    0x20102a0101823000L,
    0xa20298480804L,
    0x200826008208840L,
    0x4210c100L,
    0x180012224034600L,
    0x1000418484040048L,
    0x2280204004a00L
  };

  public static long[] magicNumberShiftsBishop = {
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
        false, bishopDeltas, occupancyVariations, magicNumberBishop, magicNumberShiftsBishop);
  }

  public static int magicIndexRook(long allPieces, long mask, int square) {
    return (int) (((allPieces & mask) * magicNumberRook[square]) >>> magicNumberShiftsRook[square]);
  }

  public static int magicIndexBishop(long allPieces, long mask, int square) {
    return (int)
        (((allPieces & mask) * magicNumberBishop[square]) >>> magicNumberShiftsBishop[square]);
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
      for (int i = 0; i < setBit(Bitops.population(mask)); i++) {
        getsetBits(i, setBitsInIndex);
        for (int j = 0; setBitsInIndex[j] != -1; j++) {
          occupancyVariation[square][i] |= 1L << setBitsInMask[setBitsInIndex[j]];
        }
      }
    }

    return occupancyVariation;
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

  public static long getRookMovesUntilBlocker(int square, long occupancyVariation) {
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

  public static long getBishopMovesUntilBlocker(int square, long occupancyVariation) {
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
      long bitCount = setBit(Bitops.population(deltas[square]));

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

  public static long[][] generateMagicNumbers(boolean isRook, long[] deltas) {
    long[][] occupancyVariations = generateOccupancyVariations(deltas);

    long[][] result = new long[2][64]; // Magic numbers and magic shifts
    int i, j, square, variationCount;

    Random r = new Random();
    long magicNumber = 0;
    int index;

    for (square = 0; square <= 63; square++) {
      System.out.println("Calculating " + Board.squareToNotation(square));
      int bitCount = Bitops.population(deltas[square]);
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
          index = (int) ((occupancyVariations[square][i] * magicNumber) >>> (64 - bitCount));

          long untilBlocker =
              isRook
                  ? getRookMovesUntilBlocker(square, occupancyVariations[square][i])
                  : getBishopMovesUntilBlocker(square, occupancyVariations[square][i]);

          if (usedBy[index] == 0) {
            usedBy[index] = untilBlocker;
          } else {
            fail = usedBy[index] != untilBlocker;
          }
        }
      } while (fail);

      result[0][square] = magicNumber;
      result[1][square] = (64 - bitCount);
    }

    return result;
  }
}
