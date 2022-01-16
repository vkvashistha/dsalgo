package com.coursera.algorithms.part1.week4;

import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;

public class Board {
  private int[][] tiles = null;
  private int manhattan;
  private int hamming;
  // create a board from an n-by-n array of tiles,
  // where tiles[row][col] = tile at (row, col)
  public Board(int[][] tiles) {
    if (tiles == null) {
      throw new IllegalArgumentException("Argument can't be null");
    }
    int n = tiles.length;
    for (int[] tile : tiles) {
      if (tile.length != n) {
        throw new IllegalArgumentException(
            "Input 2D array must be of having same rows and columns");
      }
    }

    this.tiles = new int[n][n];
    for (int i = 0; i < tiles.length; i++) {
      for (int j = 0; j < tiles[i].length; j++) {
        this.tiles[i][j] = tiles[i][j];
        if (tiles[i][j] != 0 && (tiles[i][j] - 1) != (i * tiles.length + j)) {
          hamming++;
          int num = tiles[i][j];
          if (num == 0) continue;
          int actualRow = num / tiles.length;
          int actualCol = num % tiles.length - 1;
          manhattan += Math.abs(actualRow - i) + Math.abs(actualCol - j);
        }
      }
    }
  }

  // string representation of this board
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append(tiles.length).append("\n");
    for (int i = 0; i < tiles.length; i++) {
      for (int j = 0; j < tiles[i].length; j++) {
        builder.append(tiles[i][j]).append("\t");
      }
      builder.append("\n");
    }
    return builder.toString();
  }

  // board dimension n
  public int dimension() {
    return tiles.length;
  }

  // number of tiles out of place
  public int hamming() {
    return hamming;
  }

  // sum of Manhattan distances between tiles and goal
  public int manhattan() {
    return manhattan;
  }

  // is this board the goal board?
  public boolean isGoal() {
    return hamming() == 0;
  }

  // does this board equal y?
  public boolean equals(Object y) {
    if (!(y instanceof Board)) {
      throw new IllegalArgumentException("Input argument must be an instace of Board");
    }
    Board that = (Board) y;
    if (this.dimension() != that.dimension()) return false;
    for (int i = 0; i < tiles.length; i++) {
      for (int j = 0; j < tiles[i].length; j++) {
        if (tiles[i][j] != that.tiles[i][j]) {
          return false;
        }
      }
    }
    return true;
  }

  // all neighboring boards
  public Iterable<Board> neighbors() {
    ArrayList<Board> neighbors = new ArrayList<>();
    int emptyPosRow = -1;
    int emptyPosCol = -1;
    int[][] newTiles = new int[tiles.length][tiles.length];
    for (int i = 0; i < tiles.length; i++) {
      for (int j = 0; j < tiles[i].length; j++) {
        newTiles[i][j] = tiles[i][j];
        if(tiles[i][j] == 0) {
          emptyPosCol = j;
          emptyPosRow = i;
        }
      }
    }

    if(emptyPosCol > 0) {
      exchange(newTiles, emptyPosRow, emptyPosCol, emptyPosRow, emptyPosCol-1);
      neighbors.add(new Board(newTiles));
      exchange(newTiles, emptyPosRow, emptyPosCol, emptyPosRow, emptyPosCol-1);
    }
    if (emptyPosCol < newTiles.length - 1) {
      exchange(newTiles, emptyPosRow, emptyPosCol, emptyPosRow, emptyPosCol+1);
      neighbors.add(new Board(newTiles));
      exchange(newTiles, emptyPosRow, emptyPosCol, emptyPosRow, emptyPosCol+1);
    }

    if(emptyPosRow > 0) {
      exchange(newTiles, emptyPosRow, emptyPosCol, emptyPosRow-1, emptyPosCol);
      neighbors.add(new Board(newTiles));
      exchange(newTiles, emptyPosRow, emptyPosCol, emptyPosRow-1, emptyPosCol);
    }

    if (emptyPosRow < newTiles.length - 1) {
      exchange(newTiles, emptyPosRow, emptyPosCol, emptyPosRow+1, emptyPosCol);
      neighbors.add(new Board(newTiles));
    }
    return neighbors;
  }

  private int [][] copy(int [][] arr) {
    int [][] copy = new int[arr.length][arr.length];
    for(int i=0; i<arr.length; i++) {
      for(int j=0; j<arr[i].length; j++) {
        copy[i][j] = arr[i][j];
      }
    }
    return copy;
  }

  private void exchange(int [][] tiles, int row1, int col1, int row2, int col2) {
    int temp = tiles[row1][col1];
    tiles[row1][col1] = tiles[row2][col2];
    tiles[row2][col2] = temp;
  }

  // a board that is obtained by exchanging any pair of tiles
  public Board twin() {
    int [][]newTiles = tiles.clone();
    StdRandom.setSeed(200);
    int n1 = StdRandom.uniform(tiles.length*tiles.length);
    int n2 = StdRandom.uniform(tiles.length*tiles.length);
    while(n2 == n1) {
      n2 = StdRandom.uniform(tiles.length*tiles.length);
    }
    int row1 = n1/tiles.length;
    int col1 = n1%tiles.length - 1;
    int row2 = n2/tiles.length;
    int col2 = n2%tiles.length - 1;
    exchange(newTiles, row1, col1, row2, col2);
    return new Board(newTiles);
  }

  // unit testing (not graded)
  public static void main(String[] args) {
    int [][]tiles = new int[][] {
        {1, 0, 3},
        {2,4,5},
        {6,7,8}
    };
    Board board = new Board(tiles);
    assert board.dimension() == 3;
    assert !board.isGoal();
    assert board.hamming() == 7;
    assert board.manhattan() == 10;
    Iterable<Board> boards = board.neighbors();
    for(Board b : boards) {
      assert !b.isGoal();
    }
  }
}
