package com.googleintreview.algorithms;

import com.googleintreview.datastructures.Graph;
import com.googleintreview.datastructures.Queue;
import com.googleintreview.datastructures.Stack;

public class BreadthFirstPaths extends Paths{
    private boolean [] marked;
    private int [] edgeTo;
    private int s;
    public BreadthFirstPaths(Graph G, int s) {
        super(G, s);
        bfs(G, s);
    }

    private void bfs(Graph G, int s) {
        marked[s] = true;
        Queue<Integer> vertices = new Queue<>();
        vertices.enqueue(s);
        while(!vertices.isEmpty()) {
            int v = vertices.dequeue();
            for(int w : G.adj(v)) {
                if(!marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = v;
                    vertices.enqueue(w);
                }
                
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
