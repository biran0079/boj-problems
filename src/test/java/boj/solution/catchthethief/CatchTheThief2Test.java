package boj.solution.catchthethief;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.assertEquals;

/**
 * Created by biran on 11/3/16.
 */
public class CatchTheThief2Test {

  @Rule
  public final Timeout globalTimeout = Timeout.seconds(1);
  private final CatchTheThief2 solver = new CatchTheThief2();
  
  @Test
  public void test(){
    int[] ans = new int[] {
        0, 1, 2, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34
    };
    for (int i = 0; i < 20; i++) {
      assertEquals(ans[i], solver.solve(i));
    }
  }
}