package com.apptinus.sagan.util;

import com.apptinus.sagan.board.Board;
import com.apptinus.sagan.board.Move;
import com.apptinus.sagan.board.MoveGen;
import com.apptinus.sagan.board.Search;
import com.apptinus.sagan.board.SearchSupervisor;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class EpdRunner {

  public static void runEpdTestset(String pathToEpd, int depth, int ms) throws IOException {
    List<EpdBestMove> positions = positions(pathToEpd);
    MoveGen.init();

    List<String> results = new ArrayList<>();

    for (EpdBestMove pos : positions) {
      System.out.println("Running position: " + pos.fen);
      String resultString = "Position: " + pos.fen + "\n";
      Search.Eval searchResult =
          Search.search(
              BoardUtil.createBoard(pos.fen), new SearchSupervisor(depth, 0, 0, ms, false));

      Board board = BoardUtil.createBoard(pos.fen);
      if (searchResult.line[0] == 0) {
        resultString +=
            "Failed to find move(s): " + String.join(",", pos.bm) + " instead gave nothing";
      } else {

        Move[] moves = new Move[256];
        for (int idx = 0; idx < 256; idx++) moves[idx] = new Move();
        int totalMoves = MoveGen.genAllLegalMoves(board, moves, 0);
        String shortNotationMove =
            BoardUtil.moveToShortNotation(searchResult.line[0], totalMoves, moves, board);
        if (pos.bm.isEmpty()) {
          if (!pos.am.contains(shortNotationMove)) {
            resultString +=
                "Successfully avoided move(s): "
                    + String.join(",", pos.am)
                    + " with "
                    + shortNotationMove
                    + "\n";
          } else {
            resultString +=
                "Failed to avoid move(s): "
                    + String.join(",", pos.am)
                    + " since it chose "
                    + shortNotationMove
                    + "\n";
          }
        } else {
          if (pos.bm.contains(shortNotationMove)) {
            resultString += "Successfully found move: " + shortNotationMove + "\n";
          } else {
            resultString +=
                "Failed to find move(s): "
                    + String.join(",", pos.bm)
                    + " instead gave "
                    + shortNotationMove
                    + "\n";
          }
        }
      }

      results.add(resultString);
    }

    long success = results.stream().filter(result -> result.contains("Success")).count();
    long failed = results.stream().filter(result -> result.contains("Failed")).count();

    results.forEach(System.out::println);

    System.out.println("Found " + success + "/" + (success + failed) + " moves");
  }

  private static List<EpdBestMove> positions(String pathToEpd) throws IOException {
    return Files.lines(Paths.get(pathToEpd))
        .map(
            l -> {
              Pattern p = Pattern.compile("^([^ ]+ [^ ]+ [^ ]+ [^ ]+).*(bm|am) ([^;]+);.*$");
              Matcher m = p.matcher(l);

              if (!m.matches()) {
                throw new IllegalArgumentException("Couldn't parse line in epd: " + l);
              }

              if (m.group(2).equals("bm")) {
                return new EpdBestMove(
                    Arrays.asList(m.group(3).replace("+", "").split(" ")),
                    new ArrayList<>(),
                    m.group(1));
              } else {
                return new EpdBestMove(
                    new ArrayList<>(),
                    Arrays.asList(m.group(3).replace("+", "").split(" ")),
                    m.group(1));
              }
            })
        .collect(Collectors.toList());
  }

  public static class EpdBestMove {
    private List<String> bm;
    private List<String> am;
    private String fen;

    private EpdBestMove(final List<String> bm, List<String> am, final String fen) {
      this.bm = bm;
      this.am = am;
      this.fen = fen;
    }
  }
}
