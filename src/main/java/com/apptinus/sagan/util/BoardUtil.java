package com.apptinus.sagan.util;

import static com.apptinus.sagan.board.Board.BB;
import static com.apptinus.sagan.board.Board.BK;
import static com.apptinus.sagan.board.Board.BN;
import static com.apptinus.sagan.board.Board.BP;
import static com.apptinus.sagan.board.Board.BQ;
import static com.apptinus.sagan.board.Board.BR;
import static com.apptinus.sagan.board.Board.EE;
import static com.apptinus.sagan.board.Board.WB;
import static com.apptinus.sagan.board.Board.WK;
import static com.apptinus.sagan.board.Board.WN;
import static com.apptinus.sagan.board.Board.WP;
import static com.apptinus.sagan.board.Board.WQ;
import static com.apptinus.sagan.board.Board.WR;
import static com.apptinus.sagan.board.Move.BLACK;
import static com.apptinus.sagan.board.Move.PROMO_B;
import static com.apptinus.sagan.board.Move.PROMO_N;
import static com.apptinus.sagan.board.Move.PROMO_R;
import static com.apptinus.sagan.board.Move.SPECIAL_PROMO;
import static com.apptinus.sagan.board.Move.WHITE;
import static com.apptinus.sagan.util.Bitops.isSet;
import static com.apptinus.sagan.util.Bitops.set;

import com.apptinus.sagan.Main;
import com.apptinus.sagan.board.Board;
import com.apptinus.sagan.board.Move;
import com.apptinus.sagan.board.Tt;
import java.util.Collections;

public class BoardUtil {

  public static Board createStartPosBoard() {
    return createStartPosBoard(Main.DEFAULT_TT_SIZE_MB);
  }

  public static Board createStartPosBoard(int ttSizeMb) {
    return createBoard("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", ttSizeMb);
  }

  public static Board createStartPosBoard(Tt tt) {
    return createBoard("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", tt);
  }

  public static Board createBoard(String fen) {
    return createBoard(fen, Main.DEFAULT_TT_SIZE_MB);
  }

  public static Board createBoard(String fen, int ttSizeMb) {
    Board board = new Board(ttSizeMb);
    setFen(board, fen);
    return board;
  }

  public static Board createBoard(String fen, Tt tt) {
    Board board = new Board(tt);
    setFen(board, fen);
    return board;
  }

