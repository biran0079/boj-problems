package boj.solution.binarytreediameter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.*;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
public class BinaryTreeDiameter2Test {

  @Rule
  public final Timeout globalTimeout = Timeout.seconds(1);

  @Test
  public void test() {
    assertEquals(0, new BinaryTreeDiameter2().solve(node()));
    assertEquals(1, new BinaryTreeDiameter2().solve(node(node(), null)));
    assertEquals(2, new BinaryTreeDiameter2().solve(node(node(), node())));
    assertEquals(3, new BinaryTreeDiameter2().solve(node(node(), node(node(), null))));
    assertEquals(4, new BinaryTreeDiameter2().solve(node(node(null, node()), node(node(), null))));
    assertEquals(4, new BinaryTreeDiameter2().solve(node(node(node(), node()), node(node(), node()))));
    assertEquals(4, new BinaryTreeDiameter2().solve(
        node(
            node(),
            node(
                node(
                    node(),
                    node()),
                node(
                    node(),
                    node())))));
    assertEquals(3, new BinaryTreeDiameter2().solve(node(null, node(null, node(null, node())))));

    assertEquals(9999, new BinaryTreeDiameter2().solve(list(10000)));
    assertEquals(20000, new BinaryTreeDiameter2().solve(node(list(10000), list(10000))));
    assertEquals(20001, new BinaryTreeDiameter2().solve(node(list(10000), node(list(10000), list(10000)))));
    assertEquals(20000, new BinaryTreeDiameter2().solve(node(list(9998), node(list(10000), list(10000)))));
  }

  BinaryTreeDiameter2.TreeNode list(int n) {
    BinaryTreeDiameter2.TreeNode res = null;
    for (int i = 0; i < n; i++) {
      if ((i & 1) == 0) {
        res = node(res, null);
      } else {
        res = node(null, res);
      }
    }
    return res;
  }

  BinaryTreeDiameter2.TreeNode node(BinaryTreeDiameter2.TreeNode left, BinaryTreeDiameter2.TreeNode right) {
    BinaryTreeDiameter2.TreeNode res = new BinaryTreeDiameter2.TreeNode();
    res.left = left;
    res.right = right;
    return res;
  }

  BinaryTreeDiameter2.TreeNode node() {
    return node(null, null);
  }
}
