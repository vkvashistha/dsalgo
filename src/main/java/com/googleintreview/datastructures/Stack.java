package com.googleintreview.datastructures;

import java.util.Iterator;

public class Stack<Item> implements Iterable<Item> {
    private Node top;
    private int size;

    public void push(Item item) {
        Node newNode = new Node();
        newNode.item = item;
        if(top == null) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
        size++;
    }

    public Item pop() {
        if(top == null) return null;
        Item item = top.item;
        top = top.next;
        size--;
        return item;
    }

    public int size() {
        return size;
    }


    @Override
    public Iterator<Item> iterator() {
        return new LinkedListIterator();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Item item : this) {
            builder.append(item.toString()).append(",");
        }
        return builder.toString();
    }

    private class LinkedListIterator implements Iterator<Item> {
        Node current;

        LinkedListIterator() {
            if(top != null) {
                current = new Node();
                current.item = top.item;
                current.next = top.next;
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
