/*
 * Copyright 2016 Addepar. All rights reserved.
 */
package boj.solution.cartesiantree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
class CartesianTree {

  TreeNode solve(int[] a, TreeNode.Factory factory) {
    TreeNode[] nodes = new TreeNode[a.length];
    for (int i = 0; i < a.length; i++) {
      nodes[i] = factory.create(a[i]);
    }
    Deque<Integer> stack = new LinkedList<>();
    for (int i = 0; i < a.length; i++) {
      TreeNode lastPopped = null;
      while (!stack.isEmpty() && a[stack.peek()] >= a[i]) {
        lastPopped = nodes[stack.pop()];
      }
      nodes[i].setLeft(lastPopped);
      if (!stack.isEmpty()) {
        nodes[stack.peek()].setRight(nodes[i]);
      }
      stack.push(i);
    }
    while (stack.size() > 1) {
      stack.pop();
    }
    return nodes[stack.peek()];
  }

  interface TreeNode {

    int getValue();

    TreeNode getLeft();

    TreeNode getRight();

    void setLeft(TreeNode left);

    void setRight(TreeNode right);

    interface Factory {
      TreeNode create(int value);
    }
  }
}
