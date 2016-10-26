package boj.solution;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
public class InsertIntoCircularSortedListTest {

  @Rule
  public final Timeout globalTimeout = Timeout.seconds(1);
  private final InsertIntoCircularSortedList solver = new InsertIntoCircularSortedList();

  @Test
  public void test() {
    testAllRotations(new int[] {}, 1, new int[] {1});
    testAllRotations(new int[] {1}, 1, new int[] {1,1});
    testAllRotations(new int[] {2}, 1, new int[] {1,2});
    testAllRotations(new int[] {0}, 1, new int[] {0,1});
    testAllRotations(new int[] {1,1}, 1, new int[] {1,1,1});
    testAllRotations(new int[] {0,2}, 1, new int[] {0,1,2});
    testAllRotations(new int[] {0,1,2}, 1, new int[] {0,1,1,2});
    testAllRotations(new int[] {0,1,1,2}, 1, new int[] {0,1,1,1,2});
    testAllRotations(new int[] {0,0,2,2}, 1, new int[] {0,0,1,2,2});
    testAllRotations(new int[] {0,0,1,2,2}, 1, new int[] {0,0,1,1,2,2});
    testAllRotations(new int[] {0,0,1,1,2,2}, 1, new int[] {0,0,1,1,1,2,2});
  }

  static class Node implements InsertIntoCircularSortedList.ListNode {

    private final int value ;
    private InsertIntoCircularSortedList.ListNode next;

    Node(int value) {
      this.value = value;

    }

    public int getValue() {
      return value;
    }

    public InsertIntoCircularSortedList.ListNode getNext() {
      return next;
    }

    public void setNext(InsertIntoCircularSortedList.ListNode next) {
      this.next = next;
    }
  }

  static List<Integer> toList(InsertIntoCircularSortedList.ListNode head) {
    List<Integer> res = new ArrayList<>();
    if (head == null) {
      return res;
    }
    InsertIntoCircularSortedList.ListNode cur = head;
    do {
      res.add(cur.getValue());
      cur = cur.getNext();
    } while(cur != head);
    return res;
  }

  static Node toListNode(List<Integer> arr) {
    if (arr.size() == 0) {
      return null;
    }
    Node head = new Node(arr.get(0));
    Node tail = head;
    for (int i = 1; i < arr.size(); i++) {
      Node cur = new Node(arr.get(i));
      tail.next = cur;
      tail = cur;
    }
    tail.next = head;
    return head;
  }

  private void testAllRotations(int[] arr, int value, int[] expect) {
    List<Integer> input = toList(arr);
    for (int i = 0; i < arr.length; i++) {
      assertEquals(
          toList(expect),
          toList(solver.insert(toListNode(input), Node::new, value)));
      Collections.rotate(input, 1);
    }
  }

  private static List<Integer> toList(int[] arr) {
    List<Integer> res = new ArrayList<>();
    for (int x : arr) {
      res.add(x);
    }
    return res;
  }
}
