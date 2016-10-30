package boj.solution;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * Created by biran on 10/28/16.
 */
public class RandomSamplerTest {

  private final RandomSampler randomSampler = new RandomSampler();

  @Test
  public void test() {
    randomSampler.add(1);
    randomSampler.add(2);
    randomSampler.add(3);

    randomSampler.remove(2);
    testRandomSample(new int[] {1,3});
    randomSampler.add(2);
    randomSampler.remove(1);
    testRandomSample(new int[] {2,3});
    randomSampler.add(1);
    randomSampler.remove(3);
    testRandomSample(new int[] {1,2});
    randomSampler.add(3);
  }

  @Test
  public void test2() {
    for (int i = 0; i < 100000; i++) {
      randomSampler.add(i);
    }
    for (int i = 0; i < 100000; i+=2) {
      randomSampler.remove(i);
      randomSampler.getRandom();
    }
  }

  private void testRandomSample(int[] all) {
    Set<Integer> allNumberSet = new HashSet<>();
    for (int value : all) {
      allNumberSet.add(value);
    }
    int[] samples = new int[all.length * 100];
    for (int i = 0; i < samples.length; i++) {
      samples[i] = randomSampler.getRandom();
      assertTrue(allNumberSet.contains(samples[i]));
    }
    if (uniformChiSquare(samples, all) > 5 * all.length) {
      throw new AssertionError("Your getRandom() is not random enough. "
          + "Try to resubmit if you think this is false negative.");
    }
    int run = 1, longestRun = 1;
    for (int i = 1; i < samples.length; i++) {
      if (samples[i] != samples[i - 1]) {
        longestRun = Math.max(run, longestRun);
        run = 1;
      } else {
        run++;
      }
    }
    longestRun = Math.max(run, longestRun);
    if (longestRun <= 3) {
      throw new AssertionError("Your getRandom() is not random enough. "
          + "Try to resubmit if you think this is false negative.");
    }
  }

  double uniformChiSquare(int[] sample, int[] all) {
    Map<Integer, Integer> count = new HashMap<>();
    for (int value : sample) {
      count.put(value , count.getOrDefault(value , 0) + 1);
    }
    double res = 0;
    double exp = sample.length * 1.0 / all.length;
    for (int value : all) {
      double diff = count.get(value) - exp;
      res += diff * diff / exp;
    }
    return res;
  }
}