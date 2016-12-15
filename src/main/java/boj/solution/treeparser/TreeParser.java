/*
 * Copyright 2016 Addepar. All rights reserved.
 */
package boj.solution.treeparser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
class TreeParser {

  int idx;
  
  Node parse(String input) {
    if (input.isEmpty()) {
      return null;
    }
    idx = 0;
    return helper(input.toCharArray());
  }

  private Node helper(char[] s) {
    idx++; // skip (
    Node root = new Node();
    int value = parseInt(s);
    root.value = value;
    while (s[idx] != ')') {
      root.children.add(helper(s));
    }
    idx++; // skip )
    return root;
  }

  private int parseInt(char[] s) {
    boolean pos = true;
    if (s[idx] == '-') {
      pos = false;
      idx++;
    }
    int res = 0;
    while (idx < s.length && Character.isDigit(s[idx])) {
      res *= 10;
      res += s[idx++] - '0';
    }
    if (!pos) {
      res = -res;
    }
    return res;
  }

  static class Node {
    int value;
    List<Node> children = new ArrayList<>();
  }
}
