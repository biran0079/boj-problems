/*
 * Copyright 2016 Addepar. All rights reserved.
 */
package boj.testutil;

import java.util.Random;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
public class DataGen {

  private final Random random = new Random(0);

  public int[] randomArray(int n, int bound) {
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = random.nextInt(bound);
    }
    return arr;
  }

  public double[] randomProbability(int n) {
    if (n == 0) {
      return new double[0];
    }
    double[] res = new double[n];
    double sum = 0.0;
    for (int i = 0; i < n; i++) {
      res[i] = random.nextDouble();
      sum += res[i];
    }
    if (sum == 0) {
      sum += 1e-9;
      res[0] += 1e-9;
    }
    for (int i = 0; i < n; i++) {
      res[i] /= sum;
    }
    return res;
  }
}
