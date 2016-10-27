package boj.solution;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
public class MazeNavigator2Test {

  @Rule
  public final Timeout globalTimeout = Timeout.seconds(1);

  @Test
  public void test1() {
    char[][] maze = toChars(new String[] {
        "###########",
        "# #####   #",
        "#   ### ###",
        "####  #####",
        "##  ###   #",
        "###########",
    });
    MazeNavigator2 solver = new MazeNavigator2(maze);
    assertTrue(solver.isConnected(new int[] {1,1}, new int[] {2,3}));
    assertFalse(solver.isConnected(new int[] {1,1}, new int[] {3,4}));
    assertFalse(solver.isConnected(new int[] {1,1}, new int[] {1,7}));
    assertFalse(solver.isConnected(new int[] {1,1}, new int[] {4,7}));
    solver.removeWall(new int[] {3,3});
    assertTrue(solver.isConnected(new int[] {1,1}, new int[] {3,4}));
    assertFalse(solver.isConnected(new int[] {1,1}, new int[] {1,7}));
    assertFalse(solver.isConnected(new int[] {1,1}, new int[] {4,7}));
    solver.removeWall(new int[] {3,6});
    assertTrue(solver.isConnected(new int[] {1,1}, new int[] {3,4}));
    assertFalse(solver.isConnected(new int[] {1,1}, new int[] {1,7}));
    assertFalse(solver.isConnected(new int[] {1,1}, new int[] {4,7}));
    solver.removeWall(new int[] {2,6});
    assertTrue(solver.isConnected(new int[] {1,1}, new int[] {3,4}));
    assertTrue(solver.isConnected(new int[] {1,1}, new int[] {1,7}));
    assertFalse(solver.isConnected(new int[] {1,1}, new int[] {4,7}));
    solver.removeWall(new int[] {4,6});
    assertTrue(solver.isConnected(new int[] {1,1}, new int[] {3,4}));
    assertTrue(solver.isConnected(new int[] {1,1}, new int[] {1,7}));
    assertTrue(solver.isConnected(new int[] {1,1}, new int[] {4,7}));
  }

  @Test
  public void test2() {
    char[][] maze = toChars(new String[] {
        "###########",
        "# #       #",
        "# # ### ###",
        "#   # # # #",
        "# ### ### #",
        "# #       #",
        "###########",
    });
    MazeNavigator2 solver = new MazeNavigator2(maze);
    solver.removeWall(new int[] {4,6});
    assertTrue(solver.isConnected(new int[] {1,1}, new int[] {1,7}));
    assertTrue(solver.isConnected(new int[] {5,3}, new int[] {5,7}));
    assertFalse(solver.isConnected(new int[] {5,1}, new int[] {5,7}));
    solver.removeWall(new int[] {2,5});
    assertTrue(solver.isConnected(new int[] {5,1}, new int[] {5,7}));
  }

  private char[][] toChars(String[] maze) {
    char[][] res = new char[maze.length][];
    for (int i = 0; i < maze.length; i++) {
      res[i] = maze[i].toCharArray();
    }
    return res;
  }
}
