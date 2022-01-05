package com.googleintreview.algorithms;

import com.googleintreview.datastructures.DirectedEdge;
import com.googleintreview.datastructures.EdgeWeightedDigraph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class AcyclicShortestPath {
    private DirectedEdge []edgeTo;
    private double []distTo;
    public AcyclicShortestPath(EdgeWeightedDigraph G, int s) {
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        for(int i=0; i<G.V(); i++) {
            distTo[i] = Double.MAX_VALUE;
        }
        distTo[s] = 0;
        Topological topological = new Topological(G);
        for(DirectedEdge e : topological.order()) {
            relax(e);
        }
    }

    private void relax(DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        double rWeight = distTo[v] + e.weight();
        if(distTo[w] > rWeight) {
            distTo[w] = rWeight;
            edgeTo[w] = e;
        }
    }
}
