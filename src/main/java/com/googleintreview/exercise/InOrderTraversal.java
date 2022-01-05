package com.googleintreview.exercise;

import java.math.BigInteger;
import java.util.LinkedList;

public class InOrderTraversal {
    static LinkedList<Integer> lastValue = new LinkedList<>();
    public static void main(String []args) {
        Node root = new Node(13);
        root.left = new Node(9);
        root.right = new Node(14);
        root.left.left = new Node(8);
        root.left.right = new Node(12);

        System.out.println(checkBST(root));
    }
    public static boolean checkBST(Node root) {
        return inOrderTraversal(root);
    }

    private static boolean inOrderTraversal(Node node) {
        if(node.left != null) {
            if(!inOrderTraversal(node.left))
            {
                return false;
            }
        }
        if(lastValue.isEmpty()) {
            lastValue.addLast(node.data);
        } else {
            int val = lastValue.removeLast();
            if(val > node.data) return false;
            else lastValue.addLast(node.data);
        }

        if(node.right != null) {
            return inOrderTraversal(node.right);
        }
        return true;

    }

    private static class Node {
        int data;
        Node left;
        Node right;
        private Node(int data) {
            this.data = data;
        }
    }
}
