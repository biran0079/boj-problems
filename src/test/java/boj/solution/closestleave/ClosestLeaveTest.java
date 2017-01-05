package boj.solution.closestleave;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.*;

import static org.junit.Assert.*;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
public class ClosestLeaveTest {

  @Rule
  public final Timeout globalTimeout = Timeout.seconds(1);
  private final ClosestLeave solver = new ClosestLeave();

  @Test
  public void test() {
    for (int n = 1; n < 100; n++) {
      ClosestLeave.TreeNode root = randomTree(0, n);
      for (int i = 0; i < n; i++) {
        assertEquals(distance(root, i), solver.solve(root, i));
      }
    }
  }

  private static Random random = new Random(0);

  private ClosestLeave.TreeNode randomTree(int from, int to) {
    if (from == to) {
      return null;
    }
    if (from + 1 == to) {
      return new ClosestLeave.TreeNode(from, null, null);
    }
    int mid = random.nextInt(to - from) + from;
    return new ClosestLeave.TreeNode(mid, randomTree(from, mid), randomTree(mid + 1, to));
  }

  int result;
  private int distance(ClosestLeave.TreeNode root, int target) {
    dfs(root, target);
    return result;
  }

  private int dfs(ClosestLeave.TreeNode root, int target) {
    if (root == null) {
      return -1;
    }
    if (root.value == target) {
      result = closestDescendent(root);
      return 0;
    }
    int left = dfs(root.left, target);
    int right = dfs(root.right, target);
    if (left != -1) {
      if (root.right != null) {
        result = Math.min(result, left + 1 + closestDescendent(root.right) + 1);
      }
      return left + 1;
    }
    if (right != -1) {
      if (root.left != null) {
        result = Math.min(result, right + 1 + closestDescendent(root.left) + 1);
      }
      return right + 1;
    }
    return -1;
  }

  private int closestDescendent(ClosestLeave.TreeNode root) {
    if (root.left == null && root.right == null) {
      return 0;
    }
    int left = root.left == null ? Integer.MAX_VALUE : closestDescendent(root.left);
    int right = root.right == null ? Integer.MAX_VALUE : closestDescendent(root.right);
    return Math.min(left, right) + 1;
  }
}
