/*
 * Copyright 2016 Addepar. All rights reserved.
 */
package boj.solution.binarytreediameter;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
class BinaryTreeDiameter {

  static class TreeNode {
    TreeNode left, right;
  }

  int res;

  int solve(TreeNode root) {
    res = 0;
    dfs(root);
    return res - 1;
  }

  int dfs(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int leftRes = dfs(root.left);
    int rightRes = dfs(root.right);
    res = Math.max(leftRes + rightRes + 1, res);
    return Math.max(leftRes, rightRes) + 1;
  }
}
