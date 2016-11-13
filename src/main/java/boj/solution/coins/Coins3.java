package boj.solution.coins;

/**
 * Created by biran on 11/10/16.
 */
public class Coins3 {

  int [][][] dp;
  int[] prefix;

  int solve(int[] a) {
    if (a.length == 0) {
      return 0;
    }
    dp = new int[a.length][a.length][];
    prefix = new int[a.length];
    prefix[0] = a[0];
    for (int i = 1; i < a.length; i++) {
      prefix[i] = a[i] + prefix[i - 1];
    }
    return dfs(a, 0, a.length - 1)[0];
  }

  private int[] dfs(int[] a, int i, int j) {
    if (i > j) {
      return new int[] {0, 0, 0};
    }
    if (dp[i][j] != null) {
      return dp[i][j];
    }
    int[] res1 = dfs(a, i + 1, j);
    int[] res2 = dfs(a, i, j - 1);
    if (a[i] + res1[2] >= a[j] + res2[2]) {
      return dp[i][j] = new int[] {a[i] + res1[2], res1[0], res1[1]};
    } else {
      return dp[i][j] = new int[] {a[j] + res2[2], res2[0], res2[1]};
    }
  }
}
