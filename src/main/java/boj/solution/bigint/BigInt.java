package boj.solution.bigint;

import java.util.Arrays;

/**
 * Created by biran on 11/11/16.
 */
class BigInt {

  private final int[] a;

  BigInt(int[] a) {
    this.a = a;
  }

  BigInt(String s) {
    a = new int[s.length()];
    for (int i = 0; i < a.length; i++) {
      a[i] = s.charAt(a.length - i - 1) - '0';
    }
  }

  BigInt add(BigInt bigInt) {
    int[] b = new int[Math.max(a.length, bigInt.a.length) + 1];
    int carry = 0;
    for (int i = 0; i < b.length; i++) {
      int left = i < a.length ? a[i] : 0;
      int right = i < bigInt.a.length ? bigInt.a[i] : 0;
      b[i] = left + right + carry;
      carry = b[i] / 10;
      b[i] %= 10;
    }
    return new BigInt(removeTrailingZero(b));
  }

  BigInt multiply(BigInt bigInt) {
    int[] b = new int[a.length + bigInt.a.length];
    for (int i = 0; i < a.length; i++) {
      for (int j = 0; j < bigInt.a.length; j++) {
        b[i + j] += a[i] * bigInt.a[j];
      }
    }
    for (int i = 1; i < b.length; i++) {
      b[i] += b[i - 1] / 10;
      b[i - 1] %= 10;
    }
    return new BigInt(removeTrailingZero(b));
  }

  private static int[] removeTrailingZero(int[] a) {
    int i = a.length - 1;
    while (i > 0 && a[i] == 0) {
      i--;
    }
    return Arrays.copyOf(a, i + 1);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = a.length - 1; i >= 0; i--) {
      sb.append(a[i]);
    }
    return sb.toString();
  }
}
