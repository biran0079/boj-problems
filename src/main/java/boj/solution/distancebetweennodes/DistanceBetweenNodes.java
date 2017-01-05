/*
 * Copyright 2016 Addepar. All rights reserved.
 */
package boj.solution.distancebetweennodes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
class DistanceBetweenNodes {

  static class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    TreeNode(int value, TreeNode left, TreeNode right) {
      this.value = value;
      this.left = left;
      this.right = right;
    }
  }

  int distance(TreeNode root, int value1, int value2) {
    Map<Integer, List<Integer>> g = new HashMap<>();
    dfs(root, g);
    return dfs(g, value1, value2, null);
  }

  private int dfs(Map<Integer, List<Integer>> g, int from, int to, Integer pre) {
    if (from == to) {
      return 0;
    }
    for (int child : g.get(from)) {
      if (pre != null && pre == child) {
        continue;
      }
      int res = dfs(g, child, to, from);
      if (res >= 0) {
        return res + 1;
      }
    }
    return -1;
  }

  private void dfs(TreeNode root, Map<Integer, List<Integer>> g) {
    if (root == null) {
      return;
    }
    addEdge(root, root.left, g);
    addEdge(root.left, root, g);
    addEdge(root, root.right, g);
    addEdge(root.right, root, g);
    dfs(root.left, g);
    dfs(root.right, g);
  }

  private void addEdge(TreeNode a, TreeNode b, Map<Integer, List<Integer>> g) {
    if (a == null || b == null) {
      return;
    }
    List<Integer> lst = g.get(a.value);
    if (lst == null) {
      lst = new ArrayList<>();
      g.put(a.value, lst);
    }
    lst.add(b.value);
  }
}
