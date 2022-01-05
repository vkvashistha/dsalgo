package com.googleintreview.algorithms;

public class KMPPatternSearch {

    public boolean matches(String s, String pattern) {
        DFA dfa = new DFA(pattern);
        int state = 0;
        for(int i=0; i<pattern.length(); i++) {
            state = dfa.dfa[pattern.charAt(i)][state];
        }
        return (state == pattern.length());
    }
    private static class DFA {
        private int R = 256;
        private int [][]dfa;
        public DFA(String pattern) {
            dfa = new int[R][pattern.length()];
            dfa[pattern.charAt(0)][0] = 1;
            for(int X = 0, j=1; j<pattern.length(); j++) {
                for(int c=0; c<R; c++) {
                    dfa[c][j] = dfa[c][X];
                }
                dfa[pattern.charAt(j)][j] = j+1;
                X = dfa[pattern.charAt(j)][X];
            }
        }
    }
}
