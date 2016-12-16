package boj.solution.repeatcycle;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.assertArrayEquals;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
public class RepeatCycle2dTest {

  @Rule
  public final Timeout globalTimeout = Timeout.seconds(1);
  public final RepeatCycle2d solver = new RepeatCycle2d();

  @Test
  public void test() {
    test(new char[][] {{1}}, 10, 10);
    test(new char[][] {{1,2}}, 10, 10);
    test(new char[][] {{1,2}, {3,4}}, 10, 10);
    test(new char[][] {{1,2,1,2}, {3,3,4,3}}, 10, 10);
    test(new char[][] {{1,2,1,2}, {3,3,4,3}}, 10, 10);
    test(new char[][] {{1,2,1,4}, {3,3,3,3}}, 10, 10);
    test(new char[][] {
        {1,1,1,1,1},
        {2,2,2,2,2},
        {1,1,2,2,1},
        {2,2,2,1,1},
        {2,1,2,1,2},
    }, 10, 10);
    test(new char[][] {
        {1,1,1,1,1},
        {2,2,2,2,2},
        {1,1,2,2,1},
        {2,2,2,1,1},
        {2,1,2,1,2},
    }, 200, 200);
  }

  private void test(char[][] s, int rown, int coln) {
    int[] exp = new int[] {s.length, s[0].length};
    int[] actual = solver.solve(repeat(s, rown, coln));
    assertArrayEquals(exp, actual);
  }

  private char[][] repeat(char[][] m, int rown, int coln) {
    char[][] res = new char[m.length * rown][m[0].length * coln];
    for (int i = 0; i < res.length; i++) {
      for (int j = 0; j < res[i].length; j++) {
        res[i][j] = m[i % m.length][j % m[0].length];
      }
    }
    return res;
  }
}
