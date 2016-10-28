package boj.solution;

/**
 * Created by biran on 10/27/16.
 */
public class MaxSubarrayProduction {

  double maxProduct(double[] arr) {
    double[] max = new double[arr.length];
    double[] min = new double[arr.length];
    max[0] = min[0] = arr[0];
    double res = max[0];
    for (int i = 1; i < arr.length; i++) {
      if (arr[i] > 0) {
        max[i] = Math.max(arr[i], arr[i] * max[i - 1]);
        min[i] = Math.min(arr[i], arr[i] * min[i - 1]);
      } else {
        max[i] = Math.max(arr[i], arr[i] * min[i - 1]);
        min[i] = Math.min(arr[i], arr[i] * max[i - 1]);
      }
      res = Math.max(res, max[i]);
    }
    return res;
  }
}
