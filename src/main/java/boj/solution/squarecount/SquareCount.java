/*
 * Copyright 2016 Addepar. All rights reserved.
 */
package boj.solution.squarecount;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
class SquareCount {

  long count(int n, int m, int[][] blackSquares) {
    long[][] dp = new long[n + 1][m + 1];
    long res = 0;
    Set<Integer> black = new HashSet<>();
    for (int[] p : blackSquares) {
      black.add(p[0] * m + p[1]);
    }
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (black.contains(i * m + j)) {
          dp[i + 1][j + 1] = 0;
        } else {
          dp[i + 1][j + 1] = Math.min(dp[i][j], Math.min(dp[i + 1][j], dp[i][j + 1])) + 1;
        }
        res += dp[i + 1][j + 1];
      }
    }
    return res;
  }
}
