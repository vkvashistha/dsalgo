package com.coursera.algorithms.part2.week1;

import edu.princeton.cs.algs4.*;

import java.util.HashSet;
import java.util.Set;


public class WordNet {
    private SAP sap;
    private ST<String, Set<Integer>> nounST;
    private ST<Integer, String> synST;
    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        In in = new In(synsets);
        synsets = in.readAll();
        nounST = new ST<>();
        synST = new ST<>();
        in = new In(hypernyms);
        hypernyms = in.readAll();
        String [] tokens = synsets.split("\\n");
        String [] hTokens = hypernyms.split("\\n");
        Digraph G = new Digraph(tokens.length);
        for(int i=0; i<hTokens.length; i++) {
            String []hToken = hTokens[i].split(",");
            int hyponymId = Integer.parseInt(hToken[0]);
            for(int j=1; j<hToken.length; j++) {
                int hypernymId = Integer.parseInt(hToken[j]);
                G.addEdge(hyponymId, hypernymId);
            }
        }

        for(int i=0; i<tokens.length; i++) {
            String []sToken = tokens[i].split(",");
            int synsetId = Integer.parseInt(sToken[0]);
            String []synset = sToken[1].split(" ");
            for(int j=0; j<synset.length; j++) {
                Set<Integer> st = nounST.get(synset[j]);
                if(st == null) {
                    st = new HashSet<>();
                    nounST.put(synset[j], st);
                }
                st.add(synsetId);
            }
            synST.put(synsetId, sToken[1]);
        }
        sap = new SAP(G);

    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return nounST;
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        return nounST.get(word) != null;
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if(!isNoun(nounA) || !isNoun(nounB)) throw new IllegalArgumentException("One or more argument is not a noun, distance("+nounA+","+nounB+")");
        Set<Integer> synsetId1 = nounST.get(nounA);
        Set<Integer> synsetId2 = nounST.get(nounB);
        return sap.length(synsetId1, synsetId2);
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        Set<Integer> synsetId1 = nounST.get(nounA);
        Set<Integer> synsetId2 = nounST.get(nounB);
        return synST.get(sap.ancestor(synsetId1, synsetId2));
    }

    // do unit testing of this class
    public static void main(String[] args) {
        WordNet wordNet = new WordNet("synsets.txt", "hypernyms.txt");
        System.out.println(wordNet.isNoun("rabbit"));
    }
}
