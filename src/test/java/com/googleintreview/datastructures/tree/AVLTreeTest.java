package com.googleintreview.datastructures.tree;

import org.junit.Test;

public class AVLTreeTest {
    @Test
    public void testPutWithUniqueKeys() {
        AVLTree<Integer,String> avl = new AVLTree<>();
        int []testData = new int[] {2,3,1,7,6};
        for(int i=0; i<testData.length; i++) {
            avl.put(testData[i],null);
        }
        int []expectedInOrder = new int[] { 1,2,3,6,7};
        int i=0;
        for(int k : avl.inOrderKeys()) {
            System.out.println("K : " + k);
        }
    }

    @Test
    public void testPutWithDuplicateKeys() {
        AVLTree<Integer,String> avl = new AVLTree<>();
        int []testData = new int[] {2,2,2,2,3};
        for(int i=0; i<testData.length; i++) {
            avl.put(testData[i],null);
        }
        int []expectedInOrder = new int[] { 2,2,2,2,3};
        int i=0;
        for(int k : avl.inOrderKeys()) {
            assert expectedInOrder[i++] == k;
        }
    }

    @Test
    public void testPutWithAcendingKeys() {
        AVLTree<Integer,String> avl = new AVLTree<>();
        int []testData = new int[] {1,2,3,4,5,6};
        for(int i=0; i<testData.length; i++) {
            avl.put(testData[i],null);
        }

        int []expectedInOrder = new int[] { 1,2,3,4,5,6};
        int i=0;
        for(int k : avl.inOrderKeys()) {
            assert expectedInOrder[i++] == k;
        }
    }

    @Test
    public void testPutWithDescendingKeys() {
        AVLTree<Integer,String> avl = new AVLTree<>();
        int []testData = new int[] {6,5,4,3,2,1};
        for(int i=0; i<testData.length; i++) {
            avl.put(testData[i],null);
        }
        int []expectedInOrder = new int[] { 1,2,3,4,5,6};
        int i=0;
        for(int k : avl.inOrderKeys()) {
            assert expectedInOrder[i++] == k;
        }
    }
}
