package com.googleintreview.algorithms;

import com.googleintreview.datastructures.*;

import utility.Logger;

public class ShortestPath {
    private double []distTo;
    private DirectedEdge [] edgeTo;
    private IndexMinPQV2<Double> pq;
    private Logger log = new Logger(getClass());
    public ShortestPath(EdgeWeightedDigraph G, int s) {
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        pq = new IndexMinPQV2<>(G.V());
        for(int i=0; i<G.V(); i++) {
            distTo[i] = Double.MAX_VALUE;
        }
        distTo[s] = 0;
        shortestPath(G, s);
    }

    private void shortestPath(EdgeWeightedDigraph G, int v) {
        
        // 1. Start with v, mark it as Visited.
        pq.insertKey(v, distTo[v]);
        // 2. For each adjacent edge
        while(!pq.isEmpty()) {
            v = pq.deleteMin();
            for(DirectedEdge k : G.adj(v)) {
                relax(k);
            }
        }
    }

    private void relax(DirectedEdge edge) {
        int v = edge.from();
        int w = edge.to();
        double rWeight = distTo[v] + edge.weight();
        if(distTo[w] > rWeight) {
            distTo[w] = rWeight;
            edgeTo[w] = edge;
            if(pq.contains(w)) {
                pq.decreaseKey(w, rWeight);
            } else {
                pq.insertKey(w, rWeight);
            }
        }
        
    }

    public void printPath() {
        for(int i=0; i<distTo.length; i++) {
            if(distTo[i] < Double.MAX_VALUE) {
                System.out.println(String.format("%d\t\t%.2f\t\t%s", i, distTo[i], edgeTo[i]));
            }
        }
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        Queue<DirectedEdge> path = new Queue<>();
        while(edgeTo[v] != null) {
            path.enqueue(edgeTo[v]);
            v = edgeTo[v].from();
        }
        return path;
    }
}
