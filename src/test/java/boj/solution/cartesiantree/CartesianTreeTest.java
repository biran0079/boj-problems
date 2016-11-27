package boj.solution.cartesiantree;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
public class CartesianTreeTest {

  @Rule
  public final Timeout globalTimeout = Timeout.seconds(1);
  private final CartesianTree solver = new CartesianTree();

  @Test
  public void test() {
    for (int i = 1; i <= 5; i++) {
      test(i);
    }
    int[] a = new int[1000000];
    for (int i = 0; i < a.length; i++) {
      a[i] = i;
    }
    shuffle(a);
    testInternal(a);
  }

  private void shuffle(int[] a) {
    Random r = new Random(0);
    for (int i = 1; i < a.length; i++) {
      int j = r.nextInt(i + 1);
      int t = a[i];
      a[i] = a[j];
      a[j] = t;
    }
  }

  private void test(int n) {
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = i;
    }
    do {
      testInternal(a);
    } while(nextPerm(a));
  }

  private void testInternal(int[] a) {
    CartesianTree.TreeNode root = solver.solve(a, Node::new);
    validate(root, a);
  }

  private boolean nextPerm(int[] a) {
    int i = a.length - 1;
    while (i > 0 && a[i] <= a[i - 1]) {
      i--;
    }
    if (i == 0) {
      return false;
    }
    i--;
    int j = i + 1;
    while (j < a.length - 1 && a[j + 1] > a[i]) {
      j++;
    }
    int t = a[i];
    a[i] = a[j];
    a[j] = t;
    reverse(a, i + 1, a.length - 1);
    return true;
  }

  private void reverse(int[] a, int i, int j) {
    while (i < j) {
      int t = a[i];
      a[i] = a[j];
      a[j] = t;
      i++;
      j--;
    }
  }

  private void validate(CartesianTree.TreeNode root, int[] a) {
    List<Integer> res = new ArrayList<>();
    dfs(root, res);
    int[] b = new int[res.size()];
    for (int i = 0; i < res.size(); i++) {
      b[i] = res.get(i);
    }
    assertArrayEquals(a, b);
  }

  private void dfs(CartesianTree.TreeNode root, List<Integer> res) {
    if (root == null) {
      return;
    }
    if (root.getLeft() != null) {
      assertTrue(root.getValue() < root.getLeft().getValue());
      dfs(root.getLeft(), res);
    }
    res.add(root.getValue());
    if (root.getRight() != null) {
      assertTrue(root.getValue() < root.getRight().getValue());
      dfs(root.getRight(), res);
    }
  }

  private static final class Node implements CartesianTree.TreeNode {

    private final int value;
    private CartesianTree.TreeNode left;
    private CartesianTree.TreeNode right;

    Node(int value) {
      this.value = value;
    }

    @Override
    public int getValue() {
      return value;
    }

    @Override
    public CartesianTree.TreeNode getLeft() {
      return left;
    }

    @Override
    public CartesianTree.TreeNode getRight() {
      return right;
    }

    @Override
    public void setLeft(CartesianTree.TreeNode left) {
      this.left = left;
    }

    @Override
    public void setRight(CartesianTree.TreeNode right) {
      this.right = right;
    }
  }
}
