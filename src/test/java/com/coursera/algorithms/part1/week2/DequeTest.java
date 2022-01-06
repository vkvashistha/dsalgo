package com.coursera.algorithms.part1.week2;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

public class DequeTest {
  // Test Case 1 : Test addLast : when empty list
  @Test
  public void addLastWhenEmptyList() {
    int []expectedList = new int[] {1};
    Deque<Integer> deque = new Deque<>();
    for(int i=0; i<expectedList.length; i++) {
      deque.addLast(expectedList[i]);
    }
    int i=0;
    for(int item : deque) {
      Assert.assertEquals(expectedList[i++], item);
    }
    Assert.assertEquals(expectedList.length,i);
  }
  // Test Case 2 : Test addLast : when one element
  @Test
  public void addLastOneElement() {
    int []expectedList = new int[] {1,2};
    Deque<Integer> deque = new Deque<>();
    for(int i=0; i<expectedList.length; i++) {
      deque.addLast(expectedList[i]);
    }
    int i=0;
    for(int item : deque) {
      Assert.assertEquals(expectedList[i++], item);
    }
    Assert.assertEquals(expectedList.length,i);
  }
  // Test Case 3 : Test addLast : when more than one element
  @Test
  public void addLastWhenTwoElement() {
    int []expectedList = new int[] {1,2,3};
    Deque<Integer> deque = new Deque<>();
    for(int i=0; i<expectedList.length; i++) {
      deque.addLast(expectedList[i]);
    }
    int i=0;
    for(int item : deque) {
      Assert.assertEquals(expectedList[i++], item);
    }
    Assert.assertEquals(expectedList.length,i);
  }
  // Test Case 4 : Test addFirst : when empty list
  @Test
  public void addFirstWhenEmptyList() {
    int []expectedList = new int[] {1};
    Deque<Integer> deque = new Deque<>();
    for(int i=0; i<expectedList.length; i++) {
      deque.addFirst(expectedList[i]);
    }
    int i=0;
    for(int item : deque) {
      Assert.assertEquals(expectedList[i++], item);
    }
    Assert.assertEquals(expectedList.length,i);
  }

  // Test Case 5 : Test addFirst : when one element
  @Test
  public void addFirstWhenOneElement() {
    int []expectedList = new int[] {1};
    Deque<Integer> deque = new Deque<>();
    for(int i=0; i<expectedList.length; i++) {
      deque.addFirst(expectedList[i]);
    }
    int i=0;
    for(int item : deque) {
      Assert.assertEquals(expectedList[i++], item);
    }
    Assert.assertEquals(expectedList.length,i);
  }
  // Test Case 6 : Test addFirst : when more than one element
  @Test
  public void addFirstWhenTwoElement() {
    int []expectedList = new int[] {1};
    Deque<Integer> deque = new Deque<>();
    for(int i=0; i<expectedList.length; i++) {
      deque.addFirst(expectedList[i]);
    }
    int i=0;
    for(int item : deque) {
      Assert.assertEquals(expectedList[i++], item);
    }
    Assert.assertEquals(expectedList.length,i);
  }

  // Test Case 7 : Test removeLast : when empty list
  @Test(expected = NoSuchElementException.class)
  public void removeLastWhenEmptyList() {
    Deque<Integer> deque = new Deque<>();
    deque.removeLast();
  }
  // Test Case 8 : Test removeLast : when one element
  @Test
  public void removeLastWhenOneElement() {
    Deque<Integer> deque = new Deque<>();
    deque.addLast(1);
    Assert.assertEquals(Integer.valueOf(1),deque.removeLast());
  }
  // Test Case 9 : Test removeLast : when more than one element
  @Test
  public void removeLastWhenTwoElement() {
    Deque<Integer> deque = new Deque<>();
    deque.addLast(1);
    deque.addLast(2);
    Assert.assertEquals(Integer.valueOf(2),deque.removeLast());
  }
  // Test Case 10 : Test removeFirst : when empty list
  @Test(expected = NoSuchElementException.class)
  public void removeFirstWhenEmptyList() {
    Deque<Integer> deque = new Deque<>();
    deque.removeFirst();
  }
  // Test Case 11 : Test removeFirst : when one element
  @Test
  public void removeFirstWhenOneElement() {
    Deque<Integer> deque = new Deque<>();
    deque.addLast(1);
    Assert.assertEquals(Integer.valueOf(1),deque.removeFirst());
  }
  // Test Case 12 : Test removeFirst : when more than one element
  @Test
  public void removeFirstWhenTwoElement() {
    Deque<Integer> deque = new Deque<>();
    deque.addLast(1);
    deque.addFirst(2);
    Assert.assertEquals(Integer.valueOf(2),deque.removeFirst());
  }

