package com.googleintreview.datastructures;

public class DirectedEdge implements Comparable<DirectedEdge> {
    private int v;
    private int w;
    private double weight;
    public DirectedEdge(int v, int w, double weight) {
            this.v = v;
            this.w = w;
            this.weight = weight;
    }

    public int from() {
        return v;
    }

    public int to() {
        return w;
    }
    
    public double weight() {
        return weight;
    }

    public void setWeight(double w) {
        weight = w;
    }
    
    @Override
    public int compareTo(DirectedEdge arg0) {
        if(this.weight > arg0.weight) return 1;
        if(weight < arg0.weight) return -1;
        return 0;
    }

    @Override
    public String toString() {
        return v + " " + w + " " + weight;
    }
}
