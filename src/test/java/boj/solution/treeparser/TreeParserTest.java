package boj.solution.treeparser;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.*;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
public class TreeParserTest {

  @Rule
  public final Timeout globalTimeout = Timeout.seconds(1);
  private final TreeParser parser = new TreeParser();

  @Test
  public void test() {
    test(null, "");
    test(node(1), "(1)");
    test(node(1, node(2)), "(1(2))");
    test(node(-1, node(-2)), "(-1(-2))");
    test(node(1, node(2), node(3), node(4)), "(1(2)(3)(4))");
    test(node(1, node(2, node(3), node(4)), node(5, node(6), node(7))), "(1(2(3)(4))(5(6)(7)))");
    test(node(1, node(2), node(2), node(2)), "(1(2)(2)(2))");
    test(node(123, node(345, node(567, node(678)))), "(123(345(567(678))))");
    test(node(1, node(2, node(3), node(4), node(5), node(6)), node(5, node(6, node(7), node(8), node(8)), node(7))),
        "(1(2(3)(4)(5)(6))(5(6(7)(8)(8))(7)))");
  }

  void test(TreeParser.Node exp, String s) {
    verify(exp, parser.parse(s));
  }
  
  void verify(TreeParser.Node root1, TreeParser.Node root2) {
    if (root1 == null)  {
      assertNull(root2);
    } else {
      assertNotNull(root2);
      assertEquals(root1.value, root2.value);
      assertEquals(root1.children.size(), root2.children.size());
      for (int i = 0; i < root1.children.size(); i++) {
        verify(root1.children.get(i), root2.children.get(i));
      }
    }
  }

  private TreeParser.Node node(int value, TreeParser.Node... children) {
    TreeParser.Node res = new TreeParser.Node();
    res.value = value;
    for (TreeParser.Node child : children) {
      res.children.add(child);
    }
    return res;
  }
}
