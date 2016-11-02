package boj.solution;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
public class SubtreeSumTest {

  @Rule
  public final Timeout globalTimeout = Timeout.seconds(1);
  private final Random random = new Random(0);
  private final SubtreeSum solver = new SubtreeSum();

  @Test
  public void test() {
    for (int depth = 1; depth <= 5; depth++) {
      Tree tree = randomTree(depth, 6);
      testInternal(tree);
    }
    for (int i = 0; i < 4; i++) {
      Tree tree = randomTree(6, 3);
      testInternal(tree);
      tree = randomTree(7, 2);
      testInternal(tree);
      tree = randomTree(8, 2);
      testInternal(tree);
    }
  }

  private void testInternal(Tree root) {
    List<SubtreeSum.TreeNode> nodes = new ArrayList<>();
    Map<SubtreeSum.TreeNode, Integer> sum = new HashMap<>();
    dfs(root, nodes, sum);
    solver.solve(Collections.unmodifiableList(nodes));
    for (SubtreeSum.TreeNode node : nodes) {
      Tree cur = ((Tree) node);
      assertEquals(sum.get(node).intValue(), cur.sum);
    }
  }

  private void dfs(Tree root, List<SubtreeSum.TreeNode> nodes, Map<SubtreeSum.TreeNode, Integer> sum) {
    nodes.add(root);
    int total = root.value;
    for (Tree child : root.children) {
      dfs(child, nodes, sum);
      total += sum.get(child);
    }
    sum.put(root, total);
  }

  private Tree randomTree(int depth, int branchFactor) {
    int childNum = depth == 1 ? 0 : random.nextInt(branchFactor);
    Tree[] children = new Tree[childNum];
    for (int i = 0; i < childNum; i++) {
      children[i] = randomTree(depth - 1, branchFactor);
    }
    return new Tree(random.nextInt(10), children);
  }

  class Tree implements SubtreeSum.TreeNode {

    private final int value;
    private final Tree[] children;

    private Tree parent;
    private int sum;

    public Tree( int value, Tree[] children) {
      this.value = value;
      this.children = children;
      for (Tree child : children) {
        child.parent = this;
      }
    }

    @Override
    public SubtreeSum.TreeNode getParent() {
      return parent;
    }

    @Override
    public int getValue() {
      return value;
    }

    @Override
    public void setSum(int sum) {
      this.sum = sum;
    }
  }
}
