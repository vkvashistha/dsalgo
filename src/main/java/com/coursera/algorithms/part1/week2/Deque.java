package com.coursera.algorithms.part1.week2;

import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

  private Node last;
  private Node first;
  private int size;
  // construct an empty deque
  public Deque() {}

  // is the deque empty?
  public boolean isEmpty() {
    return size == 0;
  }

  // return the number of items on the deque
  public int size() {
    return size;
  }

  // add the item to the front
  public void addFirst(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("Argument cannot be null");
    }
    Node newNode = new Node(item);
    if (first != null) {
      newNode.next = first;
      first.prev = newNode;
      first = newNode;
    } else {
      first = newNode;
      last = newNode;
    }
    size++;
  }

  // add the item to the back
  public void addLast(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("Argument cannot be null");
    }
    Node newNode = new Node(item);
    if (last != null) {
      last.next = newNode;
      newNode.prev = last;
    } else {
      first = newNode;
    }
    last = newNode;
    size++;
  }

  // remove and return the item from the front
  public Item removeFirst() {
    if (isEmpty()) {
      throw new NoSuchElementException("No element exist");
    }
    if (first.next != null) {
      first.next.prev = null;
    }
    Node firstNode = first;
    first = first.next;
    size--;
    if(size == 0) {
      first = null;
      last = null;
    }
    return firstNode.val;
  }

  // remove and return the item from the back
  public Item removeLast() {
    if (isEmpty()) {
      throw new NoSuchElementException("No element exist");
    }
    if (last.prev != null) {
      last.prev.next = null;
    }
    Node lastNode = last;
    last = last.prev;
    size--;
    if(size == 0) {
      first = null;
      last = null;
    }
    return lastNode.val;
  }

  // return an iterator over items in order from front to back
  public Iterator<Item> iterator() {
    return new LinkedListIterator();
  }

  private class LinkedListIterator implements Iterator<Item> {
    Node start;

    LinkedListIterator() {
      this.start = first;
    }

    @Override
    public boolean hasNext() {
      return start != null;
    }

    @Override
    public Item next() {
      if (!hasNext()) {
        throw new NoSuchElementException("No more elements");
      }
      Node node = start;
      start = start.next;
      return node.val;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException("This method is not supported.");
    }
  }

  private class Node {
    Item val;
    Node next;
    Node prev;

    public Node(Item val) {
      this.val = val;
    }
  }

  // unit testing (required)
  public static void main(String[] args) {
    Deque<Integer> deque = new Deque<>();
    assert deque.isEmpty();
    deque.addFirst(1);
    deque.addFirst(2);
    assert 2 == deque.size();
    deque.removeLast();
    assert 1 == deque.size();
    deque.removeLast();
    deque.addLast(1);
    deque.addLast(2);
    assert 2 == deque.size();
    deque.removeFirst();
    assert 1 == deque.size();
    deque.removeLast();
    deque.addFirst(1);
    deque.addLast(2);
    assert 2 == deque.size();
    deque.removeFirst();
    assert 1 == deque.size();
    deque.addLast(1);
    deque.addFirst(1);
    assert 3 == deque.size();
    deque.removeLast();
    assert 2 == deque.size();
    assert !deque.isEmpty();
    StdOut.println("Basic tests passed");
  }
}
