package com.coursera.algorithms.part1.week2;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

public class RandomizedQueueTest {

  @Test
  public void enqueue() {
    RandomizedQueue<Integer> queue = new RandomizedQueue<>();
    int[] unexpectedList = new int[] {1, 2, 3, 4, 5};
    for (int j : unexpectedList) {
      queue.enqueue(j);
    }
    int mismatch = 0;
    int i = 0;
    for (int item : queue) {
      if (unexpectedList[i++] != item) {
        mismatch++;
      }
    }
    Assert.assertTrue(mismatch > 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void enqueueNull() {
    RandomizedQueue<Integer> queue = new RandomizedQueue<>();
    queue.enqueue(null);
  }

  @Test(expected = NoSuchElementException.class)
  public void sampleEmptyList() {
    RandomizedQueue<Integer> queue = new RandomizedQueue<>();
    queue.sample();
  }

  @Test(expected = NoSuchElementException.class)
  public void dequeueWhenEmptyList() {
    RandomizedQueue<Integer> queue = new RandomizedQueue<>();
    queue.dequeue();
  }

  @Test
  public void dequeueWhenNonEmptyList() {
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
    Assert.assertTrue(mismatch > 0);
  }

  @Test
  public void isEmptyWhenNoItemEnque() {
    RandomizedQueue<Integer> queue = new RandomizedQueue<>();
    Assert.assertTrue(queue.isEmpty());
  }

  @Test
  public void isEmptyWhenOneItemEnque() {
    RandomizedQueue<Integer> queue = new RandomizedQueue<>();
    queue.enqueue(1);
    Assert.assertFalse(queue.isEmpty());
  }

  @Test
  public void isEmptyWhenLastItemDeque() {
    RandomizedQueue<Integer> queue = new RandomizedQueue<>();
    queue.enqueue(1);

    queue.dequeue();
    Assert.assertTrue(queue.isEmpty());
  }

  @Test
  public void sizeWhenNoItemEnque() {
    RandomizedQueue<Integer> queue = new RandomizedQueue<>();
    Assert.assertEquals(0, queue.size());
  }

  @Test
  public void sizeWhenOneItemEnque() {
    RandomizedQueue<Integer> queue = new RandomizedQueue<>();
    queue.enqueue(1);
    Assert.assertEquals(1, queue.size());
  }

  @Test
  public void sizeWhenLastItemDeque() {
    RandomizedQueue<Integer> queue = new RandomizedQueue<>();
    queue.enqueue(1);
    queue.dequeue();
    Assert.assertEquals(0, queue.size());
  }

  @Test
  public void sample() {
    RandomizedQueue<Integer> queue = new RandomizedQueue<>();
    int[] unexpectedList = new int[] {1, 2, 3, 4, 5};
    for (int j : unexpectedList) {
      queue.enqueue(j);
    }
    int []sample = new int[unexpectedList.length];
    for(int i=0; i<sample.length; i++) {
      sample[i] = queue.sample();
    }
    int mismatch = 0;
    for (int i = 0; i < unexpectedList.length; i++) {
      if (sample[i] != unexpectedList[i]) {
        mismatch++;
      }
    }
    Assert.assertTrue(mismatch > 0);
    Assert.assertEquals(unexpectedList.length, queue.size());
  }
}
