package com.apptinus.sagan;

import ch.qos.logback.classic.Logger;
import com.apptinus.sagan.board.Board;
import com.apptinus.sagan.board.Evaluation;
import com.apptinus.sagan.board.Move;
import com.apptinus.sagan.board.MoveGen;
import com.apptinus.sagan.board.Search;
import com.apptinus.sagan.board.SearchSupervisor;
import com.apptinus.sagan.util.BoardUtil;
import com.apptinus.sagan.util.EpdRunner;
import com.apptinus.sagan.util.Perft;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import org.slf4j.LoggerFactory;

public class Uci {
  private static Logger logger = (Logger) LoggerFactory.getLogger(Uci.class);

  public static BufferedReader reader =
      new BufferedReader(new InputStreamReader(System.in)); // Initialize the reader

  /**
   * Starts a loop that checks for input, used for testing mainly, when no engine is calling for
   * application (used in console window)
   */
  public static void lineInput() throws IOException {
    logger.debug("Enter line input");
    String testsetPath = null;
    Board board = BoardUtil.createStartPosBoard();
    // position

    System.out.println("\nWelcome to Sagan " + Main.VERSION + ". Type 'help' for commands.");
    for (; ; ) {

      System.out.print("\n->");
      String command = reader.readLine();

      if (command.equals("uci")) {
        uci();
        break;
      } else if ("quit".equals(command)) {
        logger.debug("Quit command received, exiting");
        System.exit(0);
      } else if (command.startsWith("help")) {
        displayHelp(command);
      } else if (command.startsWith("setboard ")) {
        board = BoardUtil.createBoard(command.substring(9)); // Insert the submitted position
      } else if (command.startsWith("testset")) {

        if (!(new File(command.split(" ")[1])).exists()) {
          System.out.println("Can't find file: " + command.split(" ")[1]);
        } else {
          testsetPath = command.split(" ")[1];
          System.out.println("Test set specified as: " + command.split(" ")[1]);
        }
      } else if (command.startsWith("perft")) {
        String[] commands = command.split(" ");
        if (commands.length == 3 && !commands[2].equals("s")) {
          // Erroneous input, ignore
        } else if (commands.length == 3 && commands[2].equals("s")) {
          // Eroneous input, ignore
        } else {
          System.out.println(Perft.perft(board, Integer.parseInt(commands[1]), false));
        }
      } else if (command.startsWith("search")) {
        String[] commands = command.split(" ");

        if (commands.length < 3) {
          System.out.println("Malformed search command, see help for details");
        } else if (Integer.parseInt(commands[1]) <= 0) {
          System.out.println("Depth needs to be higher than 0.");
        } else if (!commands[2].equals("d") && !commands[2].equals("t")) {
          System.out.println("Missing option. Needs d (depth) or t (time).");
        } else if (commands[2].equals("t")) {
          long time = System.currentTimeMillis();
          Search.search(board, new SearchSupervisor(0, 0, 0, Integer.parseInt(commands[1]), false));
          System.out.println("Time: " + Perft.convertMillis((System.currentTimeMillis() - time)));
        } else if (commands[2].equals("d")) {
          long time = System.currentTimeMillis();
          Search.search(board, new SearchSupervisor(Integer.parseInt(commands[1]), 0, 0, 0, false));
          System.out.println("Time: " + Perft.convertMillis((System.currentTimeMillis() - time)));
        }
      } else if (command.equals("eval")) {
        System.out.println(Evaluation.getEvalInfo(board));
      } else if (command.startsWith("divide ")) {
        if (Integer.parseInt(command.substring(7)) <= 0)
          System.out.println("Depth needs to be higher than 0,");
        else System.out.println(Perft.perft(board, Integer.parseInt(command.substring(7)), true));
      } else if (command.startsWith("runtest")) {
        String[] commands = command.split(" ");
        if (commands.length != 4) {
          System.out.println("Malformed runtest command, see help for details");
        } else {
          System.out.println("Starting test with file: " + commands[0]);
          if (commands[3].equals("d")) {
            EpdRunner.runEpdTestset(commands[1], Integer.parseInt(commands[2]), 0);
          } else if (commands[3].equals("t")) {
            EpdRunner.runEpdTestset(commands[1], 0, Integer.parseInt(commands[2]));
          } else {
            System.out.println(
                "Malformed runtest command, see help for details (Needs d (depth) or t (time))");
          }
        }
      } else {
        System.out.println("Command not found.");
      }
    }
  }

