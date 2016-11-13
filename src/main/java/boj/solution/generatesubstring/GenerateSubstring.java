package boj.solution.generatesubstring;

/**
 * Created by biran on 11/12/16.
 */
class GenerateSubstring {

  Integer[][] dp;

  boolean canGenerate(String s, String p, int n) {
    dp = new Integer[s.length() + 1][p.length() + 1];
    int res = 0;
    for (int i = 0; i < s.length(); i++) {
      res = Math.max(res, dfs(s, p, i, p.length() - 1));
    }
    return res + n >= p.length();
  }

  // longest suffix in s[0...i] that is a sub sequence of p[0...j]
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
