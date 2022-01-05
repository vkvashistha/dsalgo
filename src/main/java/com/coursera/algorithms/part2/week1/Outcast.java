package com.coursera.algorithms.part2.week1;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {
    private WordNet wordNet;
    // constructor takes a WordNet object
    public Outcast(WordNet wordnet)  {
        this.wordNet = wordnet;
    }
    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns)   {
        int maxDistance = Integer.MIN_VALUE;
        int outCastIndex = -1;
        for(int i=0; i<nouns.length; i++) {
            int d = 0;
            for(int j=0; j<nouns.length; j++) {
                if(i != j) {
                    d += wordNet.distance(nouns[i], nouns[j]);
                }
            }
//            System.out.println("d["+nouns[i]+"]:" + d);

            if(maxDistance < d) {
                maxDistance = d;
                outCastIndex = i;
            }
        }
        return nouns[outCastIndex];
    }

    // see test client below
    public static void main(String[] args) {
        WordNet wordnet = new WordNet("synsets.txt", "hypernyms.txt");
        Outcast outcast = new Outcast(wordnet);
       /* String ans = outcast.outcast(new String[]{"dog","horse","cat"});
        StdOut.println(wordnet.sap("dog", "cat"));*/
        args = new String[] { "outcast5.txt", "outcast8.txt", "outcast11.txt"};
//        StdOut.println(wordnet.sap("orange_juice", "apple_juice"));
//        if(true) return;
        for (int t = 0; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}
