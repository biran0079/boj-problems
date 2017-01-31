package boj.solution.longestsubstring;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
public class LongestSubStringTest {

  @Rule
  public final Timeout globalTimeout = Timeout.seconds(15);
  private final LongestSubString solver = new LongestSubString();


  static class RandomStream implements LongestSubString.Stream {

    private final Random R;
    private final int len;
    private final int charCount;
    private int generated = 0;

    RandomStream(int len, int charCount, int seed) {
      this.len = len;
      this.charCount = charCount;
      this.R = new Random(seed);
    }

    @Override
    public Integer next() {
      if (generated >= len) {
        return null;
      }
      generated++;
      if (generated == len) {
        return charCount - 1;
      }
      return R.nextInt(charCount - 1);
    }
  }

  @Test
  public void test() {
    for (int n = 10; n < 50; n++) {
      for (int cn = 2; cn <= 5; cn++) {
        for (int k = 1; k <= cn; k++) {
          for (int seed = 0; seed < 10; seed++) {
            assertEquals(solve(new RandomStream(n, cn, seed), k),
                solver.solve(new RandomStream(n, cn, seed), k));
          }
        }
      }
    }
    assertEquals(solve2(new RandomStream(20000000, 10000, 0), 9999),
        solver.solve(new RandomStream(20000000, 10000, 0), 9999));
  }

  int solve(LongestSubString.Stream stream, int k) {
    Map<Integer, Integer> lastPos = new LinkedHashMap<>();
    int res = 0, fast = 0, slow = 0;
    while (true) {
      if (lastPos.size() <= k) {
        Integer c = stream.next();
        if (c == null) {
          break;
        }
        if (lastPos.containsKey(c)) {
          lastPos.remove(c);
        }
        lastPos.put(c, fast);
        fast++;
      } else {
        Map.Entry<Integer, Integer> e = lastPos.entrySet().iterator().next();
        slow = e.getValue() + 1;
        lastPos.remove(e.getKey());
      }
      if (lastPos.size() <= k) {
        res = Math.max(res, fast - slow);
      }
    }
    return res;
  }

  int solve2(LongestSubString.Stream s, int k) {
    Queue<Integer> q = new ArrayDeque<>();
    int slow = 0, fast = 0, res = 0;
    Map<Integer, Integer> ct = new HashMap<>();
    int size = 0;
    while (true) {
      if (ct.size() <= k) {
        Integer c = s.next();
        if (c == null) {
          break;
        }
        ct.put(c, ct.getOrDefault(c, 0) + 1);
        q.add(c);
        size = Math.max(size, q.size());
        fast++;
      } else {
        int c = q.poll();
        slow++;
        if (1 == ct.put(c, ct.get(c) - 1)) {
          ct.remove(c);
        }
      }
      if (ct.size() <= k) {
        res = Math.max(res, fast - slow);
      }
    }
    System.out.println(size);
    return res;
  }
}
