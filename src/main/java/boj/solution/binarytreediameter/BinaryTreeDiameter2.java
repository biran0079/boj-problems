/*
 * Copyright 2016 Addepar. All rights reserved.
 */
package boj.solution.binarytreediameter;

import java.util.*;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
public class BinaryTreeDiameter2 {

  static class TreeNode {
    TreeNode left, right;
  }

  int res;

  int solve(TreeNode root) {
    res = 0;
    bfs(root);
    return res;
  }

  void bfs(TreeNode root) {
    Map<TreeNode, Integer> depth = new HashMap<>();
    Queue<TreeNode> q = new ArrayDeque<>();
    List<TreeNode> nodes = new ArrayList<>();
    q.add(root);
    while (!q.isEmpty()) {
      TreeNode cur = q.poll();
      nodes.add(cur);
      if (cur.left != null) {
        q.add(cur.left);
      }
      if (cur.right != null) {
        q.add(cur.right);
      }
    }
    Collections.reverse(nodes);
    for (TreeNode node : nodes) {
      int leftRes = node.left == null ? 0 : depth.get(node.left);
      int rightRes = node.right == null ? 0 : depth.get(node.right);
      depth.put(node, Math.max(leftRes, rightRes) + 1);
      res = Math.max(res, leftRes + rightRes);
    }
  }
}
