/*
 * Copyright 2016 Addepar. All rights reserved.
 */
package boj.solution.repeatcycle;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
public class RepeatCycle2d {

  static class RabinKarp {

    private static final long M = Integer.MAX_VALUE;
    private static final long BASE = 256;

    private final long[] prefix; // prefix[i] = hash(s[0...i-1])
    private final long[] power;

    RabinKarp(String s) {
      this.prefix = buildPrefixHash(s);
      this.power = computePower(s.length() + 1);
    }

    private static long[] computePower(int length) {
      long[] res = new long[length];
      res[0] = 1;
      for (int i = 1; i < length; i++) {
        res[i] = (BASE * res[i - 1]) % M;
      }
      return res;
    }

    static long[] buildPrefixHash(String s) {
      long[] res = new long[s.length() + 1];
      for (int i = 0; i < s.length(); i++) {
        res[i + 1] = (res[i] * BASE + s.charAt(i)) % M;
      }
      return res;
    }

    // s[from...to - 1]
    int hash(int from, int to) {
      return (int) ((prefix[to] - prefix[from] * power[to - from] % M + M) % M);
    }
  }

  int[] solve(char[][] matrix) {
    int lcmRow = 1, lcmCol = 1;
    for (char[] row : matrix) {
      lcmCol = lcm(lcmCol, solve1d(new String(row)));
    }
    for (char[] col : transpose(matrix)) {
      lcmRow = lcm(lcmRow, solve1d(new String(col)));
    }
    return new int[] {lcmRow, lcmCol};
  }

  private char[][] transpose(char[][] m) {
    char[][] res = new char[m[0].length][m.length];
    for (int i = 0; i < m.length; i++) {
      for (int j = 0; j < m[i].length; j++) {
        res[j][i] = m[i][j];
      }
    }
    return res;
  }

  private int lcm(int a, int b) {
    return a * b / gcd(a, b);
  }

  private int gcd(int a, int b) {
    return a == 0 ? b : gcd(b % a, a);
  }

  private int solve1d(String s) {
    RabinKarp rk = new RabinKarp(s);
    for (int i = 1; i < s.length(); i++) {
      if (rk.hash(0, s.length() - i) == rk.hash(i, s.length())
          && s.substring(0, s.length() - i).equals(s.substring(i))) {
        return i;
      }
    }
    return s.length();
  }
}
