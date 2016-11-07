package boj.solution.autorace;

import java.util.Arrays;

/**
 * Created by biran on 11/7/16.
 */
public class AutoRacing {

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

  private static class Elem {
    int[] rec;
    int idx;

    public Elem(int[] rec, int idx) {
      this.rec = rec;
      this.idx = idx;
    }
  }
}
