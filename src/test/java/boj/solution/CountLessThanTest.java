package boj.solution;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.assertEquals;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
public class CountLessThanTest {

  static class BST implements CountLessThan.TreeNode {

    private final int key;
    private final CountLessThan.TreeNode left;
    private final CountLessThan.TreeNode right;
    private final int size;

    public BST(int key, CountLessThan.TreeNode left, CountLessThan.TreeNode right) {
      this.key = key;
      this.left = left;
      this.right = right;
      this.size = getSize(left) + getSize(right)  + 1;
    }

    private static int getSize(CountLessThan.TreeNode root) {
      return root == null ? 0 : root.getSize();
    }

    public int getKey() {
      return key;
    }

    public CountLessThan.TreeNode getLeft() {
      return left;
    }

    public CountLessThan.TreeNode getRight() {
      return right;
    }

    public int getSize() {
      return size;
    }
  }

  @Rule
  public final Timeout globalTimeout = Timeout.seconds(1);
  private final CountLessThan solver = new CountLessThan();

  @Test
  public void test1() {
    for (int i = 0; i < 20; i++) {
      CountLessThan.TreeNode root = newBst(i);
      assertEquals(0, solver.solve(root, -1));
      for (int j = 0; j <= i; j++) {
        assertEquals(j, solver.solve(root, j));
      }
    }
  }

  @Test
  public void test2() {
    BST root = null;
    for (int i = 0; i < 10000; i++) {
      root = new BST(i * 2, root, null);
    }
    assertEquals(0, solver.solve(root, -1));
  }

  @Test
  public void test3() {
    CountLessThan.TreeNode root = new BST(3,
        new BST(1, null, null),
        new BST(5,
            null,
            new BST(7, null, null)));
    assertEquals(1, solver.solve(root, 2));
    assertEquals(2, solver.solve(root, 4));
    assertEquals(3, solver.solve(root, 6));
    assertEquals(4, solver.solve(root, 9));
  }


  private CountLessThan.TreeNode newBst(int n) {
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = i;
    }
    return newBst(arr, 0, arr.length);
  }

  private CountLessThan.TreeNode newBst(int[] arr, int from, int to) {
    if (from == to) {
      return null;
    }
    if (to - from == 1) {
      return new BST(arr[from], null, null);
    }
    int mid = (to - from) / 2 + from;
    CountLessThan.TreeNode left = newBst(arr, from, mid);
    CountLessThan.TreeNode right = newBst(arr, mid + 1, to);
    return new BST(arr[mid], left, right);
  }
}
