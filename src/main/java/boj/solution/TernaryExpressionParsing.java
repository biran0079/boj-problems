package boj.solution;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
public class TernaryExpressionParsing {

  interface TreeNode {

    interface Factory {

      TreeNode create(char c, TreeNode left, TreeNode right);
    }
  }

  private int idx;

  TreeNode solve(String expr, TreeNode.Factory factory) {
    if (expr.isEmpty()) {
      return null;
    }
    idx = 0;
    return dfs(expr, factory);
  }

  private TreeNode dfs(String expr, TreeNode.Factory factory) {
    char c = expr.charAt(idx);
    idx++;
    if (idx == expr.length()) {
      return factory.create(c, null, null);
    }
    if (expr.charAt(idx) == ':') {
      idx++;
      return factory.create(c, null, null);
    }
    if (expr.charAt(idx) == '?') {
      idx++; // skip '?'
      TreeNode left = dfs(expr, factory);
      TreeNode right = dfs(expr, factory);
      return factory.create(c, left, right);
    }
    throw new RuntimeException();
  }
}
