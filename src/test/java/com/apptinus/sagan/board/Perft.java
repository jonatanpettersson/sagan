package com.apptinus.sagan.board;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class Perft {

  private static final List<Map<String, List<Long>>> positions =
      Arrays.<Map<String, List<Long>>>asList(
          new HashMap<String, List<Long>>() {
            {
              put(
                  "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1",
                  Arrays.asList(
                      20L, 400L, 8902L, 197281L, 4865609L, 119060324L, 3195901860L, 84998978956L));
              put(
                  "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1",
                  Arrays.asList(48L, 2039L, 97862L, 4085603L, 193690690L, 8031647685L));
              put(
                  "rnbq1k1r/pp1Pbppp/2p5/8/2B5/8/PPP1NnPP/RNBQK2R w KQ - 1 8",
                  Arrays.asList(44L, 1486L, 62379L, 2103487L, 89941194L));
            }
          });

  @Test
  public void base() {}
}
