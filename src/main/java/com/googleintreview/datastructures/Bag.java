package com.googleintreview.datastructures;

import java.util.Iterator;

public class Bag<Item> implements Iterable<Item> {
    Stack<Item> items = new Stack<>();

    public void add(Item item) {
        items.push(item);
    }

    public int size() {
        return items.size();
    }

    @Override
    public Iterator<Item> iterator() {
        return items.iterator();
    }

    @Override
    public String toString() {
        return "Bag{" +
                "items=" + items.toString() +
                '}';
    }
}
