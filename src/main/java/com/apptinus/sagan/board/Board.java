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
import static com.apptinus.sagan.util.Bitops.isSet;
import static com.apptinus.sagan.util.Bitops.next;
import static com.apptinus.sagan.util.Bitops.set;
import static com.apptinus.sagan.util.Bitops.setBit;
import static com.apptinus.sagan.util.Bitops.unset;

import com.apptinus.sagan.util.Bitops;
import java.util.Collections;

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

  public long wPieces;
  public long bPieces;
  public long allPieces;

  public int wCastle; // 0 no castle, 1 short, 2 long, 3 both
  public int bCastle; // 0 no castle, 1 short, 2 long, 3 both
  public int toMove; // 0 white, 1 black
  public int ep; // Available en passant square
  public int fPly; // Half moves since capture (fifty moves counter)
  public int ply; // Half moves since start of game

  public void make(int move) {
    int from = from(move);
    int to = to(move);
    int special = special(move);
    int piece = board[from];
    int moving = piece <= 5 ? 0 : 1;

    toMove = (toMove == WHITE) ? BLACK : WHITE;

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

    if (piece == WP && to - from == 16) {
      ep = next(so(setBit(to)));
    } else if (piece == BP && from - to == 16) {
      ep = next(no(setBit(to)));
    } else {
      ep = -1;
    }

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

  private void setPiece(int square, int piece) {
    board[square] = piece;
    pieces[piece] = set(pieces[piece], square);
    allPieces = set(allPieces, square);
    if (piece <= 5) {
      wPieces = set(wPieces, square);
    } else {
      bPieces = set(bPieces, square);
    }
  }

  private void unsetPiece(int square, int piece) {
    board[square] = EE;
    pieces[piece] = unset(pieces[piece], square);
    allPieces = unset(allPieces, square);
    if (piece <= 5) {
      wPieces = unset(wPieces, square);
    } else {
      bPieces = unset(bPieces, square);
    }
  }

  public void setFen(String fen) {
    // Note: Unpredictable results if fen is malformed (no sanity checking at all)

    clear();

    fen = fen.trim();

    // If move counts are missing, add them first
    if (fen.split(" ").length == 4) {
      fen = fen + " 0 1";
    }

    String[] fs = fen.split(" ");

    toMove = fs[1].equals("w") ? WHITE : BLACK;
    wCastle = (fs[2].contains("K") ? 1 : 0) | (fs[2].contains("Q") ? 1 << 1 : 0);
    bCastle = (fs[2].contains("k") ? 1 : 0) | (fs[2].contains("q") ? 1 << 1 : 0);
    ep = fs[3].equals("-") ? -1 : notationToSquare(fs[3]);
    fPly = Integer.parseInt(fs[4]);
    // Add a ply if black to move, since the full move hasn't accounted for white's last half move
    ply = (Integer.parseInt(fs[5]) - 1) * 2 + (toMove == WHITE ? 0 : 1);

    int i = -1;
    int file = 0;
    int rank = 7;
    do {
      i++;
      char c = fs[0].charAt(i);

      if (c == '/') {
        file = 0;
        rank--;
        continue;
      }

      int square = fileAndRankToSquare(file, rank);

      if (c >= '0' && c <= '9') {
        int emptySquares = c - '0';
        file += emptySquares;
        for (int emptySquare = square; emptySquare < square + emptySquares; emptySquare++) {
          board[emptySquare] = EE;
        }
        continue;
      }

      switch (c) {
        case 'p':
          pieces[BP] = set(pieces[BP], square);
          board[square] = BP;
          break;
        case 'n':
          pieces[BN] = set(pieces[BN], square);
          board[square] = BN;
          break;
        case 'b':
          pieces[BB] = set(pieces[BB], square);
          board[square] = BB;
          break;
        case 'r':
          pieces[BR] = set(pieces[BR], square);
          board[square] = BR;
          break;
        case 'q':
          pieces[BQ] = set(pieces[BQ], square);
          board[square] = BQ;
          break;
        case 'k':
          pieces[BK] = set(pieces[BK], square);
          board[square] = BK;
          break;
        case 'P':
          pieces[WP] = set(pieces[WP], square);
          board[square] = WP;
          break;
        case 'N':
          pieces[WN] = set(pieces[WN], square);
          board[square] = WN;
          break;
        case 'B':
          pieces[WB] = set(pieces[WB], square);
          board[square] = WB;
          break;
        case 'R':
          pieces[WR] = set(pieces[WR], square);
          board[square] = WR;
          break;
        case 'Q':
          pieces[WQ] = set(pieces[WQ], square);
          board[square] = WQ;
          break;
        case 'K':
          pieces[WK] = set(pieces[WK], square);
          board[square] = WK;
          break;
        default:
          file += (int) c;
          continue;
      }

      file++;
    } while (i < fs[0].length() - 1);

    wPieces |= pieces[WK] | pieces[WQ] | pieces[WR] | pieces[WB] | pieces[WN] | pieces[WP];
    bPieces |= pieces[BK] | pieces[BQ] | pieces[BR] | pieces[BB] | pieces[BN] | pieces[BP];
    allPieces |= wPieces | bPieces;
  }

  public String getFen() {
    String toMoveString = toMove == WHITE ? "w" : "b";
    String epString = ep == -1 ? "-" : squareToNotation(ep);
    String fullMoves = Integer.toString(ply / 2 + 1);
    String halfSinceCapture = Integer.toString(fPly);
    String castle = "";
    if (wCastle == 1) {
      castle += "K";
    } else if (wCastle == 2) {
      castle += "Q";
    } else if (wCastle == 3) {
      castle += "KQ";
    }
    if (bCastle == 1) {
      castle += "k";
    } else if (bCastle == 2) {
      castle += "q";
    } else if (bCastle == 3) {
      castle += "kq";
    }
    castle = castle.equals("") ? "-" : castle;

    String piecesString = "";
    int consecutive = 0;
    for (int rank = 7; rank >= 0; rank--) {
      if (rank < 7) {
        if (consecutive > 0) {
          piecesString += consecutive;
          consecutive = 0;
        }
        piecesString += "/";
      }
      for (int file = 0; file <= 7; file++) {
        int square = fileAndRankToSquare(file, rank);

        if (board[square] == EE) {
          consecutive++;
        } else {
          if (consecutive > 0) {
            piecesString += consecutive;
            consecutive = 0;
          }
          switch (board[square]) {
            case 0:
              piecesString += "K";
              break;
            case 1:
              piecesString += "Q";
              break;
            case 2:
              piecesString += "R";
              break;
            case 3:
              piecesString += "B";
              break;
            case 4:
              piecesString += "N";
              break;
            case 5:
              piecesString += "P";
              break;
            case 6:
              piecesString += "k";
              break;
            case 7:
              piecesString += "q";
              break;
            case 8:
              piecesString += "r";
              break;
            case 9:
              piecesString += "b";
              break;
            case 10:
              piecesString += "n";
              break;
            case 11:
              piecesString += "p";
              break;
          }
        }
      }
    }

    if (consecutive > 0) {
      piecesString += consecutive;
    }

    return String.join(
        " ", piecesString, toMoveString, castle, epString, halfSinceCapture, fullMoves);
  }

  public void clear() {
    board = new int[64];
    pieces = new long[12];

    history = new int[1024];

    wPieces = 0L;
    bPieces = 0L;
    allPieces = 0L;

    wCastle = 0;
    bCastle = 0;
    toMove = WHITE;
    ep = -1;
    fPly = 0;
    ply = 0;
  }

  public static int fileAndRankToSquare(int file, int rank) {
    return file + rank * 8;
  }

  public static int notationToSquare(String notation) {
    int file = notation.charAt(0) - 'a';
    int rank = notation.charAt(1) - '1';

    return file + rank * 8;
  }

  public static String squareToNotation(int square) {
    String file = Character.toString((char) (square % 8 + 'a'));
    String rank = Character.toString((char) (square / 8 + '1'));

    return file + rank;
  }

  public static String prettyPrintBitBoard(long bitboard) {
    String oneLine =
        String.join("", Collections.nCopies(Long.numberOfLeadingZeros(bitboard), "0"))
            + Long.toBinaryString(bitboard);

    String print = "";
    for (int i = 0; i < 8; i++) {
      char[] chars = oneLine.substring(i * 8, i * 8 + 8).toCharArray();
      for (int j = 7; j >= 0; j--) {
        print += chars[j] + " ";
      }
      print += "\n";
    }

    return print;
  }

  public static String moveToNotation(int move) {
    return squareToNotation(Move.from(move)) + squareToNotation(Move.to(move));
  }

  public String prettyPrintBoard() {
    String boardString = "";
    for (int rank = 7; rank >= 0; rank--) {
      for (int file = 0; file <= 7; file++) {
        int i = fileAndRankToSquare(file, rank);

        if (isSet(wPieces, i)) {
          if (isSet(pieces[WP], i)) {
            boardString += "P ";
          } else if (isSet(pieces[WN], i)) {
            boardString += "N ";
          } else if (isSet(pieces[WB], i)) {
            boardString += "B ";
          } else if (isSet(pieces[WR], i)) {
            boardString += "R ";
          } else if (isSet(pieces[WQ], i)) {
            boardString += "Q ";
          } else if (isSet(pieces[WK], i)) {
            boardString += "K ";
          }
        } else if (isSet(bPieces, i)) {
          if (isSet(pieces[BP], i)) {
            boardString += "p ";
          } else if (isSet(pieces[BN], i)) {
            boardString += "n ";
          } else if (isSet(pieces[BB], i)) {
            boardString += "b ";
          } else if (isSet(pieces[BR], i)) {
            boardString += "r ";
          } else if (isSet(pieces[BQ], i)) {
            boardString += "q ";
          } else if (isSet(pieces[BK], i)) {
            boardString += "k ";
          }
        } else {
          boardString += ". ";
        }
      }
      boardString += "\n";
    }

    return boardString;
  }

  public boolean isInCheck(int sideInCheck) {
    int checkSquare = sideInCheck == WHITE ? Bitops.next(pieces[WK]) : Bitops.next(pieces[BK]);
    int attackingSide = sideInCheck == WHITE ? BLACK : WHITE;
    return isAttacked(checkSquare, attackingSide);
  }

  public boolean isAttacked(int square, int attacker) {
    long attackers = attackers(square, attacker);
    return (attackers & (attacker == 0 ? wPieces : bPieces)) != 0;
  }

  public long attackers(final int sq, final int attacker) {
    long knights, kings, bishopsQueens, rooksQueens;
    knights = pieces[WN] | pieces[BN];
    kings = pieces[WK] | pieces[BK];
    rooksQueens = bishopsQueens = pieces[WQ] | pieces[BQ];
    rooksQueens |= pieces[WR] | pieces[BR];
    bishopsQueens |= pieces[WB] | pieces[BB];

    return (MoveGen.pawnWhiteCaptureDeltas[sq] & pieces[BP])
        | (MoveGen.pawnBlackCaptureDeltas[sq] & pieces[WP])
        | (MoveGen.knightDeltas[sq] & knights)
        | (MoveGen.kingDeltas[sq] & kings)
        | (MoveGen.genTargetsBishops(sq, allPieces, attacker == 0 ? ~bPieces : ~wPieces)
            & bishopsQueens)
        | (MoveGen.genTargetsRooks(sq, allPieces, attacker == 0 ? ~bPieces : ~wPieces)
            & rooksQueens);
  }
}
