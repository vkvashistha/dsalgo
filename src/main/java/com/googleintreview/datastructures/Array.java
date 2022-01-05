package com.googleintreview.datastructures;

import java.util.Iterator;

public class Array<Item> implements Iterable<Item>{

    private Object [] items;
    private int last = -1;
    private int minSize;


    public Array() {
        this(20);
    }

    public Array(int capacity) {
        items = new Object[capacity];
        minSize = capacity;
    }

    public Item get(int index) {
        if(index > last) throw new ArrayIndexOutOfBoundsException("index : " + index + " out of bound than size " + size());
        return (Item)items[index];
    }

    public void put(int index, Item item) {
        if(index > last) throw new ArrayIndexOutOfBoundsException("index : " + index + " out of bound than size " + size());
        items[index] = item;
    }

    public void add(Item item) {
        if(last+1 >= items.length) {
            increaseSize();
        }
        last++;
        items[last] = item;
    }

    public Item delete(int index) {
        if(index > last || index < 0) {
            return null;
        }
        Item item = get(index);
        for(int i=index; i<last; i++) {
            items[i] = items[i+1];
        }
        last--;
        if(items.length > 2*last) {
            decreaseSize();
        }
        return item;
    }

    public int size() {
        return last+1;
    }

    public Item getLast() {
        if(items.length == 0) {
            return null;
        } 
        return (Item)items[last];
    }

    private void increaseSize() {
        int newSize = items.length*2;
        Object [] newItems = new Object[newSize];
        for(int i=0; i<items.length && i<newItems.length; i++) {
            newItems[i] = items[i];
        }
        items = newItems;
    }

    public void decreaseSize() {
        int newSize = items.length - items.length/4;
        if(newSize < last+1) return;
        Object [] newItems = new Object[newSize];
        for(int i=0; i<items.length && i<newItems.length; i++) {
            newItems[i] = items[i];
        }
        items = newItems;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }    

    private class ArrayIterator implements Iterator<Item> {
        private int currentIndex = -1;

        @Override
        public boolean hasNext() {
            return (currentIndex < size()-1);
        }

        @Override
        public Item next() {
            if(!hasNext()) return null;
            currentIndex++;
            return (Item)items[currentIndex];
        }

        @Override
        public void remove() {
//            Iterator.super.remove();
        }

    }
}
