package com.apptinus.sagan.board;

import static com.apptinus.sagan.board.Search.MATE_BOUND;
import static com.apptinus.sagan.board.Search.MATE_VALUE;
import static com.apptinus.sagan.board.Search.PLY;

import com.apptinus.sagan.Uci;
import com.apptinus.sagan.util.BoardUtil;
import java.io.IOException;

public class SearchSupervisor {
  public static final int TIME_CHECK_INTERVAL = 10000;

  private final boolean useFixedDepth;
  private final int depth;
  private final int movetime;
  private final long startTime;
  private final int timeForThisMove;

  private boolean shouldStop;
  private boolean ponder;
  private int nextTimeCheck;

  public SearchSupervisor(int depth, int timeLeft, int increment, int movetime, boolean ponder) {
    this.useFixedDepth = depth != 0;
    this.depth = depth;
    this.movetime = movetime;
    this.ponder = ponder;
    this.startTime = System.currentTimeMillis();
    this.shouldStop = false;

    nextTimeCheck = TIME_CHECK_INTERVAL;

    if (movetime == 0) timeForThisMove = calculateTime(timeLeft, increment);
    else timeForThisMove = movetime;
  }

  private static int calculateTime(int timeLeft, int increment) {
    int timeForThisMove; // The maximum time we are allowed to use on this move
    int percent =
        40; // How many percent of the time we will use; percent=20 -> 5%, percent=40 -> 2.5% etc.
    // (formula is 100/percent, i.e. 100/40 =2.5)

    timeForThisMove = timeLeft / percent + (increment); // Use the percent + increment for the move
    if (timeForThisMove >= timeLeft)
      timeForThisMove =
          timeLeft
              - 500; // If the increment puts us above the total time left use the timeleft - 0.5
    // seconds
    if (timeForThisMove < 0)
      timeForThisMove = 100; // If 0.5 seconds puts us below 0 use 0.1 seconds

    return timeForThisMove;
  }

  public boolean shouldStopDetected() {
    return shouldStop;
  }

  public boolean checkShouldStop() {
    if (!useFixedDepth) {
      nextTimeCheck--;
      if (nextTimeCheck == 0) {
        nextTimeCheck = TIME_CHECK_INTERVAL;
        if (!ponder && ((System.currentTimeMillis() - startTime) > timeForThisMove)) {
          shouldStop = true;
          return true;
        }

        try {
          if (Uci.reader.ready()) {
            String line = Uci.reader.readLine();
            if ("stop".equals(line)) {
              shouldStop = true;
              return true;
            }
            if ("ponderhit".equals(line)) {
              shouldStop = true;
              ponder = false;
            }
          }
        } catch (IOException e) {
          // Some error with the UCI connection, so better just stop
          shouldStop = true;
          return true;
        }

        return false;
      }
    }

    return false;
  }

  public boolean shouldRootStop(int eval, int currentDepth) {
    if (!ponder) {
      if (useFixedDepth) {
        if (currentDepth == depth || eval == -(MATE_VALUE + 1)) return true;
      } else if (movetime != 0) {
        if (System.currentTimeMillis() - startTime > timeForThisMove || eval == -(MATE_VALUE + 1))
          return true; // We have reached the allocated time or found a mate, and exit
      } else {
        // If we used 90% of the time so far, we break here
        if (System.currentTimeMillis() - startTime > timeForThisMove * 0.9
            || eval == -(MATE_VALUE + 1)) {
          return true; // We have reached the allocated time or found a mate, and exit
        }
      }
    }

    return false;
  }

  public void reportThinkingCurrentMove(int move, int searchedMoves) {
    if ((depth / PLY > 10
        && !shouldStop
        && timeForThisMove > 1000
        && System.currentTimeMillis() - startTime > timeForThisMove * 0.5)) {
      System.out.println(
          "info currmove " + BoardUtil.moveToNotation(move) + " currmovenumber " + searchedMoves);
    }
  }

  public void reportThinkingLine(int currentDepth, int totalNodesSearched, Search.Eval finalEval) {
    System.out.println(receiveThinking(currentDepth, totalNodesSearched, finalEval));
  }

  private String receiveThinking(int currentDepth, int totalNodesSearched, Search.Eval finalEval) {
    // Built the pv line
    String pvString = "";
    for (int i = 0; i < 128; i++) {
      if (i == 0) {
        pvString += (BoardUtil.moveToNotation(finalEval.line[0]) + " ");
      } else if (finalEval.line[i] == 0) break;
      else pvString += (BoardUtil.moveToNotation(finalEval.line[i]) + " ");
    }

    // Calculate the nodes per second, we need decimal values
    // to get the most accurate result.
    // If we have searched less than 1 second return the nodesSearched
    // since the numbers tend to get crazy at lower times

    long splitTime = (System.currentTimeMillis() - startTime);
    int nps;
    if ((splitTime / 1000) < 1) nps = totalNodesSearched;
    else {
      Double decimalTime = new Double(totalNodesSearched / (splitTime / 1000D));
      nps = decimalTime.intValue();
    }

    // Send the info to the uci interface
    if (finalEval.eval >= MATE_BOUND) {
      int rest = ((-MATE_VALUE) - finalEval.eval) % 2;
      int mateInN = (((-MATE_VALUE) - finalEval.eval) - rest) / 2 + rest;
      return "info score mate "
          + mateInN
          + " depth "
          + currentDepth
          + " nodes "
          + totalNodesSearched
          + " nps "
          + nps
          + " time "
          + splitTime
          + " pv "
          + pvString;
    } else if (finalEval.eval <= -MATE_BOUND) {
      int rest = ((-MATE_VALUE) + finalEval.eval) % 2;
      int mateInN = (((-MATE_VALUE) + finalEval.eval) - rest) / 2 + rest;
      return "info score mate "
          + -mateInN
          + " depth "
          + currentDepth
          + " nodes "
          + totalNodesSearched
          + " nps "
          + nps
          + " time "
          + splitTime
          + " pv "
          + pvString;
    }
    return "info score cp "
        + finalEval.eval
        + " depth "
        + currentDepth
        + " nodes "
        + totalNodesSearched
        + " nps "
        + nps
        + " time "
        + splitTime
        + " pv "
        + pvString;
  }
}
