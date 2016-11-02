package boj.solution;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.assertEquals;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
public class PrinttyPrintBinaryTreeTest {

  @Rule
  public final Timeout globalTimeout = Timeout.seconds(1);
  private PrinttyPrintBinaryTree solver = new PrinttyPrintBinaryTree();

  @Test
  public void test() {
    testInternal(
        "0 \n",
        new Tree(0));
    testInternal(""
        + " 1  \n"
        + "  0 \n",
        new Tree(1,
            null,
            new Tree(0)));
    testInternal(""
        + " 1  \n"
        + "0   \n",
        new Tree(1,
            new Tree(0),
            null));
    testInternal(""
        + " 0  \n"
        + "1 2 \n",
        new Tree(0,
            new Tree(1),
            new Tree(2)));
    testInternal(""
            + "   0    \n"
            + " 1   4  \n"
            + "2 3 5 6 \n",
        new Tree(0,
            new Tree(1,
                new Tree(2),
                new Tree(3)),
            new Tree(4,
                new Tree(5),
                new Tree(6))));
    testInternal(""
            + "   0    \n"
            + " 1   4  \n"
            + "  3   6 \n",
        new Tree(0,
            new Tree(1,
                null,
                new Tree(3)),
            new Tree(4,
                null,
                new Tree(6))));
    testInternal("" +
        "                               0                                \n" +
        "               1                               5                \n" +
        "                       2               6                        \n" +
        "                           3       7                            \n" +
        "                             4   8                              \n" +
        "                                9                               \n",
        new Tree(0,
            new Tree(1,
                null,
                new Tree(2,
                    null,
                    new Tree(3,
                        null,
                        new Tree(4)))),
            new Tree(5,
                new Tree(6,
                    new Tree(7,
                        new Tree(8,
                            new Tree(9),
                            null),
                        null),
                    null),
                null)));
    testInternal("" +
            "                               0                                \n" +
            "               1                               5                \n" +
            "       2               3               6                        \n" +
            "           2       4               7                            \n" +
            "                                 8                              \n" +
            "                                  9                             \n",
        new Tree(0,
            new Tree(1,
                new Tree(2, null, new Tree(2)),
                new Tree(3, new Tree(4), null)),
            new Tree(5,
                new Tree(6,
                    new Tree(7,
                        new Tree(8,
                            null,
                            new Tree(9)),
                        null),
                    null),
                null)));
    testInternal("" +
            "                               0                                \n" +
            "               1                               5                \n" +
            "       2               3               6               0        \n" +
            "   3       2       4       8       7       1       3       6    \n" +
            " 4   5   7                       8               2           4  \n" +
            "      6 8                         9                         1 2 \n",
        new Tree(0,
            new Tree(1,
                new Tree(2,
                    new Tree(3,
                        new Tree(4),
                        new Tree(5,
                            null,
                            new Tree(6))),
                    new Tree(2,
                        new Tree(7,
                            new Tree(8),
                            null),
                        null)),
                new Tree(3, new Tree(4), new Tree(8))),
            new Tree(5,
                new Tree(6,
                    new Tree(7,
                        new Tree(8,
                            null,
                            new Tree(9)),
                        null),
                    new Tree(1)),
                new Tree(0,
                    new Tree(3,
                        new Tree(2),
                        null),
                    new Tree(6,
                        null,
                        new Tree(4,
                            new Tree(1),
                            new Tree(2)))))));
  }

  private void testInternal(String exp, Tree root) {
    assertEquals(exp, solver.solve(root));
  }

  private static class Tree implements PrinttyPrintBinaryTree.Node {

    private final int value;
    private final Tree left;
    private final Tree right;

    public Tree(int value) {
      this(value, null, null);
    }

    public Tree(int value, Tree left, Tree right) {
      this.value = value;
      this.left = left;
      this.right = right;
    }

    @Override
    public int getValue() {
      return value;
    }

    @Override
    public PrinttyPrintBinaryTree.Node getLeft() {
      return left;
    }

    @Override
    public PrinttyPrintBinaryTree.Node getRight() {
      return right;
    }
  }
}
