package com.apptinus.sagan.board;

import com.apptinus.sagan.util.BoardUtil;
import com.apptinus.sagan.util.Perft;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpeedTest {
  private static Logger logger = LoggerFactory.getLogger(SpeedTest.class);

  private static final int DEPTH = 3;
  private static final String TEST_SET_MIDDLE = "/speedtestmiddle";
  private static final String TEST_SET_ENDING = "/speedtestending";
  private List<String> positionsMiddle;
  private List<String> positionsEnding;

  @Before
  public void setUp() throws Exception {
    loadMiddle();
    loadEnding();
  }

  private void loadMiddle() throws Exception {
    positionsMiddle = new ArrayList<>();
    URL url = this.getClass().getResource(TEST_SET_MIDDLE);
    File testSetFile = new File(url.getFile());
    FileInputStream fstream = new FileInputStream(testSetFile);
    // Get the object of DataInputStream
    DataInputStream in = new DataInputStream(fstream);
    BufferedReader br = new BufferedReader(new InputStreamReader(in));
    String strLine;
    // Read File Line By Line
    while ((strLine = br.readLine()) != null) {
      positionsMiddle.add(strLine);
    }
    in.close();
  }

  private void loadEnding() throws Exception {
    positionsEnding = new ArrayList<>();
    URL url = this.getClass().getResource(TEST_SET_ENDING);
    File testSetFile = new File(url.getFile());
    FileInputStream fstream = new FileInputStream(testSetFile);
    // Get the object of DataInputStream
    DataInputStream in = new DataInputStream(fstream);
    BufferedReader br = new BufferedReader(new InputStreamReader(in));
    String strLine;
    // Read File Line By Line
    while ((strLine = br.readLine()) != null) {
      positionsEnding.add(strLine);
    }
    in.close();
  }

  @Test
  public void speedTestMiddle() {
    Board board;

    long totalTime = System.currentTimeMillis();
    int count = 0;
    for (String pos : positionsMiddle) {
      board = BoardUtil.createBoard(pos);
      long thisTime = System.currentTimeMillis();
      Search.search(board, new SearchSupervisor(DEPTH, 0, 0, 0, false));
      logger.debug(
          "Position "
              + (++count)
              + "/"
              + positionsEnding.size()
              + " Time: "
              + Perft.convertMillis((System.currentTimeMillis() - thisTime))
              + " Total time: "
              + Perft.convertMillis((System.currentTimeMillis() - totalTime)));
    }

    logger.debug("Time: " + Perft.convertMillis((System.currentTimeMillis() - totalTime)));
  }

  @Test
  public void speedTestEnding() {
    Board board;

    long totalTime = System.currentTimeMillis();
    int count = 0;
    for (String pos : positionsEnding) {

      board = BoardUtil.createBoard(pos);
      long thisTime = System.currentTimeMillis();
      Search.search(board, new SearchSupervisor(DEPTH, 0, 0, 0, false));
      logger.debug(
          "Position "
              + (++count)
              + "/"
              + positionsEnding.size()
              + " Time: "
              + Perft.convertMillis((System.currentTimeMillis() - thisTime))
              + " Total time: "
              + Perft.convertMillis((System.currentTimeMillis() - totalTime)));
    }

    logger.debug("Time: " + Perft.convertMillis((System.currentTimeMillis() - totalTime)));
  }
}
