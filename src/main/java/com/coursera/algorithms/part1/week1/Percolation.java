package com.coursera.algorithms.part1.week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Percolation class.
 */
public class Percolation {
    private final boolean[][] grid;
    private int openSites;
    private final WeightedQuickUnionUF uf;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        grid = new boolean[n][n];
        uf = new WeightedQuickUnionUF(n * n);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        int i = row - 1;
        int j = col - 1;
        if (!grid[i][j]) {
            openSites++;
        }
        grid[i][j] = true;

        if (i > 0 && grid[i - 1][j]) {
            int p = getIndex(i, j);
            int q = getIndex(i - 1, j);
            uf.union(p, q);
        }
        if (i < grid.length - 1 && grid[i + 1][j]) {
            int p = getIndex(i, j);
            int q = getIndex(i + 1, j);
            uf.union(p, q);
        }
        if (j > 0 && grid[i][j - 1]) {
            int p = getIndex(i, j);
            int q = getIndex(i, j - 1);
            uf.union(p, q);
        }
        if (j < grid.length - 1 && grid[i][j + 1]) {
            int p = getIndex(i, j);
            int q = getIndex(i, j + 1);
            uf.union(p, q);
        }
    }

    private int getIndex(int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid.length) {
            throw new IllegalArgumentException(String.format("Given arguments" +
                            " (%d,%d) is out of bound (%d,%d)", row, col, grid.length - 1,
                    grid.length - 1));
        }
        return row * grid.length + col;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
      if(row < 1 || row > grid.length || col < 1 || col > grid.length) {
        throw new IllegalArgumentException(String.format("Given arguments" +
                        " (%d,%d) is out of bound (%d,%d)", row, col, grid.length,
                grid.length));
      }
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
      if(row < 1 || row > grid.length || col < 1 || col > grid.length) {
        throw new IllegalArgumentException(String.format("Given arguments" +
                        " (%d,%d) is out of bound (%d,%d)", row, col, grid.length,
                grid.length));
      }
        return grid[row - 1][col - 1];
    }

    /**
     * returns the number of open sites.
     *
     * @return integer for total number of sites.
     */
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        int firstRow = 0;
        int lastRow = (grid.length - 1) * grid.length;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                int p = firstRow + i;
                int q = lastRow + j;
                if (grid[0][i] && grid[grid.length - 1][j] && uf.find(p) == uf.find(q)) {
                    return true;
                }
            }
        }
        return false;
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation percolation = new Percolation(4);
        System.out.println(percolation.percolates());
        percolation.open(1, 2);
        System.out.println(percolation.percolates());
        percolation.open(2, 2);
        System.out.println(percolation.percolates());
        percolation.open(3, 2);
        System.out.println(percolation.percolates());
        percolation.open(4, 2);
        System.out.println(percolation.percolates());
    }
}
