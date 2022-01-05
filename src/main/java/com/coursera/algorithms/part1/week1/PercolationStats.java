package com.coursera.algorithms.part1.week1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
  private static final double CONFIDENCE_95 = 1.96;
  private final double[] thresholds;

  // perform independent trials on an n-by-n grid
  public PercolationStats(int n, int trials) {
    thresholds = new double[trials];
    for (int i = 0; i < trials; i++) {
      Percolation p = new Percolation(n);
      while (!p.percolates()) {
        int row = StdRandom.uniform(1, n + 1);
        int col = StdRandom.uniform(1, n + 1);
        p.open(row, col);
      }
      int totalSites = n * n;
      thresholds[i] = (0.0 + p.numberOfOpenSites()) / totalSites;
    }
  }

  // sample mean of percolation threshold
  public double mean() {
    return StdStats.mean(thresholds);
  }

  // sample standard deviation of percolation threshold
  public double stddev() {
    return StdStats.stddev(thresholds);
  }

  // low endpoint of 95% confidence interval
  public double confidenceLo() {
    double mean = mean();
    return mean - (CONFIDENCE_95 / Math.sqrt(thresholds.length));
  }

  // high endpoint of 95% confidence interval
  public double confidenceHi() {
    double mean = mean();
    return mean + (CONFIDENCE_95 / Math.sqrt(thresholds.length));
  }

  // test client (see below)
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    int trials = Integer.parseInt(args[1]);
    PercolationStats stats = new PercolationStats(n, trials);
    System.out.println("mean   " + "                 = " + stats.mean());
    System.out.println("stddev                  = " + stats.stddev());
    System.out.println(
        "95% confidence interval = [" + stats.confidenceLo() + "," + stats.confidenceHi() + "]");
  }
}
