package Algorithms4th.Search;

public class Shell {

	public void sort(Comparable[] a) {
		int n = a.length;
		int h = 1;
		while (h < n / 3)
			h = h * 3 + 1;
		while (h >= 1) {
			for (int i = h; i < n; i++) {
				for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
					exch(a, j - h, j);
				}
			}
			assert isHSorted(a, h);
			// 注意要放在assert判断后
			h /= 3;
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

	private boolean isHSorted(Comparable[] a, int h) {
		for (int i = h; i < a.length; i++) {
			if (less(a[i], a[i - h]))
				return false;
		}
		return true;
	}
}
