package com.apptinus.sagan.board;

import static com.apptinus.sagan.board.Move.A1;
import static com.apptinus.sagan.board.Move.A8;
import static com.apptinus.sagan.board.Move.BLACK;
import static com.apptinus.sagan.board.Move.C1;
import static com.apptinus.sagan.board.Move.C8;
import static com.apptinus.sagan.board.Move.D1;
import static com.apptinus.sagan.board.Move.D8;
import static com.apptinus.sagan.board.Move.F1;
import static com.apptinus.sagan.board.Move.F8;
import static com.apptinus.sagan.board.Move.G1;
import static com.apptinus.sagan.board.Move.H1;
import static com.apptinus.sagan.board.Move.H8;
import static com.apptinus.sagan.board.Move.PROMO_B;
import static com.apptinus.sagan.board.Move.PROMO_N;
import static com.apptinus.sagan.board.Move.PROMO_Q;
import static com.apptinus.sagan.board.Move.PROMO_R;
import static com.apptinus.sagan.board.Move.SPECIAL_CASTLE;
import static com.apptinus.sagan.board.Move.SPECIAL_EP;
import static com.apptinus.sagan.board.Move.SPECIAL_PROMO;
import static com.apptinus.sagan.board.Move.WHITE;
import static com.apptinus.sagan.board.Move.bCastle;
import static com.apptinus.sagan.board.Move.capturedPiece;
import static com.apptinus.sagan.board.Move.epSquare;
import static com.apptinus.sagan.board.Move.fPly;
import static com.apptinus.sagan.board.Move.from;
import static com.apptinus.sagan.board.Move.h;
import static com.apptinus.sagan.board.Move.no;
import static com.apptinus.sagan.board.Move.promotion;
import static com.apptinus.sagan.board.Move.so;
import static com.apptinus.sagan.board.Move.special;
import static com.apptinus.sagan.board.Move.to;
import static com.apptinus.sagan.board.Move.wCastle;
import static com.apptinus.sagan.util.Bitops.next;
import static com.apptinus.sagan.util.Bitops.set;
import static com.apptinus.sagan.util.Bitops.setBit;
import static com.apptinus.sagan.util.Bitops.unset;

import com.apptinus.sagan.util.Bitops;
import com.apptinus.sagan.util.Zobrist;

public class Board {
  public static final int WK = 0;
  public static final int WQ = 1;
  public static final int WR = 2;
  public static final int WB = 3;
  public static final int WN = 4;
  public static final int WP = 5;
  public static final int BK = 6;
  public static final int BQ = 7;
  public static final int BR = 8;
  public static final int BB = 9;
  public static final int BN = 10;
  public static final int BP = 11;
  public static final int EE = 12;

  public int[] board = new int[64];

  public long[] pieces = new long[12];

  public int[] history = new int[1024];
  public long[] zobristHistory = new long[1024]; // Zobrist key history, for repetition detection

  public long wPieces;
  public long bPieces;
  public long allPieces;

  public int wCastle; // 0 no castle, 1 short, 2 long, 3 both
  public int bCastle; // 0 no castle, 1 short, 2 long, 3 both
  public int toMove; // 0 white, 1 black
  public int ep; // Available en passant square
  public int fPly; // Half moves since capture (fifty moves counter)
  public int ply; // Half moves since start of game
  public long zobrist; // Current zobrist representation of the board

  public Tt tt;

  private Board() {}

  public Board(int ttSizeMb) {
    tt = new Tt(ttSizeMb);
  }

  public Board(Tt tt) {
    this.tt = tt;
  }

