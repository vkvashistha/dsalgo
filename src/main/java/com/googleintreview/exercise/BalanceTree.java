package com.googleintreview.exercise;

import java.util.*;
public class BalanceTree {
    public static void main(String []args) {

    }

    public static int balancedForest(List<Integer> c, List<List<Integer>> edges) {
        int []nodes = new int[c.size()+1];
        int []sizes = new int[c.size()+1];
        for(int i=1; i<c.size(); i++) {
            sizes[i] = c.get(i);
        }
        for(List<Integer> edge : edges) {
            int a = edge.get(0);
            int b = edge.get(1);
            nodes[b] = a;
            updateSize(nodes, sizes, b);
        }

        int maxDiff = Integer.MIN_VALUE;
        int node1 = -1;
        int node2 = -1;
        for(List<Integer> edge : edges) {
            int a = edge.get(0);
            int b = edge.get(1);
            if(sizes[b] - sizes[a] > maxDiff) {
                maxDiff = sizes[b] - sizes[a];
                node1 = a;
                node2 = b;
            }
        }
        return 0;
    }

    private static void updateSize(int []nodes, int []sizes, int current) {
        int root = nodes[current];
        while(root != current) {
            sizes[root] = sizes[root] + sizes[current];
            root = nodes[current];
        }
    }

    private static class Node {
        int size;
        int value;
        ArrayList<Node> next = new ArrayList<>();
        private Node(int value) {
            this.value = value;
        }
    }
}
