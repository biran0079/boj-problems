package boj.solution;

public class MazeNavigator {

  private final char[][] maze;
  private final int rowNum;
  private final int colNum;
  private int[] p;

  MazeNavigator(char[][] maze) {
    this.maze = maze;
    rowNum = maze.length;
    colNum = maze[0].length;
    p = new int[rowNum * colNum];
    for (int i = 0; i < p.length; i++) {
      p[i] = i;
    }
    for (int i = 0; i < rowNum; i++) {
      for (int j = 0; j < colNum; j++) {
        if (maze[i][j] != ' ') {
          continue;
        }
        removeWall(new int[] {i, j});
      }
    }
  }

  int find(int i) {
    return p[i] == i ? i : (p[i] = find(p[i]));
  }

  void union(int i, int j) {
    p[find(i)] = find(j);
  }

  boolean isConnected(int[] from, int[] to) {
    return find(from[0] * colNum + from[1]) == find(to[0] * colNum + to[1]);
  }

  void removeWall(int[] pos) {
    int i = pos[0];
    int j = pos[1];
    maze[i][j] = ' ';
    int[] dx = new int[] {1,0,-1,0};
    int[] dy = new int[] {0,1,0,-1};
    for (int k = 0; k < 4; k++) {
      int newI = i + dx[k];
      int newJ = j + dy[k];
      if (newI >= 0 && newI < rowNum && newJ >= 0 && newJ < colNum && maze[newI][newJ] == ' ') {
        union(i * colNum + j, newI * colNum + newJ);
      }
    }
  }
}
