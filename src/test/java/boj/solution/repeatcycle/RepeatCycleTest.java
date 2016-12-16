package boj.solution.repeatcycle;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.*;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
public class RepeatCycleTest {

  @Rule
  public final Timeout globalTimeout = Timeout.seconds(1);
  private RepeatCycle solver = new RepeatCycle();

  @Test
  public void test() {
    test("aaa", 1);
    test("aba", 2);
    test("abab", 2);
    test("abcabcabc", 3);
    test("abcabcabcd", 10);
    test("abcabcabcda", 10);
    test("abcabcabcab", 3);
    test("aaaaaaaaab", 10);

    test(repeat("aaaaaab", 10000), 7);
    test(repeat("aaaaaab", 10000) + "c", 70001);
    test(repeat("aaaaaabaaaaaac", 10000), 14);
    test(repeat(repeat("a", 1000) + "b", 1000), 1001);
  }

  private String repeat(String s, int n) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < n; i++) {
      sb.append(s);
    }
    return sb.toString();
  }

  private void test(String s, int exp) {
    int actual = solver.solve(s);
    assertEquals("Expect " + exp + " for input " + s + ", actual: " + actual, exp, actual);
  }
}
