package com.coursera.algorithms.part1.week2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
  public static void main(String[] args) {
    int K = Integer.parseInt(args[0]);
    RandomizedQueue<String> queues = new RandomizedQueue<>();
    while (!StdIn.isEmpty()) {
      queues.enqueue(StdIn.readString());
    }
    for (String val : queues) {
      StdOut.println(val);
      K--;
      if (K == 0) {
        break;
      }
    }
  }
}
