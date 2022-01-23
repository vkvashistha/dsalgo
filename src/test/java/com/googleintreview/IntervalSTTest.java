package com.googleintreview;

import com.googleintreview.datastructures.IntervalST;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntervalSTTest {
  @Test
  public void put() {
    IntervalST<Integer, Integer> st = new IntervalST<>();
    int []lo = new int[] {17,5,21,4,15,7};
    int []hi = new int[] {19, 8, 24, 8, 18, 10};
    int []val = new int[] {1,2,3,4,5,6};
    for(int i=0; i<val.length; i++) {
      st.put(lo[i], hi[i], val[i]);
    }
    for (int i = 0; i < val.length; i++) {
      Assert.assertEquals(Integer.valueOf(val[i]), st.get(lo[i], hi[i]));
    }
  }

  @Test
  public void get() {
    IntervalST<Integer, Integer> st = new IntervalST<>();
    int []lo = new int[] {17,5,21,4,15,7};
    int []hi = new int[] {19, 8, 24, 8, 18, 10};
    int []val = new int[] {1,2,3,4,5,6};
    for(int i=0; i<val.length; i++) {
      st.put(lo[i], hi[i], val[i]);
    }
    Assert.assertNull(st.get(17,18));
    Assert.assertNull(st.get(18,23));
  }

  @Test
  public void intersect() {
    IntervalST<Integer, Integer> st = new IntervalST<>();
    int []lo = new int[]  {17, 5, 21, 4, 15, 7};
    int []hi = new int[]  {19, 8, 24, 8, 18, 10};
    int []val = new int[] {1,  2, 3,  4, 5,  6};
    for(int i=0; i<val.length; i++) {
      st.put(lo[i], hi[i], val[i]);
    }
    Integer[] val1 = new Integer[] {5,6};
    List<Integer> lst = Arrays.asList(val1);
    int count = 0;
    for(int a : st.intersects(9, 16)) {
      Assert.assertTrue(lst.contains(a));
      count++;
    }
    Assert.assertEquals(lst.size(), count);
  }

  @Test
  public void nointersect() {
    IntervalST<Integer, Integer> st = new IntervalST<>();
    int []lo = new int[]  {17, 5, 21, 4, 15, 7};
    int []hi = new int[]  {19, 8, 24, 8, 18, 10};
    int []val = new int[] {1,  2, 3,  4, 5,  6};
    for(int i=0; i<val.length; i++) {
      st.put(lo[i], hi[i], val[i]);
    }
    List<Integer> lst = new ArrayList<>();
    int count = 0;
    for(int ignored : st.intersects(11, 14)) {
      count++;
    }
    Assert.assertEquals(0, count);
  }
}
