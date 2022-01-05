package com.googleintreview.datastructures;

import java.util.Iterator;

public class Queue<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    public void enqueue(Item item) {
        if(first == null) {
            first = new Node();
            first.item = item;
            last = first;
        } else {
            Node newNode = new Node();
            newNode.item = item;
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    public Item dequeue() {
        if(first == null) return null;
        Item item = first.item;
        first = first.next;
        size--;
        return item;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    
    @Override
    public Iterator<Item> iterator() {
        return new LinkedListIterator(first);
    }
    
    private class LinkedListIterator implements Iterator<Item> {
        Node current;
        LinkedListIterator(Node last) {
            if(last != null) {
                Node newNode = new Node();
                newNode.item = last.item;
                newNode.next = last.next;
                current = newNode;
            }
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if(!hasNext()) return null;
            Item nextItem = current.item;
            current = current.next;
            return nextItem;
        }

        @Override
        public void remove() {
//            Iterator.super.remove();
        }

    }
    private class Node {
        Item item;
        Node next;
    }
}
