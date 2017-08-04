package Algorithms4th.Search;

public class Bubble {

	public void sort(Comparable[] a) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			for (int j = n - 1; j > i; j--) {
				if (less(a[j], a[i])) {
					exch(a, i, j);
				}
			}
			assert isSorted(a, 0, i);
		}
		assert isSorted(a);
	}

	private boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	private void exch(Object[] a, int i, int j) {
		Object swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	private boolean isSorted(Comparable[] a) {
		return isSorted(a, 0, a.length - 1);
	}

	private boolean isSorted(Comparable[] a, int lo, int hi) {
		for (int i = lo; i < hi; i++) {
			if (less(a[i + 1], a[i]))
				return false;
		}
		return true;
	}
}
