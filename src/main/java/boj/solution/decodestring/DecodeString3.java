/*
 * Copyright 2016 Addepar. All rights reserved.
 */
package boj.solution.decodestring;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
class DecodeString3 {

  Integer[][] dp;
  int[][] mids;
  int[][] rep;

  String solve(String s) {
    dp = new Integer[s.length()][s.length()];
    mids = new int[s.length()][s.length()];
    rep = new int[s.length()][s.length()];
    char[] cs = s.toCharArray();
    dfs(cs, 0, cs.length - 1);
    return encode(cs, 0, cs.length - 1);
  }

  String encode(char[] s, int from, int to) {
    if (from > to) {
      return "";
    }
    if (from == to) {
      return "" + s[from];
    }
    if (dp[from][to] == to - from + 1) {
      return new String(s, from, to - from + 1);
    }
    int mid = mids[from][to];
    int n = rep[from][mid];
    int len = (mid - from + 1) / n;
    String sub = encode(s, from, from + len - 1);
    String rest = encode(s, mid + 1, to);
    if (n == 1) {
      return sub + rest;
    }
    return n + "[" + sub + "]" + rest;
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
      int tmp = dfs(s, mid + 1, to) + encodeRepeat(s, from, mid);
      if (tmp < res) {
        res = tmp;
        mids[from][to] = mid;
      }
    }
    return dp[from][to] = res;
  }

  int encodeRepeat(char[] s, int from, int to) {
    int len = to - from + 1;
    int res = len;
    rep[from][to] = 1;
    for (int n = 2; n <= len; n++) {
      if (len % n == 0 && isRepeat(s, from, to, len / n)) {
        int tmp = String.valueOf(n).length() + 2 + dfs(s, from, from + len / n - 1);
        if (tmp < res) {
          res = tmp;
          rep[from][to] = n;
        }
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
