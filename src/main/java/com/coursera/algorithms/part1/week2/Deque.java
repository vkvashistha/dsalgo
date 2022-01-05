package com.coursera.algorithms.part1.week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

  private Node<Item> last;
  private Node<Item> first;
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
    Node<Item> newNode = new Node<>(item);
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
    Node<Item> newNode = new Node<>(item);
    if (last != null) {
      last.next = newNode;
      newNode.prev = last;
      last = newNode;
    } else {
      first = newNode;
      last = newNode;
    }
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
    Node<Item> firstNode = first;
    first = first.next;
    size--;
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
    Node<Item> lastNode = last;
    last = last.prev;
    size--;
    return lastNode.val;
  }

  // return first item (but not remove it from queue
  public Item getFirst() {
    if (isEmpty()) {
      throw new NoSuchElementException("No element exist");
    }
    return first.val;
  }

  public Item getLast() {
    if (isEmpty()) {
      throw new NoSuchElementException("No element exist");
    }
    return last.val;
  }

  public Item get(int index) {
    if (index >= size) {
      throw new NoSuchElementException("No element exist at index : " + index);
    }
    Node<Item> node = first;
    for (int i = 0; i < index; i++) {
      node = node.next;
    }
    return node.val;
  }

  Node<Item> getfirstNode() {
    return first;
  }

  Node<Item> getLastNode() {
    return last;
  }

  // return an iterator over items in order from front to back
  public Iterator<Item> iterator() {
    return new LinkedListIterator(first, last);
  }

  private class LinkedListIterator implements Iterator<Item> {
    Node start;
    Node end;

    LinkedListIterator(Node<Item> first, Node<Item> last) {
      this.start = first;
      this.end = last;
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
      Node<Item> node = start;
      start = start.next;
      return node.val;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException("This method is not supported.");
    }
  }

  public static class Node<Item> {
    Item val;
    Node<Item> next;
    Node<Item> prev;

    public Node(Item val) {
      this.val = val;
    }
  }

  // unit testing (required)
  public static void main(String[] args) {
    Deque<Integer> deque = new Deque<>();
    for(int i=0; i<10; i++) {
      deque.addLast(i);
    }

  }
}
