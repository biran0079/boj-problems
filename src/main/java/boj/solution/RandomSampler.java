package boj.solution;

import java.util.*;

public class RandomSampler {

  private final Map<Integer, Integer> valueToPos = new HashMap<>();
  private final List<Integer> list = new ArrayList<>();
  private final Random random = new Random();

  // assume never add the same value twice
  void add(int v) {
    valueToPos.put(v, list.size());
    list.add(v);
  }

  void remove(int value) {
    int pos = valueToPos.get(value);
    int movedValue = list.get(list.size() - 1);
    Collections.swap(list, pos, list.size() - 1);
    valueToPos.put(movedValue, pos);
    valueToPos.remove(value);
    list.remove(list.size() - 1);
  }

  int getRandom() {
    return list.get(random.nextInt(list.size()));
  }
}
