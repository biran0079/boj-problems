/*
 * Copyright 2016 Addepar. All rights reserved.
 */
package boj.solution;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
public class ValidSpanningTree {

  private int[] p;

  private int find(int x) {
    return p[x] == x ? x : (p[x] = find(p[x]));
  }

  boolean isValid(int n, int[][] edges) {
    p = new int[n];
    for (int i = 0; i < n; i++) {
      p[i] = i;
    }
    if (edges.length != n - 1) {
      return false;
    }
    for (int[] edge : edges) {
      int a = find(edge[0]);
      int b = find(edge[1]);
      if (a == b) {
        return false;
      }
      p[a] = p[b];
    }
    return true;
  }
}
