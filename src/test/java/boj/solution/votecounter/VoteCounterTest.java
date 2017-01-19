package boj.solution.votecounter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
public class VoteCounterTest {

  @Rule
  public final Timeout globalTimeout = Timeout.seconds(1);

  @Test
  public void test() {
    List<String> vote = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        vote.add(i + "");
      }
    }
    for (List<String> v : perm(vote)) {
      VoteCounter voteCounter = new VoteCounter();
      VoteCounter2 voteCounter2 = new VoteCounter2();
      for (String name : v) {
        voteCounter.vote(name);
        voteCounter2.vote(name);
        for (int k = 1; k <= voteCounter2.count.size(); k++) {
          assertEquals(voteCounter2.topKCount(k), voteCounter2.getCount(voteCounter.topKCandidates(k)));
        }
      }
    }
  }

  private List<List<String>> perm(List<String> s) {
    List<List<String>> res = new ArrayList<>();
    perm(s, 0, res);
    return res;
  }

  private void perm(List<String> s, int i, List<List<String>> res) {
    if (i == s.size()) {
      res.add(new ArrayList<>(s));
    }
    Set<String> used = new HashSet<>();
    for (int j = i; j < s.size(); j++) {
      if (!used.contains(s.get(j))) {
        used.add(s.get(j));
        Collections.swap(s, i, j);
        perm(s, i + 1, res);
        Collections.swap(s, i, j);
      }
    }
  }

  private class VoteCounter2 {

    Map<String, Integer> count = new HashMap<>();

    void vote(String name) {
      count.put(name, count.getOrDefault(name, 0) + 1);
    }

    List<Integer> topKCount(int k) {
      List<String> all = new ArrayList<>(count.keySet());
      Collections.sort(all, Comparator.comparing(name -> -count.get(name)));
      return getCount(all.subList(0, k));
    }

    List<Integer> getCount(List<String> names) {
      List<Integer> res = new ArrayList<>();
      for (String name : names) {
        res.add(count.get(name));
      }
      return res;
    }
  }
}
