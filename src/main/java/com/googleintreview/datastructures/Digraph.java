package com.googleintreview.datastructures;

public class Digraph extends Graph{
    private int V;
    private int E;
    private Bag<Integer> []adj;

    public Digraph(int V) {
        super(V);
        this.V = V;
        adj = (Bag<Integer>[])new Bag[V];
        for(int i=0; i<adj.length; i++) {
            adj[i] = new Bag<Integer>();
        }
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        E = E + 1;
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public Digraph reverse() {
        Digraph d = new Digraph(V);
        for(int i=0; i<adj.length; i++) {
            Bag<Integer> bags = adj[i];
            for(int w : bags) {
                d.addEdge(w, i);
            }
        }
        return d;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
}
