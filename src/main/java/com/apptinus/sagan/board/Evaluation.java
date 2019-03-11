package com.apptinus.sagan.board;

import static com.apptinus.sagan.board.Board.BB;
import static com.apptinus.sagan.board.Board.BK;
import static com.apptinus.sagan.board.Board.BN;
import static com.apptinus.sagan.board.Board.BP;
import static com.apptinus.sagan.board.Board.BQ;
import static com.apptinus.sagan.board.Board.BR;
import static com.apptinus.sagan.board.Board.WB;
import static com.apptinus.sagan.board.Board.WK;
import static com.apptinus.sagan.board.Board.WN;
import static com.apptinus.sagan.board.Board.WP;
import static com.apptinus.sagan.board.Board.WQ;
import static com.apptinus.sagan.board.Board.WR;
import static com.apptinus.sagan.board.Move.BLACK;
import static com.apptinus.sagan.board.Move.SPECIAL_EP;
import static com.apptinus.sagan.board.Move.WHITE;
import static com.apptinus.sagan.util.Bitops.next;
import static com.apptinus.sagan.util.Bitops.unset;

import com.apptinus.sagan.util.Bitops;

public class Evaluation {
  public static final int[] PIECE_VALUE = {
    0, 900, 500, 300, 300, 100, 0, -900, -500, -300, -300, -100
  };

  public static final int[] PIECE_ABS_VALUE = {
    0, 900, 500, 300, 300, 100, 0, 900, 500, 300, 300, 100
  };

  public static int evaluate(Board board) {
    int openingEval = 0;
    int endingEval = 0;

    // For each piece typ
    for (int i = 0; i <= 11; i++) {
      // For each piece
      int pieceSq;
      long pieces = board.pieces[i];
      while ((pieceSq = next(pieces)) >= 0) {
        // Piece evaluation
        openingEval += PIECE_VALUE[i];
        endingEval += PIECE_VALUE[i];

        // PSQT
        openingEval += PSQT[i][pieceSq][0];
        endingEval += PSQT[i][pieceSq][1];

        // Remove the piece to move on to next
        pieces = unset(pieces, pieceSq);
      }
    }

    // Taper eval based on phase of game (taken from chessprogramming.org/Tapered_Eval
    int phase = phase(board);
    int finalEval = ((openingEval * (256 - phase)) + (endingEval * phase)) / 256;

    return board.toMove == WHITE ? finalEval : -1 * finalEval;
  }

  public static final int phase(Board board) {
    int pawnPhase = 0;
    int knightPhase = 1;
    int bishopPhase = 1;
    int rookPhase = 2;
    int queenPhase = 4;
    int totalPhase =
        pawnPhase * 16 + knightPhase * 4 + bishopPhase * 4 + rookPhase * 4 + queenPhase * 2;

    int phase = totalPhase;
    phase -= Bitops.population(board.pieces[WP]) * pawnPhase;
    phase -= Bitops.population(board.pieces[BP]) * pawnPhase;

    phase -= Bitops.population(board.pieces[WN]) * knightPhase;
    phase -= Bitops.population(board.pieces[BN]) * knightPhase;

    phase -= Bitops.population(board.pieces[WB]) * bishopPhase;
    phase -= Bitops.population(board.pieces[BB]) * bishopPhase;

    phase -= Bitops.population(board.pieces[WR]) * rookPhase;
    phase -= Bitops.population(board.pieces[BR]) * rookPhase;

    phase -= Bitops.population(board.pieces[WQ]) * queenPhase;
    phase -= Bitops.population(board.pieces[BQ]) * queenPhase;

    phase = (phase * 256 + (totalPhase / 2)) / totalPhase;

    return phase;
  }

  public static final boolean sideCantWin(Board board, int side) {
    if (side == WHITE) {
      int bishops = Bitops.population(board.pieces[WB]);
      int knights = Bitops.population(board.pieces[WN]);
      if (Bitops.population(board.pieces[WP]) != 0
          || Bitops.population(board.pieces[WR]) != 0
          || Bitops.population(board.pieces[WQ]) != 0
          || bishops > 1
          || knights > 2) {
        return false;
      }
      if ((bishops > 0 && knights > 0)) {
        return false;
      }

      return true;
    } else {
      int bishops = Bitops.population(board.pieces[BB]);
      int knights = Bitops.population(board.pieces[BN]);
      if (Bitops.population(board.pieces[BP]) != 0
          || Bitops.population(board.pieces[BR]) != 0
          || Bitops.population(board.pieces[BQ]) != 0
          || bishops > 1
          || knights > 2) {
        return false;
      }
      if ((bishops > 0 && knights > 0)) {
        return false;
      }

      return true;
    }
  }