  public void make(int move) {
    int from = from(move);
    int to = to(move);
    int special = special(move);
    int piece = board[from];
    int moving = piece <= 5 ? 0 : 1;
    zobristHistory[ply] = zobrist;

    toMove = (toMove == WHITE) ? BLACK : WHITE;
    zobrist ^= Zobrist.SIDE;

    unsetPiece(from, piece);

    int toPiece = piece;
    if (special == SPECIAL_PROMO) {
      switch (promotion(move)) {
        case PROMO_N:
          toPiece = moving == 0 ? WN : BN;
          break;
        case PROMO_B:
          toPiece = moving == 0 ? WB : BB;
          break;
        case PROMO_R:
          toPiece = moving == 0 ? WR : BR;
          break;
        case PROMO_Q:
          toPiece = moving == 0 ? WQ : BQ;
          break;
      }
    }

    int captureSquare = to;
    int capturedPiece = board[captureSquare];

    if (special == SPECIAL_EP) {
      captureSquare = moving == 0 ? next(so(setBit(to))) : next(no(setBit(to)));
      capturedPiece = board[captureSquare];
      unsetPiece(captureSquare, capturedPiece);
    } else {
      if (capturedPiece != EE) {
        unsetPiece(captureSquare, capturedPiece);
      }
    }

    setPiece(to, toPiece);

    history[ply] = h(wCastle, bCastle, ep >= 0 ? 1 : 0, ep == -1 ? 0 : ep, fPly, capturedPiece);

    if (capturedPiece != EE || piece == WP || piece == BP) {
      fPly = 0;
    } else {
      fPly++;
    }

    if (special == SPECIAL_CASTLE) {
      int rookFromSquare = H8;
      int rookToSquare = F8;
      int castlingRook = BR;
      switch (to) {
        case C1:
          rookFromSquare = A1;
          rookToSquare = D1;
          castlingRook = WR;
          break;
        case G1:
          rookFromSquare = H1;
          rookToSquare = F1;
          castlingRook = WR;
          break;
        case C8:
          rookFromSquare = A8;
          rookToSquare = D8;
          castlingRook = BR;
          break;
      }

      setPiece(rookToSquare, castlingRook);
      unsetPiece(rookFromSquare, castlingRook);
    }

    // Castling rights
    zobrist ^= Zobrist.W_CASTLING_RIGHTS[wCastle]; // Clear previous castling rights
    zobrist ^= Zobrist.B_CASTLING_RIGHTS[bCastle];
    if (wCastle != 0) {
      if (piece == WK) {
        wCastle = 0;
      } else if (from == H1 || to == H1) {
        wCastle = unset(wCastle, 0);
      } else if (from == A1 || to == A1) {
        wCastle = unset(wCastle, 1);
      }
    }
    if (bCastle != 0) {
      if (piece == BK) {
        bCastle = 0;
      } else if (from == H8 || to == H8) {
        bCastle = unset(bCastle, 0);
      } else if (from == A8 || to == A8) {
        bCastle = unset(bCastle, 1);
      }
    }
    zobrist ^= Zobrist.W_CASTLING_RIGHTS[wCastle];
    zobrist ^= Zobrist.B_CASTLING_RIGHTS[bCastle];

    // En passant square
    if (ep != -1) zobrist ^= Zobrist.EN_PASSANT[ep]; // Clear previous en passant square
    if (piece == WP && to - from == 16) {
      ep = next(so(setBit(to)));
    } else if (piece == BP && from - to == 16) {
      ep = next(no(setBit(to)));
    } else {
      ep = -1;
    }
    if (ep != -1) zobrist ^= Zobrist.EN_PASSANT[ep];

    ply++;
  }

  public void unmake(int move) {
    int from = from(move);
    int to = to(move);
    int special = special(move);
    int piece = board[to];
    int moving = piece <= 5 ? 0 : 1;

    ply--;
    toMove = (toMove == WHITE) ? BLACK : WHITE;
    wCastle = wCastle(history[ply]);
    bCastle = bCastle(history[ply]);
    ep = epSquare(history[ply]);
    fPly = fPly(history[ply]);
    zobrist = zobristHistory[ply];

    unsetPiece(to, piece);

    if (special == SPECIAL_PROMO) {
      piece = moving == 0 ? WP : BP;
    }

    if (special == SPECIAL_EP) {
      int capturedPawn;
      int capturedPawnSquare;
      if (moving == 0) {
        capturedPawn = BP;
        capturedPawnSquare = next(so(setBit(to)));
      } else {
        capturedPawn = WP;
        capturedPawnSquare = next(no(setBit(to)));
      }

      setPiece(capturedPawnSquare, capturedPawn);
    } else {
      int capturedPiece = capturedPiece(history[ply]);
      if (capturedPiece != EE) {
        setPiece(to, capturedPiece);
      }
    }

    setPiece(from, piece);

    if (special == SPECIAL_CASTLE) {
      int rookFromSquare = H8;
      int rookToSquare = F8;
      int castlingRook = BR;
      switch (to) {
        case C1:
          rookFromSquare = A1;
          rookToSquare = D1;
          castlingRook = WR;
          break;
        case G1:
          rookFromSquare = H1;
          rookToSquare = F1;
          castlingRook = WR;
          break;
        case C8:
          rookFromSquare = A8;
          rookToSquare = D8;
          castlingRook = BR;
          break;
      }

      board[rookFromSquare] = castlingRook;
      pieces[castlingRook] = set(pieces[castlingRook], rookFromSquare);
      allPieces = set(allPieces, rookFromSquare);
      board[rookToSquare] = EE;
      pieces[castlingRook] = unset(pieces[castlingRook], rookToSquare);
      allPieces = unset(allPieces, rookToSquare);
      if (piece <= 5) {
        wPieces = set(wPieces, rookFromSquare);
        wPieces = unset(wPieces, rookToSquare);
      } else {
        bPieces = set(bPieces, rookFromSquare);
        bPieces = unset(bPieces, rookToSquare);
      }
    }
  }

