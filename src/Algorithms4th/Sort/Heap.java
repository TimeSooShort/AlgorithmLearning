package Algorithms4th.Sort;

public class Heap {

	public void sort(Comparable[] a) {
		int n = a.length;
		for (int k = n / 2; k >= 1; k--) {
			sink(a, k, n);
		}
		while (n > 1) {
			exch(a, 1, n--);
			sink(a, 1, n);
		}
	}

	private void sink(Comparable[] a, int k, int n) {
		while (2 * k <= n) {
			int j = 2 * k;
			if (j < n && less(a, j, j + 1))
				j++;
			if (!less(a, k, j))
				break;
			exch(a, k, j);
			k = j;
		}
	}

	private boolean less(Comparable[] a, int i, int j) {
		return a[i - 1].compareTo(a[j - 1]) < 0;
	}

	private void exch(Object[] a, int i, int j) {
		Object temp = a[i - 1];
		a[i - 1] = a[j - 1];
		a[j - 1] = temp;
	}
}
