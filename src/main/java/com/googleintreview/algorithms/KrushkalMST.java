package com.googleintreview.algorithms;

import com.googleintreview.datastructures.Bag;
import com.googleintreview.datastructures.EdgeWeightedGraph;
import com.googleintreview.datastructures.MinPQ;
import com.googleintreview.datastructures.UF;

import utility.Logger;

public class KrushkalMST {
    private MinPQ<Edge> pq; 
    private Bag<Edge> edges;
    private double totalWeight;
    private UF uf;
    private Logger log = new Logger(getClass());
    public KrushkalMST(EdgeWeightedGraph G) {
        uf = new UF(G.V());
        pq = new MinPQ<>(G.E());
        for(Edge e : G.edges()) {
            pq.insert(e);
        }
        log.setTag("PQ");
        for(Edge e : pq) {
            if(e != null)
            log.d(e.toString());
        }
        edges = new Bag<>();
        krushkalMST(G);
    }

    private void krushkalMST(EdgeWeightedGraph G) {
        log.setTag("krushkalMST");
        while(!pq.isEmpty() && edges.size() < G.V() - 1) {
            Edge e = pq.deleteMin();
            int v = e.either();
            int w = e.other(v);
            log.d("Processing Edge : " + e);
            printPQ();
            if(!uf.connected(v, w)) {
                log.d("Adding Edge to MST : " + e);
                edges.add(e);
                uf.union(v, w);
                totalWeight += e.weight();
            }
        }
    }

    public Iterable<Edge> edges() {
        return edges;
    }

    public double weight() {
        return totalWeight;
    }

    private void printPQ() {
        for(Edge e : pq) {
            if(e != null)
            System.out.println(e.toString());
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
