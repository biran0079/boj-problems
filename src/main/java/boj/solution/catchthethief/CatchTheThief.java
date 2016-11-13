package boj.solution.catchthethief;

import java.util.BitSet;

/**
 * Created by biran on 11/2/16.
 */
public class CatchTheThief {

  int[] sequence;
  int n;
  Boolean[][] memo;

  boolean solve(int n, int[] sequence) {
    this.n = n;
    this.sequence = sequence;
    this.memo = new Boolean[sequence.length][n];
    for (int i = 0; i < n; i++) {
      if (!canCatch(0, i)) {
        return false;
      }
    }
    return true;
  }

  private boolean canCatch(int day, int thiefPos) {
    if (day == sequence.length) {
      return false; // thief wins
    }
    if (memo[day][thiefPos] != null) {
      return memo[day][thiefPos];
    }
    boolean res;
    if (sequence[day] == thiefPos) {
      res = true;
    } else if (thiefPos > 0 && !canCatch(day + 1, thiefPos - 1)) {
      res = false;
    } else if (thiefPos < n - 1 && !canCatch(day + 1, thiefPos + 1)) {
      res = false;
    } else {
      res = true;
    }
    return memo[day][thiefPos] = res;
  }
}
