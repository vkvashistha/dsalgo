package com.coursera.algorithms.part2.week1;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.ST;

import java.util.ArrayList;

public class SAP {
    private Digraph G;
    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        this.G = G;
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        return lengthAncestor(v,w)[0];
    }

    private void dfs(Digraph G, int v, boolean []visited, ST<Integer, Integer> distance, int d) {
        for(int w : G.adj(v)) {
            if(!visited[w]) {
                visited[w] = true;
                distance.put(w,d+1);
                dfs(G,w,visited, distance, d+1);
            }
        }
        
    }

    private void bfs(Digraph G, int s, boolean[] visited, ST<Integer,Integer> distance) {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(s);
        distance.put(s,0);
        while(!queue.isEmpty()) {
            int v = queue.dequeue();
            for(int w : G.adj(v)) {
                if(!visited[w]) {
                    visited[w] = true;
                    queue.enqueue(w);
                    distance.put(w,distance.get(v)+1);
                }
            }
        }
    }

    private int [] lengthAncestor(int v, int w) {
        int synsetId1 = v;
        int synsetId2 = w;
        if(synsetId1 >= G.V() || synsetId2 >= G.V()) throw new IllegalArgumentException(v+" or " + w + " out of Graph Range : " + G.V());
        boolean []visited1 = new boolean[G.V()];
        ST<Integer, Integer> syn1Dis = new ST<>();
//        dfs(G,synsetId1,visited1, syn1Dis, 0);
        bfs(G,synsetId1,visited1, syn1Dis);

        boolean []visited2 = new boolean[G.V()];
        ST<Integer, Integer> syn2Dis = new ST<>();
//        dfs(G,synsetId2,visited2, syn2Dis, 0);
        bfs(G,synsetId2,visited2, syn2Dis);
        int ancestor = -1;
        int minDistance = Integer.MAX_VALUE;
        for(int i=0; i<visited1.length; i++) {
            if(visited1[i] && visited2[i]) {
                int dis = syn1Dis.get(i) + syn2Dis.get(i);
                if(minDistance > dis) {
                    minDistance = dis;
                    ancestor = i;
                }

            }
        }
        int []lengthAncestor = new int[2];
        lengthAncestor[0] = minDistance;
        lengthAncestor[1] = ancestor;
        return lengthAncestor;
    }
    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        return lengthAncestor(v,w)[1];
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        int shortestAncestralLength = -1;
        if(v == null || w == null) throw new IllegalArgumentException("One or more arugment is null");
        for(Integer _syn1 : v) {
            if(_syn1 == null) throw new IllegalArgumentException("Iterable elements can't be null");
            for(Integer _syn2 : w) {
                if(_syn2 == null) throw new IllegalArgumentException("Iterable elements can't be null");
                int len = length(_syn1,_syn2);
                if(len < shortestAncestralLength || shortestAncestralLength == -1) {
                    shortestAncestralLength = len;
                }
            }
        }
        return shortestAncestralLength;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        int minDistance = Integer.MAX_VALUE;
        int ancestor = -1;
        if(v == null || w == null) throw new IllegalArgumentException("One or more arugment is null");
        for(Integer _syn1 : v) {
            if(_syn1 == null) throw new IllegalArgumentException("Iterable elements can't be null");
            for(Integer _syn2 : w) {
                if(_syn2 == null) throw new IllegalArgumentException("Iterable elements can't be null");
                int[] lengthAncestor = lengthAncestor(_syn1, _syn2);
                if(minDistance > lengthAncestor[0]) {
                    minDistance = lengthAncestor[0];
                    ancestor = lengthAncestor[1];
                }
            }
        }
        return ancestor;
    }

    // do unit testing of this class
    public static void main(String[] args) {
        Digraph G = new Digraph(new In("digraph2.txt"));
        SAP sap = new SAP(G);
        System.out.println("length : " + sap.length(3,11) + ", ancestor:" + sap.ancestor(3,11));
        System.out.println("length : " + sap.length(7,2) + ", ancestor:" + sap.ancestor(7,2));
        System.out.println("length : " + sap.length(1,6) + ", ancestor:" + sap.ancestor(1,6));
    }
}
