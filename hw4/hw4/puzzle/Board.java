package hw4.puzzle;

import java.util.Arrays;
import java.util.HashSet;

public class Board implements WorldState {
    private final int[][] tiles;
    private final int N;
    private HashSet<WorldState> nbs = null;

    public Board(int[][] tiles) {
        this.N = tiles.length;
        this.tiles = copy2dArray(tiles);
    }

    private int[][] copy2dArray(int[][] srcArray) {
        int[][] destArray = new int[srcArray.length][];
        for (int i = 0; i < srcArray.length; i++) {
            destArray[i] = new int[srcArray[i].length];
            System.arraycopy(srcArray[i], 0, destArray[i], 0, srcArray.length);
        }
        return destArray;
    }

    public int tileAt(int i, int j) {
        if (!valiatePosition(i, j)) {
            throw new IndexOutOfBoundsException();
        }
        return tiles[i][j];
    }

    private boolean valiatePosition(int i, int j) {
        return i >= 0 && i < N && j >= 0 && j < N;
    }

    public int size() {
        return N;
    }

    public Iterable<WorldState> neighbors() {
        if (this.nbs != null) {
            return nbs;
        }
        int x = 0;
        int y = 0;
        int[][] incs = {
                {-1, 0},
                {1, 0},
                {0, 1},
                {0, -1}
        };
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tileAt(i, j) == 0) {
                    x = i;
                    y = j;
                    break;
                }
            }
            if (tileAt(x, y) == 0) {
                break;
            }
        }

        HashSet<WorldState> neighbs = new HashSet<>();
        for (int[] inc : incs) {
            int xx = x + inc[0];
            int yy = y + inc[1];
            if (!valiatePosition(xx, yy)) {
                continue;
            }

            int[][] newArray = copy2dArray(this.tiles);
            int temp = newArray[xx][yy];
            newArray[xx][yy] = newArray[x][y];
            newArray[x][y] = temp;
            neighbs.add(new Board(newArray));
        }

        this.nbs = neighbs;
        return nbs;
    }

    public int hamming() {
        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int goal = i * N + j + 1;
                if (tiles[i][j] != 0 && tiles[i][j] != goal) {
                    res++;
                }
            }
        }
        return res;
    }

    public int manhattan() {
        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int x = (tiles[i][j] - 1) / N;
                int y = (tiles[i][j] - 1) % N;
                if (tiles[i][j] != 0) {
                    res += Math.abs(x - i) + Math.abs(y - j);
                }
            }
        }
        return res;

    }

    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    @Override
    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }
        if (y == null || getClass() != y.getClass()) {
            return false;
        }

        Board that = (Board) y;

        if (this.size() != that.size()) {
            return false;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.tileAt(i, j) != that.tileAt(i, j)) {
                    return false;
                }

            }
        }

        return true;
    }


    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
