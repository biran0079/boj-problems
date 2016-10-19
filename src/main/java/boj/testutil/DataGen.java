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
}
