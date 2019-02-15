package com.apptinus.board;

import static com.apptinus.util.Bitops.*;

import com.apptinus.util.Bitops;
import java.util.Collections;

public class Board {
  public long wP;
  public long wN;
  public long wB;
  public long wR;
  public long wQ;
  public long wK;
  public long bP;
  public long bN;
  public long bB;
  public long bR;
  public long bQ;
  public long bK;

  public long wPieces;
  public long bPieces;
  public long allPieces;

  public int wCastle; // 0 no castle, 1 short, 2 long, 3 both
  public int bCastle; // 0 no castle, 1 short, 2 long, 3 both
  public int toMove; // 0 white, 1 black
  public int ep; // Available en passant square
  public int fPly; // Half moves since capture (fifty moves counter)
  public int ply; // Half moves since start of game

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

      if (c >= '0' && c <= '9') {
        file += c - '0';
        continue;
      }

      int square = fileAndRankToSquare(file, rank);
      switch (c) {
        case 'p':
          bP = add(bP, square);
          break;
        case 'n':
          bN = add(bN, square);
          break;
        case 'b':
          bB = add(bB, square);
          break;
        case 'r':
          bR = add(bR, square);
          break;
        case 'q':
          bQ = add(bQ, square);
          break;
        case 'k':
          bK = add(bK, square);
          break;
        case 'P':
          wP = add(wP, square);
          break;
        case 'N':
          wN = add(wN, square);
          break;
        case 'B':
          wB = add(wB, square);
          break;
        case 'R':
          wR = add(wR, square);
          break;
        case 'Q':
          wQ = add(wQ, square);
          break;
        case 'K':
          wK = add(wK, square);
          break;
        default:
          file += (int) c;
          continue;
      }

      file++;
    } while (i < fs[0].length() - 1);

    wPieces |= wK | wQ | wR | wB | wN | wP;
    bPieces |= bK | bQ | bR | bB | bN | bP;
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

    String pieces = "";
    int consecutive = 0;
    for (int rank = 7; rank >= 0; rank--) {
      if (rank < 7) {
        if (consecutive > 0) {
          pieces += consecutive;
          consecutive = 0;
        }
        pieces += "/";
      }
      for (int file = 0; file <= 7; file++) {
        int i = fileAndRankToSquare(file, rank);

        if (!isSet(allPieces, i)) {
          consecutive++;
        } else {
          if (consecutive > 0) {
            pieces += consecutive;
            consecutive = 0;
          }

          if (isSet(wPieces, i)) {
            if (isSet(wP, i)) {
              pieces += "P";
            } else if (isSet(wN, i)) {
              pieces += "N";
            } else if (isSet(wB, i)) {
              pieces += "B";
            } else if (isSet(wR, i)) {
              pieces += "R";
            } else if (isSet(wQ, i)) {
              pieces += "Q";
            } else if (isSet(wK, i)) {
              pieces += "K";
            }
          } else {
            if (isSet(bP, i)) {
              pieces += "p";
            } else if (isSet(bN, i)) {
              pieces += "n";
            } else if (isSet(bB, i)) {
              pieces += "b";
            } else if (isSet(bR, i)) {
              pieces += "r";
            } else if (isSet(bQ, i)) {
              pieces += "q";
            } else if (isSet(bK, i)) {
              pieces += "k";
            }
          }
        }
      }
    }

    return String.join(" ", pieces, toMoveString, castle, epString, halfSinceCapture, fullMoves);
  }

  public void clear() {
    wP = 0L;
    wN = 0L;
    wB = 0L;
    wR = 0L;
    wQ = 0L;
    wK = 0L;
    bP = 0L;
    bN = 0L;
    bB = 0L;
    bR = 0L;
    bQ = 0L;
    bK = 0L;

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
    for (int rank = 7 ; rank >=0; rank--) {
      for (int file = 0; file <= 7; file++) {
        int i = fileAndRankToSquare(file, rank);

        if (isSet(wPieces, i)) {
          if (isSet(wP, i)) {
            boardString += "P ";
          } else if (isSet(wN, i)) {
            boardString += "N ";
          } else if (isSet(wB, i)) {
            boardString += "B ";
          } else if (isSet(wR, i)) {
            boardString += "R ";
          } else if (isSet(wQ, i)) {
            boardString += "Q ";
          } else if (isSet(wK, i)) {
            boardString += "K ";
          }
        } else if (isSet(bPieces, i)) {
          if (isSet(bP, i)) {
            boardString += "p ";
          } else if (isSet(bN, i)) {
            boardString += "n ";
          } else if (isSet(bB, i)) {
            boardString += "b ";
          } else if (isSet(bR, i)) {
            boardString += "r ";
          } else if (isSet(bQ, i)) {
            boardString += "q ";
          } else if (isSet(bK, i)) {
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
