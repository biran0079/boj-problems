package boj.solution.hanoi;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.Assert.*;

/**
 * Created by biran on 11/13/16.
 */
public class Hanoi1Test {

  @Rule
  public final Timeout globalTimeout = Timeout.seconds(1);

  @Test
  public void test() {
    for (int i = 0; i < 12; i++) {
      test(i);
    }
  }

  private void test(int n) {
    Deque<Integer>[] stack = new Deque[] {
        new ArrayDeque(),
        new ArrayDeque(),
        new ArrayDeque()
    };
    for (int i = 0; i < n; i++) {
      stack[0].push(n - i);
    }
    for (int[] move : new Hanoi1().solve(n)) {
      int i = move[0];
      int j = move[1];
      assertNotEquals(i, j);
      assertTrue(!stack[i].isEmpty());
      assertTrue(stack[j].isEmpty() || stack[i].peek() < stack[j].peek());
      stack[j].push(stack[i].pop());
    }
    assertTrue(stack[0].isEmpty());
    assertTrue(stack[1].isEmpty());
  }
}