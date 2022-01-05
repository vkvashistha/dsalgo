package com.googleintreview.algorithms;

import com.googleintreview.datastructures.Digraph;

public class StronglyConnectedComponents {
    private boolean []marked;
    private int []ids;
    private int totalStronglyConnectedComponent = 0;
    public StronglyConnectedComponents(Digraph G) {
        ids = new int[G.V()];
        marked = new boolean[G.V()];
        for(int i=0; i<ids.length; i++) {
            ids[i] = -1;
            marked[i] = false;
        }
        DepthFirstOrder d1 = new DepthFirstOrder(G.reverse());
        Iterable<Integer> vertexOrder = d1.reversePost();
        for(int v : vertexOrder) {
            System.out.println("SCC reversePostOrder v : " + v);
            if(!marked[v]) {
                dfs(G,v);
                totalStronglyConnectedComponent++;
            }
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        ids[v] = totalStronglyConnectedComponent;
        for(int w : G.adj(v)) {
            if(!marked[w]) {
                dfs(G, w);
            }
        }

    }

    public boolean stronglyConnected(int v, int w) {
        return ids[v] == ids[w];
    }

    public int count() {
        return totalStronglyConnectedComponent;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(int i=0; i<ids.length; i++) {
            builder.append(i + " : " + ids[i]).append("\n");
        } 
        return builder.toString();
    }
    
}
