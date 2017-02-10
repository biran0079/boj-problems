package boj.solution.mergeksortedlists;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

class MergeKSortedLists {

  static class Node implements Comparable<Node> {
    int first;
    Iterator<Integer> rest;

    public Node(int first, Iterator<Integer> rest) {
      this.first = first;
      this.rest = rest;
    }

    @Override
    public int compareTo(Node o) {
      return Integer.compare(first, o.first);
    }
  }

  List<Integer> merge(List<List<Integer>> lists) {
    PriorityQueue<Node> pq = new PriorityQueue<>();
    for (List<Integer> list : lists) {
      if (!list.isEmpty()) {
        Iterator<Integer> iter = list.iterator();
        pq.add(new Node(iter.next(), iter));
      }
    }
    List<Integer> res = new ArrayList<>();
    while (!pq.isEmpty()) {
      Node cur = pq.poll();
      res.add(cur.first);
      Iterator<Integer> iter = cur.rest;
      if (iter.hasNext()) {
        pq.add(new Node(iter.next(), iter));
      }
    }
    return res;
  }
}
