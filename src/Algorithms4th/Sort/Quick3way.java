package Algorithms4th.Sort;

import java.util.Random;

/**
 * 快速排序的优化，对于存在大量重复元素的数组，能将运行时间从nlgn级别降低到n级别
 * 还可以引入插入排序，当数组小于M（5~15之间）时，调用插入排序更快
 * @author Administrator
 *
 */
public class Quick3way {

	public void sort(Comparable[] a) {
		shuffle(a);
		sort(a,0,a.length-1);
		assert isSorted(a);
	}
	//a[lo, lt-1]<v; a[lt, i-1]=v; a[i, gt]未确定; a[gt+1, hi]>v
	private void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo) return;
		int lt = lo, gt = hi;
		Comparable v = a[lo];
		int i = lo;
		while (i <= gt) {
			int cmp = a[i].compareTo(v);
			if (cmp < 0) 		exch(a, lt++, i++);
			else if (cmp > 0)   exch(a, i, gt--);
			else 				i++;
		}
		sort(a, lo, lt-1);
		sort(a, gt+1, hi);
		assert isSorted(a, lo, hi);
	}
	
	private boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

	private boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	private void exch(Object[] a, int i, int j) {
		Object swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	
	private void shuffle(Object[] a) {
		if (a == null) throw new IllegalArgumentException("argument array is null");
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int randomNum = i + uniform(n-i);
			Object temp = a[randomNum];
			a[randomNum] = a[i];
			a[i] = temp;
		}
	}
	
	//[0,n)
	private int uniform(int n) {
		if (n <= 0) throw new IllegalArgumentException("argument must be positive");
		return new Random(System.currentTimeMillis()).nextInt(n);
	}
}
