package com.googleintreview.exercise;

import com.googleintreview.datastructures.RwayTrie;
import com.googleintreview.datastructures.ThreeWayTrie;

import java.util.HashMap;
import java.util.List;

public class ContactSearch {
    public static void main(String []args) {
        String []contacts = { "edd", "eddie", "sea", "shore", "shop", "shopping"};
        RwayTrie<Integer> trie = new RwayTrie<>();
        ThreeWayTrie<Integer> ttrie = new ThreeWayTrie<>();
        for(int i=0; i<contacts.length; i++) {
            trie.put(contacts[i], 1);
            ttrie.put(contacts[i], 1);
        }
//        trie.printTree();
        List<String> strings = trie.getStringsWithPrefix("s");
        System.out.println("R Way Trie Search : ");
        for (int i=0; i<strings.size(); i++) {
            System.out.println(strings.get(i));
        }

        List<String> strings1 = ttrie.keysWithPrefix("s");
        System.out.println("Three Way Trie Search : ");
        for (int i=0; i<strings1.size(); i++) {
            System.out.println(strings1.get(i));
        }

    }
}
