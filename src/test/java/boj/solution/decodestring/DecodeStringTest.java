package boj.solution.decodestring;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
public class DecodeStringTest {

  @Rule
  public final Timeout globalTimeout = Timeout.seconds(1);

  private static final Random random = new Random(0);
  private final List<String> input = new ArrayList<>();
  private final List<String> output = new ArrayList<>();

  @Before
  public void setup() {
    for (int d = 0; d <= 10; d++) {
      for (int i = 0; i < 10; i++) {
        Node root = randomTree(d);
        input.add(root.compressedString());
        output.add(root.textString());
      }
    }
    input.add("123[ab]");
    output.add(repeat("ab", 123));
    input.add("2[a2[b2[c2[d2[e2[f2[g]]]]]]]");
    output.add(repeat("a" + repeat("b" +
        repeat("c" + repeat("d" + repeat("e" + repeat("f" + repeat("g", 2), 2), 2), 2), 2), 2), 2));
  }

  private String repeat(String s, int n) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < n; i++) {
      sb.append(s);
    }
    return sb.toString();
  }

  @Test
  public void test() {
    DecodeString solver = new DecodeString();
    for (int i = 0; i < input.size(); i++) {
      assertEquals(output.get(i), solver.solve(input.get(i)));
    }
  }

  private static Node randomTree(int depth) {
    if (depth == 0) {
      return randomStrNode();
    }
    if (random.nextBoolean()) {
      int childrenN = random.nextInt(5) + 1;
      List<Node> children = new ArrayList<>();
      for (int i = 0; i < childrenN; i++) {
        children.add(randomTree(depth - 1));
      }
      return new CompNode(children);
    } else {
      int n = random.nextInt(5);
      return new RepNode(n, randomTree(depth - 1));
    }
  }

  private static StrNode randomStrNode() {
    int n = random.nextInt(5) + 1;
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < n; i++) {
      sb.append((char) (random.nextInt(26) + 'a'));
    }
    return new StrNode(sb.toString());
  }

  private interface Node {
    String compressedString();
    String textString();
  }

  private static class CompNode implements Node {
    private final List<Node> children;

    public CompNode(List<Node> children) {
      this.children = children;
    }

    @Override
    public String compressedString() {
      StringBuilder sb = new StringBuilder();
      children.forEach(node -> sb.append(node.compressedString()));
      return sb.toString();
    }

    @Override
    public String textString() {
      StringBuilder sb = new StringBuilder();
      children.forEach(node -> sb.append(node.textString()));
      return sb.toString();
    }
  }

  private static class StrNode implements Node {
    private final String s;

    public StrNode(String s) {
      this.s = s;
    }

    @Override
    public String compressedString() {
      return s;
    }

    @Override
    public String textString() {
      return s;
    }
  }

  private static class RepNode implements Node{
    private final int n;
    private final Node node;

    public RepNode(int n, Node node) {
      this.n = n;
      this.node = node;
    }

    @Override
    public String compressedString() {
      return n + "[" + node.compressedString() + "]";
    }

    @Override
    public String textString() {
      StringBuilder res = new StringBuilder();
      String text = node.textString();
      for (int i = 0; i < n; i++) {
        res.append(text);
      }
      return res.toString();
    }
  }
}
