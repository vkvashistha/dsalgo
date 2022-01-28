package com.googleintreview.datastructures.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KDTreeTest {
  @Test
  public void put() {
    KDTree<Integer, Integer> kdTree = new KDTree<>(2);
    Integer[][] points = new Integer[][] {
        {5,5},{2,5},{7,5}
    };
//, {2,5}
    Integer[] values = new Integer[] {1, 2, 3};
    for(int i=0; i<points.length; i++) {
      kdTree.put(new KDTree.Point<>(points[i]), values[i]);
    }

    for(int i=0; i<points.length; i++) {
      Assert.assertEquals(values[i], kdTree.getPoint(new KDTree.Point<>(points[i])));
    }
  }

  @Test
  public void range() {
    KDTree<Integer, Integer> kdTree = new KDTree<>(2);
    Integer[][] points = new Integer[][] {
        {5,5},{2,5},{7,5}
    };
//, {2,5}
    Integer[] values = new Integer[] {1, 2, 3};
    for(int i=0; i<points.length; i++) {
      kdTree.put(new KDTree.Point<>(points[i]), values[i]);
    }

    Iterable<Integer> actualValues = kdTree.rangeValues(new KDTree.Point<>(1,3), new KDTree.Point<>(8,6));
    List<Integer> expectedValues = Arrays.asList(values);
    int count = 0;
    for(Integer actualValue : actualValues) {
      count++;
      Assert.assertTrue(expectedValues.contains(actualValue));
    }
    Assert.assertEquals(count, expectedValues.size());
  }

  @Test
  public void rangeOnBoundary() {
    KDTree<Integer, Integer> kdTree = new KDTree<>(2);
    Integer[][] points = new Integer[][] {
        {5,5},{2,5},{7,5}
    };
//, {2,5}
    Integer[] values = new Integer[] {1, 2, 3};
    for(int i=0; i<points.length; i++) {
      kdTree.put(new KDTree.Point<>(points[i]), values[i]);
    }

    Iterable<Integer> actualValues = kdTree.rangeValues(new KDTree.Point<>(2,5), new KDTree.Point<>(7,5));
    List<Integer> expectedValues = Arrays.asList(values);
    int count = 0;
    for(Integer actualValue : actualValues) {
      count++;
      Assert.assertTrue(expectedValues.contains(actualValue));
    }
    Assert.assertEquals(expectedValues.size(),count);
  }

  @Test
  public void rangeLessThanBoundary() {
    KDTree<Integer, Integer> kdTree = new KDTree<>(2);
    Integer[][] points = new Integer[][] {
        {5,5},{2,5},{7,5}
    };
//, {2,5}
    Integer[] values = new Integer[] {1, 2, 3};
    for(int i=0; i<points.length; i++) {
      kdTree.put(new KDTree.Point<>(points[i]), values[i]);
    }

    Iterable<Integer> actualValues = kdTree.rangeValues(new KDTree.Point<>(3,3), new KDTree.Point<>(6,7));
    List<Integer> expectedValues = Arrays.asList(1);
    int count = 0;
    for(Integer actualValue : actualValues) {
      count++;
//      Assert.assertTrue(actualValue + " not in list : " + expectedValues, expectedValues.contains(actualValue));
    }
    Assert.assertEquals(expectedValues.size(),count);
  }

  @Test
  public void rangeOutsiteBoundary() {
    KDTree<Integer, Integer> kdTree = new KDTree<>(2);
    Integer[][] points = new Integer[][] {
        {5,5},{2,5},{7,5}
    };
//, {2,5}
    Integer[] values = new Integer[] {1, 2, 3};
    for(int i=0; i<points.length; i++) {
      kdTree.put(new KDTree.Point<>(points[i]), values[i]);
    }

    Iterable<Integer> actualValues = kdTree.rangeValues(new KDTree.Point<>(10,3), new KDTree.Point<>(11,5));
    List<Integer> expectedValues = new ArrayList<>();
    int count = 0;
    for(Integer actualValue : actualValues) {
      count++;
//      Assert.assertTrue(actualValue + " not in list : " + expectedValues, expectedValues.contains(actualValue));
    }
    Assert.assertEquals(expectedValues.size(),count);
  }
}
