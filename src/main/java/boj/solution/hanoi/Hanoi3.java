package boj.solution.hanoi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by biran on 11/13/16.
 */
public class Hanoi3 {

  public List<int[]> solve(int n) {
    List<int[]> res = new ArrayList<>();
    helper(n, 0, 1, 2, res);
    return res;
  }

  private void helper(int n, int from, int mid, int to, List<int[]> res) {
    if (n != 0) {
      helper(n - 1, from, mid, to, res);
      res.add(new int[] {from, mid});
      helper(n - 1, to, from, mid, res);
      helper(n - 1, mid, to, from, res);
      res.add(new int[] {mid, to});
      helper(n - 1, from, mid, to, res);
    }
  }
}
