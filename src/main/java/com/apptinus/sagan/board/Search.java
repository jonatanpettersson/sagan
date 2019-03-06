package com.apptinus.sagan.board;

import static com.apptinus.sagan.board.Board.WP;
import static com.apptinus.sagan.board.Move.BLACK;
import static com.apptinus.sagan.board.Move.SPECIAL_EP;
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

  private static int totalNodesSearched;
  private static int nodesSearched;
  private static int rootMovesCount;

  public static int currentDepth;

  public static SearchSupervisor supervisor;

  public static Eval search(Board board, SearchSupervisor searchSupervisor) {
    supervisor = searchSupervisor;

    // Inits
    finalEval = new Eval();
    searchMoves = new Move[64][256];
    for (int i = 0; i < searchMoves.length; i++)
      for (int j = 0; j < searchMoves[i].length; j++) searchMoves[i][j] = new Move();
    if (MoveGen.genMoves(board, searchMoves[0], 0) == 0) return finalEval;
    totalNodesSearched = 0;
    nodesSearched = 0;

    return iterativeSearch(board);
  }

  private static Eval iterativeSearch(Board board) {

    rootMovesCount = MoveGen.genMoves(board, searchMoves[0], 0);
    for (int i = 0; i < rootMovesCount; i++) {
      board.make(searchMoves[0][i].move);
      searchMoves[0][i].score = -alphaBeta(board, PLY, -INFINITY, INFINITY, 1);
      board.unmake(searchMoves[0][i].move);
    }

    int alpha = -INFINITY;
    int beta = INFINITY;
    int researchAlphaCount = 0;
    int researchBetaCount = 0;

    // Iterative deepening
    for (currentDepth = 1; currentDepth <= 64; ) {
      Move bestMove = alphaBetaRoot(board, currentDepth * PLY, alpha, beta);
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

      supervisor.reportThinkingLine(currentDepth, totalNodesSearched, finalEval);

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

  private static Move alphaBetaRoot(Board board, int depth, int alpha, int beta) {
    Move bestMove = new Move();

    int eval;
    int currentBestEval = -INFINITY;
    int searchedMoves = 0;

    sortMoves(searchMoves[0], depth / PLY == 1 ? 0 : 1, rootMovesCount);

    for (int i = 0; i < rootMovesCount; i++) {

      board.make(searchMoves[0][i].move);

      supervisor.reportThinkingCurrentMove(searchMoves[0][i].move, searchedMoves);

      eval = -alphaBeta(board, depth - PLY, -beta, -alpha, 1);

      searchedMoves++;

      board.unmake(searchMoves[0][i].move);

      totalNodesSearched += nodesSearched;
      nodesSearched = 0;

      if (eval > currentBestEval) {
        currentBestEval = eval;

        // If the evaluation is bigger than alpha (but less than beta) this is our new best move
        if (eval > alpha) {
          bestMove = searchMoves[0][i];
          alpha = eval;
        }
      }
    }

    // If there wasn't a legal move, it's either stalemate or checkmate
    if (searchedMoves == 0) {
      if (board.isInCheck(board.toMove)) bestMove.score = MATE_VALUE;
      else bestMove.score = DRAW_VALUE;
    }

    return bestMove;
  }

  private static int alphaBeta(Board board, int depth, int alpha, int beta, int ply) {
    int bestMove = 0;
    int eval;

    if (supervisor.checkShouldStop()) return 0;

    // If we're not in a root node and there's a threefold repetition detected, or the fifty move
    // rule is reached (in any node) return draw
    if (
    /*(depth / PLY != currentDepth
        && Settings.getInstance().getRepTable().repExists(board.zobristKey))
    || */ board.fPly >= 100) {
      return DRAW_VALUE;
    }

    boolean isInCheck = board.isInCheck(board.toMove);
    if (isInCheck) {
      depth += PLY;
    }

    // We've reached the deepest ply so start the quiescent search
    if (depth / PLY <= 0) {
      return quiesce(board, alpha, beta, ply);
    }

    if (supervisor.shouldStopDetected()) return 0;

    int bestEval = -INFINITY;
    int searchedMoves = 0;
    int currentMovesCount;
    int startIndex = 0;

    currentMovesCount = MoveGen.genMoves(board, searchMoves[ply], 0);

    // Go through the generated moves one by one
    for (int i = startIndex; i < currentMovesCount; i++) {

      if (searchMoves[ply][i].score == -10000) {
        continue; // This means the move has already been searched so skip it
      }

      board.make(searchMoves[ply][i].move); // Make the move on the board

      nodesSearched++;

      if (searchedMoves >= 1) {
        // PVS search
        eval = -alphaBeta(board, depth - PLY, -alpha - 1, -alpha, ply + 1);

        if (eval > alpha && eval < beta) {
          // Full depth search
          eval = -alphaBeta(board, depth - PLY, -beta, -alpha, ply + 1);
        }
      } else {
        eval = -alphaBeta(board, depth - PLY, -beta, -alpha, ply + 1);
      }

      searchedMoves++;

      board.unmake(searchMoves[ply][i].move); // Reset the board

      if (eval > bestEval) {

        if (eval >= beta) {
          searchMoves[ply][0].move = searchMoves[ply][i].move;
          return eval;
        }

        bestEval = eval;

        // If the evaluation is bigger than alpha (but less than beta) this is our new best move
        if (eval > alpha) {
          bestMove = searchMoves[ply][i].move;
          alpha = eval;
        }
      }
    } // End for loop

    // If there wasn't a legal move, it's either stalemate or checkmate
    if (searchedMoves == 0) {
      if (board.isInCheck(board.toMove)) {
        searchMoves[ply][0].move = 0;
        return (MATE_VALUE + ply);
      }
      return DRAW_VALUE;
    }

    searchMoves[ply][0].move = bestMove;

    return alpha;
  }

  private static int quiesce(Board board, int alpha, int beta, int ply) {
    int eval;

    if (supervisor.checkShouldStop()) return 0;

    int standPatEval = Evaluation.evaluate(board);
    if (standPatEval > alpha) {
      if (standPatEval >= beta) return beta;
      alpha = standPatEval;
    }

    if (supervisor.shouldStopDetected()) return 0;

    int currentMoveCount = MoveGen.genPseudoLegalCaptures(board, searchMoves[ply], 0);

    for (int i = 0; i < currentMoveCount; i++) {
      if (Move.special(searchMoves[ply][i].move) == SPECIAL_EP) {
        // If the move is en passant we know the captured pieces and capturer are both pawns, so
        // just take the pawn value on both (doesn't matter which color since the value is the same)
        searchMoves[ply][i].score =
            256 * Evaluation.PIECE_ABS_VALUE[WP] - Evaluation.PIECE_ABS_VALUE[WP];
      } else {
        searchMoves[ply][i].score =
            256 * Evaluation.PIECE_ABS_VALUE[board.board[Move.to(searchMoves[ply][i].move)]]
                - Evaluation.PIECE_ABS_VALUE[board.board[Move.from(searchMoves[ply][i].move)]];
      }
    }

    sortMoves(searchMoves[ply], 0, currentMoveCount);

    for (int i = 0; i < currentMoveCount; i++) {

      board.make(searchMoves[ply][i].move);

      // Since we're using pseudolegal captures, need to check if the side that just made a move
      // is now in check, if so the move is not legal so skip searching it
      if (board.isInCheck(board.toMove == WHITE ? BLACK : WHITE)) {
        board.unmake(searchMoves[ply][i].move);
        continue;
      }

      nodesSearched++;

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
