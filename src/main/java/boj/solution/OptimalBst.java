package boj.solution;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
public class OptimalBst {

  private Double[][] dp;

  public double solve(double[] probability) {
    if (probability.length == 0) {
      return 0;
    }
    // dp[i][j]: expectation of node depth for arr[i...j]
    // dp[i][j] = dp[i][k-1] + dp[k+1][j] + sum if probability[i..j] for k in i...j
    double[] prefix = buildPrefixSum(probability);
    dp = new Double[probability.length][probability.length];
    return dfs(0, probability.length - 1, prefix);
  }

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

  private double dfs(int i, int j, double[] prefix) {
    if (i > j) {
      // empty sub array
      return 0;
    }
    if (dp[i][j] != null) {
      return dp[i][j];
    }
    double res = Double.POSITIVE_INFINITY;
    for (int k = i; k <= j; k++) {
      res = Math.min(res,
          sum(prefix, i, j) + dfs(i, k - 1, prefix) + dfs(k + 1, j, prefix));

    }
    return dp[i][j] = res;
  }

  public double solve2(double[] probability) {
    if (probability.length == 0) {
      return 0;
    }
    double[][] dp = new double[probability.length][probability.length];
    double[] prefix = buildPrefixSum(probability);
    for (int n = 1; n <= probability.length; n++) {
      for (int i = 0; i + n - 1< probability.length; i++) {
        int j = i + n - 1;
        double res = Double.POSITIVE_INFINITY;
        for (int k = i; k <= j; k++) {
          res = Math.min(res,
              sum(prefix, i, j)
                  + (k == 0 ? 0 : dp[i][k - 1])
                  + (k == dp.length - 1 ? 0 : dp[k + 1][j]));
        }
        dp[i][j] = res;
      }
    }
    return dp[0][probability.length - 1];
  }
}
