/*
 * Copyright 2016 Addepar. All rights reserved.
 */
package boj.solution;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
public class InsertIntoCircularSortedList {

  interface ListNode {

    int getValue();

    ListNode getNext();

    void setNext(ListNode next);

    /**
     * Factory for ListNode.
     */
    interface Factory {

      /**
       * Creates a new list node, next pointer will be null.
       */
      ListNode create(int value);
    }
  }

  ListNode insert(ListNode head, ListNode.Factory factory, int value) {
    ListNode result = factory.create(value);
    if (head == null) {
      result.setNext(result);
      return result;
    }
    // head is not null now
    ListNode tail = findTail(head);
    head = tail.getNext();
    if (value < head.getValue()) {
      result.setNext(head);
      tail.setNext(result);
      return result;
    }
    if (value >= tail.getValue()) {
      tail.setNext(result);
      result.setNext(head);
      return head;
    }
    // insert into the middle of the list
    ListNode prev = tail, cur = head;
    // value < tail.getValue(), so this loop will eventually
    while (value >= cur.getValue()) {
      prev = cur;
      cur = cur.getNext();
    }
    //insert between prev and cur
    prev.setNext(result);
    result.setNext(cur);
    return head;
  }

  private ListNode findTail(ListNode head) {
    ListNode cur = head;
    while (cur.getValue() <= cur.getNext().getValue()) {
      cur = cur.getNext();
      if (cur == head) {
        // all nodes are equal
        return head;
      }
    }
    return cur;
  }
}
