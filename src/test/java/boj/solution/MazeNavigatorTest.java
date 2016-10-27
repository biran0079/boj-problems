package boj.solution;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.assertEquals;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
public class MazeNavigatorTest {

  @Rule
  public final Timeout globalTimeout = Timeout.seconds(1);

  @Test
  public void test1() {
    char[][] maze = toChars(new String[] {
        "###########",
        "# #####   #",
        "#   ### ###",
        "###   #####",
        "##  ###   #",
        "###########",
    });
    MazeNavigator solver = new MazeNavigator(maze);
    assertEquals(false, solver.isConnected(new int[]{1, 1}, new int[] {1, 7}));
    assertEquals(false, solver.isConnected(new int[]{1, 1}, new int[] {1, 8}));
    assertEquals(false, solver.isConnected(new int[]{1, 1}, new int[] {1, 9}));
    assertEquals(true, solver.isConnected(new int[]{1, 1}, new int[] {2, 1}));
    assertEquals(true, solver.isConnected(new int[]{1, 1}, new int[] {2, 2}));
    assertEquals(true, solver.isConnected(new int[]{1, 1}, new int[] {2, 3}));
    assertEquals(false, solver.isConnected(new int[]{1, 1}, new int[] {2, 7}));
    assertEquals(true, solver.isConnected(new int[]{1, 1}, new int[] {3, 3}));
    assertEquals(true, solver.isConnected(new int[]{1, 1}, new int[] {3, 4}));
    assertEquals(true, solver.isConnected(new int[]{1, 1}, new int[] {3, 5}));
    assertEquals(true, solver.isConnected(new int[]{1, 1}, new int[] {4, 2}));
    assertEquals(true, solver.isConnected(new int[]{1, 1}, new int[] {4, 3}));
    assertEquals(false, solver.isConnected(new int[]{1, 1}, new int[] {4, 7}));
    assertEquals(false, solver.isConnected(new int[]{1, 1}, new int[] {4, 8}));
    assertEquals(false, solver.isConnected(new int[]{1, 1}, new int[] {4, 9}));
    assertEquals(true, solver.isConnected(new int[]{1, 7}, new int[] {1, 8}));
    assertEquals(true, solver.isConnected(new int[]{1, 7}, new int[] {1, 9}));
    assertEquals(false, solver.isConnected(new int[]{1, 7}, new int[] {2, 1}));
    assertEquals(false, solver.isConnected(new int[]{1, 7}, new int[] {2, 2}));
    assertEquals(false, solver.isConnected(new int[]{1, 7}, new int[] {2, 3}));
    assertEquals(true, solver.isConnected(new int[]{1, 7}, new int[] {2, 7}));
    assertEquals(false, solver.isConnected(new int[]{1, 7}, new int[] {3, 3}));
    assertEquals(false, solver.isConnected(new int[]{1, 7}, new int[] {3, 4}));
    assertEquals(false, solver.isConnected(new int[]{1, 7}, new int[] {3, 5}));
    assertEquals(false, solver.isConnected(new int[]{1, 7}, new int[] {4, 2}));
    assertEquals(false, solver.isConnected(new int[]{1, 7}, new int[] {4, 3}));
    assertEquals(false, solver.isConnected(new int[]{1, 7}, new int[] {4, 7}));
    assertEquals(false, solver.isConnected(new int[]{1, 7}, new int[] {4, 8}));
    assertEquals(false, solver.isConnected(new int[]{1, 7}, new int[] {4, 9}));
    assertEquals(true, solver.isConnected(new int[]{1, 8}, new int[] {1, 9}));
    assertEquals(false, solver.isConnected(new int[]{1, 8}, new int[] {2, 1}));
    assertEquals(false, solver.isConnected(new int[]{1, 8}, new int[] {2, 2}));
    assertEquals(false, solver.isConnected(new int[]{1, 8}, new int[] {2, 3}));
    assertEquals(true, solver.isConnected(new int[]{1, 8}, new int[] {2, 7}));
    assertEquals(false, solver.isConnected(new int[]{1, 8}, new int[] {3, 3}));
    assertEquals(false, solver.isConnected(new int[]{1, 8}, new int[] {3, 4}));
    assertEquals(false, solver.isConnected(new int[]{1, 8}, new int[] {3, 5}));
    assertEquals(false, solver.isConnected(new int[]{1, 8}, new int[] {4, 2}));
    assertEquals(false, solver.isConnected(new int[]{1, 8}, new int[] {4, 3}));
    assertEquals(false, solver.isConnected(new int[]{1, 8}, new int[] {4, 7}));
    assertEquals(false, solver.isConnected(new int[]{1, 8}, new int[] {4, 8}));
    assertEquals(false, solver.isConnected(new int[]{1, 8}, new int[] {4, 9}));
    assertEquals(false, solver.isConnected(new int[]{1, 9}, new int[] {2, 1}));
    assertEquals(false, solver.isConnected(new int[]{1, 9}, new int[] {2, 2}));
    assertEquals(false, solver.isConnected(new int[]{1, 9}, new int[] {2, 3}));
    assertEquals(true, solver.isConnected(new int[]{1, 9}, new int[] {2, 7}));
    assertEquals(false, solver.isConnected(new int[]{1, 9}, new int[] {3, 3}));
    assertEquals(false, solver.isConnected(new int[]{1, 9}, new int[] {3, 4}));
    assertEquals(false, solver.isConnected(new int[]{1, 9}, new int[] {3, 5}));
    assertEquals(false, solver.isConnected(new int[]{1, 9}, new int[] {4, 2}));
    assertEquals(false, solver.isConnected(new int[]{1, 9}, new int[] {4, 3}));
    assertEquals(false, solver.isConnected(new int[]{1, 9}, new int[] {4, 7}));
    assertEquals(false, solver.isConnected(new int[]{1, 9}, new int[] {4, 8}));
    assertEquals(false, solver.isConnected(new int[]{1, 9}, new int[] {4, 9}));
    assertEquals(true, solver.isConnected(new int[]{2, 1}, new int[] {2, 2}));
    assertEquals(true, solver.isConnected(new int[]{2, 1}, new int[] {2, 3}));
    assertEquals(false, solver.isConnected(new int[]{2, 1}, new int[] {2, 7}));
    assertEquals(true, solver.isConnected(new int[]{2, 1}, new int[] {3, 3}));
    assertEquals(true, solver.isConnected(new int[]{2, 1}, new int[] {3, 4}));
    assertEquals(true, solver.isConnected(new int[]{2, 1}, new int[] {3, 5}));
    assertEquals(true, solver.isConnected(new int[]{2, 1}, new int[] {4, 2}));
    assertEquals(true, solver.isConnected(new int[]{2, 1}, new int[] {4, 3}));
    assertEquals(false, solver.isConnected(new int[]{2, 1}, new int[] {4, 7}));
    assertEquals(false, solver.isConnected(new int[]{2, 1}, new int[] {4, 8}));
    assertEquals(false, solver.isConnected(new int[]{2, 1}, new int[] {4, 9}));
    assertEquals(true, solver.isConnected(new int[]{2, 2}, new int[] {2, 3}));
    assertEquals(false, solver.isConnected(new int[]{2, 2}, new int[] {2, 7}));
    assertEquals(true, solver.isConnected(new int[]{2, 2}, new int[] {3, 3}));
    assertEquals(true, solver.isConnected(new int[]{2, 2}, new int[] {3, 4}));
    assertEquals(true, solver.isConnected(new int[]{2, 2}, new int[] {3, 5}));
    assertEquals(true, solver.isConnected(new int[]{2, 2}, new int[] {4, 2}));
    assertEquals(true, solver.isConnected(new int[]{2, 2}, new int[] {4, 3}));
    assertEquals(false, solver.isConnected(new int[]{2, 2}, new int[] {4, 7}));
    assertEquals(false, solver.isConnected(new int[]{2, 2}, new int[] {4, 8}));
    assertEquals(false, solver.isConnected(new int[]{2, 2}, new int[] {4, 9}));
    assertEquals(false, solver.isConnected(new int[]{2, 3}, new int[] {2, 7}));
    assertEquals(true, solver.isConnected(new int[]{2, 3}, new int[] {3, 3}));
    assertEquals(true, solver.isConnected(new int[]{2, 3}, new int[] {3, 4}));
    assertEquals(true, solver.isConnected(new int[]{2, 3}, new int[] {3, 5}));
    assertEquals(true, solver.isConnected(new int[]{2, 3}, new int[] {4, 2}));
    assertEquals(true, solver.isConnected(new int[]{2, 3}, new int[] {4, 3}));
    assertEquals(false, solver.isConnected(new int[]{2, 3}, new int[] {4, 7}));
    assertEquals(false, solver.isConnected(new int[]{2, 3}, new int[] {4, 8}));
    assertEquals(false, solver.isConnected(new int[]{2, 3}, new int[] {4, 9}));
    assertEquals(false, solver.isConnected(new int[]{2, 7}, new int[] {3, 3}));
    assertEquals(false, solver.isConnected(new int[]{2, 7}, new int[] {3, 4}));
    assertEquals(false, solver.isConnected(new int[]{2, 7}, new int[] {3, 5}));
    assertEquals(false, solver.isConnected(new int[]{2, 7}, new int[] {4, 2}));
    assertEquals(false, solver.isConnected(new int[]{2, 7}, new int[] {4, 3}));
    assertEquals(false, solver.isConnected(new int[]{2, 7}, new int[] {4, 7}));
    assertEquals(false, solver.isConnected(new int[]{2, 7}, new int[] {4, 8}));
    assertEquals(false, solver.isConnected(new int[]{2, 7}, new int[] {4, 9}));
    assertEquals(true, solver.isConnected(new int[]{3, 3}, new int[] {3, 4}));
    assertEquals(true, solver.isConnected(new int[]{3, 3}, new int[] {3, 5}));
    assertEquals(true, solver.isConnected(new int[]{3, 3}, new int[] {4, 2}));
    assertEquals(true, solver.isConnected(new int[]{3, 3}, new int[] {4, 3}));
    assertEquals(false, solver.isConnected(new int[]{3, 3}, new int[] {4, 7}));
    assertEquals(false, solver.isConnected(new int[]{3, 3}, new int[] {4, 8}));
    assertEquals(false, solver.isConnected(new int[]{3, 3}, new int[] {4, 9}));
    assertEquals(true, solver.isConnected(new int[]{3, 4}, new int[] {3, 5}));
    assertEquals(true, solver.isConnected(new int[]{3, 4}, new int[] {4, 2}));
    assertEquals(true, solver.isConnected(new int[]{3, 4}, new int[] {4, 3}));
    assertEquals(false, solver.isConnected(new int[]{3, 4}, new int[] {4, 7}));
    assertEquals(false, solver.isConnected(new int[]{3, 4}, new int[] {4, 8}));
    assertEquals(false, solver.isConnected(new int[]{3, 4}, new int[] {4, 9}));
    assertEquals(true, solver.isConnected(new int[]{3, 5}, new int[] {4, 2}));
    assertEquals(true, solver.isConnected(new int[]{3, 5}, new int[] {4, 3}));
    assertEquals(false, solver.isConnected(new int[]{3, 5}, new int[] {4, 7}));
    assertEquals(false, solver.isConnected(new int[]{3, 5}, new int[] {4, 8}));
    assertEquals(false, solver.isConnected(new int[]{3, 5}, new int[] {4, 9}));
    assertEquals(true, solver.isConnected(new int[]{4, 2}, new int[] {4, 3}));
    assertEquals(false, solver.isConnected(new int[]{4, 2}, new int[] {4, 7}));
    assertEquals(false, solver.isConnected(new int[]{4, 2}, new int[] {4, 8}));
    assertEquals(false, solver.isConnected(new int[]{4, 2}, new int[] {4, 9}));
    assertEquals(false, solver.isConnected(new int[]{4, 3}, new int[] {4, 7}));
    assertEquals(false, solver.isConnected(new int[]{4, 3}, new int[] {4, 8}));
    assertEquals(false, solver.isConnected(new int[]{4, 3}, new int[] {4, 9}));
    assertEquals(true, solver.isConnected(new int[]{4, 7}, new int[] {4, 8}));
    assertEquals(true, solver.isConnected(new int[]{4, 7}, new int[] {4, 9}));
    assertEquals(true, solver.isConnected(new int[]{4, 8}, new int[] {4, 9}));
  }

  private char[][] toChars(String[] maze) {
    char[][] res = new char[maze.length][];
    for (int i = 0; i < maze.length; i++) {
      res[i] = maze[i].toCharArray();
    }
    return res;
  }
}
