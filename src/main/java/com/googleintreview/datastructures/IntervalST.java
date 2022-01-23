package com.googleintreview.datastructures;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class IntervalST<Key extends Comparable<Key>, Value>{
  private IntervalSTNode root;
  // create interval search tree
  public IntervalST() {

  }

  // put interval-value pair into ST
  public void put(Key lo, Key hi, Value val) {
    IntervalSTNode node = new IntervalSTNode(lo, hi, val);
    if(root == null) {
      root = node;
    } else {
      insert(root, node);
    }
  }

  private void insert(IntervalSTNode parent, IntervalSTNode node) {
    parent.max = max(parent.max, node.max);
    if(parent.left == null && parent.interval.lo.compareTo(node.interval.lo) > 0) {
      parent.left = node;
    } else if(parent.right == null && parent.interval.lo.compareTo(node.interval.lo) < 0) {
      parent.right = node;
    } else if (parent.interval.lo.compareTo(node.interval.lo) < 0) {
      insert(parent.right, node);
    } else if (parent.interval.lo.compareTo(node.interval.lo) >= 0) {
      insert(parent.left, node);
    }
  }

  private Key max(Key key1, Key key2) {
    if(key1.compareTo(key2) >= 0) {
      return key1;
    } else {
      return key2;
    }
  }

  // value paired with given interval
  public Value get(Key lo, Key hi) {
    IntervalSTNode node = root;
    while(node != null && !node.interval.lo.equals(lo)) {
      if(node.interval.lo.compareTo(lo) < 0) {
        node = node.right;
      } else {
        node = node.left;
      }
    }
    if(node == null || !node.interval.hi.equals(hi)) {
      return null;
    } else {
      return node.val;
    }
  }

  // delete the given interval
  public void delete(Key lo, Key hi) {
    IntervalSTNode node = root;
    IntervalSTNode parentNode = node;
    while(node != null && !node.interval.lo.equals(lo)) {
      parentNode = node;
      if(node.interval.lo.compareTo(lo) < 0) {
        node = node.right;
      } else {
        node = node.left;
      }
    }
    if(node == null || !node.interval.hi.equals(hi)) {
      throw new NoSuchElementException("Key doesn't exist");
    } else {
      if(node.equals(parentNode.left)) {
        parentNode.left = null;
      } else {
        parentNode.right = null;
      }
      root.max = updateMax(root);
    }
  }

  private Key updateMax(IntervalSTNode node) {
    if(node.left == null && node.right == null) {
      return node.max;
    } else if(node.left == null) {
      node.max = node.right.max;
    } else if(node.right == null) {
      node.max = node.left.max;
    } else {
      node.max = max(node.left.max, node.right.max);
    }
    return node.max;
  }

  // all intervals that intersect the given interval
  public Iterable<Value> intersects(Key lo, Key hi) {
    ArrayList<Value> intersects = new ArrayList<>();
    IntervalSTNode node = root;
    while(node != null) {
      if(node.interval.intersects(lo, hi)) {
        intersects.add(node.val);
      }
      if(node.left == null || node.left.max.compareTo(lo) < 0) {
        node = node.right;
      } else {
        node = node.left;
      }
    }
    return intersects;
  }

  private class IntervalSTNode  {
    Value val;
    Key max;
    IntervalSTNode left;
    IntervalSTNode right;
    Interval interval;
    public IntervalSTNode(Key lo, Key hi, Value val) {
      interval = new Interval(lo, hi);
      this.val = val;
      this.max = hi;
    }
  }

  private class Interval {
    Key lo;
    Key hi;
    Interval(Key lo, Key hi) {
      this.lo = lo;
      this.hi = hi;
    }

    boolean intersects(Key lo, Key hi) {
      return this.hi.compareTo(lo) >= 0 && this.lo.compareTo(hi) <= 0;
    }
  }
}
