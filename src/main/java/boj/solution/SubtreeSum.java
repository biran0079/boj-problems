/*
 * Copyright 2016 Addepar. All rights reserved.
 */
package boj.solution;

import java.util.*;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
public class SubtreeSum {

  void solve(List<TreeNode> treeNode) {
    Map<TreeNode, Integer> indegree = new HashMap<>();
    Map<TreeNode, Integer> sum = new HashMap<>();
    for (TreeNode node : treeNode) {
      indegree.put(node, 0);
      sum.put(node, node.getValue());
    }
    for (TreeNode node : treeNode) {
      if (node.getParent() != null) {
        indegree.put(node.getParent(), indegree.get(node.getParent()) + 1);
      }
    }
    Queue<TreeNode> queue = new ArrayDeque<>();
    for (TreeNode node : treeNode) {
      if (indegree.get(node) == 0) {
        queue.add(node);
      }
    }
    while (!queue.isEmpty()) {
      TreeNode cur = queue.poll();
      TreeNode parent = cur.getParent();
      if (parent != null) {
        sum.put(parent, sum.get(parent) + sum.get(cur));
        indegree.put(parent, indegree.get(parent) - 1);
        if (indegree.get(parent) == 0) {
          queue.add(parent);
        }
      }
    }
    for (TreeNode node : treeNode) {
      node.setSum(sum.get(node));
    }
  }

  interface TreeNode {

    TreeNode getParent();

    int getValue();

    void setSum(int sum);
  }
}
