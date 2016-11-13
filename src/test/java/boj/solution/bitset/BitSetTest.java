package boj.solution.bitset;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by biran on 11/10/16.
 */
public class BitSetTest {

  @Rule
  public final Timeout globalTimeout = Timeout.seconds(1);

  @Test
  public void test() {
    BitSet set1 = new BitSet();

    assertFalse(set1.contains(0));
    assertFalse(set1.contains(1));
    assertFalse(set1.contains(2));
    assertFalse(set1.contains(3));

    set1.add(1);
    set1.add(0);
    set1.add(3);
    assertTrue(set1.contains(0));
    assertTrue(set1.contains(1));
    assertFalse(set1.contains(2));
    assertTrue(set1.contains(3));

    set1.remove(0);
    set1.remove(100);

    assertFalse(set1.contains(0));
    assertTrue(set1.contains(1));
    assertFalse(set1.contains(2));
    assertTrue(set1.contains(3));

    BitSet set2 = new BitSet();
    set2.add(1);
    set2.add(2);

    BitSet set3 = set1.intersection(set2);
    assertFalse(set3.contains(0));
    assertTrue(set3.contains(1));
    assertFalse(set3.contains(2));
    assertFalse(set3.contains(3));

    BitSet set4 = set1.union(set2);
    assertFalse(set4.contains(0));
    assertTrue(set4.contains(1));
    assertTrue(set4.contains(2));
    assertTrue(set4.contains(3));
  }

  @Test
  public void test2() {
    BitSet[] sets = new BitSet[100];
    for (int i = 2; i < 100; i++) {
      sets[i] = new BitSet();
      for (int j = i * 2; j < 100; j += i) {
        sets[i].add(j);
      }
    }
    BitSet union = new BitSet();
    for (int i = 2; i < 100; i++) {
      union = union.union(sets[i]);
    }

    List<Integer> act = new ArrayList<>();
    List<Integer> exp = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97);
    for (int i = 2; i < 100; i++) {
      if (!union.contains(i)) {
        act.add(i);
      }
    }
    assertEquals(exp, act);
  }
}