package utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.googleintreview.algorithms.Edge;
import com.googleintreview.datastructures.Digraph;
import com.googleintreview.datastructures.DirectedEdge;
import com.googleintreview.datastructures.EdgeWeightedDigraph;
import com.googleintreview.datastructures.EdgeWeightedGraph;

public class DataReader {
    public static String readFile(String fileName) throws Exception {
        File file = new File("digraph1.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        StringBuffer sb = new StringBuffer();
        String line = null;
        while((line = br.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        fr.close();
        return sb.toString();
    }

    public static Digraph readDigraph(String fileName) throws Exception {
        File file = new File("digraph1.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        StringBuffer sb = new StringBuffer();
        String line = null;
        int lineCount = 0;
        Digraph digraph = null;
        int E = 0;
        while((line = br.readLine()) != null) {
            if (lineCount == 0) {
                int V = Integer.parseInt(line);
                digraph = new Digraph(V);
            } else if(lineCount == 1) {
                E = Integer.parseInt(line);
            } else {
                String []tokens = line.split(" ");
                int v = Integer.parseInt(tokens[0]);
                int w = Integer.parseInt(tokens[1]);
                digraph.addEdge(v, w);
            }
            lineCount++;
        }
        fr.close();
        return digraph;
    }

    public static EdgeWeightedGraph readEdgeWeightedGraph() throws Exception {
        File file = new File("mstgraph.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        StringBuffer sb = new StringBuffer();
        String line = null;
        int lineCount = 0;
        EdgeWeightedGraph graph = null;
        int E = 0;
        while((line = br.readLine()) != null) {
            if (lineCount == 0) {
                int V = Integer.parseInt(line);
                graph = new EdgeWeightedGraph(V);
            } else if(lineCount == 1) {
                E = Integer.parseInt(line);
            } else {
                String []tokens = line.split(" ");
                int v = Integer.parseInt(tokens[0]);
                int w = Integer.parseInt(tokens[1]);
                double weight = Double.parseDouble(tokens[2]);
                graph.addEdge(new Edge(v, w,weight));
            }
            lineCount++;
        }
        fr.close();
        return graph;
    }

    public static EdgeWeightedDigraph readEdgeWeightedDigraph() throws Exception {
        File file = new File("shortestpath.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        StringBuffer sb = new StringBuffer();
        String line = null;
        int lineCount = 0;
        EdgeWeightedDigraph graph = null;
        int E = 0;
        while((line = br.readLine()) != null) {
            if (lineCount == 0) {
                int V = Integer.parseInt(line);
                graph = new EdgeWeightedDigraph(V);
            } else if(lineCount == 1) {
                E = Integer.parseInt(line);
            } else {
                String []tokens = line.split(" ");
                int v = Integer.parseInt(tokens[0]);
                int w = Integer.parseInt(tokens[1]);
                double weight = Double.parseDouble(tokens[2]);
                graph.addEdge(new DirectedEdge(v, w,weight));
            }
            lineCount++;
        }
        fr.close();
        return graph;
    }
}
