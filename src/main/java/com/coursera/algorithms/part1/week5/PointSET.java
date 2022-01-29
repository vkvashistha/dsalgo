package com.coursera.algorithms.part1.week5;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

import java.util.ArrayList;

public class PointSET {
  private SET<Point2D> points = new SET<>();
  // construct an empty set of points
  public PointSET() {}

  // is the set empty?
  public boolean isEmpty() {
    return points.isEmpty();
  }

  // number of points in the set
  public int size() {
    return points.size();
  }

  // add the point to the set (if it is not already in the set)
  public void insert(Point2D p) {
    checkNull(p);
    points.add(p);
  }

  // does the set contain point p?
  public boolean contains(Point2D p) {
    checkNull(p);
    return points.contains(p);
  }

  // draw all points to standard draw
  public void draw() {
    for(Point2D p : points) {
      p.draw();
    }
  }

  // all points that are inside the rectangle (or on the boundary)
  public Iterable<Point2D> range(RectHV rect) {
    checkNull(rect);
    ArrayList<Point2D> range = new ArrayList<>();
    for(Point2D point : points) {
      if(rect.contains(point)) {
        range.add(point);
      }
    }
    return range;
  }

  // a nearest neighbor in the set to point p; null if the set is empty
  public Point2D nearest(Point2D p) {
    checkNull(p);
    if(points.isEmpty()) return null;
    double minDis = Double.MAX_VALUE;
    Point2D minPoint = null;
    for(Point2D point : points) {
      double dis = point.distanceTo(p);
      if(dis < minDis) {
        minPoint = point;
        minDis = dis;
      }
    }
    return minPoint;
  }

  private void checkNull(Object p) {
    if(p == null) {
      throw new IllegalArgumentException("Input argument is null");
    }
  }

  // unit testing of the methods (optional)
  public static void main(String[] args) {}
}
