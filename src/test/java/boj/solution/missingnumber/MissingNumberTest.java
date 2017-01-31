package boj.solution.missingnumber;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.*;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
public class MissingNumberTest {

  @Rule
  public final Timeout globalTimeout = Timeout.seconds(1);

  @Test
  public void test() {
    MissingNumber missingNumber = new MissingNumber();
    for (int i = 1; i < 50; i++) {
      int[] a = new int[i * 3 - 1];
      for (int j = 1; j <= i; j++) {
        int idx = 0;
        for (int k = 1; k <= i; k++) {
          int ct = k == j ? 2 : 3;
          while (ct > 0) {
            a[idx++] = k;
            ct--;
          }
        }
        assertEquals(j, missingNumber.solve(a));
      }
    }
  }
}