  private static Board setFen(Board board, String fen) {
    // Note: Unpredictable results if fen is malformed (no sanity checking at all)

    fen = fen.trim();

    // If move counts are missing, add them first
    if (fen.split(" ").length == 4) {
      fen = fen + " 0 1";
    }

    String[] fs = fen.split(" ");

    board.toMove = fs[1].equals("w") ? WHITE : BLACK;
    board.wCastle = (fs[2].contains("K") ? 1 : 0) | (fs[2].contains("Q") ? 1 << 1 : 0);
    board.bCastle = (fs[2].contains("k") ? 1 : 0) | (fs[2].contains("q") ? 1 << 1 : 0);
    board.ep = fs[3].equals("-") ? -1 : notationToSquare(fs[3]);
    board.fPly = Integer.parseInt(fs[4]);
    // Add a ply if black to move, since the full move hasn't accounted for white's last half move
    board.ply = (Integer.parseInt(fs[5]) - 1) * 2 + (board.toMove == WHITE ? 0 : 1);

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
          board.board[emptySquare] = EE;
        }
        continue;
      }

      switch (c) {
        case 'p':
          board.pieces[BP] = set(board.pieces[BP], square);
          board.board[square] = BP;
          break;
        case 'n':
          board.pieces[BN] = set(board.pieces[BN], square);
          board.board[square] = BN;
          break;
        case 'b':
          board.pieces[BB] = set(board.pieces[BB], square);
          board.board[square] = BB;
          break;
        case 'r':
          board.pieces[BR] = set(board.pieces[BR], square);
          board.board[square] = BR;
          break;
        case 'q':
          board.pieces[BQ] = set(board.pieces[BQ], square);
          board.board[square] = BQ;
          break;
        case 'k':
          board.pieces[BK] = set(board.pieces[BK], square);
          board.board[square] = BK;
          break;
        case 'P':
          board.pieces[WP] = set(board.pieces[WP], square);
          board.board[square] = WP;
          break;
        case 'N':
          board.pieces[WN] = set(board.pieces[WN], square);
          board.board[square] = WN;
          break;
        case 'B':
          board.pieces[WB] = set(board.pieces[WB], square);
          board.board[square] = WB;
          break;
        case 'R':
          board.pieces[WR] = set(board.pieces[WR], square);
          board.board[square] = WR;
          break;
        case 'Q':
          board.pieces[WQ] = set(board.pieces[WQ], square);
          board.board[square] = WQ;
          break;
        case 'K':
          board.pieces[WK] = set(board.pieces[WK], square);
          board.board[square] = WK;
          break;
        default:
          file += (int) c;
          continue;
      }

      file++;
    } while (i < fs[0].length() - 1);

    board.wPieces |=
      board.pieces[WK]
      | board.pieces[WQ]
      | board.pieces[WR]
      | board.pieces[WB]
      | board.pieces[WN]
      | board.pieces[WP];
    board.bPieces |=
      board.pieces[BK]
      | board.pieces[BQ]
      | board.pieces[BR]
      | board.pieces[BB]
      | board.pieces[BN]
      | board.pieces[BP];
    board.allPieces |= board.wPieces | board.bPieces;

    board.zobrist = Zobrist.getZobristKey(board);

    return board;
  }

  public static String getFen(Board board) {
    String toMoveString = board.toMove == WHITE ? "w" : "b";
    String epString = board.ep == -1 ? "-" : squareToNotation(board.ep);
    String fullMoves = Integer.toString(board.ply / 2 + 1);
    String halfSinceCapture = Integer.toString(board.fPly);
    String castle = "";
    if (board.wCastle == 1) {
      castle += "K";
    } else if (board.wCastle == 2) {
      castle += "Q";
    } else if (board.wCastle == 3) {
      castle += "KQ";
    }
    if (board.bCastle == 1) {
      castle += "k";
    } else if (board.bCastle == 2) {
      castle += "q";
    } else if (board.bCastle == 3) {
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

        if (board.board[square] == EE) {
          consecutive++;
        } else {
          if (consecutive > 0) {
            piecesString += consecutive;
            consecutive = 0;
          }
          switch (board.board[square]) {
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

  public static int fileAndRankToSquare(int file, int rank) {
    return file + rank * 8;
  }

  public static int squareToRank(int sq) {
    return sq / 8;
  }

  public static int squareToFile(int sq) {
    return sq % 8;
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
    String notation = squareToNotation(Move.from(move)) + squareToNotation(Move.to(move));

    if (Move.special(move) == SPECIAL_PROMO) {
      switch (Move.promotion(move)) {
        case PROMO_N:
          notation += "n";
          break;
        case PROMO_B:
          notation += "b";
          break;
        case PROMO_R:
          notation += "r";
          break;
        default:
          notation += "q";
      }
    }

    return notation;
  }

  public static String prettyPrintBoard(Board board) {
    String boardString = "";
    for (int rank = 7; rank >= 0; rank--) {
      for (int file = 0; file <= 7; file++) {
        int i = fileAndRankToSquare(file, rank);

        if (isSet(board.wPieces, i)) {
          if (isSet(board.pieces[WP], i)) {
            boardString += "P ";
          } else if (isSet(board.pieces[WN], i)) {
            boardString += "N ";
          } else if (isSet(board.pieces[WB], i)) {
            boardString += "B ";
          } else if (isSet(board.pieces[WR], i)) {
            boardString += "R ";
          } else if (isSet(board.pieces[WQ], i)) {
            boardString += "Q ";
          } else if (isSet(board.pieces[WK], i)) {
            boardString += "K ";
          }
        } else if (isSet(board.bPieces, i)) {
          if (isSet(board.pieces[BP], i)) {
            boardString += "p ";
          } else if (isSet(board.pieces[BN], i)) {
            boardString += "n ";
          } else if (isSet(board.pieces[BB], i)) {
            boardString += "b ";
          } else if (isSet(board.pieces[BR], i)) {
            boardString += "r ";
          } else if (isSet(board.pieces[BQ], i)) {
            boardString += "q ";
          } else if (isSet(board.pieces[BK], i)) {
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
