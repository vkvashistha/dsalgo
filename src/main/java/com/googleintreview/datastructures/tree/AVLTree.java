package com.googleintreview.datastructures.tree;

import java.util.Objects;

public class AVLTree<Key extends Comparable<Key>,Value> extends BST<Key,Value>{
    @Override
    public void put(Key key, Value val) {
        super.put(key, val);
        balanceTree(root);
    }

    private void balanceTree(BSTNode<Key,Value> root) {
        int balanceFactor = height(root.left()) - height(root.right());
        if(balanceFactor == 2) {
            BSTNode<Key,Value> child = root.left();
            balanceFactor = height(child.left()) - height(child.right());
            if(balanceFactor >= 0) {
                leftLeftRotation(root);
                this.root = root.parent();
            } else {
                leftRightRotation(root);
                leftLeftRotation(root);
            }
            this.root = root.parent();
        } else if(balanceFactor == -2) {
            BSTNode<Key,Value> child = root.right();
            balanceFactor = height(child.left()) - height(child.right());
            if(balanceFactor < 0) {
                rightRightRotation(root);
            } else {
                rightLeftRotation(root);
                rightRightRotation(root);

            }
            this.root = root.parent();
        }
    }

    protected void leftLeftRotation(BSTNode<Key,Value> parentNode) {
        BSTNode<Key,Value> superParent = parentNode.parent();
        BSTNode<Key,Value> left = parentNode.left();
        parentNode.setLeft(left.right());
        if(parentNode.left() != null) {
            parentNode.left().setParent(parentNode);
        }
        left.setRight(parentNode);
        left.right().setParent(left);
        left.setParent(superParent);
        if(root == parentNode) {
            root = left;
        }
        else if(superParent.left() == parentNode) {
            superParent.setLeft(left);
        } else {
            superParent.setRight(left);
        }
    }

    protected void rightRightRotation(BSTNode<Key,Value> parentNode) {
        BSTNode<Key,Value> superParent = parentNode.parent();
        BSTNode<Key,Value> right = parentNode.right();
        parentNode.setRight(right.left());
        if(parentNode.right() != null) {
            parentNode.right().setParent(parentNode);
        }
        right.setLeft(parentNode);
        right.left().setParent(right);
        right.setParent(superParent);

    }

    protected void leftRightRotation(BSTNode<Key,Value> parentNode) {
        BSTNode<Key,Value> left = parentNode.left();
        BSTNode<Key,Value> leftRight = left.right();
        parentNode.setLeft(leftRight);
        if(parentNode.left() != null) {
            parentNode.left().setParent(parentNode);
        }
        left.setRight(Objects.requireNonNull(leftRight).left());
        if(left.right() != null) {
            left.right().setParent(left);
        }
        leftRight.setLeft(left);

        leftRight.left().setParent(leftRight);
    }

    protected void rightLeftRotation(BSTNode<Key,Value> parentNode) {
        BSTNode<Key,Value> right = parentNode.right();
        BSTNode<Key,Value> rightLeft = right.left();
        right.setLeft(rightLeft.right());
        if(right.left() != null) {
            right.left().setParent(rightLeft);
        }

        rightLeft.setRight(right);

        rightLeft.right().setParent(rightLeft);


        parentNode.setRight(rightLeft);

        parentNode.right().setParent(parentNode);

    }

    protected int height(BSTNode<Key,Value> node) {
        if(node == null) return -1;
        if(node.left() == null && node.right() == null) {
            return 0;
        }
        return 1+Math.max(height(node.left()), height(node.right()));
    }

    public static void main(String []args) {
        AVLTree<Integer,Object> tree = new AVLTree<>();
        int []testData = new int[] {2,1,3,6,4,8,5};
        for(int i=0; i<testData.length; i++) {
            System.out.println("Inserting : " + testData[i]);
            tree.put(testData[i], null);
            System.out.println(testData[i] + " inserted. BF : " + ((tree.height(tree.root.left()) - tree.height(tree.root.right()))));
        }
    }
}
