package boj.solution.deletefromcircularlist;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by biran on 11/18/16.
 */
public class DeleteFromCircularListTest {

  @Rule
  public final Timeout globalTimeout = Timeout.seconds(1);
  private final DeleteFromCircularList solver = new DeleteFromCircularList();

  @Test
  public void test() {
    testAllRotations(new int[] {}, 1, new int[] {});
    testAllRotations(new int[] {1}, 1, new int[] {});
    testAllRotations(new int[] {2}, 1, new int[] {2});
    testAllRotations(new int[] {1,1}, 1, new int[] {1});
    testAllRotations(new int[] {0,1,2}, 1, new int[] {0,2});
    testAllRotations(new int[] {0,1,1,2}, 1, new int[] {0,1,2});
    testAllRotations(new int[] {0,0,2,2}, 1, new int[] {0,0,2,2});
    testAllRotations(new int[] {0,0,1,2,2}, 1, new int[] {0,0,2,2});
    testAllRotations(new int[] {0,0,1,1,2,2}, 1, new int[] {0,0,1,2,2});
  }

  static class Node implements DeleteFromCircularList.ListNode {

    private final int value ;
    private DeleteFromCircularList.ListNode next;

    Node(int value) {
      this.value = value;

    }

    public int getValue() {
      return value;
    }

    public DeleteFromCircularList.ListNode getNext() {
      return next;
    }

    public void setNext(DeleteFromCircularList.ListNode next) {
      this.next = next;
    }
  }

  static List<Integer> toList(DeleteFromCircularList.ListNode head) {
    List<Integer> res = new ArrayList<>();
    if (head == null) {
      return res;
    }
    DeleteFromCircularList.ListNode cur = head;
    do {
      res.add(cur.getValue());
      cur = cur.getNext();
    } while(cur != head);
    Collections.sort(res);
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
      Node head = toListNode(input);
      assertEquals(toList(expect), toList(solver.delete(head, value)));
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