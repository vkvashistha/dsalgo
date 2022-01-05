package com.googleintreview.datastructures;

import java.util.HashMap;
import java.util.Iterator;

public class IndexMinPQ<Key extends Comparable<Key>> implements Iterable<Key>{
    private Object []items;
    private HashMap<Object, Integer> qp = new HashMap<>();
    private MinHeap<Key> pq;

    public IndexMinPQ(int N) {
        pq = new MinHeap<>(N);
        items = new Object[N];
    }

    public void insertKey(int i, Key key) {
        items[i] = key;
        qp.put(key, i);
        pq.insert(key);
        printHeap();
    }

    public void decreaseKey(int i, Key key) {
        Key lastKey = (Key)items[i];
        System.out.println("lastKey at " + i + ":" + lastKey + " at heap pos:" + pq.getHeapPosition(lastKey));
        if(lastKey.compareTo(key) > 0) {
            items[i] = key;
            qp.remove(lastKey);
            qp.put(key,i);
            System.out.println("lastKey at " + i + ":" + lastKey + " at heap pos:" + pq.getHeapPosition(lastKey));
            pq.put(pq.getHeapPosition(lastKey), key);
        }
        printHeap();
    }

    public Key deleteMin() {
        //TODO: we need to determine to which node this edge is associated.
        // So that we can set null that node
        Key k = pq.deleteMin();
        int i = qp.get(k); // determine position of edge
        items[i] = null;
        qp.remove(k);
        printHeap();
        return k;
    }

    public Key min() {
        return pq.top();
    }

    public boolean contains(int i) {
        return items[i] != null;
    }

    public int size() {
        return pq.size();
    }

    public boolean isEmpty() {
        return pq.size() == 0;
    }

    private void printHeap() {
        System.out.println("Heap:");
        for(Key e : pq) {
            if(e != null)
            System.out.println(pq.getHeapPosition(e)+":"+e.toString());
        }

        System.out.println("Items :");
        for(int i=0; i<items.length; i++) {
            System.out.println(i+":"+items[i]);
        }
    }



    @Override
    public Iterator<Key> iterator() {
        return pq.iterator();
    }
}
