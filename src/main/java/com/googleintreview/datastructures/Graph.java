package com.googleintreview.datastructures;

import java.io.InputStream;

public class Graph {
    private int v = 0;
    private int e = 0;
    private Bag<Integer> [] adj;
    public Graph(int V) {
        v = V;
        adj = (Bag<Integer>[])new Bag[V];
        for(int i=0; i<adj.length; i++) {
            adj[i] = new Bag<Integer>();
        }
    }

    public Graph(InputStream in) {

    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        e = e + 1;
    }

    public int V() {
        return v;
    }

    public int E() {
        return e;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
}
