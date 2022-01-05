package com.coursera.algorithms.part1.week2;

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
  public static void main(String []args) {
    int K = Integer.parseInt(args[0]);
    String []s = StdIn.readString().split(" ");
    RandomizedQueue<String> queues = new RandomizedQueue<>();
    for(int i=0; i<s.length; i++) {
      queues.enqueue(s[i]);
    }
    for(String val : queues) {
      System.out.println(val);
      if(--K == 0) {
        break;
      }
    }
  }
}
