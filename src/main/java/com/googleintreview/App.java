package com.googleintreview;

import com.googleintreview.algorithms.GraphProcessor;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        // Digraph digraph = DataReader.readDigraph("digraph1.txt");
        // StronglyConnectedComponents scc = new StronglyConnectedComponents(digraph);
        // System.out.println("scc : " + scc.count() + "\n" + scc);

        // GraphProcessor.krushkalMST();
        // GraphProcessor.lazyPrimsMST();
        // GraphProcessor.eagerPrimsMST();
        GraphProcessor.shortestPath();
    }
}
