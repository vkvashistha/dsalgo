package com.googleintreview.datastructures.tree;

import java.util.ArrayList;

public class BST<Key extends Comparable<Key>,Value> extends Tree<Key,Value>{
    protected BSTNode<Key,Value> root;
    @Override
    public void put(Key key, Value val) {
        if(root == null) {
            root = new BSTNode<>(key,val);
        } else {
            BSTNode<Key,Value> parent = findParent(root, key);
            if(less(parent.key, key)) {
                parent.setRight(new BSTNode<>(key,val));
                parent.right().setParent(parent);
            } else {
                parent.setLeft(new BSTNode<>(key, val));
                parent.left().setParent(parent);
            }
        }
    }

    @Override
    public Value get(Key key) {
        BSTNode<Key,Value> childNode = get(root, key);
        if(childNode != null) {
            return childNode.value();
        } else {
            return null;
        }
    }

    @Override
    public boolean contains(Key key) {
        return get(root,key) != null;
    }

    @Override
    public Value remove(Key key) {
        BSTNode<Key,Value> child = get(root,key);
        if(child != null) {
            BSTNode<Key,Value> parentNode = child.parent();
            if(parentNode.left == child) {
                parentNode.setLeft(null);
            } else {
                parentNode.setRight(null);
            }
            return child.value();
        } else {
            return null;
        }

    }

    @Override
    public Iterable<Key> getKeysLessthan(Key key) {
        BSTNode<Key,Value> node = root;
        ArrayList<Key> keys = new ArrayList<>();
        range(keys,node, key, null);
        return keys;
    }

    @Override
    public Iterable<Key> getKeysGreaterThan(Key key) {
        BSTNode<Key,Value> node = root;
        ArrayList<Key> keys = new ArrayList<>();
        range(keys,node, key, null);
        return keys;
    }

    @Override
    public Iterable<Key> range(Key key1, Key key2) {
        ArrayList<Key> keys = new ArrayList<>();
        range(key1, key2);
        return keys;
    }

    public Iterable<Key> inOrderKeys() {
        ArrayList<Key> inOrder = new ArrayList<>();
        inOrder(inOrder,root);
        return inOrder;
    }

    public Iterable<Key> preOrderKeys() {
        ArrayList<Key> preOrder = new ArrayList<>();
        preOrder(preOrder,root);
        return preOrder;
    }

    public Iterable<Key> postOrderKeys() {
        ArrayList<Key> postOrder = new ArrayList<>();
        inOrder(postOrder,root);
        return postOrder;
    }

    private void range(ArrayList<Key> queue, BSTNode<Key,Value> node, Key lo, Key hi) {
        if(node == null) return;
        if((lo != null && lo.compareTo(node.key) <=0) || (hi != null && hi.compareTo(node.key) >=0)) {
            return;
        }
        range(queue, node.left(), lo, hi);
        queue.add(node.key());
        range(queue, node.right(), lo, hi);
    }

    private void inOrder(ArrayList<Key> queue, BSTNode<Key,Value> node) {
        if(node == null) return;
        inOrder(queue, node.left());
        queue.add(node.key());
        inOrder(queue, node.right());
    }

    private void preOrder(ArrayList<Key> queue, BSTNode<Key,Value> node) {
        if(node == null) return;
        queue.add(node.key());
        inOrder(queue, node.left());
        inOrder(queue, node.right());
    }

    private void postOrder(ArrayList<Key> queue, BSTNode<Key,Value> node) {
        if(node == null) return;
        postOrder(queue, node.left());
        postOrder(queue, node.right());
        queue.add(node.key());
    }


    protected BSTNode<Key,Value> get(BSTNode<Key,Value> root, Key key) {
        BSTNode<Key,Value> node = root;
        while(node != null&&!(node.key().compareTo(key)==0)) {
            if(less(node.key(),key)) {
                node = node.right();
            } else {
                node = node.left();
            }
        }
        return node;
    }

    protected BSTNode<Key,Value> findParent(BSTNode<Key,Value> root, Key key) {
        BSTNode<Key,Value> node = root;
        BSTNode<Key,Value> parentNode = null;
        while(node != null) {
            parentNode = node;
            if(less(node.key(),key)) {
                node = node.right();
            } else {
                node = node.left();
            }
        }
        return parentNode;
    }

    protected static class BSTNode<Key extends Comparable<Key>,Value> extends Node<Key,Value> {
        private BSTNode<Key,Value> left;
        private BSTNode<Key,Value> right;
        private BSTNode<Key,Value> parent;
        public BSTNode(Key key, Value value) {
            super(key,value);
        }

        public void setLeft(BSTNode<Key,Value> left) {
            this.left = left;
        }

        public BSTNode<Key,Value> left() {
            return left;
        }

        public void setRight(BSTNode<Key,Value> right) {
            this.right = right;
        }

        public BSTNode<Key,Value> right() {
            return right;
        }

        public void setParent(BSTNode<Key,Value> parent) {
            this.parent = parent;
        }

        public BSTNode<Key,Value> parent() {
            return parent;
        }
    }
}
