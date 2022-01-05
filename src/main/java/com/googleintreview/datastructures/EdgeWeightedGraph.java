package com.googleintreview.datastructures;

import com.googleintreview.algorithms.Edge;

public class EdgeWeightedGraph {
    private int V;
    private int E;
    private Bag<Edge> []adj;
    private Bag<Edge> edges;
    public EdgeWeightedGraph(int V) {
        this.V = V;
        adj = (Bag<Edge>[])new Bag[V];
        edges = new Bag<>();
        for(int i=0; i<V; i++) {
            adj[i] = new Bag<Edge>();
        }
    }

    public void addEdge(Edge edge) {
        int v = edge.either();
        int w = edge.other(v);
        adj[v].add(edge);
        adj[w].add(edge);
        edges.add(edge);
        E++;
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public Iterable<Edge> edges() {
        return edges;
    }
}
