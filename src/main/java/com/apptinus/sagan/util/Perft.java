package com.apptinus.sagan.util;

import com.apptinus.sagan.board.Board;
import com.apptinus.sagan.board.Move;
import com.apptinus.sagan.board.MoveGen;
import java.util.Arrays;
import java.util.Objects;

/**
 * class Perft
 *
 * <p>This class runs has utility methods for perft tests
 *
 * <p>Borrowed from
 * https://sourceforge.net/p/mediocrechess/code/HEAD/tree/trunk/src/main/java/mediocrechess/mediocre/perft/Perft.java
 */
public class Perft {

  /**
   * Start the perft search
   *
   * @param board The board to search
   * @param depth The depth to search to
   * @param divide Should we divide the first moves or just return the total value
   * @return number of nodes
   */
  public static long perft(Board board, int depth, boolean divide) {
    long nNodes;

    if (divide) {
      nNodes = divide(board, depth);
    } else {
      nNodes = miniMax(board, depth);
    }

    return nNodes;
  }

  /**
   * Keeps track of every starting move and its number of child moves, and then prints it on the
   * screen.
   *
   * @param board The position to search
   * @param depth The depth to search to
   */
  private static long divide(Board board, int depth) {
    Move[] moves = new Move[256];

    for (int i = 0; i < 256; i++) moves[i] = new Move();
    int totalMoves = MoveGen.genMoves(board, moves, 0);
    Long[] children = new Long[256];

    for (int i = 0; i < totalMoves; i++) {
      board.make(moves[i].move);
      children[i] = new Long(miniMax(board, depth - 1));
      board.unmake(moves[i].move);
    }

    long nodes = 0;
    for (int i = 0; i < totalMoves; i++) {
      nodes += children[i].longValue();
    }

    System.out.println("Moves: " + totalMoves);
    return nodes;
  }

  /**
   * Generates every move from the position on board and returns the total number of moves found to
   * the depth
   *
   * @param board The board used
   * @param depth The depth currently at
   * @return int The number of moves found
   */
  private static long miniMax(Board board, int depth) {
    long nodes = 0;

    if (depth == 0) return 1;

    Move[] moves = new Move[256];
    for (int i = 0; i < 256; i++) moves[i] = new Move();
    int totalMoves = MoveGen.genMoves(board, moves, 0);

    for (int i = 0; i < totalMoves; i++) {
      board.make(moves[i].move);
      nodes += miniMax(board, depth - 1);
      board.unmake(moves[i].move);
    }

    return nodes;
  }

  /**
   * Takes number and converts it to minutes, seconds and fraction of a second also includes leading
   * zeros
   *
   * @param millis the Milliseconds to convert
   * @return String the conversion
   */
  public static String convertMillis(long millis) {
    long minutes = millis / 60000;
    long seconds = (millis % 60000) / 1000;
    long fracSec = (millis % 60000) % 1000;

    String timeString = "";

    // Add minutes to the string, if no minutes this part will not add to
    // the string
    if (minutes < 10 && minutes != 0) timeString += "0" + Long.toString(minutes) + ":";
    else if (minutes >= 10) timeString += Long.toString(minutes) + ":";

    // Add seconds to the string
    if (seconds == 0) timeString += "0";
    else if (minutes != 0 && seconds < 10) timeString += "0" + Long.toString(seconds);
    else if (seconds < 10) timeString += Long.toString(seconds);
    else timeString += Long.toString(seconds);

    timeString += ".";

    // Add fractions of a second to the string
    if (fracSec == 0) timeString += "000";
    else if (fracSec < 10) timeString += "00" + Long.toString(fracSec);
    else if (fracSec < 100) timeString += "0" + Long.toString(fracSec);
    else timeString += Long.toString(fracSec);

    return timeString;
  }

  public static class BoardStateTest {

    private long wPieces;
    private long bPieces;
    private long allPieces;
    private int[] board;
    private long[] pieces;

    public BoardStateTest(Board board) {
      this.wPieces = board.wPieces;
      this.bPieces = board.bPieces;
      this.allPieces = board.allPieces;
      this.board = Arrays.copyOf(board.board, 64);
      this.pieces = Arrays.copyOf(board.pieces, 12);
    }

    @Override
    public boolean equals(final Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      final BoardStateTest that = (BoardStateTest) o;
      return wPieces == that.wPieces
          && bPieces == that.bPieces
          && allPieces == that.allPieces
          && Arrays.equals(board, that.board)
          && Arrays.equals(pieces, that.pieces);
    }

    @Override
    public int hashCode() {
      int result = Objects.hash(wPieces, bPieces, allPieces);
      result = 31 * result + Arrays.hashCode(board);
      result = 31 * result + Arrays.hashCode(pieces);
      return result;
    }
  }
}
