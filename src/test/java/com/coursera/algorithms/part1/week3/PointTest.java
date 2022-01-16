package com.coursera.algorithms.part1.week3;

import org.junit.Assert;
import org.junit.Test;

public class PointTest {
  @Test
  public void slopeToVertical() {
    Point p1 = new Point(1,1);
    Point p2 = new Point(1,2);
    Assert.assertEquals(Double.POSITIVE_INFINITY, p1.slopeTo(p2), 0);
  }

  @Test
  public void slopeToNegativeInfinity() {
    Point p1 = new Point(1,1);
    Point p2 = new Point(1,1);
    Assert.assertEquals(Double.NEGATIVE_INFINITY, p1.slopeTo(p2), 0);
  }

  @Test
  public void slopeToHorizontalPoints() {
    Point p1 = new Point(1,1);
    Point p2 = new Point(2,1);
    Assert.assertEquals(0, p1.slopeTo(p2), 0);
  }

  @Test
  public void slopeToRandomPositiveSlope() {
    Point p1 = new Point(1,1);
    Point p2 = new Point(3,4);
    Assert.assertEquals(1.5, p1.slopeTo(p2), 0);
  }

  @Test
  public void slopeToRandomNegativeSlope() {
    Point p2 = new Point(3,1);
    Point p1 = new Point(1,4);
    Assert.assertEquals(-1.5, p1.slopeTo(p2), 0);
  }
}
