package com.apptinus.sagan.board;

import static com.apptinus.sagan.util.Bitops.isSet;
import static com.apptinus.sagan.util.Bitops.set;

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

  public long wPieces;
  public long bPieces;
  public long allPieces;

  public int wCastle; // 0 no castle, 1 short, 2 long, 3 both
  public int bCastle; // 0 no castle, 1 short, 2 long, 3 both
  public int toMove; // 0 white, 1 black
  public int ep; // Available en passant square
  public int fPly; // Half moves since capture (fifty moves counter)
  public int ply; // Half moves since start of game

  public void make(int move) {}

  public void unmake(int move) {}

  public void setFen(String fen) {
    // Note: Unpredictable results if fen is malformed (no sanity checking at all)

    clear();
    String[] fs = fen.split(" ");

    toMove = fs[1].equals("w") ? 0 : 1;
    wCastle = (fs[2].contains("K") ? 1 : 0) | (fs[2].contains("Q") ? 1 << 1 : 0);
    bCastle = (fs[2].contains("k") ? 1 : 0) | (fs[2].contains("q") ? 1 << 1 : 0);
    ep = fs[3].equals("-") ? -1 : notationToSquare(fs[3]);
    fPly = Integer.parseInt(fs[4]);
    // Add a ply if black to move, since the full move hasn't accounted for white's last half move
    ply = (Integer.parseInt(fs[5]) - 1) * 2 + (toMove == 0 ? 0 : 1);

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
    String toMoveString = toMove == 0 ? "w" : "b";
    String epString = ep == -1 ? "-" : squareToNotation(ep);
    String fullMoves = Integer.toString(ply / 2 + 1);
    String halfSinceCapture = Integer.toString(fPly);
    String castle = "";
    switch (wCastle) {
      case 1:
        castle += "K";
        break;
      case 2:
        castle += "Q";
        break;
      case 3:
        castle += "KQ";
        break;
    }
    switch (bCastle) {
      case 1:
        castle += "k";
        break;
      case 2:
        castle += "q";
        break;
      case 3:
        castle += "kq";
        break;
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

    return String.join(
        " ", piecesString, toMoveString, castle, epString, halfSinceCapture, fullMoves);
  }

  public void clear() {
    pieces = new long[12];

    wPieces = 0L;
    bPieces = 0L;
    allPieces = 0L;

    wCastle = 0;
    bCastle = 0;
    toMove = 0;
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
}
