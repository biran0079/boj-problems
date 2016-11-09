/*
 * Copyright 2016 Addepar. All rights reserved.
 */
package boj.solution.decodestring;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
class DecodeString {

  int idx;

  String solve(String s) {
    idx = 0;
    char[] cs = s.toCharArray();
    StringBuilder sb = new StringBuilder();
    parse(cs, sb);
    return sb.toString();
  }

  private void parse(char[] s, StringBuilder sb) {
    while (idx < s.length && s[idx] != ']') {
      if (Character.isDigit(s[idx])) {
        int n = parseNum(s);
        idx++; // skip [
        StringBuilder subBuilder = new StringBuilder();
        parse(s, subBuilder);
        String sub = subBuilder.toString();
        for (int i = 0; i < n; i++) {
          sb.append(sub);
        }
        idx++; // skip ]
      } else {
        sb.append(s[idx]);
        idx++;
      }
    }
  }

  private int parseNum(char[] s) {
    int res = 0;
    while (idx < s.length && Character.isDigit(s[idx])) {
      res *= 10;
      res += s[idx] - '0';
      idx++;
    }
    return res;
  }
}
