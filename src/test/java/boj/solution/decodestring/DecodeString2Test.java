package boj.solution.decodestring;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.*;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
public class DecodeString2Test {

  @Rule
  public final Timeout globalTimeout = Timeout.seconds(1);

  @Test
  public void test() {
    assertEquals(0, new DecodeString2().solve(""));
    assertEquals(1, new DecodeString2().solve("a"));
    assertEquals(4, new DecodeString2().solve("abcd"));
    assertEquals(4, new DecodeString2().solve("aaaaaaa"));
    assertEquals(5, new DecodeString2().solve("aaaaaaaaaa"));
    assertEquals(6, new DecodeString2().solve("ababababa"));
    assertEquals(15, new DecodeString2().solve("abababababcdefgabcdefgabcdefg"));
    assertEquals(18, new DecodeString2().solve(
        "abababababcdefgabcdefgabcdefgabababababcdefgabcdefgabcdefgabababababcdefgabcdefgabcdefg"));
    assertEquals(3, new DecodeString2().solve("aaa"));
    assertEquals(21, new DecodeString2().solve(
        "aabbcaabbcdaabbcaabbcdeaabbcaabbcdaabbcaabbcdefaabbcaabbcdaabbcaabbcdeaabbcaabbcdaabbcaabbcdefg"));
    assertEquals(11, new DecodeString2().solve("cxcwgdppwgdppwgdpp"));
    assertEquals(26, new DecodeString2().solve(
        "dddeenwheenwheenwheenwhdtxbhdduohdduohdduohdduodddeenwheenwheenwheenwhdtxbhdduohdduohdduohdduo"));
    assertEquals(84, new DecodeString2().solve(
        "dddeenwheenwheenwheenwhdtxbhdduohdduohdduohdduodddeenwheenwheenwheenwhdtxbhdduohdduohdduohdduoxxxddde" +
            "enwheenwheenwheenwhdtxbhdduohdduohdduohdduodddeenwheenwheenwheenwhdtxbhdduohdduohdduohdduoyyydddeenwh" +
            "eenwheenwheenwhdtxbhdduohdduohdduohdduodddeenwheenwheenwheenwhdtxbhdduohdduohdduohdduo"));
  }
}
