package com.googleintreview.datastructures;

public class EdgeWeightedDigraph {
    private int E;
    private int V;
    private Bag<DirectedEdge>[]adj;
    private Queue<DirectedEdge> edges;

    public EdgeWeightedDigraph(int V) {
        this.V = V;
        adj = (Bag<DirectedEdge>[])new Bag[V];
        for(int i=0; i<V; i++) {
            adj[i] = new Bag<DirectedEdge>();
        }
        edges = new Queue<>();
    }

    public void addEdge(DirectedEdge edge) {
        int v = edge.from();
        adj[v].add(edge);
        E++;
        edges.enqueue(edge);
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    public Iterable<DirectedEdge> edges() {
        return edges;
    }
    
}
