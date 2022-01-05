package com.googleintreview.datastructures;

import java.util.HashMap;
import java.util.Iterator;

import utility.Logger;

public class MinHeap<Item extends Comparable<Item>> implements Iterable<Item> {
    private Array<Item> items;
    private Logger log = new Logger(getClass());
    private HashMap<Item, Integer> heapPos;

    public MinHeap() {
        items = new Array<>();
        items.add(null);
    }

    public MinHeap(int capacity) {
        items = new Array<>(capacity+1);
        items.add(null);
        heapPos = new HashMap<>();
    }

    public void insert(Item item) {
        items.add(item);
        heapPos.put(item, items.size()-1);
        // log.d("insert", "Adding item : " + item);
        swim(items.size()-1);
    }

    public void put(int i, Item item) {
        Item oldItem = items.get(i+1);
        items.put(i+1, item);
        heapPos.remove(oldItem);
        heapPos.put(item, i+1);
        if(item.compareTo(oldItem) < 0) {
            swim(i+1);
        } else if(item.compareTo(oldItem) > 0) {
            sink(i+1);
        }
    }

    public Item top() {
        if(items.size() > 1)
            return items.get(1);
        return null;
    }

    public Item deleteMin() {
        Item min = top();
        if(min != null) {
            exchange(items.size()-1, 1);
            items.delete(items.size()-1);
            heapPos.remove(min);
            sink(1);
        }
        return min;
    }

    public int size() {
        return items.size()-1;
    }
    
    public int getHeapPosition(Item item) {
        if(heapPos.containsKey(item)) {
            return heapPos.get(item)-1;    
        } else {
            return -1;
        }
        
    }
    private void sink(int k) {
        int j = 2*k;
        while(j < items.size()) {
            if((j+1) < items.size() && more(j, j+1)) {
                j++;
            }
            if(more(k,j)) {
                exchange(k, j);
                k = j;
                j = 2*k;
            } else {
                break;
            }
            
        }
        
     
    }

    private boolean more(int index1, int index2) {
        return items.get(index1).compareTo(items.get(index2)) > 0;
    }

    private void swim(int N) {
        while(N > 1 && more(N/2, N)) {
            // log.d("swim", "exchange : " + items.get(N/2) + " <->" + items.get(N));
            exchange(N, N/2);
            N = N/2;
        }
    }

    private void exchange(int index1, int index2) {
        Item first = items.get(index1);
        Item second = items.get(index2);
        items.put(index1, second);
        items.put(index2, first);
        heapPos.put(first, index2);
        heapPos.put(second, index1);
    }

    @Override
    public Iterator<Item> iterator() {
        return items.iterator();
    }
}
