package com.googleintreview.algorithms;

import com.googleintreview.datastructures.Digraph;

import java.util.Iterator;
import java.util.List;

public class DirectedDFS {
    boolean []marked;

    public DirectedDFS(Digraph G, int s) {
        marked = new boolean[G.V()];
        dfs(G,s);
    }

    public DirectedDFS(Digraph G, Iterable<Integer> s) {
        marked = new boolean[G.V()];
        for(int node : s) {
            dfs(G,node);
        }
    }

    private void dfs(Digraph G, int s) {
        for(int node : G.adj(s)) {
            if(!marked[node]) {
                marked[node] = true;
                dfs(G,node);
            }
        }


    }

    public DirectedDFS(Digraph G, Iterator<Integer> s) {

    }

    public boolean marked(int s) {
        return marked[s];
    }

}
