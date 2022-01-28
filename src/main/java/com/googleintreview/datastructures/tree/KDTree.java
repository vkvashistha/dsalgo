package com.googleintreview.datastructures.tree;

import java.util.ArrayList;
import java.util.Arrays;

public class KDTree<Key extends Comparable<Key>, Value> {
  private int dimension;
  private Node root;
  public KDTree(int dimension) {
    this.dimension = dimension;
  }

  public void put(Point<Key> point, Value value) {
    if(root == null) {
      root = new Node(point, value, 0);
      return;
    }
    Node parentNode = searchParent(root, point);
    if(parentNode.key.equals(point)) {
      parentNode.value = value;
    } else {
      if(parentNode.key.compareTo(point) < 0) {
        parentNode.right = new Node(point, value, parentNode.key.level+1);
      } else {
        parentNode.left = new Node(point, value, parentNode.key.level+1);
      }
    }
  }

  private Node searchParent(Node node, Point<Key> point) {
    Node parent = node;
    while(node != null) {
      if(node.key.equals(point)) {
        return node;
      }
      parent = node;
      if(node.key.compareTo(point) < 0) {
        node = node.right;
      } else {
        node = node.left;
      }
    }
    return parent;
  }

  public Value getPoint(Point<Key> point) {
    Node parentNode = searchParent(root, point);
    if(parentNode.key.equals(point)) {
      return parentNode.value;
    }
    return null;
  }

  public Iterable<Value> rangeValues(Point<Key> point1, Point<Key> point2) {
    Node node = root;
    ArrayList<Value> points = new ArrayList<>();
    getValueInRange(node, point1, point2, 0, points);
    return points;
  }

  private void getValueInRange(Node node, Point<Key> point1, Point<Key> point2, int level,
                               ArrayList<Value> result) {
    if(node == null) return;
    if(isInRange(node.key, point1, point2)) {
      result.add(node.value);
      getValueInRange(node.left, point1, point2, level+1, result);
      getValueInRange(node.right, point1, point2, level+1, result);
    } else if(node.key.compareTo(point1) < 0) {
      getValueInRange(node.right, point1, point2, level+1, result);
    } else {
      getValueInRange(node.left, point1, point2, level+1, result);
    }
  }

  private boolean isInRange(Point<Key> point, Point<Key> point1, Point<Key> point2) {
    Key[] keys = point.keys;
    for(int i=0; i<keys.length; i++) {
      if ((keys[i].compareTo(point1.keys[i]) > 0 || keys[i].compareTo(point2.keys[i]) < 0) && (keys[i].compareTo(point1.keys[i]) < 0 || keys[i].compareTo(point2.keys[i]) > 0)) {
        return false;
      }
    }
    return true;
  }

  public Value deletePoint(Point<Key> point) {
    Node node = root;
    Node parent = null;
    while(node != null) {
      if(node.key.equals(point)) {
        break;
      }
      parent = node;
      if(node.key.compareTo(point) < 0) {
        node = node.right;
      } else {
        node = node.left;
      }
    }

    Value val = root.value;
    if(node != null && node == root) {
      val = root.value;
      root = null;
    } else if(node != null) {
      val = node.value;
      if(parent.left == node) {
        parent.left = null;
      } else {
        parent.right = null;
      }
    }
    return val;

  }

  public static class Point<Key extends Comparable<Key>> implements Comparable<Point<Key>> {
    Key [] keys;
    int level;
    @SafeVarargs
    public Point(Key ...keys) {
      this.keys = keys;
    }

    @Override
    public int compareTo(Point<Key> point) {
      return this.keys[level].compareTo(point.keys[level]);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Point<?> point = (Point<?>) o;
      return Arrays.equals(keys, point.keys);
    }

    @Override
    public int hashCode() {
      return Arrays.hashCode(keys);
    }
  }

  private class Node {
    Point<Key> key;
    Value value;
    Node left;
    Node right;
    public Node(Point<Key> key, Value value, int level) {
      this.key = key;
      this.value = value;
      this.key.level = level;
    }
  }
}
