package com.apptinus.sagan.board;

public class Search {
//  public static final int TIME_CHECK_INTERVAL = 10000;
//  public static final int PLY = 16;
//  public static final int MATE_VALUE = -31999;
//  public static final int MATE_BOUND = 31000;
//  public static final int DRAW_VALUE = 0;
//  public static final int INFINITY = 32000;
//  public static final int EVALNOTFOUND = 32001;
//
//  private static Eval finalEval;
//  private static SearchMove[][] searchMoves;
//
//  private static int totalNodesSearched;
//  private static long startTime;
//  private static boolean stopSearch;
//  private static int timeForThisMove;
//  private static int nextTimeCheck;
//  private static boolean useFixedDepth;
//  private static boolean ponder;
//  private static int nodesSearched;
//  private static int rootMovesCount;
//
//  public static int currentDepth;
//
//  public static Eval search(
//      Board board, int depth, int timeLeft, int increment, int movetime, boolean isPonder) {
//    finalEval = new Eval();
//    searchMoves = new SearchMove[64][256];
//    for(int i = 0; i < searchMoves.length; i++)
//      for(int j = 0; j < searchMoves[i].length; j++)
//        searchMoves[i][j] = new SearchMove();
//
//    if (MoveGen.genMoves(board, searchMoves[0], 0) == 0) return finalEval;
//    totalNodesSearched = 0;
//    startTime = System.currentTimeMillis();
//    stopSearch = false;
//    if (movetime == 0) timeForThisMove = calculateTime(timeLeft, increment);
//    else timeForThisMove = movetime;
//    nextTimeCheck = TIME_CHECK_INTERVAL;
//    useFixedDepth = depth != 0;
//    ponder = isPonder;
//
//    nodesSearched = 0;
//
//    rootMovesCount = MoveGen.genMoves(board, searchMoves[0], 0);
//    for (int i = 0; i < rootMovesCount; i++) {
//      board.make(searchMoves[0][i]);
//      searchEvals[0][i] = -alphaBeta(board, 1 * PLY, -INFINITY, INFINITY, false, 1);
//      board.unmake(searchMoves[0][i]);
//    }
//
//    int alpha = -INFINITY;
//    int beta = INFINITY;
//    int researchAlphaCount = 0;
//    int researchBetaCount = 0;
//
//    // Iterative deepening
//    for (currentDepth = 1; currentDepth <= 64; ) {
//      Eval eval = alphaBetaRoot(board, currentDepth * PLY, alpha, beta, 0);
//
//      if (stopSearch) {
//        break;
//      }
//
//      if (eval.eval <= alpha) {
//        if (researchAlphaCount == 0) {
//          alpha -= 100;
//          researchAlphaCount++;
//        } else if (researchAlphaCount == 1) {
//          alpha -= 200;
//          researchAlphaCount++;
//        } else {
//          alpha = -INFINITY;
//          researchAlphaCount++;
//        }
//        continue;
//      } else if (eval.eval >= beta) {
//        if (researchBetaCount == 0) {
//          beta += 100;
//          researchBetaCount++;
//        } else if (researchBetaCount == 1) {
//          beta += 200;
//          researchBetaCount++;
//        } else {
//          beta = INFINITY;
//          researchBetaCount++;
//        }
//        continue;
//      } else if (eval.line[0] == 0) {
//        // No root move achieved > alpha so need to open the window
//        alpha = -INFINITY;
//        beta = INFINITY;
//        continue;
//      }
//
//      finalEval = new Eval(new int[128], eval.eval);
//      finalEval.line[0] = eval.line[0];
//
//      System.out.println(receiveThinking(startTime, finalEval)); // Get a thinking string and send
//
//      alpha = eval.eval - 60; // Get ready for a new search, with a new window
//      beta = eval.eval + 60; // The current window equals 3/10 of a pawn
//      researchAlphaCount = 0;
//      researchBetaCount = 0;
//
//      if (alpha <= -INFINITY) alpha = -INFINITY;
//      if (beta >= INFINITY) beta = INFINITY;
//
//      if (!ponder) {
//        if (useFixedDepth) {
//          if (currentDepth == depth || eval.eval == -(MATE_VALUE + 1)) break;
//        } else if (movetime != 0) {
//          if (System.currentTimeMillis() - startTime > timeForThisMove
//              || eval.eval == -(MATE_VALUE + 1))
//            break; // We have reached the allocated time or found a mate, and exit
//        } else {
//          // If we used 90% of the time so far, we break here
//          if (System.currentTimeMillis() - startTime > timeForThisMove * 0.9
//              || eval.eval == -(MATE_VALUE + 1)) {
//            break; // We have reached the allocated time or found a mate, and exit
//          }
//        }
//      }
//      currentDepth++; // Go to the next depth
//    }
//
//    return finalEval;
//  }
//
//  public static Eval alphaBetaRoot(Board board, int depth, int alpha, int beta, int ply) {
//    int bestMove = 0; // Initialize the best move
//    int bestEval = -INFINITY;
//
//    int eval = 0; // Initialize the eval
//    int currentBestEval = -INFINITY;
//    int searchedMoves =
//        0; // Number of moves that have been searched, more than 1 will enable pvs, 0 at the end of
//    // move generation will result in a draw/mate check
//
//    for (int i = 0; i < rootMovesCount; i++) {
//
//      board.make(searchMoves[ply][i]);
//
//      // Report what move we're looking at currently
//      if ((depth / PLY > 10
//          && !stopSearch
//          && timeForThisMove > 1000
//          && System.currentTimeMillis() - startTime > timeForThisMove * 0.5)) {
//        System.out.println(
//            "info currmove "
//                + Board.moveToNotation(searchMoves[ply][i])
//                + " currmovenumber "
//                + searchedMoves);
//      }
//
//      eval = -alphaBeta(board, depth - PLY, -beta, -alpha, true, ply + 1);
//
//      searchedMoves++;
//
//      board.unmake(searchMoves[ply][i]);
//
//      totalNodesSearched += nodesSearched;
//      nodesSearched = 0;
//
//      if (eval > currentBestEval) {
//        currentBestEval = eval;
//
//        // If the evaluation is bigger than alpha (but less than beta) this is our new best move
//        if (eval > alpha) {
//          bestMove = searchMoves[ply][i];
//          bestEval = eval;
//          alpha = eval;
//        }
//      }
//    }
//
//    // If there wasn't a legal move, it's either stalemate or checkmate
//    if (searchedMoves == 0) {
//      if (board.isInCheck(board.toMove)) bestMove = (MATE_VALUE + ply);
//      else bestMove = DRAW_VALUE;
//    }
//
//    return new Eval(new int[] {bestMove}, bestEval);
//  }
//
//  public static int alphaBeta(
//      Board board, int depth, int alpha, int beta, boolean allowNull, int ply) {
//    int bestMove = 0; // Initialize the best move
//    int eval = 0; // Initialize the eval
//
//    // Check if we've run out of time
//    if (!useFixedDepth) {
//      nextTimeCheck--;
//      if (nextTimeCheck == 0) {
//        nextTimeCheck = TIME_CHECK_INTERVAL;
//        if (shouldWeStop()) {
//          stopSearch = true;
//          return 0;
//        }
//      }
//    }
//
//    // If we're not in a root node and there's a threefold repetition detected, or the fifty move
//    // rule is reached (in any node) return draw
//    if (/*(depth / PLY != currentDepth
//            && Settings.getInstance().getRepTable().repExists(board.zobristKey))
//        || */board.fPly >= 100) {
//      return DRAW_VALUE;
//    }
//
//    // Check if the value in the hashtable was found at same or higher depth search
//    /*if (Settings.getInstance().getTranspositionTable().entryExists(board.zobristKey)
//        && Settings.getInstance().getTranspositionTable().getDepth(board.zobristKey)
//            >= depth / PLY) {
//      if (Settings.getInstance().getTranspositionTable().getFlag(board.zobristKey) == HASH_EXACT) {
//        // Since this is stored as an exact value we can use it right away
//        searchMoves[ply][0].move =
//            Settings.getInstance().getTranspositionTable().getMove(board.zobristKey);
//        return Settings.getInstance().getTranspositionTable().getEval(board.zobristKey);
//      } else if (Settings.getInstance().getTranspositionTable().getFlag(board.zobristKey)
//              == HASH_ALPHA
//          && Settings.getInstance().getTranspositionTable().getEval(board.zobristKey) <= alpha) {
//        // Since this was stored as an alpha value and it's less than the current alpha (i.e.
//        // greater than the beta since they've been swapped for this level, = opponent wouldn't go
//        // down this path) we can use cut off here
//        searchMoves[ply][0].move =
//            Settings.getInstance().getTranspositionTable().getMove(board.zobristKey);
//        return Settings.getInstance().getTranspositionTable().getEval(board.zobristKey);
//      } else if (Settings.getInstance().getTranspositionTable().getFlag(board.zobristKey)
//              == HASH_BETA
//          && Settings.getInstance().getTranspositionTable().getEval(board.zobristKey) >= beta) {
//        // Since this was stored as a beta and is greater than the current beta (i.e. less than
//        // alpha = there is atleast one better move already found) we can cut off here
//        searchMoves[ply][0].move =
//            Settings.getInstance().getTranspositionTable().getMove(board.zobristKey);
//        return Settings.getInstance().getTranspositionTable().getEval(board.zobristKey);
//      }
//    }*/
//
//    boolean isInCheck = board.isInCheck(board.toMove);
//    if (isInCheck) {
//      depth += PLY;
//    }
//
//    // We've reached the deepest ply so start the quiescent search
////    if (depth / PLY <= 0) {
////      eval = quiescentSearch(board, alpha, beta, ply);
////      return eval;
////    }
//
//    if (stopSearch) return 0; // Stop the search if it's been detected
//
////    int hashMove = Settings.getInstance().getTranspositionTable().getMove(board.zobristKey);
//
////    if (hashMove == 0 && beta - alpha > 1 && depth / PLY >= 5) {
////      alphaBeta(board, depth - 2 * PLY, alpha, beta, false, ply + 1);
////      hashMove = searchMoves[ply + 1][0].move;
////    }
//
////    if (hashMove != 0 && !board.validateHashMove(hashMove)) {
////      hashMove = 0;
////    }
//
////    Settings.getInstance().getRepTable().recordRep(board.zobristKey);
//
////    int generationState = GEN_HASH;
//    int tempMove;
//    int bestEval = -INFINITY;
////    int eval_type = HASH_ALPHA;
//    int searchedMoves =
//        0; // Number of moves that have been searched, more than 1 will enable pvs, 0 at the end of
//    // move generation will result in a draw/mate check
//    int currentMovesCount = 0;
//    int capturesCount = 0;
//    int startLosingCaptures = -1;
//    int startIndex = 0;
//    int killerOne = 0;
//    int killerTwo = 0;
//    int killerOneOld = 0;
//    int killerTwoOld = 0;
//
//    int materialEval = 0;
//
//    // Outer loop, this loops 6 times, one time for every part of move generation
//    while (generationState < GEN_END) {
//      // Generate part of the moves depending on how many times we have looped
//      switch (generationState) {
//        case GEN_HASH:
//
//          // If there is a hash move (which we got before or from iid), record it and give it a very
//          // high ordering value
//          if (hashMove != 0) {
//            searchMoves[ply][currentMovesCount].move = hashMove;
//            searchMoves[ply][currentMovesCount].score = 10000;
//            currentMovesCount++;
//          }
//          break;
//        case GEN_CAPS:
//          // Generate all pseudo legal captures on the board
//          currentMovesCount = board.gen_caps(searchMoves[ply], 0);
//          capturesCount = currentMovesCount;
//
//          // Go through the moves and assign the ordering values according to see
//          for (int i = 0; i < currentMovesCount; i++) {
//            tempMove = searchMoves[ply][i].move;
//
//            // If the capture is the same as the hashMove, we have already searched it
//            // so give it a very low value, it will be skipped when run into below.
//            if (tempMove == hashMove) {
//              searchMoves[ply][i].score = -10000;
//            } else
//              searchMoves[ply][i].score =
//                  See.see(board, tempMove); // The move is not a duplicate so give it a see value
//          }
//
//          // We now have ordering values for all the captures so order them
//          sortMoves(searchMoves[ply], 0, currentMovesCount);
//
//          int index = 0;
//          while (index < capturesCount && startLosingCaptures == -1) {
//            if (searchMoves[ply][index].score < 0) {
//              startLosingCaptures = index;
//              break;
//            }
//            index++;
//          }
//
//          currentMovesCount = index;
//
//          break;
//        case GEN_KILLERS:
//          currentMovesCount = capturesCount;
//          if (killers.getPrimary(ply) != hashMove
//              && board.validateKiller(killers.getPrimary(ply))) {
//            killerOne = killers.getPrimary(ply);
//            searchMoves[ply][currentMovesCount].move = killers.getPrimary(ply);
//            searchMoves[ply][currentMovesCount].score = 5000;
//            currentMovesCount++;
//          }
//          if (killers.getSecondary(ply) != hashMove
//              && board.validateKiller(killers.getSecondary(ply))) {
//            killerTwo = killers.getSecondary(ply);
//            searchMoves[ply][currentMovesCount].move = killers.getSecondary(ply);
//            searchMoves[ply][currentMovesCount].score = 4000;
//            currentMovesCount++;
//          }
//          startIndex = capturesCount;
//          break;
//        case GEN_NONCAPS:
//          // Generate all pseudo legal captures on the board
//          currentMovesCount = capturesCount;
//          currentMovesCount += board.gen_noncaps(searchMoves[ply], currentMovesCount);
//
//          // Go through the moves and assign the ordering values
//          for (int i = capturesCount; i < currentMovesCount; i++) {
//            tempMove = searchMoves[ply][i].move;
//
//            if (tempMove == hashMove
//                || tempMove == killerOne
//                || tempMove == killerTwo
//                || tempMove == killerOneOld
//                || tempMove == killerTwoOld) {
//              searchMoves[ply][i].score = -10000; // Move is already searched so skip it
//            } else {
//              if (historyValues[Move.fromIndex(searchMoves[ply][i].move)][
//                      Move.toIndex(searchMoves[ply][i].move)]
//                  != 0)
//                searchMoves[ply][i].score =
//                    1000
//                        * historyBetaHits[Move.fromIndex(searchMoves[ply][i].move)][
//                            Move.toIndex(searchMoves[ply][i].move)]
//                        / historyValues[Move.fromIndex(searchMoves[ply][i].move)][
//                            Move.toIndex(searchMoves[ply][i].move)];
//            }
//          }
//
//          // Sort the non-captures
//          sortMoves(searchMoves[ply], capturesCount, currentMovesCount);
//
//          startIndex = capturesCount;
//
//          break;
//        case GEN_LOSINGCAPS:
//          if (startLosingCaptures != -1) {
//            currentMovesCount = capturesCount;
//            startIndex = startLosingCaptures;
//          } else {
//            startIndex = 0;
//            currentMovesCount = 0;
//          }
//          break;
//      }
//
//      // Go through the generated moves one by one
//      for (int i = startIndex; i < currentMovesCount; i++) {
//
//        if (searchMoves[ply][i].score == -10000) {
//          continue; // This means the move has already been searched so skip it
//        }
//
//        if (Move.pieceMoving(searchMoves[ply][i].move) == W_PAWN
//            && Board.rank(Move.toIndex(searchMoves[ply][i].move)) == 6) threat = true;
//        else if (Move.pieceMoving(searchMoves[ply][i].move) == B_PAWN
//            && Board.rank(Move.toIndex(searchMoves[ply][i].move)) == 1) threat = true;
//
//        // Futility pruning, if we decided that we could not reach alpha
//        // above, see if the move is a checking move, if it isn't just
//        // set the score to whatever the eval was and continue with the next move
//        // Do this before makeMove - if we are going to prune there is no use making the move
//        // Do not prune the 1st move - we need to know
//        // if we have any legal moves or are stalemated.
//        if (searchedMoves >= 1 && fprune && !threat && !board.isInCheck()) {
//          // If the move was a capture we add the value of the captured piece
//          // if the move was not a capture this will add 0 (leaving materialEval unchanged)
//          int gain =
//              Math.abs(Evaluation.PIECE_VALUE_ARRAY[Move.capture(searchMoves[ply][i].move) + 7]);
//          int moveType = Move.moveType(searchMoves[ply][i].move);
//          if (moveType >= PROMOTION_QUEEN) {
//            gain += Evaluation.PIECE_VALUE_ARRAY[moveType + 5];
//          }
//          if ((materialEval + gain + fmargin) <= alpha) {
//            if (materialEval + gain > bestEval) bestEval = materialEval + gain;
//            continue;
//          }
//        }
//
//        board.makeMove(searchMoves[ply][i].move); // Make the move on the board
//        nodesSearched++;
//
//        // Make sure we don't leave the king in check when making this move
//        // If black to move and black is attacking the white king, the move made above was illegal
//        // so skip it
//        if (board.toMove == BLACK_TO_MOVE && board.isAttacked(board.w_king.pieces[0], BLACK)) {
//          board.unmakeMove(searchMoves[ply][i].move);
//          continue;
//        } else if (board.toMove == WHITE_TO_MOVE
//            && board.isAttacked(board.b_king.pieces[0], WHITE)) {
//          board.unmakeMove(searchMoves[ply][i].move);
//          continue;
//        }
//
//        historyValues[Move.fromIndex(searchMoves[ply][i].move)][
//                Move.toIndex(searchMoves[ply][i].move)] +=
//            depth;
//
//        if (searchedMoves >= 1) {
//          // Late move reduction
//          if (searchedMoves > 3
//              && generationState == GEN_NONCAPS
//              && depth > 3 * PLY
//              && !threat
//              && !board.isInCheck()) {
//            eval = -alphaBeta(board, depth - (2 * PLY), -alpha - 1, -alpha, true, ply + 1);
//          } else {
//            // PVS search
//            eval = -alphaBeta(board, depth - PLY, -alpha - 1, -alpha, true, ply + 1);
//          }
//
//          if (eval > alpha && eval < beta) {
//            // Full depth search
//            eval = -alphaBeta(board, depth - PLY, -beta, -alpha, true, ply + 1);
//          }
//        } else {
//          eval = -alphaBeta(board, depth - PLY, -beta, -alpha, true, ply + 1);
//        }
//
//        searchedMoves++;
//
//        board.unmakeMove(searchMoves[ply][i].move); // Reset the board
//
//        if (eval > bestEval) {
//
//          if (eval >= beta) {
//            historyBetaHits[Move.fromIndex(searchMoves[ply][i].move)][
//                    Move.toIndex(searchMoves[ply][i].move)] +=
//                depth;
//            // If the evaluation is bigger than beta, we cutoff here (since there is another move
//            // the opponent will choose so this will never happen)
//            if (!stopSearch)
//              Settings.getInstance()
//                  .getTranspositionTable()
//                  .record(board.zobristKey, depth / PLY, HASH_BETA, eval, searchMoves[ply][i].move);
//            searchMoves[ply][0].move = searchMoves[ply][i].move;
//            // Remove this from the rep table since it didn't happen
//            Settings.getInstance().getRepTable().removeRep(board.zobristKey);
//
//            // Add this move as a killer since it caused a cutoff
//            // (do not add captures as killers since they're searched early anyway)
//            if (Move.capture(searchMoves[ply][i].move) == 0) {
//              killers.addKiller(searchMoves[ply][i], ply);
//            }
//
//            return eval;
//          }
//
//          bestEval = eval;
//
//          // If the evaluation is bigger than alpha (but less than beta) this is our new best move
//          if (eval > alpha) {
//            eval_type = HASH_EXACT;
//            bestMove = searchMoves[ply][i].move;
//            alpha = eval;
//          }
//        }
//      } // End for loop
//
//      generationState++; // We have gone through all the generated moves from the last state so move
//      // to the next
//    } // End while loop
//
//    // If there wasn't a legal move, it's either stalemate or checkmate
//    if (searchedMoves == 0) {
//      if (board.isInCheck()) {
//        // Don't count this position toward repetitions, since the game is over anyway
//        Settings.getInstance().getRepTable().removeRep(board.zobristKey);
//        searchMoves[ply][0].move = 0;
//        return (MATE_VALUE + ply);
//      }
//      return DRAW_VALUE;
//    }
//
//    if (!stopSearch)
//      Settings.getInstance()
//          .getTranspositionTable()
//          .record(board.zobristKey, depth / PLY, eval_type, bestEval, bestMove);
//    searchMoves[ply][0].move = bestMove;
//
//    Settings.getInstance().getRepTable().removeRep(board.zobristKey);
//
//    return alpha;
//  } // END alphaBeta
//
//  private static String receiveThinking(long time, Eval finalEval) {
//    // Built the pv line
//    String pvString = "";
//    for (int i = 0; i < 128; i++) {
//      if (i == 0) {
//        pvString += (Move.inputNotation(finalEval.line[0]) + " ");
//      } else if (finalEval.line[i] == 0) break;
//      else pvString += (Move.inputNotation(finalEval.line[i]) + " ");
//    }
//
//    // Calculate the nodes per second, we need decimal values
//    // to get the most accurate result.
//    // If we have searched less than 1 second return the nodesSearched
//    // since the numbers tend to get crazy at lower times
//
//    long splitTime = (System.currentTimeMillis() - time);
//    int nps;
//    if ((splitTime / 1000) < 1) nps = totalNodesSearched;
//    else {
//      Double decimalTime = new Double(totalNodesSearched / (splitTime / 1000D));
//      nps = decimalTime.intValue();
//    }
//
//    // Send the info to the uci interface
//    if (finalEval.eval >= MATE_BOUND) {
//      int rest = ((-MATE_VALUE) - finalEval.eval) % 2;
//      int mateInN = (((-MATE_VALUE) - finalEval.eval) - rest) / 2 + rest;
//      return "info score mate "
//          + mateInN
//          + " depth "
//          + currentDepth
//          + " nodes "
//          + totalNodesSearched
//          + " nps "
//          + nps
//          + " time "
//          + splitTime
//          + " pv "
//          + pvString;
//    } else if (finalEval.eval <= -MATE_BOUND) {
//      int rest = ((-MATE_VALUE) + finalEval.eval) % 2;
//      int mateInN = (((-MATE_VALUE) + finalEval.eval) - rest) / 2 + rest;
//      return "info score mate "
//          + -mateInN
//          + " depth "
//          + currentDepth
//          + " nodes "
//          + totalNodesSearched
//          + " nps "
//          + nps
//          + " time "
//          + splitTime
//          + " pv "
//          + pvString;
//    }
//    return "info score cp "
//        + finalEval.eval
//        + " depth "
//        + currentDepth
//        + " nodes "
//        + totalNodesSearched
//        + " nps "
//        + nps
//        + " time "
//        + splitTime
//        + " pv "
//        + pvString;
//  }
//
//  public static boolean shouldWeStop() {
//    if (!ponder && ((System.currentTimeMillis() - startTime) > timeForThisMove)) return true;
//
//    if (Uci.reader.ready()) {
//      String line = Uci.reader.readLine();
//      if ("stop".equals(line)) return true;
//      if ("ponderhit".equals(line)) {
//        ponder = false;
//      }
//    }
//
//    return false;
//  }
//
//  private static int calculateTime(int timeLeft, int increment) {
//    int timeForThisMove; // The maximum time we are allowed to use on this move
//    int percent =
//        40; // How many percent of the time we will use; percent=20 -> 5%, percent=40 -> 2.5% etc.
//    // (formula is 100/percent, i.e. 100/40 =2.5)
//
//    timeForThisMove = timeLeft / percent + (increment); // Use the percent + increment for the move
//    if (timeForThisMove >= timeLeft)
//      timeForThisMove =
//          timeLeft
//              - 500; // If the increment puts us above the total time left use the timeleft - 0.5
//    // seconds
//    if (timeForThisMove < 0)
//      timeForThisMove = 100; // If 0.5 seconds puts us below 0 use 0.1 seconds
//
//    return timeForThisMove;
//  }
//
//  public static class Eval {
//    public int[] line;
//    public int eval;
//
//    public Eval() {
//      this.line = new int[128];
//      this.eval = 0;
//    }
//
//    public Eval(int[] line, int eval) {
//      this.line = line;
//      this.eval = eval;
//    }
//  }
//
//  public static class SearchMove {
//    public int move;
//    public int score;
//  }
}
