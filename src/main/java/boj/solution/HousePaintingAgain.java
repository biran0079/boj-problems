package boj.solution;

/**
 * Created by biran on 10/29/16.
 */
public class HousePaintingAgain {

  int[] solve(int n, int m, int[][] cost) {
    int[][] dp = new int[n + 1][m];
    int[][] ptr = new int[n][m];
    for (int i = n - 1; i >= 0; i--) {
      for (int j = 0; j < m; j++) {
        dp[i][j] = Integer.MAX_VALUE;
        for (int k = 0; k < m; k++) {
          if (k == j) {
            continue;
          }
          int newCost = cost[i][k] + dp[i + 1][k];
          if (newCost < dp[i][j]) {
            dp[i][j] = newCost;
            ptr[i][j] = k;
          }
        }
      }
    }
    int[] res = new int[n];
    int color0 = 0;
    for (int i = 1; i < m; i++) {
      if (dp[0][i] < dp[0][color0]) {
        color0 = i;
      }
    }
    int cur = color0;
    for (int i = 0; i < n; i++) {
      res[i] = ptr[i][cur];
      cur = ptr[i][cur];
    }
    return res;
  }
}
