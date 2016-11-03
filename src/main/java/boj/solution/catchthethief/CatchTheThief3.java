package boj.solution.catchthethief;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by biran on 11/3/16.
 */
public class CatchTheThief3 {

  int[] solve(int n) {
    Queue<Integer> q = new ArrayDeque<>();
    int mask = (1 << n) - 1;
    boolean[] vis = new boolean[mask + 1];
    int[][] back = new int[mask + 1][];
    q.add(mask);
    vis[mask] = true;
    int level = 0;
    while (!q.isEmpty()) {
      int qsize = q.size();
      for (int i = 0; i < qsize; i++) {
        int cur = q.poll();
        if (cur == 0) {
          int[] res = new int[level];
          for (int j = 0; j < level; j++) {
            res[level - j - 1] = back[cur][1];
            cur = back[cur][0];
          }
          return res;
        }
        for (int j = 0; j < n; j++) {
          if ((cur & (1 << j)) != 0) {
            int next = cur;
            next &= ~(1 << j);
            next = ((next << 1) | (next >> 1)) & mask;
            if (!vis[next]) {
              vis[next] = true;
              q.add(next);
              back[next] = new int[] {cur, j};
            }
          }
        }
      }
      level++;
    }
    return null;
  }
}
