package boj.solution.generatesubstring;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Created by biran on 11/12/16.
 */
class GenerateSubstringTest {

  private final Random random = new Random(0);

  @Test
  public void test() {
    test("abcdefg", "aabbcc", 3, true);
    test("abcdefg", "aabbcc", 2, false);
    for (int i = 10; i < 100; i++) {
      String s = randStr(i);
      int from = random.nextInt(i);
      int to = random.nextInt(i);
      if (from > to) {
        int t = from;
        from = to;
        to = t;
      }
      String p = randomAdd(s.substring(from, to), random.nextInt(i / 2));
      int minN = minN(s, p);
      test(s, p, minN - 1, false);
      test(s, p, minN, true);
    }
  }

  private String randomAdd(String s, int n) {
    StringBuilder sb = new StringBuilder(s);
    for (int i = 0; i < n; i++) {
      sb.insert(random.nextInt(sb.length() + 1), (char) (random.nextInt(26) + 'a'));
    }
    return sb.toString();
  }

  private String randStr(int n) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < n; i++) {
      sb.append((char) (random.nextInt(26) + 'a'));
    }
    return sb.toString();
  }

  private void test(String s, String p, int n, boolean ans) {
    assertEquals(ans, new GenerateSubstring().canGenerate(s, p, n));
  }

  Integer[][] dp;

  int minN(String s, String p) {
    dp = new Integer[s.length() + 1][p.length() + 1];
    int res = 0;
    for (int i = 0; i < s.length(); i++) {
      res = Math.max(res, dfs(s, p, i, p.length() - 1));
    }
    return p.length() - res;
  }

  private int dfs(String s, String p, int i, int j) {
    if (i < 0 || j < 0) {
      return 0;
    }
    if (dp[i][j] != null) {
      return dp[i][j];
    }
    int res = dfs(s, p, i, j - 1);
    if (s.charAt(i) == p.charAt(j)) {
      res = Math.max(res, dfs(s, p, i - 1, j - 1) + 1);
    }
    return res;
  }
}