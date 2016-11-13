package boj.solution.smallestrange;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by biran on 11/12/16.
 */
class SmallestRange {

  private static class Elem implements Comparable<Elem> {
    List<Integer> list;
    int index;

    public Elem(List<Integer> list, int index) {
      this.list = list;
      this.index = index;
    }

    @Override
    public int compareTo(Elem elem) {
      return Integer.compare(list.get(index), elem.list.get(elem.index));
    }
  }

  int[] solve(List<List<Integer>> sortedLists) {
    PriorityQueue<Elem> pq = new PriorityQueue<>();
    int max = Integer.MIN_VALUE;
    int[] res = null;
    for (List<Integer> sortedList : sortedLists) {
      pq.add(new Elem(sortedList, 0));
      max = Math.max(sortedList.get(0), max);
    }
    while (true) {
      Elem cur = pq.poll();
      int min = cur.list.get(cur.index);
      if (res == null || res[1] - res[0] > max - min) {
        res = new int[] {min, max};
      }
      if (cur.index == cur.list.size() - 1) {
        break;
      }
      pq.add(new Elem(cur.list, cur.index + 1));
      max = Math.max(cur.list.get(cur.index + 1), max);
    }
    return res;
  }
}
