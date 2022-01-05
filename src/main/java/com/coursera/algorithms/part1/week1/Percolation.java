package com.coursera.algorithms.part1.week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/** Percolation class. */
public class Percolation {
  private final boolean[][] openedSites;
  private int openSites;
  private final WeightedQuickUnionUF uf;
  private final int top = 0;
  private final int bottom;

  // creates n-by-n grid, with all sites initially blocked
  public Percolation(int n) {
    if (n <= 0) {
      throw new IllegalArgumentException("Input argument must be non negative");
    }
    bottom = n * n + 1;
    openedSites = new boolean[n][n];
    uf = new WeightedQuickUnionUF((n * n) + 2);
  }

  // opens the site (row, col) if it is not open already
  public void open(int row, int col) {
    if (row < 1 || row > openedSites.length || col < 1 || col > openedSites.length) {
      throw new IllegalArgumentException(
          String.format(
              "Given arguments" + " (%d,%d) is out of bound (%d,%d)",
              row, col, openedSites.length, openedSites.length));
    }
    if (isOpen(row, col)) return;
    openSites++;
    int i = row - 1;
    int j = col - 1;
    openedSites[i][j] = true;
    if (row == 1) {
      uf.union(getIndex(row, col), top);
    } else if (row == openedSites.length) {
      uf.union(getIndex(row, col), bottom);
    }
    if (row > 1 && isOpen(row - 1, col)) {
      int p = getIndex(row, col);
      int q = getIndex(row - 1, col);
      uf.union(p, q);
    }
    if (row < openedSites.length && isOpen(row + 1, col)) {
      int p = getIndex(row, col);
      int q = getIndex(row + 1, col);
      uf.union(p, q);
    }
    if (col > 1 && isOpen(row, col - 1)) {
      int p = getIndex(row, col);
      int q = getIndex(row, col - 1);
      uf.union(p, q);
    }
    if (col < openedSites.length && isOpen(row, col + 1)) {
      int p = getIndex(row, col);
      int q = getIndex(row, col + 1);
      uf.union(p, q);
    }
  }

  private int getIndex(int rowIndex, int colIndex) {
    if (rowIndex < 1
        || rowIndex > openedSites.length
        || colIndex < 1
        || colIndex > openedSites.length) {
      throw new IllegalArgumentException(
          String.format(
              "Given arguments" + " (%d,%d) is out of bound (%d,%d)",
              rowIndex, colIndex, openedSites.length - 1, openedSites.length - 1));
    }
    return (rowIndex - 1) * openedSites.length + colIndex;
  }

  // is the site (row, col) open?
  public boolean isOpen(int row, int col) {
    if (row < 1 || row > openedSites.length || col < 1 || col > openedSites.length) {
      throw new IllegalArgumentException(
          String.format(
              "Given arguments" + " (%d,%d) is out of bound (%d,%d)",
              row, col, openedSites.length, openedSites.length));
    }
    return openedSites[row - 1][col - 1];
  }

  // is the site (row, col) full?
  public boolean isFull(int row, int col) {
    if (row < 1 || row > openedSites.length || col < 1 || col > openedSites.length) {
      throw new IllegalArgumentException(
          String.format(
              "Given arguments" + " (%d,%d) is out of bound (%d,%d)",
              row, col, openedSites.length, openedSites.length));
    }

    return uf.find(top) == uf.find(getIndex(row, col));
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
    return uf.find(top) == uf.find(bottom);
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
    System.out.println(percolation.isFull(4, 3));
  }
}
