/*
 * Copyright 2016 Addepar. All rights reserved.
 */
package boj.solution.uneatenleaves;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
class UneatenLeaves {

  int res;
  int n;

  int solve(int n, int[] a) {
    this.res = 0;
    this.n = n;
    dfs(a, 0, new ArrayList<>());
    return n - res;
  }

  void dfs(int[] a, int i, List<Integer> subset) {
    if (i == a.length) {
      if (!subset.isEmpty()) {
        int diff = n / lcm(subset);
        if (subset.size() % 2 == 0) {
          diff = -diff;
        }
        res += diff;
      }
      return;
    }
    dfs(a, i + 1, subset);
    subset.add(a[i]);
    dfs(a, i + 1, subset);
    subset.remove(subset.size() - 1);
  }

  int lcm(List<Integer> list) {
    int res = 1;
    for (int v : list) {
      res = lcm(res, v);
    }
    return res;
  }

  int lcm(int a, int b) {
    return a * b / gcd(a, b);
  }

  int gcd(int a, int b) {
    if (b == 0) {
      return a;
    }
    return gcd(b, a % b);
  }

  int naive(int n, int[] a) {
    Set<Integer> set = new HashSet<>();
    for (int i = 1; i <= n; i++) {
      set.add(i);
    }
    for (int v : a) {
      for (int t = v; t <= n; t += v) {
        set.remove(t);
      }
    }
    return set.size();
  }
}
