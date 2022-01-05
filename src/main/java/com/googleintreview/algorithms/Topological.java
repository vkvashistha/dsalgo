package com.googleintreview.algorithms;

import com.googleintreview.datastructures.DirectedEdge;
import com.googleintreview.datastructures.EdgeWeightedDigraph;
import com.googleintreview.datastructures.Stack;

public class Topological {
    private boolean []marked;
    private Stack<DirectedEdge> reversPost;

    public Topological(EdgeWeightedDigraph G) {
        int N = G.V();
        reversPost = new Stack<>();
        marked = new boolean[N];
        for(int i=0; i<N; i++) {
            if(!marked[i]) {
                dfs(G, i);
            }
        }
    }

    private void dfs(EdgeWeightedDigraph G, int v) {
        marked[v] = true;
        for(DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if(!marked[w]) {
                dfs(G,w);
                reversPost.push(e);
            }
        }
    }

    public Iterable<DirectedEdge> order() {
        return reversPost;
    }
}
