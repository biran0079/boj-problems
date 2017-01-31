/*
 * Copyright 2016 Addepar. All rights reserved.
 */
package boj.solution.longestsubstring;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
class LongestSubString {

  interface Stream {
    Integer next();
  }

  int solve(Stream stream, int k) {
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
}
