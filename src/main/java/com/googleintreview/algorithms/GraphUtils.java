package com.googleintreview.algorithms;

import com.googleintreview.datastructures.Graph;

public class GraphUtils {
    public int degree(Graph G, int v) {
        int degree = 0;
        for(Integer w : G.adj(v)) {
            degree++;
        }
        return degree;
    }

    public int maxDegree(Graph G) {
        int max = 0;
        for(int v=0; v < G.V(); v++) {
            int degree = degree(G, v);
            if(max < degree) {
                max = degree;
            }
        }
        return max;
    }

    public int averageDegree(Graph G) {
        return 2*G.E()/G.V();
    }

    public int numberOfSelfLoops(Graph G) {
        int totalSelfLoops = 0;
        for(int v = 0; v < G.V(); v++) {
            for(int w : G.adj(v)) {
                if(v == w) {
                    totalSelfLoops++;
                    break;
                }
            }
        }
        return totalSelfLoops/2;
    }
    
}
