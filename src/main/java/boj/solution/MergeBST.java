/*
 * Copyright 2016 Addepar. All rights reserved.
 */
package boj.solution;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
public class MergeBST {

  interface Node {

    int getValue();

    Node getLeft();

    Node getRight();

    Node setLeft(Node left);

    Node setRight(Node right);

    interface Factory {

      Node create(int value);
    }
  }

  Node merge(Node root1, Node root2, Node.Factory factory) {
    Node head1 = toList(root1, null);
    Node head2 = toList(root2, null);
    Node head = merge(head1, head2);
    return toBst(head);
  }

  Node toBst(Node head) {
    return toBst(head, count(head))[0];
  }

  Node[] toBst(Node head, int n) {
    if (n == 0) {
      return new Node[] {null, head};
    }
    n--;
    Node[] left = toBst(head, n / 2);
    Node mid = left[1];
    Node[] right = toBst(mid.getRight(), n - n / 2);
    mid.setLeft(left[0]);
    mid.setRight(right[0]);
    return new Node[] {mid, right[1]};
  }

  int count(Node head) {
    int res = 0;
    while (head != null) {
      head = head.getRight();
      res++;
    }
    return res;
  }

  Node merge(Node node1, Node node2) {
    if (node1 == null) {
      return node2;
    }
    if (node2 == null) {
      return node1;
    }
    if (node1.getValue() < node2.getValue()) {
      node1.setRight(merge(node1.getRight(), node2));
      return node1;
    } else {
      node2.setRight(merge(node1, node2.getRight()));
      return node2;
    }
  }

  // serialize tree into a list and prepend it to res
  Node toList(Node cur, Node res) {
    if (cur == null) {
      return res;
    }
    Node head = toList(cur.getRight(), res);
    cur.setRight(head);
    return toList(cur.getLeft(), cur);
  }
}
