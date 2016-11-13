package boj.solution.coins;

/**
 * Created by biran on 11/10/16.
 */
public class Coins4 {

  Integer[][] dp;
  int[] prefix;

  int solve(int[] a) {
    if (a.length == 0) {
      return 0;
    }
    dp = new Integer[a.length][a.length];
    prefix = new int[a.length];
    prefix[0] = a[0];
    for (int i = 1; i < a.length; i++) {
      prefix[i] = a[i] + prefix[i - 1];
    }
    return dfs1(a, 0, a.length - 1);
  }

  private int dfs1(int[] a, int i, int j) {
    if (i > j) {
      return 0;
    }
    if (dp[i][j] != null) {
      return dp[i][j];
    }
    int sum = prefix[j] - prefix[i] + a[i];
    return dp[i][j] = sum - Math.min(dfs2(a, i + 1, j), dfs2(a, i, j - 1));
  }

  private int dfs2(int[] a, int i, int j) {
    if (i > j) {
      return 0;
    }
    int sum = prefix[j] - prefix[i] + a[i];
    return sum - Math.min(
        dfs1(a, i + 2, j),
        Math.min(
            dfs1(a, i + 1, j - 1),
            dfs1(a, i, j - 2)));
  }
}
