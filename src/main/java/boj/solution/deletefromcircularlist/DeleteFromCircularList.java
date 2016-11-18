package boj.solution.deletefromcircularlist;

/**
 * Created by biran on 11/18/16.
 */
class DeleteFromCircularList {

  ListNode delete(ListNode head, int value) {
    if (head == null) {
      return null;
    }
    ListNode pre = findValue(head, value);
    if (pre != null) {
      if (pre.getNext() == pre) {
        return null;
      }
      pre.setNext(pre.getNext().getNext());
      return pre;
    }
    return head;
  }

  private ListNode findValue(ListNode head, int value) {
    ListNode cur = head;
    while (true) {
      if (cur.getNext().getValue() == value) {
        return cur;
      }
      cur = cur.getNext();
      if (cur == head) {
        return null;
      }
    }
  }

  interface ListNode {

    int getValue();

    ListNode getNext();

    void setNext(ListNode next);
  }
}
