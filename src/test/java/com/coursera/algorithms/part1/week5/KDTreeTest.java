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
}
