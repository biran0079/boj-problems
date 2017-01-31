package boj.solution.binarytreediameter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.*;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
public class BinaryTreeDiameterTest {

  @Rule
  public final Timeout globalTimeout = Timeout.seconds(1);

  @Test
  public void test() {
    assertEquals(0, new BinaryTreeDiameter().solve(node()));
    assertEquals(1, new BinaryTreeDiameter().solve(node(node(), null)));
    assertEquals(2, new BinaryTreeDiameter().solve(node(node(), node())));
    assertEquals(3, new BinaryTreeDiameter().solve(node(node(), node(node(), null))));
    assertEquals(4, new BinaryTreeDiameter().solve(node(node(null, node()), node(node(), null))));
    assertEquals(4, new BinaryTreeDiameter().solve(node(node(node(), node()), node(node(), node()))));
    assertEquals(4, new BinaryTreeDiameter().solve(
        node(
            node(),
            node(
                node(
                    node(),
                    node()),
                node(
                    node(),
                    node())))));
    assertEquals(3, new BinaryTreeDiameter().solve(node(null,node(null, node(null, node())))));
  }

  BinaryTreeDiameter.TreeNode node(BinaryTreeDiameter.TreeNode left, BinaryTreeDiameter.TreeNode right) {
    BinaryTreeDiameter.TreeNode res = new BinaryTreeDiameter.TreeNode();
    res.left = left;
    res.right = right;
    return res;
  }

  BinaryTreeDiameter.TreeNode node() {
    return node(null, null);
  }
}
