/*
 * Copyright 2016 Addepar. All rights reserved.
 */
package boj.solution.missingnumber;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
class MissingNumber {

  int solve(int[] arr) {
    int n = (arr.length + 1) / 3;
    int left = 1, right = n;
    while (left < right) {
      int mid = (right - left) / 2 + left;
      if (mid == arr.length - 1 || arr[mid * 3 - 1] != mid) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    return left;
  }
}
