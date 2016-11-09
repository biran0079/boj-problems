/*
 * Copyright 2016 Addepar. All rights reserved.
 */
package boj.solution.decodestring;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
class DecodeString2 {

  Integer[][] dp;

  int solve(String s) {
    char[] cs = s.toCharArray();
    dp = new Integer[s.length()][s.length()];
    return dfs(cs, 0, cs.length - 1);
  }

  int dfs(char[] s, int from, int to) {
    int res = to - from + 1;
    if (from >= to) {
      return res;
    }
    if (dp[from][to] != null) {
      return dp[from][to];
    }
    for (int mid = from; mid <= to; mid++) {
      res = Math.min(res, dfs(s, mid + 1, to) + encodeRepeat(s, from, mid));
    }
    return dp[from][to] = res;
  }

  int encodeRepeat(char[] s, int from, int to) {
    int len = to - from + 1;
    int res = len;
    for (int n = 2; n <= len; n++) {
      if (len % n == 0 && isRepeat(s, from, to, len / n)) {
        res = Math.min(res,
            String.valueOf(n).length() + 2 + dfs(s, from, from + len/n - 1));
      }
    }

    return res;
  }

  boolean isRepeat(char[] s, int from, int to, int len) {
    for (int i = from + len; i <= to; i++) {
      if (s[i] != s[i - len]) {
        return false;
      }
    }
    return true;
  }
}