  public final void nullmoveToggle() {
    toMove *= -1;
    zobrist ^= Zobrist.SIDE;
  }

  public boolean isDraw() {
    // 50 move rule
    if (fPly >= 100) {
      return true;
    }

    // 3-fold repetition. Only compares with the current zobrist, since we would never go passed
    // a 3-fold repetition in the search, so it can't have occured previously without counting
    // current position
    int repetitions = 0;
    for (int i = ply; i >= 0; i--) {
      if (zobrist == zobristHistory[i]) {
        repetitions++;
      }
      if (repetitions >= 2) {
        return true;
      }
    }

    // Insufficient material
    int wBishops = Bitops.population(pieces[WB]);
    int wKnights = Bitops.population(pieces[WN]);
    int bBishops = Bitops.population(pieces[BB]);
    int bKnights = Bitops.population(pieces[BN]);
    if (Bitops.population(pieces[WP]) != 0
        || Bitops.population(pieces[WR]) != 0
        || Bitops.population(pieces[WQ]) != 0
        || wBishops > 1
        || wKnights > 2
        || Bitops.population(pieces[BP]) != 0
        || Bitops.population(pieces[BR]) != 0
        || Bitops.population(pieces[BQ]) != 0
        || bBishops > 1
        || bKnights > 2) {
      return false;
    }
    if ((wBishops > 0 && wKnights > 0) || (bBishops > 0 && bKnights > 0)) {
      return false;
    }

    return true;
  }

  private void setPiece(int square, int piece) {
    board[square] = piece;
    pieces[piece] = set(pieces[piece], square);
    allPieces = set(allPieces, square);
    if (piece <= 5) {
      wPieces = set(wPieces, square);
      zobrist ^= Zobrist.PIECES[piece][0][square];
    } else {
      bPieces = set(bPieces, square);
      zobrist ^= Zobrist.PIECES[piece-6][1][square];
    }
  }

  private void unsetPiece(int square, int piece) {
    board[square] = EE;
    pieces[piece] = unset(pieces[piece], square);
    allPieces = unset(allPieces, square);
    if (piece <= 5) {
      wPieces = unset(wPieces, square);
      zobrist ^= Zobrist.PIECES[piece][0][square];
    } else {
      bPieces = unset(bPieces, square);
      zobrist ^= Zobrist.PIECES[piece-6][1][square];
    }
  }

  public boolean isInCheck(int sideInCheck) {
    int checkSquare = sideInCheck == WHITE ? Bitops.next(pieces[WK]) : Bitops.next(pieces[BK]);
    int attackingSide = sideInCheck == WHITE ? BLACK : WHITE;
    return isAttacked(checkSquare, attackingSide);
  }

  public boolean isAttacked(int square, int attacker) {
    long attackers = attackers(square, allPieces, 0xffffffffffffffffL);
    return (attackers & (attacker == WHITE ? wPieces : bPieces)) != 0;
  }

  public long attackers(final int sq, long allPieces, long onlyOnTheseSquares) {
    long knights, kings, bishopsQueens, rooksQueens;
    knights = (pieces[WN] | pieces[BN]) & onlyOnTheseSquares;
    kings = (pieces[WK] | pieces[BK]) & onlyOnTheseSquares;
    rooksQueens = bishopsQueens = (pieces[WQ] | pieces[BQ]) & onlyOnTheseSquares;
    rooksQueens |= (pieces[WR] | pieces[BR]) & onlyOnTheseSquares;
    bishopsQueens |= (pieces[WB] | pieces[BB]) & onlyOnTheseSquares;

    return (MoveGen.pawnWhiteCaptureDeltas[sq] & (pieces[BP] & onlyOnTheseSquares))
        | (MoveGen.pawnBlackCaptureDeltas[sq] & (pieces[WP] & onlyOnTheseSquares))
        | (MoveGen.knightDeltas[sq] & knights)
        | (MoveGen.kingDeltas[sq] & kings)
        | (MoveGen.genTargetsBishops(sq, allPieces, ~bPieces | ~wPieces)
            & bishopsQueens)
        | (MoveGen.genTargetsRooks(sq, allPieces, ~bPieces | ~wPieces)
            & rooksQueens);
  }
}
