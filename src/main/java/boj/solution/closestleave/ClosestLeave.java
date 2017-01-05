/*
 * Copyright 2016 Addepar. All rights reserved.
 */
package boj.solution.closestleave;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
public class ClosestLeave {

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

  int solve(TreeNode root, int target) {
    Map<Integer, List<Integer>> g = new HashMap<>();
    dfs(root, g);
    if (g.size() == 0) {
      return 0;
    }
    return dfs(target, -1, root.value, g);
  }

  private int dfs(int cur, int parent, int root, Map<Integer, List<Integer>> g) {
    if (cur != root && g.get(cur).size() == 1) {
      return 0;
    }
    int res = -1;
    for (int child : g.get(cur)) {
      if (child != parent) {
        int t = dfs(child, cur, root, g);
        if (t != -1 && (res == -1 || res > t + 1)) {
          res = t + 1;
        }
      }
    }
    return res;
  }

  private void dfs(TreeNode root, Map<Integer, List<Integer>> g) {
    if (root == null) {
      return;
    }
    if (root.left != null) {
      addBiDirEdge(root, root.left, g);
      dfs(root.left, g);
    }
    if (root.right != null) {
      addBiDirEdge(root, root.right, g);
      dfs(root.right, g);
    }
  }

  private void addBiDirEdge(TreeNode a, TreeNode b, Map<Integer, List<Integer>> g) {
    addEdge(a, b, g);
    addEdge(b, a, g);
  }

  private void addEdge(TreeNode from, TreeNode to, Map<Integer, List<Integer>> g) {
    if (!g.containsKey(from.value)) {
      g.put(from.value, new ArrayList<>());
    }
    g.get(from.value).add(to.value);
  }
}
