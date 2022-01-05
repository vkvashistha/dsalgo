package com.googleintreview.datastructures;

import java.util.ArrayList;
import java.util.List;

public class RwayTrie<Value> {
    private Node<Value> root = new Node<>();
    public void put(String key, Value value) {
        if(key == null || key.length() == 0) return;
        Node<Value> n = root;
        for(int i=0; i<key.length(); i++) {
            char ch = key.charAt(i);
            if(n.R[ch] == null) {
                n.R[ch] = new Node<>();
            }
            n = (Node<Value>)n.R[ch];
        }
        n.value = value;

    }

    public Value get(String key) {
       if(key == null || key.length() == 0) return null;
        Node<Value> n = root;
        for(int i=0; i<key.length(); i++) {
            char ch = key.charAt(i);
            if(n.R[ch] == null) {
                return null;
            }
            n = (Node<Value>)n.R[ch];
        }
        return n.value;
    }

    public List<String> getStringsWithPrefix(String prefix) {
        if(prefix == null || prefix.length() == 0) return null;
        Node<Value> n = root;
        for(int i=0; i<prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if(n.R[ch] == null) {
                return null;
            }
            n = (Node<Value>)n.R[ch];
        }
        List<String> strings = getString(n);
        List<String> _strings = new ArrayList<>();
        for(int i=0; i<strings.size(); i++) {
            _strings.add(prefix+strings.get(i));
        }
        return _strings;
    }

    private List<String> getString(Node<Value> start) {
        if(start == null) return null;
        List<String> strings = new ArrayList<>();
        for(char i=0; i<start.R.length; i++) {
            if(start.R[i] != null) {
                String s = "";
                s = s+i;
                List<String> _string = getString((Node<Value>)start.R[i]);
                for(int j=0; j<_string.size(); j++) {
                    strings.add(s+_string.get(j));
                }
                Node _n = (Node<Value>)start.R[i];
                if(_n.value != null) {
                    strings.add(s);
                }
            }
        }
        return strings;
    }

    public void printTree() {
        printTree(root, "");
    }
    private void printTree(Node<Value> root, String space) {
        Node<Value> n = root;
        for(char i=0; i<root.R.length; i++) {
            if(root.R[i] != null) {
                Node<Value> _n = (Node<Value>)root.R[i];
                System.out.println(space+i+":"+_n.value);
                printTree(_n, space+" ");
            }
        }
    }

    private static class Node<Value> {
        private Object [] R = new Object[255];
        private Value value;

        private void putNode(char ch, Node node) {
            R[ch] = node;
        }

        private Node getNode(char ch) {
            if(R[ch] == null) return null;
            return (Node)R[ch];
        }
    }
}
