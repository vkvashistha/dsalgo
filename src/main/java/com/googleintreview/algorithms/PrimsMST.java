package com.googleintreview.algorithms;

import com.googleintreview.datastructures.Bag;
import com.googleintreview.datastructures.EdgeWeightedGraph;
import com.googleintreview.datastructures.MinPQ;

import utility.Logger;

public class PrimsMST {
    private MinPQ<Edge> pq;
    private boolean []marked;
    private Bag<Edge> edges;
    private Logger log = new Logger(getClass());

    public PrimsMST(EdgeWeightedGraph G) {
        marked = new boolean[G.V()];
        edges = new Bag<>();
        pq = new MinPQ<>(G.V());
        primsMST(G);
    }

    private void primsMST(EdgeWeightedGraph G) {
        visit(G,0);
        while(!pq.isEmpty() && edges.size() < G.V()-1) {
            Edge minEdge = pq.deleteMin();
            int v = minEdge.either();
            int w = minEdge.other(v);
            if(marked[v] && marked[w]) continue;
            edges.add(minEdge);
            if(!marked[v]) visit(G,v);
            if(!marked[w]) visit(G,w);
        }
        
    }

    private void visit(EdgeWeightedGraph G, int v) {
        log.d("visit", ""+v);
        marked[v] = true;
        log.d("\n adj["+v+"] : ");
        for(Edge e : G.adj(v)) {
            int other = e.other(v);
            if(!marked[other]) {
                pq.insert(e);
            }
            log.d(e + " : ");
        }
        System.out.println();
        // printPQ();

    }

    private void printPQ() {
        log.d("PQ:");
        for(Edge e : pq) {
            if(e != null) {
                System.out.println(e.toString());
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(Edge e : edges) {
            builder.append(e.toString()).append("\n");
        }
        return builder.toString();
    }
    
}
