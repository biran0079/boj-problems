/*
 * Copyright 2016 Addepar. All rights reserved.
 */
package boj.solution;



public class WeightedTriangulation {

  Integer[][] memo;

  int solve(int[] weight) {
    memo = new Integer[weight.length][weight.length];
    return memo(weight, 0, weight.length - 1);
  }

  private int memo(int[] weight, int i, int j) {
    if (memo[i][j] != null) {
      return memo[i][j];
    }
    if (j - i <= 1) {
      return 0;
    }
    int res = Integer.MIN_VALUE;
    for (int k = i + 1; k < j; k++) {
      res = Math.max(res,
          memo(weight, i, k) + memo(weight, k, j)
              + weight[i] * weight[j] * weight[k]);
    }
    return memo[i][j] = res;
  }

  int solve2(int[] weight) {
    // dp[i][j]: the max sum of production of weights of triangulation for arr[i...j]
    int[][] dp = new int[weight.length][weight.length];
    for (int n = 3; n <= weight.length; n++) {
      for (int i = 0; i + n - 1 < weight.length; i++) {
        int j = i + n - 1;
        for (int k = i + 1; k < j; k++) {
          dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + weight[i] * weight[j] * weight[k]);
        }
      }
    }
    return dp[0][weight.length - 1];
  }
}
