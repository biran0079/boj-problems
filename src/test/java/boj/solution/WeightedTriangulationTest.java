package boj.solution;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.assertEquals;

public class WeightedTriangulationTest {

  @Rule
  public final Timeout globalTimeout = Timeout.seconds(1);
  private final WeightedTriangulation solver = new WeightedTriangulation();

  @Test
  public void test() {
    test(new int[] {60, 48, 29}, 83520);
    test(new int[] {47, 15, 53, 91}, 264046);
    test(new int[] {61, 19, 54, 77, 77}, 677893);
    test(new int[] {73, 62, 95, 44, 84, 75}, 1899715);
    test(new int[] {41, 20, 43, 88, 24, 47, 52}, 692356);
    test(new int[] {60, 3, 82, 92, 23, 45, 45, 37}, 1106340);
    test(new int[] {87, 2, 62, 25, 53, 38, 35, 60, 75}, 1247620);
    test(new int[] {55, 30, 98, 91, 74, 36, 12, 62, 19, 77, 16, 46, 7, 16, 8, 37, 43, 47, 87, 88, 5, 58, 8, 17, 51, 18,
        58, 18, 38, 72, 57, 51, 26, 80, 97, 62, 35, 20, 67, 73, 17, 69, 5, 52, 89, 43, 1, 41, 23, 80, 68, 14, 16, 23,
        57, 22, 5, 71, 36, 65, 19, 53, 67, 67, 31, 97, 88, 63, 30, 25, 98, 21, 97, 57, 86, 41, 90, 51, 71, 34, 30, 65,
        86, 4, 84, 62, 92, 50, 28, 53, 49, 45, 41, 10, 25, 62, 94, 59, 17, 11}, 31198378);
  }

  private void test(int[] weight, int expected) {
    assertEquals(expected, solver.solve(weight));
  }
}
