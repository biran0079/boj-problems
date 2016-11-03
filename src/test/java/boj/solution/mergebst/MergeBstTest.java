package boj.solution.mergebst;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by biran on 11/2/16.
 */
public class MergeBstTest {

  @Rule
  public final Timeout globalTimeout = Timeout.seconds(1);

  private final MergeBst solver = new MergeBst();
  private final Random random = new Random(0);

  @Test
  public void test() {
    for (int i = 0; i < 50; i++) {
      testInternal(i);
    }
  }

  static class Tree implements MergeBst.Node {

    private int value;
    private MergeBst.Node left;
    private MergeBst.Node right;

    public Tree(int value) {
      this.value = value;
    }

    @Override
    public int getValue() {
      return value;
    }

    @Override
    public MergeBst.Node getLeft() {
      return left;
    }

    @Override
    public MergeBst.Node getRight() {
      return right;
    }

    @Override
    public void setLeft(MergeBst.Node left) {
      this.left = left;
    }

    @Override
    public void setRight(MergeBst.Node right) {
      this.right = right;
    }
  }

  public void testInternal(int n) {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      list.add(i);
    }
    List<Integer> list1 = new ArrayList<>();
    List<Integer> list2 = new ArrayList<>();
    randomSplit(list, list1, list2);
    verifyBalancedBst(
        solver.merge(buildTree(list1), buildTree(list2)),
        list);
  }

  private void verifyBalancedBst(MergeBst.Node root, List<Integer> list) {
    Iterator<Integer> iter = list.iterator();
    inOrder(root, iter);
    if (iter.hasNext()) {
      throw new AssertionError("invalid BST");
    }
  }

  private int inOrder(MergeBst.Node root, Iterator<Integer> iter) {
    if (root == null) {
      return 0;
    }
    int left = inOrder(root.getLeft(), iter);
    if (!iter.hasNext() || iter.next() != root.getValue()) {
      throw new AssertionError("invalid BST");
    }
    int right = inOrder(root.getRight(), iter);
    if (Math.abs(left - right) > 1) {
      throw new AssertionError("unbalanced BST");
    }
    return left + right + 1;
  }

  private MergeBst.Node buildTree(List<Integer> list) {
    return buildTreeHelper(list, 0, list.size());
  }

  private MergeBst.Node buildTreeHelper(List<Integer> list, int from, int to) {
    if (to <= from) {
      return null;
    }
    int mid = random.nextInt(to - from) + from;
    MergeBst.Node root = new Tree(list.get(mid));
    root.setLeft(buildTreeHelper(list, from, mid));
    root.setRight(buildTreeHelper(list, mid + 1, to));
    return root;
  }

  private void randomSplit(List<Integer> list, List<Integer> list1, List<Integer> list2) {
    for (int value : list) {
      if (random.nextBoolean()) {
        list1.add(value);
      } else {
        list2.add(value);
      }
    }
  }
}