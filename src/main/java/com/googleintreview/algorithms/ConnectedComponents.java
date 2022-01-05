package com.googleintreview.algorithms;

import com.googleintreview.datastructures.Graph;

public class ConnectedComponents {

    private int []ids;
    private int totalConnectedComponent = 0;

    public ConnectedComponents(Graph G) {
        ids = new int[G.V()];
        for(int i=0; i<ids.length; i++) {
            if(ids[i] <= 0) {
                totalConnectedComponent++;
                DepthFirstPaths dFirstPaths = new DepthFirstPaths(G, i);
                addComponent(dFirstPaths, totalConnectedComponent);
            }
        }
    }

    public ConnectedComponents(Graph G, Iterable<Integer> vertexOrder) {
        ids = new int[G.V()];
        for(int v : vertexOrder) {
            if(ids[v] <= 0) {
                totalConnectedComponent++;
                DepthFirstPaths dFirstPaths = new DepthFirstPaths(G, v);
                addComponent(dFirstPaths, totalConnectedComponent);
            }
        }
    }

    private void addComponent(Paths path, int id) {
        for (int i=0; i<ids.length; i++) {
            if(path.hasPathTo(i)) {
                ids[i] = id;
            }
        }
    }

    public boolean isConnected(int v, int w) {
        return ids[v] == ids[w];
    }

    public int count() {
        return totalConnectedComponent;
    }

    public int id(int v) {
        return ids[v];
    }

}
