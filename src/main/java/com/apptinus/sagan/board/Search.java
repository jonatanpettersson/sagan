package com.apptinus.sagan.board;

import static com.apptinus.sagan.board.Move.BLACK;
import static com.apptinus.sagan.board.Move.SPECIAL_PROMO;
import static com.apptinus.sagan.board.Move.WHITE;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class Search {
  private static Logger logger = (Logger) LoggerFactory.getLogger(Search.class);

  public static final int PLY = 16;
  public static final int MATE_VALUE = -31999;
  public static final int MATE_BOUND = 31000;
  public static final int DRAW_VALUE = 0;
  public static final int INFINITY = 32000;
  public static final int EVALNOTFOUND = 32001;

  private static Eval finalEval;
  private static Move[][] searchMoves;

  public static int currentDepth;

  public static SearchSupervisor supervisor;

  public static Eval search(Board board, SearchSupervisor searchSupervisor) {
    supervisor = searchSupervisor;

    // Inits
    finalEval = new Eval();
    searchMoves = new Move[64][256];
    for (int i = 0; i < searchMoves.length; i++)
      for (int j = 0; j < searchMoves[i].length; j++) searchMoves[i][j] = new Move();
    if (MoveGen.genAllLegalMoves(board, searchMoves[0], 0) == 0) return finalEval;

    return iterativeSearch(board);
  }

  private static Eval iterativeSearch(Board board) {

    int rootMovesCount = MoveGen.genAllLegalMoves(board, searchMoves[0], 0);
    for (int i = 0; i < rootMovesCount; i++) {
      board.make(searchMoves[0][i].move);
      searchMoves[0][i].score = -alphaBeta(board, PLY, -INFINITY, INFINITY, false, 1);
      board.unmake(searchMoves[0][i].move);
    }

    int alpha = -INFINITY;
    int beta = INFINITY;
    int researchAlphaCount = 0;
    int researchBetaCount = 0;

    // Iterative deepening
    for (currentDepth = 1; currentDepth < 64; ) {
      Move bestMove = alphaBetaRoot(board, currentDepth * PLY, alpha, beta, rootMovesCount);
      int eval = bestMove.score;

      if (supervisor.checkShouldStop()) {
        break;
      }

      if (eval <= alpha) {
        if (researchAlphaCount == 0) {
          alpha -= 100;
          researchAlphaCount++;
        } else if (researchAlphaCount == 1) {
          alpha -= 200;
          researchAlphaCount++;
        } else {
          alpha = -INFINITY;
          researchAlphaCount++;
        }
        continue;
      } else if (eval >= beta) {
        if (researchBetaCount == 0) {
          beta += 100;
          researchBetaCount++;
        } else if (researchBetaCount == 1) {
          beta += 200;
          researchBetaCount++;
        } else {
          beta = INFINITY;
          researchBetaCount++;
        }
        continue;
      } else if (bestMove.move == 0) {
        // No root move achieved > alpha so need to open the window
        alpha = -INFINITY;
        beta = INFINITY;
        continue;
      }

      finalEval = new Eval(new int[128], eval);
      finalEval.line[0] = bestMove.move;

      supervisor.reportThinkingLine(currentDepth, finalEval);

      alpha = eval - 60; // Get ready for a new search, with a new window
      beta = eval + 60; // The current window equals 3/10 of a pawn
      researchAlphaCount = 0;
      researchBetaCount = 0;

      if (alpha <= -INFINITY) alpha = -INFINITY;
      if (beta >= INFINITY) beta = INFINITY;

      if (supervisor.shouldRootStop(eval, currentDepth)) {
        break;
      }
      currentDepth++; // Go to the next depth
    }

    return finalEval;
  }

  private static Move alphaBetaRoot(
      Board board, int depth, int alpha, int beta, int rootMovesCount) {
    Move bestMove = new Move();

    int eval;
    int evalType = Tt.UPPER_BOUND;
    int currentBestEval = -INFINITY;
    int searchedMoves = 0;

    sortMoves(searchMoves[0], depth / PLY == 1 ? 0 : 1, rootMovesCount);

    for (int i = 0; i < rootMovesCount; i++) {

      board.make(searchMoves[0][i].move);

      supervisor.reportThinkingCurrentMove(searchMoves[0][i].move, searchedMoves);

      eval = -alphaBeta(board, depth - PLY, -beta, -alpha, false, 1);

      searchedMoves++;

      board.unmake(searchMoves[0][i].move);

      supervisor.resetCurrentNodesSearched();

      if (eval > currentBestEval) {
        currentBestEval = eval;

        // If the evaluation is bigger than alpha (but less than beta) this is our new best move
        if (eval > alpha) {
          bestMove = searchMoves[0][i];
          alpha = eval;
          evalType = Tt.EXACT;
        }
      }
    }

    // If there wasn't a legal move, it's either stalemate or checkmate
    if (searchedMoves == 0) {
      if (board.isInCheck(board.toMove)) bestMove.score = MATE_VALUE;
      else bestMove.score = DRAW_VALUE;
    }

    board.tt.set(board.zobrist, currentDepth, evalType, currentBestEval, bestMove.move);

    return bestMove;
  }

  private static int alphaBeta(
      Board board, int depth, int alpha, int beta, boolean allowNull, int ply) {
    int bestMove = 0;
    int eval;

    if (supervisor.checkShouldStop()) return 0;
    if (depth / PLY != currentDepth && board.isDraw()) return DRAW_VALUE;

    if (board.tt.probe(board.zobrist)) {
      if (board.tt.getProbedDepth() >= depth / PLY) {
        if ((board.tt.getProbedEvalType() == Tt.EXACT)
            || (board.tt.getProbedEvalType() == Tt.UPPER_BOUND && board.tt.getProbedEval() <= alpha)
            || (board.tt.getProbedEvalType() == Tt.LOWER_BOUND
                && board.tt.getProbedEval() >= beta)) {
          searchMoves[ply][0].move = board.tt.getProbedMove();
          return board.tt.getProbedEval();
        }
      }
    }

    boolean isInCheck = board.isInCheck(board.toMove);
    if (isInCheck) {
      depth += PLY;
    }

    // We've reached the deepest ply so start the quiescent search
    if (depth / PLY <= 0) {
      return quiesce(board, alpha, beta, ply);
    }

    boolean threat = false;
    if (beta - alpha <= 1
        && // non-PV node
        allowNull
        && // Don't do two null moves in a row
        !isInCheck
        && depth > PLY
        && Evaluation.isPawnEnding(board)) {

      int R = (depth > 6 * PLY) ? PLY * 3 : PLY * 2;

      board.nullmoveToggle();
      eval = -alphaBeta(board, depth - PLY - R, -beta, -beta + 1, false, ply + 1);
      board.nullmoveToggle();

      if (eval >= beta) {
        return eval;
      }
      if (eval <= -MATE_BOUND) {
        threat = true;
      }
    }

    if (supervisor.shouldStopDetected()) return 0;

    // ProbedMove will be set since we probed above when looking for cutoffs (make sure this is
    // still done if moving stuff around)
    int hashMove = board.tt.getProbedMove();

    if (hashMove == 0 && beta - alpha > 1 && depth / PLY >= 5) {
      alphaBeta(board, depth - 2 * PLY, alpha, beta, false, ply + 1);
      hashMove = searchMoves[ply + 1][0].move;
    }

    int evalType = Tt.UPPER_BOUND;
    int bestEval = -INFINITY;
    int searchedMoves = 0;
    int firstEmptyMoveIndex = 0;
    int startIndex = 0;

    for (int generationState = 0; generationState <= 6; generationState++) {
      // Generate moves depending on state
      if (generationState == 0) {
        // Hash move
        if (hashMove == 0) continue;
        searchMoves[ply][0].move = hashMove;
      } else if (generationState == 1) {
        // Queen promotions
        startIndex = firstEmptyMoveIndex;
        firstEmptyMoveIndex =
            MoveGen.genPseudoLegalQueenPromotions(board, searchMoves[ply], firstEmptyMoveIndex);
      } else if (generationState == 3) {
        // Winning captures
        startIndex = firstEmptyMoveIndex;
        firstEmptyMoveIndex =
            MoveGen.genPseudoLegalCaptures(board, searchMoves[ply], firstEmptyMoveIndex);

        for (int i = startIndex; i < firstEmptyMoveIndex; i++) {
          Move thisMove = searchMoves[ply][i];

          if (thisMove.move == hashMove) {
            thisMove.score = -10000;
          } else {

          }
        }

      } else if (generationState == 4) {
        // Other promotions
        startIndex = firstEmptyMoveIndex;
        firstEmptyMoveIndex =
            MoveGen.genPseudoLegalOtherPromotions(board, searchMoves[ply], firstEmptyMoveIndex);
      } else if (generationState == 5) {
        // Non-captures
        startIndex = firstEmptyMoveIndex;
        firstEmptyMoveIndex =
            MoveGen.genPseudoLegalNonCaptures(board, searchMoves[ply], firstEmptyMoveIndex);
      } else if (generationState == 6) {
        // Losing captures
        startIndex = firstEmptyMoveIndex + 1;
      }

      // Go through the generated moves one by one
      for (int i = startIndex; i < firstEmptyMoveIndex; i++) {

        if (searchMoves[ply][i].score == -10000) {
          continue; // This means the move has already been searched so skip it
        }

        board.make(searchMoves[ply][i].move); // Make the move on the board
        // Since we're using pseudolegal moves, need to check if the side that just made a move
        // is now in check, if so the move is not legal so skip searching it
        if (board.isInCheck(board.toMove == WHITE ? BLACK : WHITE)) {
          board.unmake(searchMoves[ply][i].move);
          continue;
        }

        supervisor.incrCurrentNodesSearched();

        if (searchedMoves >= 1) {
          // PVS search
          eval = -alphaBeta(board, depth - PLY, -alpha - 1, -alpha, true, ply + 1);

          if (eval > alpha && eval < beta) {
            // Full depth search
            eval = -alphaBeta(board, depth - PLY, -beta, -alpha, true, ply + 1);
          }
        } else {
          eval = -alphaBeta(board, depth - PLY, -beta, -alpha, true, ply + 1);
        }

        searchedMoves++;

        board.unmake(searchMoves[ply][i].move); // Reset the board

        if (eval > bestEval) {

          if (eval >= beta) {
            searchMoves[ply][0].move = searchMoves[ply][i].move;
            board.tt.set(
                board.zobrist, depth / PLY, Tt.LOWER_BOUND, eval, searchMoves[ply][i].move);
            return eval;
          }

          bestEval = eval;

          // If the evaluation is bigger than alpha (but less than beta) this is our new best move
          if (eval > alpha) {
            bestMove = searchMoves[ply][i].move;
            evalType = Tt.EXACT;
            alpha = eval;
          }
        }
      }
    }

    // If there wasn't a legal move, it's either stalemate or checkmate
    if (searchedMoves == 0) {
      if (board.isInCheck(board.toMove)) {
        searchMoves[ply][0].move = 0;
        return (MATE_VALUE + ply);
      }
      return DRAW_VALUE;
    }

    searchMoves[ply][0].move = bestMove;
    board.tt.set(board.zobrist, depth / PLY, evalType, bestEval, bestMove);

    return alpha;
  }

  private static int quiesce(Board board, int alpha, int beta, int ply) {
    int eval;

    if (supervisor.checkShouldStop()) return 0;
    if (board.isDraw()) return DRAW_VALUE;

    int standPatEval = Evaluation.evaluate(board);
    if (standPatEval > alpha) {
      if (standPatEval >= beta) return beta;
      alpha = standPatEval;
    }

    if (supervisor.shouldStopDetected()) return 0;

    int currentMoveIndex = MoveGen.genPseudoLegalCaptures(board, searchMoves[ply], 0);
    currentMoveIndex =
        MoveGen.genPseudoLegalQueenPromotions(board, searchMoves[ply], currentMoveIndex);

    for (int i = 0; i < currentMoveIndex; i++) {
      if (Move.special(searchMoves[ply][i].move) == SPECIAL_PROMO) {
        // High value, order queening first
        searchMoves[ply][i].score = 250000;
      } else {
        searchMoves[ply][i].score = Evaluation.getMvvLva(board, searchMoves[ply][i].move);
      }
    }

    sortMoves(searchMoves[ply], 0, currentMoveIndex);

    for (int i = 0; i < currentMoveIndex; i++) {

      board.make(searchMoves[ply][i].move);

      // Since we're using pseudolegal moves, need to check if the side that just made a move
      // is now in check, if so the move is not legal so skip searching it
      if (board.isInCheck(board.toMove == WHITE ? BLACK : WHITE)) {
        board.unmake(searchMoves[ply][i].move);
        continue;
      }

      supervisor.incrCurrentNodesSearched();

      eval = -quiesce(board, -beta, -alpha, ply + 1);
      board.unmake(searchMoves[ply][i].move);

      if (eval > alpha) {
        if (eval >= beta) return beta;

        alpha = eval;
      }
    }

    return alpha;
  }

  private static void sortMoves(Move[] moves, int from, int to) {
    for (int i = from + 1; i < to; i++) {
      int j = i;
      Move B = moves[i];
      while ((j > from) && (moves[j - 1].score < B.score)) {
        moves[j] = moves[j - 1];
        j--;
      }
      moves[j] = B;
    }
  }

  public static class Eval {
    public int[] line;
    public int eval;

    public Eval() {
      this.line = new int[128];
      this.eval = 0;
    }

    public Eval(int[] line, int eval) {
      this.line = line;
      this.eval = eval;
    }
  }
}
