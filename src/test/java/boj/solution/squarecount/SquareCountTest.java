package boj.solution.squarecount;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.*;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
public class SquareCountTest {

  @Rule
  public final Timeout globalTimeout = Timeout.seconds(1);

  @Test
  public void test() {
    test(4, 3, 0, 20);
    test(2, 2, 1, 3);
    test(2, 2, 4, 1);
    test(5, 5, 6, 25);
    test(6, 6, 10, 39);
    test(10, 10, 30, 105);
    test(100, 200, 300, 125066);
    test(1000, 2000, 123456, 6111098);
  }

  private int[][] black(int k, int n, int m) {
    int seed = 89513;
    int[][] res = new int[k][2];
    for (int i = 0; i < k; i++) {
      int t = Math.abs(seed) % (m * n);
      res[i][0] = t / m;
      res[i][1] = t % m;
      seed *= 19891121;
      seed += 12345677;
    }
    return res;
  }

  void test(int n, int m, int k, long ans) {
    assertEquals(ans, new SquareCount().count(n, m, black(k, n, m)));
  }
}
