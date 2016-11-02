/*
 * Copyright 2016 Addepar. All rights reserved.
 */
package boj.solution;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
public class PrinttyPrintBinaryTree {

  String solve(Node root) {
    StringBuilder sb = new StringBuilder();
    int height = getHeight(root);
    int width = (1 << height);
    Queue<Node> queue = new LinkedList<>();
    queue.offer(root);
    for (int i = 0; i < height; i++) {
      int qsize = queue.size();
      for (int j = 0; j < qsize; j++) {
        Node cur = queue.poll();
        appendElement(sb, cur, width);
        if (cur == null) {
          queue.offer(null);
          queue.offer(null);
        } else {
          queue.offer(cur.getLeft());
          queue.offer(cur.getRight());
        }
      }
      sb.append('\n');
      width >>= 1;
    }
    return sb.toString();
  }

  private void appendElement(StringBuilder sb, Node cur, int width) {
    String value = cur == null ? "" : String.valueOf(cur.getValue());
    int spaces = width - value.length();
    for (int i = 0; i < spaces / 2; i++) {
      sb.append(' ');
    }
    sb.append(value);
    for (int i = spaces / 2; i < spaces; i++) {
      sb.append(' ');
    }
  }

  private int getHeight(Node root) {
    return root == null ? 0
        : (Math.max(getHeight(root.getLeft()), getHeight(root.getRight())) + 1);
  }

  interface Node {

    int getValue();

    Node getLeft();

    Node getRight();
  }
}
