/*
 * Copyright 2016 Addepar. All rights reserved.
 */
package boj.solution.votecounter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
public class VoteCounter {

  List<String> cands = new ArrayList<>();
  Map<String, Integer> count = new HashMap<>();
  Map<String, Integer> idx = new HashMap<>();
  Map<Integer, Integer> start = new HashMap<>();

  void vote(String name) {
    int old = count.getOrDefault(name, 0);
    count.put(name, old + 1);
    if (old == 0) {
      idx.put(name, cands.size());
      if (!start.containsKey(1)) {
        start.put(1, cands.size());
      }
      cands.add(name);
    } else {
      int i = idx.get(name);
      int j = start.get(old);
      swap(i, j);
      if (j + 1 < cands.size() && count.get(cands.get(j + 1)) == old) {
        start.put(old, j + 1);
      } else {
        start.remove(old);
      }
      if (!start.containsKey(old + 1)) {
        start.put(old + 1, j);
      }
    }
  }

  void swap(int i, int j) {
    if (i != j) {
      String s = cands.get(i);
      cands.set(i, cands.get(j));
      cands.set(j, s);
      idx.put(cands.get(i), i);
      idx.put(cands.get(j), j);
    }
  }

  List<String> topKCandidates(int k) {
    return new ArrayList<>(cands.subList(0, k));
  }
}
