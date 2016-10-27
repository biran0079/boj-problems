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
public class ReverseSublistTest {
  
  @Rule
  public final Timeout globalTimeout = Timeout.seconds(1);
  private final ReverseSublist solver = new ReverseSublist();
  
  @Test
  public void test() {
    testAllReverse(new int[] {1});
    testAllReverse(new int[] {1,2});
    testAllReverse(new int[] {1,2,3});
    testAllReverse(new int[] {1,2,3,4});
    testAllReverse(new int[] {1,2,3,4,5});
    testAllReverse(new int[] {1,2,3,4,5,6});
    testAllReverse(new int[] {1,2,3,4,5,6,7});
    testAllReverse(new int[] {1,2,3,4,5,6,7,8});
    int[] arr = new int[10000];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = i;
    }
    doTest(arr, 1, arr.length - 1);
  }

  static class Node implements ReverseSublist.ListNode {

    private final int value ;
    private ReverseSublist.ListNode next;

    Node(int value) {
      this.value = value;

    }

    public int getValue() {
      return value;
    }

    public ReverseSublist.ListNode getNext() {
      return next;
    }

    public void setNext(ReverseSublist.ListNode next) {
      this.next = next;
    }
  }

  static List<Integer> toList(ReverseSublist.ListNode head) {
    List<Integer> res = new ArrayList<>();
    while (head != null) {
      res.add(head.getValue());
      head = head.getNext();
    }
    return res;
  }

  static ReverseSublistTest.Node toListNode(List<Integer> arr) {
    ReverseSublistTest.Node head = null;
    for (int i = arr.size() - 1; i >= 0; i--) {
      ReverseSublistTest.Node cur = new ReverseSublistTest.Node(arr.get(i));
      cur.next = head;
      head = cur;
    }
    return head;
  }

  private void testAllReverse(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      for (int j = i + 1; j <= arr.length; j++) {
        doTest(arr, i, j);
      }
    }
  }

  private void doTest(int[] arr, int i, int j) {
    List<Integer> origin = toList(arr);
    ReverseSublist.ListNode input = toListNode(origin);
    Collections.reverse(origin.subList(i, j));
    assertEquals(origin, toList(solver.reverseSublist(input, i, j, Node::new)));
    Collections.reverse(origin.subList(i, j));
  }

  private static List<Integer> toList(int[] arr) {
    List<Integer> res = new ArrayList<>();
    for (int x : arr) {
      res.add(x);
    }
    return res;
  }
}
