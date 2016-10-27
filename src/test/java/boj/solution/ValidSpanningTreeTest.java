package boj.solution;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.*;

public class ValidSpanningTreeTest {

  @Rule
  public final Timeout globalTimeout = Timeout.seconds(1);
  private final ValidSpanningTree solver = new ValidSpanningTree();

  @Test
  public void test1() {
    assertTrue(solver.isValid(1, new int[][] {}));
    assertFalse(solver.isValid(2, new int[][] {}));
    assertTrue(solver.isValid(2, new int[][] {
        {0,1}
    }));
    assertFalse(solver.isValid(3, new int[][] {
        {0,1}
    }));
    assertTrue(solver.isValid(3, new int[][] {
        {0,1},
        {1,2},
    }));
    assertFalse(solver.isValid(3, new int[][] {
        {0,1},
        {1,2},
        {0,2},
    }));
    assertTrue(solver.isValid(4, new int[][] {
        {0,1},
        {1,2},
        {2,3},
    }));
    assertTrue(solver.isValid(4, new int[][] {
        {0,1},
        {1,2},
        {1,3},
    }));
    assertTrue(solver.isValid(4, new int[][] {
        {0,1},
        {1,2},
        {0,3},
    }));
    assertFalse(solver.isValid(4, new int[][] {
        {0,1},
        {1,2},
        {0,2},
    }));
    assertTrue(solver.isValid(4, new int[][] {
        {0,1},
        {0,2},
        {0,3},
    }));
  }

  @Test
  public void test2() {
    int n = 10000;
    int[][] edge = new int[n - 1][];
    for (int i = 0; i < edge.length; i++) {
      edge[i] = new int[] {i, i + 1};
    }
    assertTrue(solver.isValid(n, edge));
  }
}
