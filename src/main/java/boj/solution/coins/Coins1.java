package boj.solution.coins;

/**
 * Created by biran on 11/9/16.
 */
class Coins1 {

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
    return dfs(a, 0, a.length - 1);
  }

  private int dfs(int[] a, int i, int j) {
    if (i > j) {
      return 0;
    }
    if (dp[i][j] != null) {
      return dp[i][j];
    }

    int sum = prefix[j] - prefix[i] + a[i];
    return dp[i][j] = Math.max(sum - greedy(a, i + 1, j), sum - greedy(a, i, j - 1));
  }

  private int greedy(int[] a, int i, int j) {
    if (i > j) {
      return 0;
    }
    int sum = prefix[j] - prefix[i] + a[i];
    if (a[i] >= a[j]) {
      return sum - dfs(a, i + 1, j);
    } else {
      return sum - dfs(a, i, j - 1);
    }
  }
}
