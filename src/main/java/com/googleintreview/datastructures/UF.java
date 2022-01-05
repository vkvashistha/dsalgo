package com.googleintreview.datastructures;

public class UF {
    private int[] connectivity;
    private int[]sz;
    private int count = -1;
    public UF(int N) {
        connectivity = new int[N];
        sz = new int[N];
        for(int i=0; i<N; i++) {
            connectivity[i] = i;
            sz[i] = 1;
        }
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if(i == j) return;
        if(sz[i] > sz[j]) {
            connectivity[j] = i;
            sz[i] += sz[j];
        } else {
            connectivity[i] = j;
            sz[j] += sz[i];
        }
    }

    private int root(int p) {
        int i = connectivity[p];
        while(i != connectivity[i]) {
            connectivity[i] = connectivity[connectivity[i]];
            i = connectivity[i];
        }
        connectivity[p] = i;
        return i;
    }

    public boolean connected(int p, int q) {
        return connectivity[p] == connectivity[q];
    }

    public int findP(int p) {
        return connectivity[p];
    }

    public int count() {
        return count+1;
    }
    
}
