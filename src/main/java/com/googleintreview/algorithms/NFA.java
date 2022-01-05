package com.googleintreview.algorithms;

import com.googleintreview.datastructures.Bag;
import com.googleintreview.datastructures.Digraph;
import com.googleintreview.datastructures.Stack;

public class NFA {
    private char []re;
    private int M;
    private Digraph G;

    public NFA(String regex) {
        M = regex.length();
        re = regex.toCharArray();
        G = buildEpsilonTransitionGraph();
    }

    private Digraph buildEpsilonTransitionGraph() {
        Stack<Integer> ops = new Stack<>();
        Digraph G = new Digraph(M+1);
        for(int i=0; i < M; i++) {
            int lp = i;
            if(re[i] == '(' || re[i] == '|') {
                ops.push(i);
            }
            else if(re[i] == ')') {
                int or = ops.pop();
                if(re[or] == '|') {
                    lp = ops.pop();
                    G.addEdge(lp,or+1);
                    G.addEdge(or,i);
                } else {
                    lp = or;
                }
            }
            if(i<M-1 && re[i+1] == '*') {
                G.addEdge(lp,i+1);
                G.addEdge(i+1,lp);
            }
            if(re[i] == '(' || re[i] == '*' || re[i] == ')') {
                G.addEdge(i,i+1);
            }
        }
        return G;
    }

    public boolean recognizes(String s) {
        Bag<Integer> pc = new Bag<>();
        DirectedDFS dfs = new DirectedDFS(G,0);
        for(int v=0; v<G.V(); v++) {
            if(dfs.marked(v)) {
                pc.add(v);
            }
        }
        for(int i = 0; i<s.length(); i++) {
            System.out.println("At character : " + s.charAt(i) + ", initial states : " + pc);
            Bag<Integer> states = new Bag<>();
            for(int v : pc) {
                if(v == M) continue;
                if(re[v] == s.charAt(i) || re[v] == '.') {
                    states.add(v+1);
                }
            }
            dfs = new DirectedDFS(G,states);
            pc = new Bag<>();
            for(int v=0; v<G.V(); v++) {
                if(dfs.marked(v)) pc.add(v);
            }
            System.out.println("At character : " + s.charAt(i) + ", final states : " + pc);
        }
        for(int v : pc) {
            if(v == M) {
                return true;
            }
        }
        return false;
    }

    public static void main(String []args) {
        NFA nfa = new NFA("((A*B|AC)B)");
        System.out.println("Recongizes " + "AABB : " + nfa.recognizes("AABB"));
    }
}
