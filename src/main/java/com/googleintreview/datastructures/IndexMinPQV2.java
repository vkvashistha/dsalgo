package com.googleintreview.datastructures;

import java.util.Iterator;

public class IndexMinPQV2<Key extends Comparable<Key>> implements Iterable<Key> {
    private Object []keys;      // Store Keys associated with an index
    private int []pq;           // Store index of key in Heap
    private int []qp;           // Store heap position of key
    private int heapSize = 0;   // Current Heap Size

    public IndexMinPQV2(int N) {
        keys = new Object[N+1];
        pq = new int[N+1];
        qp = new int[N+1];
    }

    public void insertKey(int i, Key key) {
        keys[i] = key;
        int heapLastIndex = heapSize+1;
        heapSize++;
        pq[heapLastIndex] = i;
        qp[i] = heapLastIndex;
        swim(heapLastIndex);
    }

    public int deleteMin() {
        Key k = (Key)keys[pq[1]];
        int keyIndex = pq[1];
        heapSize--;
        sink(1);
        return keyIndex;
    }

    public void decreaseKey(int i, Key key) {
        Key oldKey = (Key)keys[i];
        if(oldKey.compareTo(key) > 0) {
            keys[i] = key;
            int heapPos = qp[i];
            swim(heapPos);
        }
    }

    private void swim(int N) {
        while(N/2 > 0 && less(N, N/2)) {
            exchange(N, N/2);
            N = N/2;
        }
    }

    private void sink(int N) {
        int j = 2*N;
        while(j < heapSize) {
            if(less(j+1, j)) {
                j++;
            }
            if(less(j, N)) {
                exchange(j, N);
            } else {
                break;
            }
        }
    }

    private boolean less(int v, int w) {
        Key kV = (Key)keys[pq[v]];
        Key kW = (Key)keys[pq[w]];
        return kV.compareTo(kW) < 0;
    }
    private void exchange(int v, int w) {
        int temp1 = pq[v];
        int temp2 = pq[w];
        pq[v] = temp2;
        pq[w] = temp1;
        qp[temp1] = pq[w];
        qp[temp2] = pq[v];

    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public boolean contains(int v) {
        return keys[v] != null;
    }

    @Override
    public Iterator<Key> iterator() {
        Queue<Key> edges = new Queue<Key>();
        for(int i=1; i<heapSize; i++) {
            Key k = (Key)keys[pq[i]];
            edges.enqueue(k);
        }
        return edges.iterator();
    }
}
