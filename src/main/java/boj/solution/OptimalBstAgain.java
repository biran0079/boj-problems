package boj.solution;

/**
 * Created by biran on 10/30/16.
 */
public class OptimalBstAgain {

  private double[] buildPrefixSum(double[] probability) {
    double[] prefix = new double[probability.length];
    prefix[0] = probability[0];
    for (int i = 1; i < probability.length; i++) {
      prefix[i] = probability[i] + prefix[i - 1];
    }
    return prefix;
  }

  private double sum(double[] prefix, int i, int j) {
    if (i == 0) {
      return prefix[j];
    }
    return prefix[j] - prefix[i - 1];
  }

  TreeNode solve(double[] probability, TreeNode.Factory factory) {
    if (probability.length == 0) {
      return null;
    }
    double[][] dp = new double[probability.length][probability.length];
    int[][] ptr =  new int[probability.length][probability.length];
    double[] prefix = buildPrefixSum(probability);
    for (int n = 1; n <= probability.length; n++) {
      for (int i = 0; i + n - 1 < probability.length; i++) {
        int j = i + n - 1;
        dp[i][j] = Double.POSITIVE_INFINITY;
        for (int k = i; k <= j; k++) {
          double tmp = sum(prefix, i, j)
              + (k == 0 ? 0 : dp[i][k - 1])
              + (k == dp.length - 1 ? 0 : dp[k + 1][j]);
          if (tmp < dp[i][j]) {
            dp[i][j] = tmp;
            ptr[i][j] = k;
          }
        }
      }
    }
    return buildTree(ptr, 0, prefix.length - 1, factory);
  }

  private TreeNode buildTree(int[][] ptr, int i, int j,  TreeNode.Factory factory) {
    if (i > j) {
      return null;
    }
    return factory.create(ptr[i][j],
        buildTree(ptr, i, ptr[i][j] - 1, factory),
        buildTree(ptr, ptr[i][j] + 1, j, factory));
  }

  interface TreeNode {

    int getValue();

    TreeNode getLeft();

    TreeNode getRight();

    interface Factory {

      TreeNode create(int value, TreeNode left, TreeNode right);
    }
  }
}
