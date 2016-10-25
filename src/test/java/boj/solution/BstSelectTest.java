package boj.solution;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.*;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
public class BstSelectTest {

  static class BST implements BstSelect.TreeNode {

    private final int key;
    private final BstSelect.TreeNode left;
    private final BstSelect.TreeNode right;
    private final int size;

    public BST(int key, BstSelect.TreeNode left, BstSelect.TreeNode right) {
      this.key = key;
      this.left = left;
      this.right = right;
      this.size = getSize(left) + getSize(right)  + 1;
    }

    private static int getSize(BstSelect.TreeNode root) {
      return root == null ? 0 : root.getSize();
    }

    public int getKey() {
      return key;
    }

    public BstSelect.TreeNode getLeft() {
      return left;
    }

    public BstSelect.TreeNode getRight() {
      return right;
    }

    public int getSize() {
      return size;
    }
  }

  @Rule
  public final Timeout globalTimeout = Timeout.seconds(1);
  private final BstSelect solver = new BstSelect();

  @Test
  public void test1() {
    for (int i = 0; i < 20; i++) {
      BstSelect.TreeNode root = newBst(i);
      assertNull(solver.solve(root, -1));
      assertNull(solver.solve(root, i));
      for (int j = 0; j < i; j++) {
        assertEquals(j, solver.solve(root, j).intValue());
      }
    }
  }

  @Test
  public void test2() {
    BstSelectTest.BST root = null;
    for (int i = 0; i < 10000; i++) {
      root = new BstSelectTest.BST(i * 2, root, null);
    }
    assertEquals(0, solver.solve(root, 0).intValue());
  }

  @Test
  public void test3() {
    BstSelect.TreeNode root = new BstSelectTest.BST(3,
        new BstSelectTest.BST(1, null, null),
        new BstSelectTest.BST(5,
            null,
            new BstSelectTest.BST(7, null, null)));
    assertNull(solver.solve(root, -1));
    assertEquals(1, solver.solve(root, 0).intValue());
    assertEquals(3, solver.solve(root, 1).intValue());
    assertEquals(5, solver.solve(root, 2).intValue());
    assertEquals(7, solver.solve(root, 3).intValue());
    assertNull(solver.solve(root, 4));
  }


  private BstSelect.TreeNode newBst(int n) {
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = i;
    }
    return newBst(arr, 0, arr.length);
  }

  private BstSelect.TreeNode newBst(int[] arr, int from, int to) {
    if (from == to) {
      return null;
    }
    if (to - from == 1) {
      return new BstSelectTest.BST(arr[from], null, null);
    }
    int mid = (to - from) / 2 + from;
    BstSelect.TreeNode left = newBst(arr, from, mid);
    BstSelect.TreeNode right = newBst(arr, mid + 1, to);
    return new BstSelectTest.BST(arr[mid], left, right);
  }
}
