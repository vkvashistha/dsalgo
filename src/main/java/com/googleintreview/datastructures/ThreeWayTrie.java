package com.googleintreview.datastructures;

import java.util.ArrayList;
import java.util.List;

public class ThreeWayTrie<Value> {

    private Node root = null;
    public void put(String key, Value value) {
        if(key == null || key.length() == 0) return;
        root = put(root, key, value, 0);
    }

    private Node put(Node x, String key, Value value, int d) {
        char ch = key.charAt(d);
        if(x == null) {
            x = new Node(ch, null);
        }
        if(ch < x.ch) x.left = put(x.left, key, value, d);
        else if(ch > x.ch) x.right = put(x.right, key, value, d);
        else if(d < key.length()-1) x.middle = put(x.middle, key, value, d+1);
        else x.value = value;
        return x;
    }

    public Value get(String key) {
        Node node =get(root,key, 0);
        if(node != null) {
            return node.value;
        } else return null;
    }

    private Node get(Node node, String key, int d) {
        char ch = key.charAt(d);
        if(node == null) return null;
        if(node.ch < ch) return get(node.right, key, d);
        else if(node.ch > ch) return get(node.left, key, d);
        else if(d < key.length()-1) return get(node.middle, key, d+1);
        else return node;
    }

    public List<String> keysWithPrefix(String prefix) {
        Node node = get(root, prefix, 0);
        List<String> suffixes =  allKeys(node.middle);
        List<String> strings = new ArrayList<>();
        for(String suffix : suffixes) {
            strings.add(prefix+suffix);
        }
        return strings;
    }

    private List<String> allKeys(Node node) {
        if(node == null) return null;
        char ch = node.ch;
        List<String> keys = new ArrayList<>();
        List<String> subKeys1 = allKeys(node.middle);
        if(subKeys1 != null) {
            for (String key : subKeys1) {
                keys.add(ch + key);
            }
        }

        List<String> subKeys2 = allKeys(node.left);
        if(subKeys2 != null) {
            for (String key : subKeys2) {
                keys.add(key);
            }
        }

        List<String> subKeys3 = allKeys(node.right);
        if(subKeys3 != null) {
            for (String key : subKeys3) {
                keys.add(key);
            }
        }
        if(node.value != null)
            keys.add(ch +"");
        return keys;
    }

    private class Node {
        char ch;
        Value value;
        Node left;
        Node right;
        Node middle;
        public Node(char ch, Value value) {
            this.ch = ch;
            this.value = value;
        }
    }
}
