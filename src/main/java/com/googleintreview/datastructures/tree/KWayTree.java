package com.googleintreview.datastructures.tree;

import java.util.LinkedList;

public class KWayTree<Key extends Comparable<Key>, Value> extends Tree<Key, Value>{
    private NodeList root = new NodeList();
    private int K;
    public KWayTree(int K) {
        this.K = K;
    }

    @Override
    public void put(Key key, Value val) {
        System.out.println("putting key : " + key);
        Node newNode = new Node(key,val);
        NodeList keyList = root;
        if(keyList.size() == 0) {
            keyList.insert(newNode);
            return;
        }
        while(true) {
            int i = 0;
            while (i < keyList.size() && keyList.get(i).key.compareTo(key) <= 0) {
                i++;
            }
            NodeList childList = null;
            if (i == keyList.size()) {
                childList = keyList.get(keyList.size() - 1).right;
            } else if(keyList.get(i).key.compareTo(key)==0) {
                keyList.get(i).val = val;
                break;
            } else {
                childList = keyList.get(i).left;
            }
            if (childList.size() == 0) {
                keyList.insert(newNode);
                balanceTree(keyList);
                break;
            } else {
                keyList = childList;
            }
        }
    }

    @Override
    public Value get(Key key) {
        NodeList keyList = root;
        while(true) {
            int i = 0;
            while (i < keyList.size() && keyList.get(i).key.compareTo(key) < 0) {
                i++;
            }
            NodeList childList = null;
            if (i == keyList.size()) {
                childList = keyList.get(keyList.size() - 1).right;
            } else if(keyList.get(i).key.compareTo(key)==0) {
                return keyList.get(i).val;
            } else {
                childList = keyList.get(i).left;
            }
            if (childList.size() == 0) {
                return null;
            } else {
                keyList = childList;
            }
        }
    }

    private void balanceTree(NodeList nodeList) {
        if(nodeList.size() <= K) {
            return;
        }
        System.out.println("balancing tree");
        int mid = nodeList.size()/2;
        NodeList left = new NodeList();
        NodeList right = new NodeList();
        NodeList parent = nodeList.parent;
        Node midNode = nodeList.get(mid);
        if(parent == null) {
            parent = new NodeList();
            root = parent;
        }
        for(int i=0; i<mid; i++) {
            left.insert(nodeList.get(i));
        }
        for(int i=mid+1; i<nodeList.size(); i++) {
            right.insert(nodeList.get(i));
        }
        midNode.left = left;
        midNode.right = right;
        left.parent = parent;
        right.parent = parent;
        parent.insert(midNode);
        balanceTree(parent);
    }

    private class Node {
        Key key;
        Value val;
        NodeList left = new NodeList();
        NodeList right = new NodeList();
        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }
    }

    private class NodeList {
        LinkedList<Node> keys = new LinkedList<>();
        NodeList parent;
        public void insert(int position, Node node) {
            keys.add(position,node);
        }

        public void insert(Node node) {
            int i=0;
            Node left = null;
            Node right = null;
            while(i<keys.size() && keys.get(i).key.compareTo(node.key) <= 0) {
                left = keys.get(i);
                i++;
                if(i < keys.size()) {
                    right = keys.get(i);
                }
            }
            if(left != null) {
                left.right = node.left;
            }
            if(right != null) {
                right.left = node.right;
            }
            keys.add(i,node);
        }

        public Node get(int position) {
            return keys.get(position);
        }

        public int size() {
            return keys.size();
        }
    }

    public static void main(String []args) {
        KWayTree<Integer, Object> kWayTree = new KWayTree<Integer,Object>(2);
        int []testData = new int[] {2,1,3,6,4,8,5};
        for(int i=0; i<testData.length; i++) {
            kWayTree.put(testData[i], null);
        }
    }
}
