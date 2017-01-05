package boj.solution.distancebetweennodes;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
public class DistanceBetweenNodesTest {

  @Rule
  public final Timeout globalTimeout = Timeout.seconds(1);
  private final DistanceBetweenNodes solver = new DistanceBetweenNodes();

  @Test
  public void test() {
    for (int n = 1; n < 30; n++) {
      DistanceBetweenNodes.TreeNode root = randomTree(0, n);
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          assertEquals(distance(root, i, j), solver.distance(root, i, j));
        }
      }
    }
  }

  private static Random random = new Random(0);

  private DistanceBetweenNodes.TreeNode randomTree(int from, int to) {
    if (from == to) {
      return null;
    }
    if (from + 1 == to) {
      return new DistanceBetweenNodes.TreeNode(from, null, null);
    }
    int mid = random.nextInt(to - from) + from;
    return new DistanceBetweenNodes.TreeNode(mid, randomTree(from, mid), randomTree(mid + 1, to));
  }

  int distance(DistanceBetweenNodes.TreeNode root, int value1, int value2) {
    Map<Integer, List<Integer>> g = new HashMap<>();
    dfs(root, g);
    return dfs(g, value1, value2, null);
  }

  private int dfs(Map<Integer, List<Integer>> g, int from, int to, Integer pre) {
    if (from == to) {
      return 0;
    }
    for (int child : g.get(from)) {
      if (pre != null && pre == child) {
        continue;
      }
      int res = dfs(g, child, to, from);
      if (res >= 0) {
        return res + 1;
      }
    }
    return -1;
  }

  private void dfs(DistanceBetweenNodes.TreeNode root, Map<Integer, List<Integer>> g) {
    if (root == null) {
      return;
    }
    addEdge(root, root.left, g);
    addEdge(root.left, root, g);
    addEdge(root, root.right, g);
    addEdge(root.right, root, g);
    dfs(root.left, g);
    dfs(root.right, g);
  }

  private void addEdge(DistanceBetweenNodes.TreeNode a, DistanceBetweenNodes.TreeNode b, Map<Integer, List<Integer>> g) {
    if (a == null || b == null) {
      return;
    }
    List<Integer> lst = g.get(a.value);
    if (lst == null) {
      lst = new ArrayList<>();
      g.put(a.value, lst);
    }
    lst.add(b.value);
  }
}
