package com.apptinus.sagan.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TtTest {

  @Test
  public void insertAndExtractTest() {
    Tt tt = new Tt(1);

    tt.set(Long.MAX_VALUE, 63, 3, Search.INFINITY, Move.m(63, 62, 3, 3));

    assertFalse(tt.probe(123L));
    assertTrue(tt.probe(Long.MAX_VALUE));

    assertEquals(63, tt.getProbedDepth());
    assertEquals(3, tt.getProbedEvalType());
    assertEquals(Search.INFINITY, tt.getProbedEval());
    assertEquals(Move.m(63, 62, 3, 3), tt.getProbedMove());

    tt.set(0L, 0, 0, -Search.INFINITY, Move.m(0, 1, 0, 0));

    assertFalse(tt.probe(123L));
    assertTrue(tt.probe(0L));

    assertEquals(0, tt.getProbedDepth());
    assertEquals(0, tt.getProbedEvalType());
    assertEquals(-Search.INFINITY, tt.getProbedEval());
    assertEquals(Move.m(0, 1, 0, 0), tt.getProbedMove());
  }
}
