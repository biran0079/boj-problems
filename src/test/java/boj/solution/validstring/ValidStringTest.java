package boj.solution.validstring;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by biran on 11/11/16.
 */
public class ValidStringTest {

  int[] ans = new int[] {
      0,
      3,
      9,
      21,
      51,
      123,
      297,
      717,
      1731,
      4179,
      10089,
      24357,
      58803,
      141963,
      342729,
      827421,
      1997571,
      4822563};

  @Test
  public void test() {
    for (int i = 0; i < ans.length; i++) {
      assertEquals(ans[i], new ValidString().solve(i));
    }
  }
}