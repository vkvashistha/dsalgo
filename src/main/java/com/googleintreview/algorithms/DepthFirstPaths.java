package com.googleintreview.algorithms;

import com.googleintreview.datastructures.Graph;
import com.googleintreview.datastructures.Stack;

public class DepthFirstPaths extends Paths {
    private boolean [] marked;
    private int [] edgeTo;
    private int s;

    public DepthFirstPaths(Graph G, int s) {
        super(G,s);
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        for(int i=0; i<G.V(); i++) {
            marked[i] = false;
            edgeTo[i] = -1;
        }
        dfs(G,s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for(int w : G.adj(v)) {
            if(!marked[w]) {
                dfs(G, w);
                edgeTo[w] = v;
            }
        }
    }
    
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if(!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        for(int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        return path;
    }
}
