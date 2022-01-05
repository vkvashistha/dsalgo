package com.googleintreview.datastructures.tree;

import org.junit.Test;

public class BSTTest {
    @Test
    public void testPutWithUniqueKeys() {
        BST<Integer,String> bst = new BST<>();
        int []testData = new int[] {2,3,1,7,6};
        for(int i=0; i<testData.length; i++) {
            bst.put(testData[i],null);
        }
        int []expectedInOrder = new int[] { 1,2,3,6,7};
        int i=0;
        for(int k : bst.inOrderKeys()) {
            assert expectedInOrder[i++] == k;
        }
    }

    @Test
    public void testPutWithDuplicateKeys() {
        BST<Integer,String> bst = new BST<>();
        int []testData = new int[] {2,2,2,2,3};
        for(int i=0; i<testData.length; i++) {
            bst.put(testData[i],null);
        }
        int []expectedInOrder = new int[] { 2,2,2,2,3};
        int i=0;
        for(int k : bst.inOrderKeys()) {
            assert expectedInOrder[i++] == k;
        }
    }

    @Test
    public void testPutWithAcendingKeys() {
        BST<Integer,String> bst = new BST<>();
        int []testData = new int[] {1,2,3,4,5,6};
        for(int i=0; i<testData.length; i++) {
            bst.put(testData[i],null);
        }

        int []expectedInOrder = new int[] { 1,2,3,4,5,6};
        int i=0;
        for(int k : bst.inOrderKeys()) {
            assert expectedInOrder[i++] == k;
        }
    }

    @Test
    public void testPutWithDescendingKeys() {
        BST<Integer,String> bst = new BST<>();
        int []testData = new int[] {6,5,4,3,2,1};
        for(int i=0; i<testData.length; i++) {
            bst.put(testData[i],null);
        }
        int []expectedInOrder = new int[] { 1,2,3,4,5,6};
        int i=0;
        for(int k : bst.inOrderKeys()) {
            assert expectedInOrder[i++] == k;
        }
    }

}
