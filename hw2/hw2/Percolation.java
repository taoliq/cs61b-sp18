package hw2;

import static org.junit.Assert.*;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF uf;
    private int openSites;
    private boolean[] isOpened;
    private int N;
    private int top, bottom;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        uf = new WeightedQuickUnionUF(N * N + 2);
        this.N = N;
        top = N * N;
        bottom = N * N + 1;
        openSites = 0;
        isOpened = new boolean[N * N + 2];
        for (int i = 0; i < N; i++) {
            uf.union(top, i);
            uf.union(bottom, N * (N - 1) + i);
        }
    }

    private int getId(int row, int col) {
        return row * N + col;
    }

    public void open(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }

        int id = getId(row, col);
        if (isOpened[id]) {
            return;
        }

        isOpened[id] = true;
        openSites += 1;
        int[] neibors = new int[]{id + 1, id - 1, id + N, id - N};
        for (int i : neibors) {
            if (i >= 0 && i < N * N && isOpened[i]) {
                uf.union(i, id);
            }
        }
    }

    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }

        return isOpened[getId(row, col)];
    }

    public boolean isFull(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }

        return uf.connected(top, getId(row, col));
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        return uf.connected(top, bottom);
    }

    public static void main(String[] args) {
        Percolation percolation = new Percolation(5);
        assertFalse(percolation.percolates());
        percolation.open(3, 4);
        percolation.open(2, 4);
        percolation.open(2, 2);
        percolation.open(2, 2);
        percolation.open(2, 3);
        percolation.open(0, 2);
        assertEquals(5, percolation.numberOfOpenSites());
        percolation.open(1, 2);
        assertTrue(percolation.isFull(2, 2));
        percolation.open(4, 4);
        assertTrue(percolation.percolates());
    }
}