  public static boolean isPawnEnding(Board board) {
    return Bitops.population(board.pieces[WN]) == 0
        && Bitops.population(board.pieces[WB]) == 0
        && Bitops.population(board.pieces[WR]) == 0
        && Bitops.population(board.pieces[WQ]) == 0
        && Bitops.population(board.pieces[BN]) == 0
        && Bitops.population(board.pieces[BB]) == 0
        && Bitops.population(board.pieces[BR]) == 0
        && Bitops.population(board.pieces[BQ]) == 0;
  }

  public static String getEvalInfo(Board board) {
    String evalString = "";
    evalString += evaluate(board);
    return evalString;
  }

  public static int getMvvLva(final Board board, final int move) {
    if (Move.special(move) == SPECIAL_EP) {
      // If the move is en passant we know the captured pieces and capturer are both pawns, so
      // just take the pawn value on both (doesn't matter which color since the value is the same)
      return 256 * Evaluation.PIECE_ABS_VALUE[WP] - Evaluation.PIECE_ABS_VALUE[WP];
    } else {
      return 256 * Evaluation.PIECE_ABS_VALUE[board.board[Move.to(move)]]
          - Evaluation.PIECE_ABS_VALUE[board.board[Move.from(move)]];
    }
  }

  public static int getSee(final Board board, final int move) {
    int from = Move.from(move);
    int to = Move.to(move);

    // Get a bitboard with all pieces that can attack the to square (including xray)
    long potentialBlockers = board.allPieces;
    long attackers = board.attackers(to, potentialBlockers, 0xffffffffffffffffL);
    long usedAttackers = 0L;

    int[] score = new int[32];

    if (Move.special(move) == SPECIAL_EP) {
      score[0] = PIECE_ABS_VALUE[WP];
    } else {
      score[0] = PIECE_ABS_VALUE[board.board[to]];
    }

    int toMove = board.toMove;

    int depth = 0;
    do {
      depth++;
      attackers ^= Bitops.setBit(from);
      usedAttackers |= Bitops.setBit(from);
      toMove = toMove == WHITE ? BLACK : WHITE;
      score[depth] = PIECE_ABS_VALUE[board.board[from]] - score[depth - 1];

      // Add potential xray piece behind attacker
      if ((Bitops.setBit(from) & (MoveGen.rankMask(to) ^ MoveGen.fileMask(to))) != 0L) {
        attackers |=
            board.attackers(
                to,
                board.allPieces & ~usedAttackers,
                (MoveGen.rankMask(to) ^ MoveGen.fileMask(to))
                    & ~usedAttackers
                    & (board.pieces[WR] | board.pieces[BR] | board.pieces[BQ] | board.pieces[WQ]));
      } else if ((Bitops.setBit(from) & (MoveGen.diagonalMask(to) ^ MoveGen.antiDiagMask(to)))
          != 0L) {
        attackers |=
            board.attackers(
                to,
                board.allPieces & ~usedAttackers,
                (MoveGen.diagonalMask(to) ^ MoveGen.antiDiagMask(to))
                    & ~usedAttackers
                    & (board.pieces[WB] | board.pieces[BB] | board.pieces[WQ] | board.pieces[BQ]));
      }

      long toMoveAttackers = attackers & (toMove == WHITE ? board.wPieces : board.bPieces);

      int nextAttackerValue = 10000;
      int thisAttackerSquare;
      from = -1;
      while ((thisAttackerSquare = next(toMoveAttackers)) != -1) {
        toMoveAttackers = unset(toMoveAttackers, thisAttackerSquare);
        if (nextAttackerValue > PIECE_ABS_VALUE[board.board[thisAttackerSquare]]) {
          nextAttackerValue = PIECE_ABS_VALUE[board.board[thisAttackerSquare]];
          from = thisAttackerSquare;
        }
      }
    } while (from >= 0);

    while (depth > 1) {
      depth--;
      score[depth - 1] = -Math.max(-score[depth - 1], score[depth]);
    }

    return score[0];
  }

