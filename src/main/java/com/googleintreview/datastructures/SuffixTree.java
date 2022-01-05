package com.googleintreview.datastructures;

import java.util.List;

public class SuffixTree {
    private RwayTrie<Boolean> trie;
    public SuffixTree(String text) {
        trie = new RwayTrie<>();
        for(int i=0; i<text.length(); i++) {
            String suffix = text.substring(i);
            trie.put(suffix, true);
        }
    }

    public List<String> matchesPattern(String pattern) {
        return trie.getStringsWithPrefix(pattern);
    }
}
