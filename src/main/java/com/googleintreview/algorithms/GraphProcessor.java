package com.googleintreview.algorithms;

import com.googleintreview.datastructures.Digraph;
import com.googleintreview.datastructures.DirectedEdge;
import com.googleintreview.algorithms.KrushkalMST;
import com.googleintreview.algorithms.StronglyConnectedComponents;
import utility.DataReader;


public class GraphProcessor {
    public int countStronglyConnectedComponent(Digraph G) {
        Digraph Gr = G.reverse();
        DepthFirstPaths d1 = new DepthFirstPaths(Gr, 0);
        return 0;
    }

    public static void krushkalMST() throws Exception {
        KrushkalMST mst = new KrushkalMST(DataReader.readEdgeWeightedGraph());
        System.out.print("Kushkals MST : \n" + mst);
    }

    public static void eagerPrimsMST() throws Exception {
        EagerPrimsMST mst = new EagerPrimsMST(DataReader.readEdgeWeightedGraph());
        System.out.print("PrimsMST : \n" + mst);
    }

    public static void lazyPrimsMST() throws Exception {
        PrimsMST mst = new PrimsMST(DataReader.readEdgeWeightedGraph());
        System.out.print("LazyPrimsMST : \n" + mst);
    }

    public static void shortestPath() throws Exception {
        ShortestPath sp = new ShortestPath(DataReader.readEdgeWeightedDigraph(), 0);
        sp.printPath();
    }
}