  private static long[][][] PSQT;

  static {
    PSQT = new long[12][64][2];

    // From https://bitbucket.org/christian_g_nther/nemorino
    PSQT[WQ] =
        new long[][] {
          {-10, -8}, {-7, -5}, {-4, -2}, {-2, 0}, {-2, 0}, {-4, -2}, {-7, -5}, {-10, -8}, // A1 - H1
          {-4, -5}, {-2, -2}, {0, 0}, {3, 2}, {3, 2}, {0, 0}, {-2, -2}, {-4, -5}, // A2 - H2
          {-2, -2}, {0, 0}, {3, 2}, {5, 5}, {5, 5}, {3, 2}, {0, 0}, {-2, -2}, // A3 - H3
          {0, 0}, {3, 2}, {5, 5}, {8, 8}, {8, 8}, {5, 5}, {3, 2}, {0, 0}, // A4 - H4
          {0, 0}, {3, 2}, {5, 5}, {8, 8}, {8, 8}, {5, 5}, {3, 2}, {0, 0}, // A5 - H5
          {-2, -2}, {0, 0}, {3, 2}, {5, 5}, {5, 5}, {3, 2}, {0, 0}, {-2, -2}, // A6 - H6
          {-4, -5}, {-2, -2}, {0, 0}, {3, 2}, {3, 2}, {0, 0}, {-2, -2}, {-4, -5}, // A7 - H7
          {-7, -8}, {-4, -5}, {-2, -2}, {0, 0}, {0, 0}, {-2, -2}, {-4, -5}, {-7, -8} // A8 - H8
        };

    PSQT[BQ] =
        new long[][] {
          {7, 8}, {4, 5}, {2, 2}, {0, 0}, {0, 0}, {2, 2}, {4, 5}, {7, 8}, //
          {4, 5}, {2, 2}, {0, 0}, {-3, -2}, {-3, -2}, {0, 0}, {2, 2}, {4, 5}, //
          {2, 2}, {0, 0}, {-3, -2}, {-5, -5}, {-5, -5}, {-3, -2}, {0, 0}, {2, 2}, //
          {0, 0}, {-3, -2}, {-5, -5}, {-8, -8}, {-8, -8}, {-5, -5}, {-3, -2}, {0, 0}, //
          {0, 0}, {-3, -2}, {-5, -5}, {-8, -8}, {-8, -8}, {-5, -5}, {-3, -2}, {0, 0}, //
          {2, 2}, {0, 0}, {-3, -2}, {-5, -5}, {-5, -5}, {-3, -2}, {0, 0}, {2, 2}, //
          {4, 5}, {2, 2}, {0, 0}, {-3, -2}, {-3, -2}, {0, 0}, {2, 2}, {4, 5}, //
          {10, 8}, {7, 5}, {4, 2}, {2, 0}, {2, 0}, {4, 2}, {7, 5}, {10, 8} //
        };

    PSQT[WR] =
        new long[][] {
          {-1, 0}, {-1, 0}, {0, 0}, {1, 0}, {1, 0}, {0, 0}, {-1, 0}, {-1, 0}, //
          {-1, 0}, {-1, 0}, {0, 0}, {1, 0}, {1, 0}, {0, 0}, {-1, 0}, {-1, 0}, //
          {-1, 0}, {-1, 0}, {0, 0}, {1, 0}, {1, 0}, {0, 0}, {-1, 0}, {-1, 0}, //
          {-1, 0}, {-1, 0}, {0, 0}, {1, 0}, {1, 0}, {0, 0}, {-1, 0}, {-1, 0}, //
          {-1, 0}, {-1, 0}, {0, 0}, {1, 0}, {1, 0}, {0, 0}, {-1, 0}, {-1, 0}, //
          {-1, 0}, {-1, 0}, {0, 0}, {1, 0}, {1, 0}, {0, 0}, {-1, 0}, {-1, 0}, //
          {3, 0}, {3, 0}, {4, 0}, {6, 0}, {6, 0}, {4, 0}, {3, 0}, {3, 0}, //
          {-1, 0}, {-1, 0}, {0, 0}, {1, 0}, {1, 0}, {0, 0}, {-1, 0}, {-1, 0} //
        };

    PSQT[BR] =
        new long[][] {
          {1, 0}, {1, 0}, {0, 0}, {-1, 0}, {-1, 0}, {0, 0}, {1, 0}, {1, 0}, //
          {-3, 0}, {-3, 0}, {-4, 0}, {-6, 0}, {-6, 0}, {-4, 0}, {-3, 0}, {-3, 0}, //
          {1, 0}, {1, 0}, {0, 0}, {-1, 0}, {-1, 0}, {0, 0}, {1, 0}, {1, 0}, //
          {1, 0}, {1, 0}, {0, 0}, {-1, 0}, {-1, 0}, {0, 0}, {1, 0}, {1, 0}, //
          {1, 0}, {1, 0}, {0, 0}, {-1, 0}, {-1, 0}, {0, 0}, {1, 0}, {1, 0}, //
          {1, 0}, {1, 0}, {0, 0}, {-1, 0}, {-1, 0}, {0, 0}, {1, 0}, {1, 0}, //
          {1, 0}, {1, 0}, {0, 0}, {-1, 0}, {-1, 0}, {0, 0}, {1, 0}, {1, 0}, //
          {1, 0}, {1, 0}, {0, 0}, {-1, 0}, {-1, 0}, {0, 0}, {1, 0}, {1, 0} //
        };

    PSQT[WB] =
        new long[][] {
          {-12, -8}, {-10, -5}, {-7, -2}, {-4, 0}, {-4, 0}, {-7, -2}, {-10, -5}, {-12, -8}, //
          {-4, -5}, {3, -2}, {0, 0}, {3, 2}, {3, 2}, {0, 0}, {3, -2}, {-4, -5}, //
          {-2, -2}, {0, 0}, {3, 2}, {5, 5}, {5, 5}, {3, 2}, {0, 0}, {-2, -2}, //
          {0, 0}, {3, 2}, {5, 5}, {8, 8}, {8, 8}, {5, 5}, {3, 2}, {0, 0}, //
          {0, 0}, {5, 2}, {5, 5}, {8, 8}, {8, 8}, {5, 5}, {5, 2}, {0, 0}, //
          {-2, -2}, {0, 0}, {3, 2}, {5, 5}, {5, 5}, {3, 2}, {0, 0}, {-2, -2}, //
          {-4, -5}, {-2, -2}, {0, 0}, {3, 2}, {3, 2}, {0, 0}, {-2, -2}, {-4, -5}, //
          {-7, -8}, {-4, -5}, {-2, -2}, {0, 0}, {0, 0}, {-2, -2}, {-4, -5}, {-7, -8} //
        };

    PSQT[BB] =
        new long[][] {
          {7, 8}, {4, 5}, {2, 2}, {0, 0}, {0, 0}, {2, 2}, {4, 5}, {7, 8}, //
          {4, 5}, {2, 2}, {0, 0}, {-3, -2}, {-3, -2}, {0, 0}, {2, 2}, {4, 5}, //
          {2, 2}, {0, 0}, {-3, -2}, {-5, -5}, {-5, -5}, {-3, -2}, {0, 0}, {2, 2}, //
          {0, 0}, {-5, -2}, {-5, -5}, {-8, -8}, {-8, -8}, {-5, -5}, {-5, -2}, {0, 0}, //
          {0, 0}, {-3, -2}, {-5, -5}, {-8, -8}, {-8, -8}, {-5, -5}, {-3, -2}, {0, 0}, //
          {2, 2}, {0, 0}, {-3, -2}, {-5, -5}, {-5, -5}, {-3, -2}, {0, 0}, {2, 2}, //
          {4, 5}, {-3, 2}, {0, 0}, {-3, -2}, {-3, -2}, {0, 0}, {-3, 2}, {4, 5}, //
          {12, 8}, {10, 5}, {7, 2}, {4, 0}, {4, 0}, {7, 2}, {10, 5}, {12, 8} //
        };

    PSQT[WN] =
        new long[][] {
          {-19, -18},
          {-14, -12},
          {-8, -7},
          {-3, -2},
          {-3, -2},
          {-8, -7},
          {-14, -12},
          {-19, -18}, //
          {-14, -12},
          {-8, -7},
          {-3, -2},
          {4, 5},
          {4, 5},
          {-3, -2},
          {-8, -7},
          {-14, -12}, //
          {-8, -7},
          {-3, -2},
          {4, 5},
          {15, 16},
          {15, 16},
          {4, 5},
          {-3, -2},
          {-8, -7}, //
          {-3, -2},
          {4, 5},
          {15, 16},
          {21, 22},
          {21, 22},
          {15, 16},
          {4, 5},
          {-3, -2}, //
          {-3, -2},
          {4, 5},
          {26, 16},
          {31, 22},
          {31, 22},
          {26, 16},
          {4, 5},
          {-3, -2}, //
          {-8, -7},
          {-3, -2},
          {15, 5},
          {36, 16},
          {36, 16},
          {15, 5},
          {-3, -2},
          {-8, -7}, //
          {-14, -12},
          {-8, -7},
          {-3, -2},
          {4, 5},
          {4, 5},
          {-3, -2},
          {-8, -7},
          {-14, -12}, //
          {-19, -18},
          {-14, -12},
          {-8, -7},
          {-3, -2},
          {-3, -2},
          {-8, -7},
          {-14, -12},
          {-19, -18} //
        };

    PSQT[BN] =
        new long[][] {
          {19, 18}, {14, 12}, {8, 7}, {3, 2}, {3, 2}, {8, 7}, {14, 12}, {19, 18}, //
          {14, 12}, {8, 7}, {3, 2}, {-4, -5}, {-4, -5}, {3, 2}, {8, 7}, {14, 12}, //
          {8, 7}, {3, 2}, {-15, -5}, {-36, -16}, {-36, -16}, {-15, -5}, {3, 2}, {8, 7}, //
          {3, 2}, {-4, -5}, {-26, -16}, {-31, -22}, {-31, -22}, {-26, -16}, {-4, -5}, {3, 2}, //
          {3, 2}, {-4, -5}, {-15, -16}, {-21, -22}, {-21, -22}, {-15, -16}, {-4, -5}, {3, 2}, //
          {8, 7}, {3, 2}, {-4, -5}, {-15, -16}, {-15, -16}, {-4, -5}, {3, 2}, {8, 7}, //
          {14, 12}, {8, 7}, {3, 2}, {-4, -5}, {-4, -5}, {3, 2}, {8, 7}, {14, 12}, //
          {19, 18}, {14, 12}, {8, 7}, {3, 2}, {3, 2}, {8, 7}, {14, 12}, {19, 18} //
        };

    PSQT[WP] =
        new long[][] {
          {0, 0},
          {0, 0},
          {0, 0},
          {0, 0},
          {0, 0},
          {0, 0},
          {0, 0},
          {0, 0}, //
          {-17, -11},
          {-9, -11},
          {-7, -11},
          {-9, -11},
          {-9, -11},
          {-7, -11},
          {-9, -11},
          {-17, -11}, //
          {-15, -9},
          {-12, -9},
          {-7, -9},
          {-1, -9},
          {-1, -9},
          {-7, -9},
          {-12, -9},
          {-15, -9}, //
          {-17, -6},
          {-9, -6},
          {-7, -6},
          {6, -6},
          {6, -6},
          {-7, -6},
          {-9, -6},
          {-17, -6}, //
          {-9, -3},
          {-7, -3},
          {-4, -3},
          {9, -3},
          {9, -3},
          {-4, -3},
          {-7, -3},
          {-9, -3}, //
          {3, 9},
          {6, 9},
          {9, 9},
          {11, 9},
          {11, 9},
          {9, 9},
          {6, 9},
          {3, 9}, //
          {22, 20},
          {22, 20},
          {22, 20},
          {22, 20},
          {22, 20},
          {22, 20},
          {22, 20},
          {22, 20}, //
          {0, 0},
          {0, 0},
          {0, 0},
          {0, 0},
          {0, 0},
          {0, 0},
          {0, 0},
          {0, 0} //
        };

    PSQT[BP] =
        new long[][] {
          {0, 0},
          {0, 0},
          {0, 0},
          {0, 0},
          {0, 0},
          {0, 0},
          {0, 0},
          {0, 0}, //
          {-22, -20},
          {-22, -20},
          {-22, -20},
          {-22, -20},
          {-22, -20},
          {-22, -20},
          {-22, -20},
          {-22, -20}, //
          {-3, -9},
          {-6, -9},
          {-9, -9},
          {-11, -9},
          {-11, -9},
          {-9, -9},
          {-6, -9},
          {-3, -9}, //
          {9, 3},
          {7, 3},
          {4, 3},
          {-9, 3},
          {-9, 3},
          {4, 3},
          {7, 3},
          {9, 3}, //
          {17, 6},
          {9, 6},
          {7, 6},
          {-6, 6},
          {-6, 6},
          {7, 6},
          {9, 6},
          {17, 6}, //
          {15, 9},
          {12, 9},
          {7, 9},
          {1, 9},
          {1, 9},
          {7, 9},
          {12, 9},
          {15, 9}, //
          {17, 11},
          {9, 11},
          {7, 11},
          {9, 11},
          {9, 11},
          {7, 11},
          {9, 11},
          {17, 11}, //
          {0, 0},
          {0, 0},
          {0, 0},
          {0, 0},
          {0, 0},
          {0, 0},
          {0, 0},
          {0, 0} //
        };

    PSQT[WK] =
        new long[][] {
          {16, -20},
          {17, -15},
          {16, -9},
          {14, -4},
          {14, -4},
          {16, -9},
          {17, -15},
          {16, -20}, //
          {16, -15},
          {16, -9},
          {14, -4},
          {11, 7},
          {11, 7},
          {14, -4},
          {16, -9},
          {16, -15}, //
          {11, -9},
          {11, -4},
          {11, 7},
          {8, 23},
          {8, 23},
          {11, 7},
          {11, -4},
          {11, -9}, //
          {8, -4},
          {8, 7},
          {3, 23},
          {-2, 28},
          {-2, 28},
          {3, 23},
          {8, 7},
          {8, -4}, //
          {3, -4},
          {0, 7},
          {-2, 23},
          {-7, 28},
          {-7, 28},
          {-2, 23},
          {0, 7},
          {3, -4}, //
          {-7, -9},
          {-7, -4},
          {-12, 7},
          {-18, 23},
          {-18, 23},
          {-12, 7},
          {-7, -4},
          {-7, -9}, //
          {-12, -15},
          {-12, -9},
          {-18, -4},
          {-18, 7},
          {-18, 7},
          {-18, -4},
          {-12, -9},
          {-12, -15}, //
          {-18, -20},
          {-18, -15},
          {-18, -9},
          {-18, -4},
          {-18, -4},
          {-18, -9},
          {-18, -15},
          {-18, -20} //
        };

    PSQT[BK] =
        new long[][] {
          {18, 20}, {18, 15}, {18, 9}, {18, 4}, {18, 4}, {18, 9}, {18, 15}, {18, 20}, //
          {12, 15}, {12, 9}, {18, 4}, {18, -7}, {18, -7}, {18, 4}, {12, 9}, {12, 15}, //
          {7, 9}, {7, 4}, {12, -7}, {18, -23}, {18, -23}, {12, -7}, {7, 4}, {7, 9}, //
          {-3, 4}, {0, -7}, {2, -23}, {7, -28}, {7, -28}, {2, -23}, {0, -7}, {-3, 4}, //
          {-8, 4}, {-8, -7}, {-3, -23}, {2, -28}, {2, -28}, {-3, -23}, {-8, -7}, {-8, 4}, //
          {-11, 9}, {-11, 4}, {-11, -7}, {-8, -23}, {-8, -23}, {-11, -7}, {-11, 4}, {-11, 9}, //
          {-16, 15}, {-16, 9}, {-14, 4}, {-11, -7}, {-11, -7}, {-14, 4}, {-16, 9}, {-16, 15}, //
          {-16, 20}, {-17, 15}, {-16, 9}, {-14, 4}, {-14, 4}, {-16, 9}, {-17, 15}, {-16, 20} //
        };
  }
}
