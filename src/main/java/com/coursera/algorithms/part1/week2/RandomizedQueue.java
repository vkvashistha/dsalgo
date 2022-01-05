package com.coursera.algorithms.part1.week2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

  private Deque<Item> deque = new Deque<>();

  // construct an empty randomized queue
  public RandomizedQueue() {}

  // is the randomized queue empty?
  public boolean isEmpty() {
    return deque.isEmpty();
  }

  // return the number of items on the randomized queue
  public int size() {
    return deque.size();
  }

  // add the item
  public void enqueue(Item item) {
    boolean first = StdRandom.uniform(0, 2) == 0;
    if (first) {
      deque.addFirst(item);
    } else {
      deque.addLast(item);
    }
  }

  // remove and return a random item
  public Item dequeue() {
    return deque.removeLast();
  }

  // return a random item (but do not remove it)
  public Item sample() {
    int index = StdRandom.uniform(size());
    return deque.get(index);
  }

  // return an independent iterator over items in random order
  public Iterator<Item> iterator() {
    return new RandomizedIterator();
  }

  private class RandomizedIterator implements Iterator<Item> {

    Deque.Node<Item> start = deque.getfirstNode();
    Deque.Node<Item> end = deque.getLastNode();
    int startIndex = 1;
    int endIndex = size();

    @Override
    public boolean hasNext() {
      return startIndex <= endIndex;
    }

    @Override
    public Item next() {
      if (!hasNext()) {
        throw new NoSuchElementException("No element exist");
      }
      boolean isFirst = StdRandom.uniform(0, 2) == 0;
      Deque.Node<Item> node = null;
      if (isFirst) {
        node = start;
        start = start.next;
        startIndex++;
      } else {
        node = end;
        end = end.prev;
        endIndex--;
      }
      return node.val;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException("Method not supported");
    }
  }
  // unit testing (required)
  public static void main(String[] args) {
    RandomizedQueue<Integer> queue = new RandomizedQueue<>();
    for(int i=0; i<10; i++) {
      queue.enqueue(i);
    }
    /*for(int i=0; i<10; i++) {
      System.out.println(queue.sample());
    }*/
    for(int val : queue) {
      System.out.println(val);
    }
  }
}
