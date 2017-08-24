package Algorithms4th.Sort;
import java.util.Random;

import edu.princeton.cs.algs4.StdRandom;

public class Quick {

	public void sort(Comparable[] a) {
		//StdRandom.shuffle(a);
		shuffle(a);
		sort(a, 0, a.length - 1);
	}

	public void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo)
			return;
		int j = partition(a, lo, hi);
		sort(a, lo, j - 1);
		sort(a, j + 1, hi);
	}

	private int partition(Comparable[] a, int lo, int hi) {
		int i = lo, j = hi + 1;
		Comparable v = a[lo];
		while (true) {
			while (less(a[++i], v))
				if (i == hi) break;

			while (less(v, a[--j]))
				if (j == lo) break;

			if (i >= j) break;

			exch(a, i, j);
		}
		exch(a, lo, j);

		return j;
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
