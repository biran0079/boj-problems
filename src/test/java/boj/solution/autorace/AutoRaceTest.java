package boj.solution.autorace;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by biran on 11/7/16.
 */
public class AutoRaceTest {

  @Rule
  public final Timeout globalTimeout = Timeout.seconds(2);
  private final AutoRacing autoRacing = new AutoRacing();
  private final Solver standardSolver = new Solver();
  private final Random random = new Random(0);

  @Test
  public void sample() {
    int[][] data = new int[][] {{1,6},{2,5},{3,4},{2,7}};
    assertArrayEquals(new int[] {2, 1, 0, 1}, autoRacing.solve(data));
  }

  @Test
  public void test() {
    for (int n = 1; n < 100; n++) {
      for (int m = 1; m < 1000; m *= 2) {
        int[][] data = randomInput(n, m);
        int[] exp = standardSolver.solve(data);
        int[] act = autoRacing.solve(data);
        assertArrayEquals(exp, act);
      }
    }
    int[][] data = randomInput(50000, 100000);
    int[] exp = standardSolver.solve(data);
    int[] act = autoRacing.solve(data);
    assertArrayEquals(exp, act);
  }

  private int[][] randomInput(int n, int m) {
    int[][] res = new int[n][2];
    for (int i = 0; i < n; i++) {
      int a = random.nextInt(m);
      int b = random.nextInt(m);
      if (a > b) {
        res[i][0] = b;
        res[i][1] = a;
      } else {
        res[i][0] = a;
        res[i][1] = b;
      }
    }
    return res;
  }

  private static class Solver {

    int[] solve(int[][] record) {
      Elem[] elems = new Elem[record.length];
      for (int i = 0; i < elems.length; i++) {
        elems[i] = new Elem(record[i], i);
      }
      Arrays.sort(elems, (a, b) -> {
        if (a.rec[0] != b.rec[0]) {
          return Integer.compare(a.rec[0], b.rec[0]);
        }
        return Integer.compare(a.rec[1], b.rec[1]);
      });
      int[] res = new int[record.length];
      Elem[] tmp = new Elem[record.length];
      mergeSort(elems, tmp, 0, elems.length - 1, res);
      return res;
    }

    private void mergeSort(Elem[] elems, Elem[] tmp, int from, int to, int[] res) {
      if (from >= to) {
        return;
      }
      int mid = from + (to - from) / 2;
      mergeSort(elems, tmp, from, mid, res);
      mergeSort(elems, tmp, mid + 1, to, res);
      int i = from, j = mid + 1, k = from;
      while (i <= mid && j <= to) {
        if (elems[i].rec[1] <= elems[j].rec[1]) {
          tmp[k++] = elems[i];
          res[elems[i].idx] += j - mid - 1;
          i++;
        } else {
          tmp[k++] = elems[j];
          j++;
        }
      }
      while (i <= mid) {
        tmp[k++] = elems[i];
        res[elems[i].idx] += j - mid - 1;
        i++;
      }
      while (j <= to) {
        tmp[k++] = elems[j];
        j++;
      }
      for (k = from; k <= to; k++) {
        elems[k] = tmp[k];
      }
    }

    int[] naive(int[][] rec) {
      int[] res = new int[rec.length];
      for (int i = 0; i < rec.length; i++) {
        for (int j = 0; j < rec.length; j++) {
          if (i == j) {
            continue;
          }
          if (rec[i][0] < rec[j][0] && rec[i][1] > rec[j][1]) {
            res[i]++;
          }
        }
      }
      return res;
    }

    static class Elem {
      int[] rec;
      int idx;

      public Elem(int[] rec, int idx) {
        this.rec = rec;
        this.idx = idx;
      }
    }
  }
}