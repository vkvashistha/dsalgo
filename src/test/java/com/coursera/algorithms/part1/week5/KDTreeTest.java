package com.coursera.algorithms.part1.week5;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class KDTreeTest {
  @Test
  public void insert() {
    KDTree kdTree = new KDTree();
    Point2D []points = new Point2D[] {
        new Point2D(0.5,0.5),
        new Point2D(0.2, 0.5),
        new Point2D(0.7, 0.5)
    };

    for(Point2D point : points) {
      kdTree.insert(point);
    }
    for (Point2D point : points) {
      Assert.assertTrue(kdTree.contains(point));
    }
  }

  @Test
  public void size() {
    KDTree kdTree = new KDTree();
    Assert.assertEquals(0, kdTree.size());
    Assert.assertTrue(kdTree.isEmpty());
    Point2D []points = new Point2D[] {
        new Point2D(0.5,0.5),
        new Point2D(0.2, 0.5),
        new Point2D(0.7, 0.5)
    };

    int expectedSize = 0;
    for(Point2D point : points) {
      kdTree.insert(point);
      Assert.assertFalse(kdTree.isEmpty());
      Assert.assertEquals(++expectedSize, kdTree.size());
    }

    // insert duplicate
    kdTree.insert(points[0]);
    Assert.assertEquals(expectedSize, kdTree.size());
  }

  @Test
  public void nearest() {
    KDTree kdTree = new KDTree();
    Assert.assertEquals(0, kdTree.size());
    Assert.assertTrue(kdTree.isEmpty());
    Assert.assertNull(kdTree.nearest(new Point2D(0.5, 0.5)));
    Point2D []points = new Point2D[] {
        new Point2D(0.5,0.5),
        new Point2D(0.2, 0.5),
        new Point2D(0.7, 0.5)
    };

    for(Point2D point : points) {
      kdTree.insert(point);
    }

    Assert.assertEquals(points[0], kdTree.nearest(new Point2D(0.5, 0.8)));
    Assert.assertEquals(points[0], kdTree.nearest(new Point2D(0.5, 0.3)));
    Assert.assertEquals(points[0], kdTree.nearest(new Point2D(0.5, 0.5)));
    Assert.assertEquals(points[1], kdTree.nearest(new Point2D(0.1, 0.6)));
    Assert.assertEquals(points[1], kdTree.nearest(new Point2D(0.1, 0.3)));
    Assert.assertEquals(points[1], kdTree.nearest(new Point2D(0.2, 0.5)));
    Assert.assertEquals(points[2], kdTree.nearest(new Point2D(0.8, 0.6)));
    Assert.assertEquals(points[2], kdTree.nearest(new Point2D(0.8, 0.3)));
    Assert.assertEquals(points[2], kdTree.nearest(new Point2D(0.7, 0.5)));
  }

  @Test
  public void range() {
    KDTree kdTree = new KDTree();
    Point2D []points = new Point2D[] {
        new Point2D(0.5,0.5),
        new Point2D(0.2, 0.5),
        new Point2D(0.7, 0.5)
    };

    for(Point2D point : points) {
      kdTree.insert(point);
    }

    List<Point2D> expected = Arrays.asList(points);
    Iterable<Point2D> actual = kdTree.range(new RectHV(0,0, 1,1));
    for(Point2D point : actual) {
      Assert.assertTrue(expected.contains(point));
    }

    actual = kdTree.range(new RectHV(0.2,0.5, 0.7,0.5));
    for(Point2D point : actual) {
      Assert.assertTrue(expected.contains(point));
    }

    expected = Arrays.asList(points[1]);
    actual = kdTree.range(new RectHV(0.2,0.5, 0.2,0.5));
    for(Point2D point : actual) {
      Assert.assertTrue(expected.contains(point));
    }

    expected = Arrays.asList(points[1]);
    actual = kdTree.range(new RectHV(0.1,0.4, 0.3,0.6));
    for(Point2D point : actual) {
      Assert.assertTrue(expected.contains(point));
    }

    expected = Arrays.asList(points[0]);
    actual = kdTree.range(new RectHV(0.3,0.4, 0.6,0.6));
    for(Point2D point : actual) {
      Assert.assertTrue(expected.contains(point));
    }
  }

  @Test
  public void rangeTest() {
    String testData = "A  0.53125 0.59375\n" +
                          "      B  0.9375 0.65625\n" +
                          "      C  0.25 0.0625\n" +
                          "      D  0.46875 0.90625\n" +
                          "      E  0.1875 0.9375\n" +
                          "      F  0.84375 0.5\n" +
                          "      G  0.75 0.8125\n" +
                          "      H  0.125 0.46875\n" +
                          "      I  0.03125 0.28125\n" +
                          "      J  0.5625 1.0\n" +
                          "      K  0.625 0.625\n" +
                          "      L  0.0625 0.0\n" +
                          "      M  0.21875 0.09375\n" +
                          "      N  0.28125 0.40625\n" +
                          "      O  0.59375 0.25\n" +
                          "      P  1.0 0.5625\n" +
                          "      Q  0.71875 0.1875\n" +
                          "      R  0.6875 0.71875\n" +
                          "      S  0.4375 0.53125\n" +
                          "      T  0.3125 0.34375";
    String []lines = testData.split("\n");
    Point2D []points = new Point2D[lines.length];
    KDTree kdTree = new KDTree();
    for(int i=0; i<lines.length; i++) {
      String []tokens = lines[i].trim().split(" ");
      points[i] = new Point2D(Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]));
      kdTree.insert(points[i]);
    }

    //    A H I N S T K O Q
    RectHV rectHV1 = new RectHV(0.0, 0.6875, 0.15625, 0.8125);
    RectHV rectHV2 = new RectHV(0.0, 0.15625,0.8125,  0.6875);
    Iterable<Point2D> range = kdTree.range(rectHV2);
    int count = 0;
    for(Point2D point : range) {
      count++;
    }
    Assert.assertEquals(9,count);
  }

  @Test
  public void nearestTest() {
    String testData = "A  0.125 0.125\n" +
                          "      B  1.0 0.625\n" +
                          "      C  1.0 0.25\n" +
                          "      D  0.875 0.5\n" +
                          "      E  1.0 0.375\n" +
                          "      F  0.25 0.0\n" +
                          "      G  0.5 0.125\n" +
                          "      H  0.25 0.125\n" +
                          "      I  0.125 0.25\n" +
                          "      J  0.0 1.0\n" +
                          "      K  0.5 0.0\n" +
                          "      L  0.75 1.0\n" +
                          "      M  0.375 0.375\n" +
                          "      N  0.125 0.5\n" +
                          "      O  0.0 0.625";
    String []lines = testData.split("\n");
    KDTree kdTree = new KDTree();
    Point2D []points = new Point2D[lines.length];
    for(int i=0; i<lines.length; i++) {
      String []tokens = lines[i].trim().split(" ");
      points[i] = new Point2D(Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]));
      kdTree.insert(points[i]);
    }

    Point2D point = kdTree.nearest(new Point2D(0.25, 0.5));
    Assert.assertEquals(points[13], point);

  }
}
