package boj.solution.bitset;

/**
 * Created by biran on 11/10/16.
 */
class BitSet {

  int[] data;

  /**
   * Constructs an empty bit set with capacity n.
   * Numbers from 0 to n - 1
   */
  BitSet() {
    this.data = new int[1];
  }

  boolean isValid(int i) {
    return i >= 0 && i < data.length * 32;
  }

  /**
   * Returns if int i is in the set.
   */
  boolean contains(int i) {
    if (!isValid(i)) {
      return false;
    }
    return (data[i / 32] & (1 << (i % 32))) != 0;
  }

  private void resize(int n) {
    int size = data.length;
    do {
      size <<= 1;
    }
    while (size * 32 <= n);
    int[] old = data;
    data = new int[size];
    for (int i = 0; i < old.length; i++) {
      data[i] = old[i];
    }
  }

  /**
   * Add i to set.
   */
  void add(int i) {
    if (!isValid(i)) {
      resize(i);
    }
    data[i / 32] |= (1 << (i % 32));
  }

  /**
   * Remove i from set. Do nothing if i is not in the set.
   */
  void remove(int i) {
    if (!isValid(i)) {
      return;
    }
    data[i / 32] &= ~(1 << (i % 32));
  }

  /**
   * Return intersection of two bitsets.
   */
  BitSet intersection(BitSet bitSet) {
    BitSet result = new BitSet();
    result.data = new int[Math.min(data.length, bitSet.data.length)];
    for (int i = 0; i < result.data.length; i++) {
      result.data[i] = data[i] & bitSet.data[i];
    }
    return result;
  }

  /**
   * Return union of two bitsets.
   */
  BitSet union(BitSet bitSet) {
    BitSet result = new BitSet();
    result.data = new int[Math.max(data.length, bitSet.data.length)];
    for (int i = 0; i < result.data.length; i++) {
      int a = i < data.length ? data[i] : 0;
      int b = i < bitSet.data.length ? bitSet.data[i] : 0;
      result.data[i] = a | b;
    }
    return result;
  }
}
