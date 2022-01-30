package com.coursera.algorithms.part1.week5;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import java.util.ArrayList;

public class KDTree {
  private Node root;
  private int size;
  private final RectHV canvas;
  // construct an empty set of points
  public KDTree() {
    canvas = new RectHV(0,0,1,1);
  }

  // is the set empty?
  public boolean isEmpty() {
    return size == 0;
  }

  // number of points in the set
  public int size() {
    return size;
  }

  // add the point to the set (if it is not already in the set)
  public void insert(Point2D p) {
    checkNull(p);
    Node node = root;
    if (root == null) {
      size++;
      root = new Node(p, canvas);
      return;
    }
    Node parent = root;
    int level = 0;
    boolean toLeft = true;
    while (node != null) {
      parent = node;
      if(node.point.equals(p)) {
        return;
      }
      if (level % 2 == 0) {
        if (node.point.x() > p.x()) {
          node = node.left;
          toLeft = true;
        } else {
          node = node.right;
          toLeft = false;
        }
      } else {
        if (node.point.y() > p.y()) {
          node = node.left;
          toLeft = true;
        } else {
          node = node.right;
          toLeft = false;
        }
      }
      level++;
    }
    RectHV parentRect = parent.rect;
    RectHV childRect;
    if (toLeft) {
      if((level-1)%2 == 0) {
        childRect = new RectHV(parentRect.xmin(), parentRect.ymin(), parent.point.x(), parentRect.ymax());
      } else {
        childRect = new RectHV(parentRect.xmin(), parentRect.ymin(), parentRect.xmax(), parent.point.y());
      }
      parent.left = new Node(p, childRect);
    } else {
      if((level-1)%2 == 0) {
        childRect = new RectHV(parent.point.x(), parentRect.ymin(), parentRect.xmax(), parentRect.ymax());
      } else {
        childRect = new RectHV(parentRect.xmin(), parent.point.y(), parentRect.xmax(), parentRect.ymax());
      }
      parent.right = new Node(p, childRect);
    }
    size++;
  }

  // does the set contain point p?
  public boolean contains(Point2D p) {
    checkNull(p);
    Node node = root;
    int level = 0;
    while(node != null) {
      if(node.point.equals(p)) {
        return true;
      } else {
        if(level%2 == 0) {
          if(node.point.x() > p.x()) {
            node = node.left;
          } else {
            node = node.right;
          }
        } else {
          if(node.point.y() > p.y()) {
            node = node.left;
          } else {
            node = node.right;
          }
        }
        level++;
      }
    }
    return false;
  }

  // draw all points to standard draw
  public void draw() {
    draw(root, 0);
  }

  private void draw(Node node, int level) {
    if(node == null) return;
    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.setPenRadius(.01);
    node.point.draw();
    if(level%2 == 0) {
      StdDraw.setPenColor(StdDraw.RED);
      StdDraw.line(node.rect.xmin(), node.point.y(), node.rect.xmax(), node.point.y());
    } else {
      StdDraw.setPenColor(StdDraw.BLUE);
      StdDraw.line(node.point.x(), node.rect.ymin(), node.point.x(),node.rect.ymax());
    }
    draw(node.left, level+1);
    draw(node.right, level+1);
  }

  // all points that are inside the rectangle (or on the boundary)
  public Iterable<Point2D> range(RectHV rect) {
    checkNull(rect);
    if(root == null) {
      return new ArrayList<>();
    }
    ArrayList<Point2D> range = new ArrayList<>();
    range(rect, root, 0, range);
    return range;
  }

  private void range(RectHV rect, Node node, int level, ArrayList<Point2D> result) {
    if(node == null) return;
    if (rect.contains(node.point)) {
      result.add(node.point);
      range(rect, node.left, level + 1, result);
      range(rect, node.right, level + 1, result);
    } else {
      if (level % 2 == 0) {
        if (node.point.x() <= rect.xmin()) {
          range(rect, node.right, level + 1, result);
        } else if (node.point.x() >= rect.xmax()) {
          range(rect, node.left, level + 1, result);
        } else {
          range(rect, node.left, level + 1, result);
          range(rect, node.right, level + 1, result);
        }
      } else {
        if (node.point.y() <= rect.ymin()) {
          range(rect, node.right, level + 1, result);
        } else if (node.point.y() >= rect.ymax()) {
          range(rect, node.left, level + 1, result);
        } else {
          range(rect, node.left, level + 1, result);
          range(rect, node.right, level + 1, result);
        }
      }
    }
  }

  // a nearest neighbor in the set to point p; null if the set is empty
  public Point2D nearest(Point2D p) {
    checkNull(p);
    if(root == null) return null;
    Point2D p1 = nearest(root.left, 1, p, root.point,root.point.distanceTo(p));
    Point2D p2 = nearest(root.right, 1, p, root.point,root.point.distanceTo(p));
    Point2D minPoint;
    if(p.distanceTo(p1) < p.distanceTo(p2)) {
      minPoint = p1;
    } else {
      minPoint = p2;
    }
    return minPoint;
  }

  private Point2D nearest(Node node, int level, Point2D p, Point2D minPoint, double minDis) {
    if(node == null) {
      return minPoint;
    }
    double dis = node.point.distanceTo(p);
    if(dis < minDis) {
      minDis = dis;
      minPoint = node.point;
      Point2D p1 = nearest(node.left, level+1, p, minPoint,minDis);
      Point2D p2 = nearest(node.right, level+1, p, minPoint, minDis);
      if(p.distanceTo(p1) < p.distanceTo(p2)) {
        minPoint = p1;
      } else {
        minPoint = p2;
      }
    } else {
      if(level%2 == 0) {
        if(node.point.x() < p.x()) {
          minPoint = nearest(node.right, level+1, p, minPoint, minDis);
        } else {
          minPoint = nearest(node.left, level+1, p, minPoint, minDis);
        }
      } else {
        if(node.point.y() < p.y()) {
          minPoint = nearest(node.right, level+1, p, minPoint, minDis);
        } else {
          minPoint = nearest(node.left, level+1, p, minPoint, minDis);
        }
      }
    }
    return minPoint;
  }

  private void checkNull(Object p) {
    if (p == null) {
      throw new IllegalArgumentException("Input argument is null");
    }
  }

  private static class Node {
    Point2D point;
    Node left;
    Node right;
    RectHV rect;

    Node(Point2D point, RectHV rect) {
      this.point = point;
      this.rect = rect;
    }
  }
  // unit testing of the methods (optional)
  public static void main(String[] args) {}
}
