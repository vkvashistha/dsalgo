package com.coursera.algorithms.part1.week2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

  private static final int N = 50;
  private Object[] queue = new Object[N];
  private int start;
  private int end;
  private int size;
  // construct an empty randomized queue
  public RandomizedQueue() {
    start = 0;
    end = 0;
  }

  // is the randomized queue empty?
  public boolean isEmpty() {
    if (size == 0) {
      start = 0;
      end = 0;
      return true;
    } else {
      return false;
    }
  }

  // return the number of items on the randomized queue
  public int size() {
    return size;
  }

  // add the item
  public void enqueue(Item item) {
    if (item == null) throw new IllegalArgumentException("Null can't be enqueue");
    resizeQueue();
    if (StdRandom.uniform(0, 2) == 0) {
      if (size > 0) start = dec(start);
      queue[start] = item;
    } else {
      if (size > 0) end = inc(end);
      queue[end] = item;
    }
    size++;
  }

  // remove and return a random item
  public Item dequeue() {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is Empty");
    }
    resizeQueue();
    size--;
    if (StdRandom.uniform(0, 2) == 0) {
      Item item = (Item) queue[start];
      start = inc(start);
      return item;
    } else {
      Item item = (Item) queue[end];
      end = dec(end);
      return item;
    }
  }

  private int inc(int index) {
    index = (index + 1) % queue.length;
    return index;
  }

  private int dec(int index) {
    if (index > 0) {
      index = index - 1;
    } else {
      index = queue.length + index - 1;
    }
    return index;
  }

  // return a random item (but do not remove it)
  public Item sample() {
    if (size == 0) {
      throw new NoSuchElementException("Queue is Empty");
    }
    int pos = (start + StdRandom.uniform(size)) % queue.length;
    return (Item) queue[pos];
  }

  // return an independent iterator over items in random order
  public Iterator<Item> iterator() {
    return new RandomListIterator();
  }

  private void resizeQueue() {
    if (size < N) {
      return;
    }
    Object[] newQueue;
    if (size == queue.length) {
      newQueue = new Object[queue.length * 2];
    } else if (size < queue.length / 2) {
      newQueue = new Object[Math.max(N, (int) Math.ceil((queue.length * 3.0) / 4))];
    } else {
      return;
    }
    int idx = 0;
    for (int i = 0; i < size; i++) {
      newQueue[idx++] = queue[(start + i) % queue.length];
    }

    start = 0;
    end = size - 1;
    queue = newQueue;
    /*    for (int i = queue.length - 1; i >= start; i--) {
      newQueue[--idx] = queue[i];
    }
    int newStart = idx;
    for (int i = 0; i <= end; i++) {
      newQueue[i] = queue[i];
    }
    queue = newQueue;
    start = newStart;*/
  }

  private class RandomListIterator implements Iterator<Item> {
    private final Object[] randomItems = new Object[size];
    private int idx = 0;

    private RandomListIterator() {
      for (int i = 0; i < randomItems.length; i++) {
        int index = (start + i) % queue.length;
        randomItems[i] = queue[index];
      }
      StdRandom.shuffle(randomItems);
    }

    @Override
    public boolean hasNext() {
      return idx < randomItems.length;
    }

    @Override
    public Item next() {
      if (!hasNext()) throw new NoSuchElementException("Empty list");
      return (Item) randomItems[idx++];
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }
  // unit testing (required)
  public static void main(String[] args) {
    RandomizedQueue<Integer> queue = new RandomizedQueue<>();
    int[] unexpectedList = new int[] {1, 2, 3, 4, 5};
    for (int j : unexpectedList) {
      queue.enqueue(j);
    }

    int mismatch = 0;
    int[] actualList = new int[unexpectedList.length];
    for (int i = 0; i < unexpectedList.length; i++) {
      actualList[i] = queue.dequeue();
    }
    for (int i = 0; i < unexpectedList.length; i++) {
      if (actualList[i] != unexpectedList[i]) {
        mismatch++;
      }
    }
    assert mismatch > 0;
    StdOut.println("Basic tests passed");
  }
}
