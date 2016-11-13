package boj.solution.validstring;

/**
 * Created by biran on 11/11/16.
 */
public class ValidString {

  int solve(int n) {
    if (n == 0) {
      return 0;
    }
    if (n == 1) {
      return 3;
    }
    int same = 1, diff = 1;
    for (int i = 1; i <= n - 2; i++) {
      int newSame = 2 * diff + same;
      int newDiff = same + diff;
      same = newSame;
      diff = newDiff;
    }
    return 3 * same + 6 * diff;
  }
}
