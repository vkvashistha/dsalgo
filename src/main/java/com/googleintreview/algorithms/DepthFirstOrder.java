package com.googleintreview.algorithms;

import com.googleintreview.datastructures.Stack;
import com.googleintreview.datastructures.Digraph;

public class DepthFirstOrder {
    private boolean []marked;
    private Stack<Integer> reversePost = new Stack<>();
    public DepthFirstOrder(Digraph G) {
        marked = new boolean[G.V()];
        for(int i=0; i<G.V(); i++) {
            if(!marked[i]) {
                dfs(G, i);
            }
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for(int w : G.adj(v)) {
            if(!marked[w]) {
                dfs(G,w);
            }
        }
        reversePost.push(v);
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }
    
}
