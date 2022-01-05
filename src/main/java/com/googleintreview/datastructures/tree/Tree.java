package com.googleintreview.datastructures.tree;

import java.util.Iterator;

public class Tree<Key extends Comparable<Key>, Value>{
    public void put(Key key, Value val) {

    }

    public Value get(Key key) {
        return null;
    }

    public boolean contains(Key key) {
        return false;
    }

    public Iterable<Key> getKeysLessthan(Key key) {
        throw new RuntimeException("Method not implemented");
    }

    public Iterable<Key> range(Key key1, Key key2) {
        throw new RuntimeException("Method not implemented");
    }

    public Iterable<Key> getKeysGreaterThan(Key key) {
        throw new RuntimeException("Method not implemented");
    }

    public Value remove(Key key) {
        return null;
    }

    public int size() {
        return 0;
    }

    protected boolean less(Comparable key1, Comparable key2) {
        return key1.compareTo(key2) < 0;
    }

    public Iterator<Key> keySet() {
        return null;
    }

    public Iterator<EntrySet<Key,Value>> entrySet() {
        return null;
    }

    public static class EntrySet<Key, Value> {
        Key key;
        Value val;
        public EntrySet(Key key, Value value) {
            this.key = key;
            this.val = value;
        }
    }

    protected static class Node<Key,Value> {
        Key key;
        Value val;
        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }

        public Key key() {
            return key;
        }

        public Value value() {
            return val;
        }
    }
}
