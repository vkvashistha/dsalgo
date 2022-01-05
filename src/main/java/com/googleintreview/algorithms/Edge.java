package com.googleintreview.algorithms;

public class Edge implements Comparable<Edge> {
    private int v;
    private int w;
    private double weight;
    public Edge(int v, int w, double weight) {
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
    
    public int either() {
        return v;
    }

    public int other(int v) {
        if(this.v == v) {
            return w;
        } else {
            return this.v;
        }
    }

    public double weight() {
        return weight;
    }

    @Override
    public int compareTo(Edge arg0) {
        if(this.weight > arg0.weight) return 1;
        if(weight < arg0.weight) return -1;
        return 0;
    }

    @Override
    public String toString() {
        return v + " " + w + " " + weight;
    }
}
