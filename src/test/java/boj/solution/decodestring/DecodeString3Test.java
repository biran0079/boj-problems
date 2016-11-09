package boj.solution.decodestring;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.*;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
public class DecodeString3Test {

  @Rule
  public final Timeout globalTimeout = Timeout.seconds(1);

  @Test
  public void test() {
    testInternal(0, "");
    testInternal(1, "a");
    testInternal(4, "abcd");
    testInternal(4, "aaaaaaa");
    testInternal(5, "aaaaaaaaaa");
    testInternal(6, "ababababa");
    testInternal(15, "abababababcdefgabcdefgabcdefg");
    testInternal(18, 
        "abababababcdefgabcdefgabcdefgabababababcdefgabcdefgabcdefgabababababcdefgabcdefgabcdefg");
    testInternal(3, "aaa");
    testInternal(21, 
        "aabbcaabbcdaabbcaabbcdeaabbcaabbcdaabbcaabbcdefaabbcaabbcdaabbcaabbcdeaabbcaabbcdaabbcaabbcdefg");
    testInternal(11, "cxcwgdppwgdppwgdpp");
    testInternal(26, 
        "dddeenwheenwheenwheenwhdtxbhdduohdduohdduohdduodddeenwheenwheenwheenwhdtxbhdduohdduohdduohdduo");
    testInternal(84, 
        "dddeenwheenwheenwheenwhdtxbhdduohdduohdduohdduodddeenwheenwheenwheenwhdtxbhdduohdduohdduohdduoxxxddde" +
            "enwheenwheenwheenwhdtxbhdduohdduohdduohdduodddeenwheenwheenwheenwhdtxbhdduohdduohdduohdduoyyydddeenwh" +
            "eenwheenwheenwhdtxbhdduohdduohdduohdduodddeenwheenwheenwheenwhdtxbhdduohdduohdduohdduo");
  }

  private void testInternal(int len, String input) {
    String output = new DecodeString3().solve(input);
    assertEquals(len, output.length());
    assertEquals(input, new DecodeString().decode(output));
  }

  private static class DecodeString {

    int idx;

    String decode(String s) {
      idx = 0;
      char[] cs = s.toCharArray();
      StringBuilder sb = new StringBuilder();
      parse(cs, sb);
      return sb.toString();
    }

    private void parse(char[] s, StringBuilder sb) {
      while (idx < s.length && s[idx] != ']') {
        if (Character.isDigit(s[idx])) {
          int n = parseNum(s);
          idx++; // skip [
          StringBuilder subBuilder = new StringBuilder();
          parse(s, subBuilder);
          String sub = subBuilder.toString();
          for (int i = 0; i < n; i++) {
            sb.append(sub);
          }
          idx++; // skip ]
        } else {
          sb.append(s[idx]);
          idx++;
        }
      }
    }

    private int parseNum(char[] s) {
      int res = 0;
      while (idx < s.length && Character.isDigit(s[idx])) {
        res *= 10;
        res += s[idx] - '0';
        idx++;
      }
      return res;
    }
  }
}
