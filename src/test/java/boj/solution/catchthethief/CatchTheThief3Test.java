package boj.solution.catchthethief;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.*;

/**
 * Created by biran on 11/3/16.
 */
public class CatchTheThief3Test {

  @Rule
  public final Timeout globalTimeout = Timeout.seconds(1);
  private final CatchTheThief3 solver = new CatchTheThief3();

  @Test
  public void test() {
    int[] lens = new int[] {
        0, 1, 2, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34
    };
    for (int i = 0; i < 20; i++) {
      int[] res = solver.solve(i);
      assertEquals(lens[i], res.length);
      assertTrue(new CatchTheThief().canCatch(i, res));
    }
  }

  private static class CatchTheThief {

    int[] sequence;
    int n;
    Boolean[][] memo;

    boolean canCatch(int n, int[] sequence) {
      this.n = n;
      this.sequence = sequence;
      this.memo = new Boolean[sequence.length][n];
      for (int i = 0; i < n; i++) {
        if (!canCatch(0, i)) {
          return false;
        }
      }
      return true;
    }

    private boolean canCatch(int day, int thiefPos) {
      if (day == sequence.length) {
        return false; // thief wins
      }
      if (memo[day][thiefPos] != null) {
        return memo[day][thiefPos];
      }
      boolean res;
      if (sequence[day] == thiefPos) {
        res = true;
      } else if (thiefPos > 0 && !canCatch(day + 1, thiefPos - 1)) {
        res = false;
      } else if (thiefPos < n - 1 && !canCatch(day + 1, thiefPos + 1)) {
        res = false;
      } else {
        res = true;
      }
      return memo[day][thiefPos] = res;
    }
  }
}