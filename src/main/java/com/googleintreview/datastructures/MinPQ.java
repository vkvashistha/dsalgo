package com.googleintreview.datastructures;

import java.util.Iterator;

public class MinPQ<Key extends Comparable<Key>> implements Iterable<Key> {
    private MinHeap<Key> minHeap;
    public MinPQ() {
        minHeap = new MinHeap<Key>();
    }

    public MinPQ(int capacity) {
        minHeap = new MinHeap<>(capacity);
    }

    public MinPQ(Key []a) {
        minHeap = new MinHeap<Key>(a.length);
    }

    public void insert(Key key) {
        minHeap.insert(key);
    }

    public Key deleteMin() {
        return minHeap.deleteMin();
    }

    public Key min() {
        return minHeap.top();
    }

    public int size() {
        return minHeap.size();
    }

    public boolean isEmpty() {
        return minHeap.size() == 0;
    }

    @Override
    public Iterator<Key> iterator() {
        return minHeap.iterator();
    }
}
