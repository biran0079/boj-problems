package boj.solution.mergeksortedlists;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
public class MergeKSortedListsTest {

  @Rule
  public final Timeout globalTimeout = Timeout.seconds(1);

  @Test
  public void test() {
    test(Arrays.asList(), 0);
    test(Arrays.asList(Arrays.asList()), 0);
    test(Arrays.asList(Arrays.asList(), Arrays.asList(0, 1)), 2);
    test(Arrays.asList(Arrays.asList(0), Arrays.asList(1)), 2);
    test(Arrays.asList(Arrays.asList(0, 2), Arrays.asList(1, 3)), 4);
    test(Arrays.asList(Arrays.asList(0, 2), Arrays.asList(1, 3), Arrays.asList(4, 5)), 6);
    test(Arrays.asList(Arrays.asList(0, 2, 3), Arrays.asList(), Arrays.asList(1, 4, 5)), 6);
    List<List<Integer>> linkedList = new LinkedList<>();
    for (int i = 0; i < 10000; i++) {
      linkedList.add(new LinkedList<>());
    }
    Iterator<List<Integer>> iter = linkedList.iterator();
    for (int i = 0; i < 100000; i++) {
      if (!iter.hasNext()) {
        iter = linkedList.iterator();
      }
      iter.next().add(i);
    }
    for (int i = 100000; i < 200000; i++) {
      linkedList.get(0).add(i);
    }
    test(linkedList, 200000);
  }

  void test(List<List<Integer>> in, int n) {
    List<Integer> out = new MergeKSortedLists().merge(in);
    int ct = 0;
    for (int v : out) {
      assertEquals(ct++, v);
    }
    assertEquals(n, ct);
  }
}
