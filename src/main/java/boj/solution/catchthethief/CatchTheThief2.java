package boj.solution.catchthethief;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by biran on 11/2/16.
 */
public class CatchTheThief2 {

  int solve(int n) {
    Queue<Integer> q = new ArrayDeque<>();
    int mask = (1 << n) - 1;
    boolean[] vis = new boolean[mask + 1];
    q.add(mask);
    vis[mask] = true;
    int level = 0;
    while (!q.isEmpty()) {
      int qsize = q.size();
      for (int i = 0; i < qsize; i++) {
        int cur = q.poll();
        if (cur == 0) {
          return level;
        }
        for (int j = 0; j < n; j++) {
          if ((cur & (1 << j)) != 0) {
            int next = cur;
            next &= ~(1 << j);
            next = ((next << 1) | (next >> 1)) & mask;
            if (!vis[next]) {
              vis[next] = true;
              q.add(next);
            }
          }
        }
      }
      level++;
    }
    return -1;
  }
}
