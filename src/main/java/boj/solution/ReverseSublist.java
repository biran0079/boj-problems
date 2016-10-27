/*
 * Copyright 2016 Addepar. All rights reserved.
 */
package boj.solution;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
public class ReverseSublist {

  /**
   * Reverse sublist starting from position i, inclusive, ending at position j, exclusive.
   * Assume 0 <= i < j <= list length.
   */
  ListNode reverseSublist(ListNode head, int i, int j, ListNode.Factory factory) {
    if (i == 0) {
      return reversePrefix(head, j);
    }
    ListNode cur = head;
    ListNode prev = null;
    while (i > 0) {
      prev = cur;
      cur = cur.getNext();
      j--;
      i--;
    }
    prev.setNext(reversePrefix(cur, j));
    return head;
  }

  // reverse first n elements of a list
  private ListNode reversePrefix(ListNode head, int n) {
    ListNode res = null;
    ListNode cur = head;
    for (int i = 0; i < n; i++) {
      ListNode next = cur.getNext();
      cur.setNext(res);
      res = cur;
      cur = next;
    }
    head.setNext(cur);
    return res;
  }

  interface ListNode {

    int getValue();

    ListNode getNext();

    void setNext(ListNode next);

    interface Factory {

      ListNode create(int value);
    }
  }
}
