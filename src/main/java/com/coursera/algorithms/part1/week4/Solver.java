package com.coursera.algorithms.part1.week4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.LinkedList;

public class Solver {
  private static final int PRIOTITY_FUNCTION_MANHATTAN = 0;
  private static final int PRIORITY_FUNCTION_HAMMING = 1;
  private static final int PRIORITY_FUNCTION = PRIOTITY_FUNCTION_MANHATTAN;

  private SearchNode searchPath;

  private class SearchNode implements Comparable<SearchNode> {
    Board board;
    SearchNode prev;
    int moves;

    SearchNode(Board board, SearchNode prev) {
      this.board = board;
      this.prev = prev;
      if(prev != null) {
        moves += this.prev.moves + 1;
      } else {
        moves = 1;
      }
    }


    @Override
    public int compareTo(SearchNode that) {
      if(PRIORITY_FUNCTION == PRIOTITY_FUNCTION_MANHATTAN) {
        int thisManhattanPriority = this.board.manhattan()+moves;
        int thatManhattanPriority = that.board.manhattan()+that.moves;
        return Integer.compare(thisManhattanPriority, thatManhattanPriority);
      } else {
        int thisHammingPriority = this.board.hamming()+moves;
        int thatHammingPriority = that.board.hamming()+that.moves;
        return Integer.compare(thisHammingPriority, thatHammingPriority);
      }
    }
  }

  // find a solution to the initial board (using the A* algorithm)
  public Solver(Board initial) {
    searchPath = performAStartSearch(new SearchNode(initial, null), new MinPQ<>());
  }

  private ArrayList<Board> visited = new ArrayList<>();
  private SearchNode performAStartSearch(SearchNode currentSearchNode, MinPQ<SearchNode> pq) {
    SearchNode path = null;
    System.out.println("Processing Current Node : \n" + currentSearchNode.board);
    if (!currentSearchNode.board.isGoal()) {
      for(Board b : currentSearchNode.board.neighbors()) {
        if (currentSearchNode.prev == null || !currentSearchNode.prev.board.equals(b) || !visited.contains(b)) {
          SearchNode node = new SearchNode(b, currentSearchNode);
          System.out.println("Inserting Node into Priority Queue : \n" + node.board);
          pq.insert(node);
          visited.add(b);
        }
      }
      if (!pq.isEmpty()) {
        currentSearchNode = pq.delMin();
        path = performAStartSearch(currentSearchNode, pq);
      }

    } else if(currentSearchNode.board.isGoal()) {
      path = currentSearchNode;
    }
    return path;
  }

  // is the initial board solvable? (see below)
  public boolean isSolvable() {
    return searchPath != null && searchPath.board.isGoal();
  }

  // min number of moves to solve initial board; -1 if unsolvable
  public int moves() {
    if(searchPath != null && searchPath.board.isGoal()) {
      return searchPath.moves;
    } else {
      return -1;
    }
  }

  // sequence of boards in a shortest solution; null if unsolvable
  public Iterable<Board> solution() {
    if(searchPath == null || !searchPath.board.isGoal()) return null;
    LinkedList<Board> solution = new LinkedList<>();
    SearchNode node = searchPath;
    while(node != null) {
      solution.addFirst(node.board);
      node = node.prev;
    }
    return solution;
  }

  public static void main(String[] args) {
    args = new String[] { "tile3.txt"};
    // create initial board from file
    In in = new In(args[0]);
    int n = in.readInt();
    int[][] tiles = new int[n][n];
    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++)
        tiles[i][j] = in.readInt();
    Board initial = new Board(tiles);

    // solve the puzzle
    Solver solver = new Solver(initial);

    // print solution to standard output
    if (!solver.isSolvable())
      StdOut.println("No solution possible");
    else {
      StdOut.println("Minimum number of moves = " + solver.moves());
      for (Board board : solver.solution())
        StdOut.println(board);
    }
  }
}
