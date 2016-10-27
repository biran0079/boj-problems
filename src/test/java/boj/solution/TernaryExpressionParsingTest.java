package boj.solution;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Objects;

import static org.junit.Assert.assertEquals;


/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
public class TernaryExpressionParsingTest {

  @Rule
  public final Timeout globalTimeout = Timeout.seconds(1);
  private final TernaryExpressionParsing solver = new TernaryExpressionParsing();

  @Test
  public void test1() {
    assertEquals(null, solver.solve("", Node::new));
    assertEquals(new Node('a', new Node('b'), new Node('c')),
        solver.solve("a?b:c", Node::new));
    assertEquals(new Node('a',
        new Node('b', new Node('c'), new Node('d')),
        new Node('e')),
        solver.solve("a?b?c:d:e", Node::new));
    assertEquals(new Node('a',
        new Node('b'),
        new Node('c',new Node('d'),new Node('e'))),
        solver.solve("a?b:c?d:e", Node::new));
    assertEquals(new Node('a',
            new Node('b', new Node('c'), new Node('d')),
            new Node('e', new Node('f'), new Node('g'))),
        solver.solve("a?b?c:d:e?f:g", Node::new));
    assertEquals(new Node('a',
            new Node('b',
                new Node('c',
                    new Node('d', new Node('e'), new Node('f')),
                    new Node('g')),
                new Node('h')),
            new Node('i')),
        solver.solve("a?b?c?d?e:f:g:h:i", Node::new));
    assertEquals(new Node('a',
            new Node('b'),
            new Node('c',
                new Node('d'),
                new Node('e',
                    new Node('f'),
                    new Node('g',
                        new Node('h'),
                        new Node('i'))))),
        solver.solve("a?b:c?d:e?f:g?h:i", Node::new));
  }

  private class Node implements TernaryExpressionParsing.TreeNode {

    private final char c;
    private final TernaryExpressionParsing.TreeNode left;
    private final TernaryExpressionParsing.TreeNode right;

    Node(char c) {
      this(c, null, null);
    }

    Node(char c,
         TernaryExpressionParsing.TreeNode left,
         TernaryExpressionParsing.TreeNode right) {
      this.c = c;
      this.left = left;
      this.right = right;
    }

    @Override
    public boolean equals(Object o) {
      if (o instanceof Node) {
        Node that = (Node) o;
        return c == that.c && Objects.equals(left, that.left) && Objects.equals(right, that.right);
      }
      return false;
    }

    @Override
    public int hashCode() {
      return 0;
    }
  }
}
