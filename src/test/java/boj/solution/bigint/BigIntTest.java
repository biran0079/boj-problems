package boj.solution.bigint;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.math.BigInteger;

import static org.junit.Assert.*;

/**
 * Created by biran on 11/11/16.
 */
public class BigIntTest {

  @Rule
  public final Timeout globalTimeout = Timeout.seconds(1);

  @Test
  public void testAdd() {
    for (int i = 0; i < 100; i++) {
      for (int j = 0; j < 100; j++) {
        testAdd("" + i, "" + j);
      }
    }
    testAdd("1234324234235675641235423452345", "234523452345234523452346245658756797689");
  }

  @Test
  public void testFact() {
    for (int i = 0; i < 100; i++) {
      assertEquals(fact1(i), fact2(i));
    }
  }

  @Test
  public void testFib() {
    for (int i = 0; i < 100; i++) {
      assertEquals(fib1(i), fib2(i));
    }
  }

  private String fact1(int n) {
    BigInteger a =  new BigInteger("1");
    for (int i = 2; i <= n; i++) {
      a = a.multiply(new BigInteger("" + i));
    }
    return a.toString();
  }

  private String fact2(int n) {
    BigInt a =  new BigInt("1");
    for (int i = 2; i <= n; i++) {
      a = a.multiply(new BigInt("" + i));
    }
    return a.toString();
  }

  private String fib1(int n) {
    BigInteger a =  new BigInteger("0");
    BigInteger b =  new BigInteger("1");
    for (int i = 0; i < n; i++) {
      BigInteger c = a.add(b);
      a = b;
      b = c;
    }
    return b.toString();
  }

  private String fib2(int n) {
    BigInt a =  new BigInt("0");
    BigInt b =  new BigInt("1");
    for (int i = 0; i < n; i++) {
      BigInt c = a.add(b);
      a = b;
      b = c;
    }
    return b.toString();
  }

  private void testAdd(String a, String b) {
    assertEquals(a + " + " + b,
        new BigInteger(a).add(new BigInteger(b)).toString(),
        new BigInt(a).add(new BigInt(b)).toString());
  }
}