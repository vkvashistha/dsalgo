package com.googleintreview.datastructures.tree;

public class RedBlackTree<Key extends Comparable<Key>, Value> extends AVLTree<Key,Value>{
    private static boolean RED = true;
    private static boolean BLACK = false;
    private RBNode<Key,Value> root;

    @Override
    public void put(Key key, Value val) {
        RBNode<Key,Value> newNode = null;
        if(root == null) {
            newNode = new RBNode<Key,Value>(key,val,BLACK);
            root = newNode;
            return;
        }
        RBNode<Key,Value> node = (RBNode<Key,Value>)findParent(root,key);

        if(less(node.key, key)) {
            newNode = new RBNode<Key,Value>(key, val, RED);
            node.setRight(newNode);
            newNode.setParent(node);
        } else if(node.key.compareTo(key) == 0) {
            node.val = val;
        } else {
            newNode = new RBNode<Key,Value>(key, val, RED);
            node.setLeft(newNode);
            newNode.setParent(node);
        }
        balanceTree(node);
    }

    private void balanceTree(RBNode<Key,Value> parentNode) {
        if(parentNode == null) return;
        RBNode<Key,Value> superParent = parentNode.parent();
        System.out.println("balancing tree with parentNode : " + parentNode.key());
        if(isRed(parentNode.left())&& isRed(parentNode.right())) {
            System.out.println("color flip at parentNod : " + parentNode.key());
            parentNode.left().color = BLACK;
            parentNode.right().color = BLACK;
            parentNode.color = RED;
        } else if(isRed(parentNode.right())) {
            System.out.println("right right rotation at parentNod : " + parentNode.key());
            RBNode<Key,Value> right = parentNode.right();
            rightRightRotation(parentNode);
            right.color = parentNode.color;
            parentNode.color = RED;
            if(root == parentNode) {
                root = right;
            } else if(superParent.left() == parentNode) {
                superParent.setLeft(right);
            } else {
                superParent.setRight(right);
            }
        } else if(parentNode.left() != null && isRed(parentNode.left()) && isRed(parentNode.left().left())) {
            System.out.println("left left rotation at parentNod : " + parentNode.key());
            RBNode<Key,Value> left = parentNode.left();
            leftLeftRotation(parentNode);
            left.color = parentNode.color;
            parentNode.color = RED;
            superParent = left;
        } else {
            System.out.println("No balancing required at parentNod : " + parentNode.key());
        }
        balanceTree(superParent);
    }

    private boolean isRed(RBNode<Key, Value> node) {
        if(node == null) {
            return BLACK;
        }
        return node.color == RED;
    }

    protected static class RBNode<Key extends Comparable<Key>,Value> extends BST.BSTNode<Key,Value> {
        boolean color;
        private RBNode<Key,Value> left;
        private RBNode<Key,Value> right;
        private RBNode<Key, Value> parent;
        public RBNode(Key key, Value val, boolean color) {
            super(key,val);
            this.color = color;
        }

        @Override
        public RBNode<Key, Value> left() {
            return left;
        }

        @Override
        public RBNode<Key, Value> right() {
            return right;
        }

        @Override
        public RBNode<Key, Value> parent() {
            return parent;
        }

        @Override
        public void setLeft(BSTNode<Key, Value> left) {
            this.left = (RBNode<Key,Value>)left;
        }

        @Override
        public void setRight(BSTNode<Key, Value> right) {
            this.right = (RBNode<Key, Value>) right;
        }

        @Override
        public void setParent(BSTNode<Key, Value> parent) {
            this.parent = (RBNode<Key, Value>) parent;
        }
    }

    public static void main(String []args) {
        RedBlackTree<Integer,Object> tree = new RedBlackTree<>();
        int []testData = new int[] {2,1,3,6,4,8,5};
        for(int i=0; i<testData.length; i++) {
            System.out.println("Inserting : " + testData[i]);
            tree.put(testData[i], null);
            System.out.println(testData[i] + " inserted. BF : " + ((tree.height(tree.root.left()) - tree.height(tree.root.right()))));
        }
    }
}