  // Test Case 13 : isEmpty() : when list is empty
  @Test
  public void isEmptyWhenListIsEmpty() {
    Deque<Integer> deque = new Deque<>();
    Assert.assertTrue(deque.isEmpty());
  }

  // Test Case 13 : isEmpty() : when only remaining element removed from last
  // Test Case 14 : isEmpty() : when only remaining element removed from first
  // Test Case 15 : isEmpty() : when element just added to first
  // Test Case 16 : isEmpty() : when element just added to last
  @Test
  public void isEmptyWhenRemainingElementRemovedFromLast() {
    Deque<Integer> deque = new Deque<>();
    deque.addFirst(1);
    Assert.assertFalse(deque.isEmpty());
    deque.removeLast();
    Assert.assertTrue(deque.isEmpty());
    deque.addLast(1);
    Assert.assertFalse(deque.isEmpty());
    deque.removeFirst();
    Assert.assertTrue(deque.isEmpty());
    deque.addFirst(1);
    Assert.assertFalse(deque.isEmpty());
    deque.removeFirst();
    Assert.assertTrue(deque.isEmpty());
    deque.addLast(1);
    Assert.assertFalse(deque.isEmpty());
    deque.removeLast();
    Assert.assertTrue(deque.isEmpty());
  }

  // Test Case 17 : isEmpty() : when element added to last in non empty list
  // Test Case 18 : isEmpty() : when element added to first in non empty list
  @Test
  public void isEmptyWhenElementAddedToNonEmptyList() {
    Deque<Integer> deque = new Deque<>();
    deque.addFirst(1);
    deque.addFirst(2);
    Assert.assertFalse(deque.isEmpty());
    deque.removeLast();
    Assert.assertFalse(deque.isEmpty());
    deque.removeLast();
    Assert.assertTrue(deque.isEmpty());
    deque.addLast(1);
    deque.addLast(2);
    Assert.assertFalse(deque.isEmpty());
    deque.removeFirst();
    deque.removeFirst();
    Assert.assertTrue(deque.isEmpty());
    deque.addFirst(1);
    deque.addLast(2);
    Assert.assertFalse(deque.isEmpty());
    deque.removeFirst();
    deque.removeLast();
    Assert.assertTrue(deque.isEmpty());
    deque.addLast(1);
    deque.addFirst(1);
    Assert.assertFalse(deque.isEmpty());
    deque.removeLast();
    deque.removeFirst();
    Assert.assertTrue(deque.isEmpty());
  }

  // Test Case 19 : size() : when list is empty
  @Test
  public void size() {
    Deque<Integer> deque = new Deque<>();
    deque.addFirst(1);
    Assert.assertEquals(1,deque.size());
    deque.removeLast();
    Assert.assertEquals(0,deque.size());
    deque.addLast(1);
    Assert.assertEquals(1,deque.size());
    deque.removeFirst();
    Assert.assertEquals(0,deque.size());
    deque.addFirst(1);
    Assert.assertEquals(1,deque.size());
    deque.removeFirst();
    Assert.assertEquals(0,deque.size());
    deque.addLast(1);
    Assert.assertEquals(1,deque.size());
    deque.removeLast();
    Assert.assertEquals(0,deque.size());
  }
  // Test Case 20 : size() : when only remaining element removed from last
  // Test Case 21 : size() : when only remaining element removed from first
  // Test Case 22 : size() : when element just added to first
  // Test Case 23 : size() : when element just added to last
  // Test Case 24 : size() : when element added to last in non empty list
  // Test Case 25 : size() : when element added to first in non empty list
  @Test
  public void sizeWhenElementAddedToNonEmptyList() {
    Deque<Integer> deque = new Deque<>();
    deque.addFirst(1);
    deque.addFirst(2);
    Assert.assertEquals(2,deque.size());
    deque.removeLast();
    Assert.assertEquals(1,deque.size());
    deque.removeLast();
    deque.addLast(1);
    deque.addLast(2);
    Assert.assertEquals(2,deque.size());
    deque.removeFirst();
    Assert.assertEquals(1,deque.size());
    deque.removeLast();
    deque.addFirst(1);
    deque.addLast(2);
    Assert.assertEquals(2,deque.size());
    deque.removeFirst();
    Assert.assertEquals(1,deque.size());
    deque.addLast(1);
    deque.addFirst(1);
    Assert.assertEquals(3,deque.size());
    deque.removeLast();
    Assert.assertEquals(2,deque.size());
  }

  @Test
  public void randomOperation() {
    Deque<Integer> deque = new Deque<>();
    Assert.assertTrue(deque.isEmpty());
    deque.addFirst(2);
    Assert.assertEquals(Integer.valueOf(2),deque.removeLast());
    deque.addFirst(4);
    Assert.assertEquals(Integer.valueOf(4), deque.removeLast());
  }

}