  private static void displayHelp(String help) {
    if (help == null || help.length() == 0 || help.split(" ").length > 2) return;

    if (help.split(" ").length == 1) {
      System.out.println("quit ................... ->  Exit the program");
      System.out.println("help ................... ->  Shows this menu");
      System.out.println("help [command] ......... ->  Further information about a command");
      System.out.println("");
      System.out.println("setboard [fen] ......... ->  Set to board to the fen string");
      System.out.println("testset [path] ......... ->  Define a testset path");
      System.out.println("");
      System.out.println("perft <depth <s>> ...... ->  Run a perft check");
      System.out.println("divide [depth] ......... ->  Run a divide check");
      System.out.println("");
      System.out.println("eval ................... ->  Static evaluation breakdown");
      System.out.println("");
      System.out.println("search [value] [d/t] ... ->  Search the position");
      System.out.println("");
      System.out.println("runtest [path] [v] [d/t] ->  Run the a test on the specified positions");
    } else if ("help quit".equals(help)) {
      System.out.println("quit");
      System.out.println("Exits Mediocre.");
    } else if ("help help".equals(help)) {
      System.out.println("help");
      System.out.println("Help help help?");
    } else if ("help setboard".equals(help)) {
      System.out.println("setboard");
      System.out.println("Sets the board to the fen-position.");
      System.out.println("");
      System.out.println("Example:");
      System.out.println("setboard rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
    } else if ("help testset".equals(help)) {
      System.out.println("testset");
      System.out.println(
          "Sets the path to a test set. Can be epd or fen positions. If just the filename");
      System.out.println("is specified the directory where Mediocre is located will be used.");
      System.out.println("");
      System.out.println("Example:");
      System.out.println("testset testpositions.epd");
      System.out.println("testset C:\\tests\\testpositions.epd");
    } else if ("help perft".equals(help)) {
      System.out.println("perft");
      System.out.println(
          "Runs a perft test on the position on the board (see setboard) to the specified depth");
      System.out.println("");
      System.out.println("Example:");
      System.out.println("perft 10");
    } else if ("help divide".equals(help)) {
      System.out.println("divide");
      System.out.println(
          "Runs a perft test to the specified depth on the current position on the board.");
      System.out.println("Will display node counts on a move basis (good for debugging).");
      System.out.println("");
      System.out.println("Example:");
      System.out.println("divide 10");
    } else if ("help eval".equals(help)) {
      System.out.println("eval");
      System.out.println("Runs a static evaluation on the current position on the board.");
      System.out.println("(will not do any search at all)");
      System.out.println("");
      System.out.println("Example:");
      System.out.println("eval");
    } else if ("help search".equals(help)) {
      System.out.println("search");
      System.out.println("Runs a search on the current position on the board to either specified");
      System.out.println("depth (d) or time (t), time is given in miliseconds.");
      System.out.println("Output is the uci format.");
      System.out.println("");
      System.out.println("Example:");
      System.out.println("search 5 d");
      System.out.println("search 2000 t");
    } else if ("help runtest".equals(help)) {
      System.out.println("runtest (not implemented yet)");
      System.out.println(
          "Runs all positions in the specified testset to either depth (d) or time (t),");
      System.out.println(
          "time is given in miliseconds. If the positions are in the epd format it will");
      System.out.println("verify the moves and give a total score.");
      System.out.println("");
      System.out.println("Example:");
      System.out.println("runtest /home/user/testset.epd 5 d");
      System.out.println("runtest /home/user/testset.epd 2000 t");
    }
  }

  /**
   * Handles input from the UCI application and also sends moves and settings from the engine
   *
   * <p>I had some much appreciated help with this from Yves Catineau
   */
  public static void uci() throws IOException {
    logger.debug("Enter UCI protocol");
    Board board = BoardUtil.createStartPosBoard();

    boolean useBook = false;
    boolean defaultPonder = false;

    @SuppressWarnings("unused")
    String openingLine = ""; // Holds the opening line so far
    int searchDepth = 0; // Initialize search depth
    int movetime = 0; // Initialize fixed time per move
    System.out.println("");
    System.out.println("id name Sagan " + Main.VERSION);
    System.out.println("id author Jonatan Pettersson");
    //    System.out.println(
    //        "option name Hash type spin default " + Settings.DEFAULT_HASH_SIZE + " min 1 max
    // 512");
    //    System.out.println(
    //        "option name EvalHash type spin default "
    //            + Settings.DEFAULT_EVAL_HASH_SIZE
    //            + " min 1 max 32");
    //    System.out.println(
    //        "option name PawnHash type spin default "
    //            + Settings.DEFAULT_PAWN_HASH_SIZE
    //            + " min 1 max 32");
    System.out.println("option name Ponder type check default " + defaultPonder);
    System.out.println("option name OwnBook type check default " + useBook);
    System.out.println("uciok");

    // This is the loop in which we look for incoming commands from Uci
    for (; ; ) {
      String command = reader.readLine(); // Receive the input
      logger.debug("Received command: >" + command + "<");

      if ("uci".equals(command)) {
        System.out.println("id name Sagan " + Main.VERSION);
        System.out.println("id author Jonatan Pettersson");
        System.out.println("uciok");
      }

      if ("isready".equals(command)) {
        System.out.println("readyok");
      }

      if ("quit".equals(command)) {
        logger.debug("Quit command received, exiting");
        System.exit(0);
      }

      if (command.startsWith("setoption")) {
        String[] commandSplit = command.split(" ");
        if (commandSplit.length == 5) {
          try {
            if (commandSplit[2].equals("Ponder")) {
              boolean isUse = Boolean.parseBoolean(commandSplit[4]);
              logger.debug("Setting use ponder to " + isUse);
              defaultPonder = isUse;
            } /* else if (commandSplit[2].equals("Hash")) {
                int size = Integer.parseInt(commandSplit[4]);
                logger.debug("Setting TT size to " + size);
                Settings.getInstance().setTranspositionTableSize(size);
              } else if (commandSplit[2].equals("EvalHash")) {
                int size = Integer.parseInt(commandSplit[4]);
                logger.debug("Setting evalTT size to " + size);
                Settings.getInstance().setEvalTableSize(size);
              } else if (commandSplit[2].equals("PawnHash")) {
                int size = Integer.parseInt(commandSplit[4]);
                logger.debug("Setting pawn TT size to " + size);
                Settings.getInstance().setPawnTableSize(size);
              } else if (commandSplit[2].equals("OwnBook")) {
                boolean isUse = Boolean.parseBoolean(commandSplit[4]);
                Settings.getInstance().setUseOwnBook(isUse);
                useBook = isUse;
              }*/
          } catch (Exception e) {
            System.err.println("Failure when parsing set option: " + e.getMessage());
          }
        }
      }

      // A new game is starting, can be both from start and inserted
      // position
      if ("ucinewgame".equals(command)) {
        board = BoardUtil.createStartPosBoard();
        searchDepth = 0;
        movetime = 0;
      }

      // Using the UCI protocol we receive the moves by the opponent
      // in a 'position' string, this string comes with a FEN-string (or
      // states "startpos")
      // followed by the moves played on the board.
      //
      // The UCI protocol states that the position should be set on the board and all moves played
      if (command.startsWith("position")) {
        // Set the position on the board

        if (command.indexOf("startpos") != -1) {
          // Starting from start position

          openingLine = ""; // Initialize opening line
          board = BoardUtil.createStartPosBoard(board.tt); // Get a new board but retain tt
        } else {
          // Starting from fen string
          String fen = extractFEN(command);

          useBook = false; // The position was not played from the start so don't attempt to use
          // the opening book
          openingLine = "none"; // Make sure we can't receive a book move
          board = BoardUtil.createBoard(fen, board.tt); // Insert the FEN, but retain tt
        }

        // Play moves if there are any

        String[] moves = extractMoves(command);

        if (moves != null) // There are moves to be played
        {
          openingLine = ""; // Get ready for new input
          for (int i = 0; i < moves.length; i++) {

            int moveToMake = receiveMove(moves[i], board);
            if (moveToMake == 0) {
              System.out.println(
                  "Error in position string. Move " + moves[i] + " could not be found.");
            } else {
              board.make(moveToMake); // Make the move on the
            }
          }
        }
      }

      // The GUI has told us to start calculating on the position, if the
      // opponent made a move this will have been caught in the 'position'
      // string
      if (command.startsWith("go")) {
        System.out.println("Received go at" + System.currentTimeMillis());
        int wtime = 0; // Initialize the times
        int btime = 0;
        int winc = 0;
        int binc = 0;
        boolean ponder = false;

        // If infinite time, set the times to 'infinite'
        if ("infinite".equals(command.substring(3))) {
          wtime = 99990000;
          btime = 99990000;
          winc = 0;
          binc = 0;
        } else if ("depth".equals(command.substring(3, 8))) {
          try {
            searchDepth = Integer.parseInt(command.substring(9));
          } catch (NumberFormatException ex) {
            logger.error("Malformed search depth");
          }
        } else if ("movetime".equals(command.substring(3, 11))) {
          try {
            movetime = Integer.parseInt(command.substring(12));
          } catch (NumberFormatException ex) {
            logger.error("Malformed move time");
          }
        } else // Extract the times since it's not infinite time
        // controls
        {
          String[] splitGo = command.split(" ");
          for (int goindex = 1; goindex < splitGo.length - 1; goindex++) {

            try {
              if ("wtime".equals(splitGo[goindex])) wtime = Integer.parseInt(splitGo[goindex + 1]);
              else if ("btime".equals(splitGo[goindex]))
                btime = Integer.parseInt(splitGo[goindex + 1]);
              else if ("winc".equals(splitGo[goindex]))
                winc = Integer.parseInt(splitGo[goindex + 1]);
              else if ("binc".equals(splitGo[goindex]))
                binc = Integer.parseInt(splitGo[goindex + 1]);
              else if ("ponder".equals(splitGo[goindex + 1])) {
                ponder = true;
              }
            }

            // Catch possible errors so the engine doesn't crash
            // if the go command is flawed
            catch (ArrayIndexOutOfBoundsException ex) {
              logger.error("Malformed go command");
            } catch (NumberFormatException ex) {
              logger.error("Malformed go command");
            }
          }
        }

        // We now have the times so set the engine's time and increment
        // to whatever side he is playing (the side to move on the
        // board)

        int engineTime;
        int engineInc;
        if (board.toMove == 1) {
          engineTime = wtime;
          engineInc = winc;
        } else {
          engineTime = btime;
          engineInc = binc;
        }

        // The engine's turn to move, so find the best line
        Search.Eval bestLine = new Search.Eval();

        try {
          bestLine =
              Search.search(
                  board,
                  new SearchSupervisor(searchDepth, engineTime, engineInc, movetime, ponder));
        } catch (Exception e) {
          logger.error("Error while searching", e);
        }
        if (bestLine.line[0] != 0) // We have found a move to make
        {
          board.make(bestLine.line[0]); // Make best move on the
          // board

          if (defaultPonder & bestLine.line[1] != 0) {
            System.out.println(
                "bestmove "
                    + (BoardUtil.moveToNotation(bestLine.line[0]))
                    + " ponder "
                    + (BoardUtil.moveToNotation(bestLine.line[1])));
          } else {
            System.out.println("bestmove " + (BoardUtil.moveToNotation(bestLine.line[0])));
          }
        }
      }
    }
  } // END uci()

  /**
   * Used by uci mode
   *
   * <p>Extracts the fen-string from a position-string, do not call if the position string has
   * 'startpos' and not fen
   *
   * <p>Throws 'out of bounds' exception so faulty fen string won't crash the program
   *
   * @param position The position-string
   * @return String Either the start position or the fen-string found
   */
  public static String extractFEN(String position) throws ArrayIndexOutOfBoundsException {

    String[] splitString = position.split(" "); // Splits the string at the
    // spaces

    String fen = "";
    if (splitString.length < 6) {
      System.out.println("Error: position fen command faulty");
    } else {
      fen += splitString[2] + " "; // Pieces on the board
      fen += splitString[3] + " "; // Side to move
      fen += splitString[4] + " "; // Castling rights
      fen += splitString[5] + " "; // En passant
      if (splitString.length >= 8) {
        fen += splitString[6] + " "; // Half moves
        fen += splitString[7]; // Full moves
      }
    }

    return fen;
  } // END extractFEN()

  /**
   * Used by uci mode
   *
   * <p>Extracts the moves at the end of the 'position' string sent by the UCI interface
   *
   * <p>Originally written by Yves Catineau and modified by Jonatan Pettersson
   *
   * @return moves The last part of 'position' that contains the moves
   */
  private static String[] extractMoves(String position) {
    String pattern = " moves ";
    int index = position.indexOf(pattern);
    if (index == -1) return null; // No moves found

    String movesString = position.substring(index + pattern.length());
    String[] moves = movesString.split(" "); // Create an array with the
    // moves
    return moves;
  } // END extractMoves()

  /**
   * Takes an inputted move-string and matches it with a legal move generated from the board
   *
   * @param move The inputted move
   * @param board The board on which to find moves
   * @return int The matched move
   */
  public static int receiveMove(String move, Board board) throws IOException {

    Move[] legalMoves = new Move[256];
    for (int i = 0; i < 256; i++) legalMoves[i] = new Move();
    int totalMoves = MoveGen.genAllLegalMoves(board, legalMoves, 0); // All moves

    for (int i = 0; i < totalMoves; i++) {
      if (BoardUtil.moveToNotation(legalMoves[i].move).equals(move)) {
        return legalMoves[i].move;
      }
    }

    // If no move was found return null
    return 0;
  } // END receiveMove

  /** Checks the board and the repetition table if the game is over */
  //  public static String isGameOver(Board board, int[] gameHistory, int gameHistoryIndex) {
  //    Move[] legalMoves = new Move[256];
  //    for (int i = 0; i < 256; i++) legalMoves[i] = new Move();
  //    if (MoveGen.genAllLegalMoves(board, legalMoves, 0) == 0) {
  //      if (board.isInCheck(board.toMove)) {
  //        if (board.toMove == WHITE) {
  //          return "0-1 (Black mates)";
  //        } else {
  //          return "1-0 (White mates)";
  //        }
  //      } else {
  //        return "1/2-1/2 (Stalemate)";
  //      }
  //    }
  //
  //    if (board.fPly >= 100) {
  //      return "1/2-1/2 (50 moves rule)";
  //    }
  //
  //    for (int i = 0; i < gameHistoryIndex; i++) {
  //      int repetitions = 0;
  //      for (int j = i + 1; j < gameHistoryIndex; j++) {
  //        if (gameHistory[i] == gameHistory[j]) repetitions++;
  //        if (repetitions == 2) {
  //          return "1/2-1/2 (Drawn by repetition)";
  //        }
  //      }
  //    }
  //
  //    if (Evaluation.drawByMaterial(board)) {
  //      return "1/2-1/2 (Drawn by material)";
  //    }
  //
  //    return "no";
  //  } // END isGameOver
}
