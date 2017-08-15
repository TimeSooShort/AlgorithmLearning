package Algorithms4th.Search;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Merge {

	private Merge() {

	}

	public static void sort(Comparable[] a) {
		Comparable[] aux = new Comparable[a.length];
		sort(a, aux, 0, a.length - 1);
		assert isSorted(a);
	}

	private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
		if (hi <= lo)
			return;
		int mid = lo + (hi - lo) / 2;
		sort(a, aux, lo, mid);
		sort(a, aux, mid + 1, hi);
		merge(a, aux, lo, mid, hi);
	}

	private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {

		assert isSorted(a, lo, mid);
		assert isSorted(a, mid + 1, hi);

		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k];
		}

		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid)
				a[k] = aux[j++];
			else if (j > hi)
				a[k] = aux[i++];
			else if (less(aux[j], aux[i]))
				a[k] = aux[j++];
			else
				a[k] = aux[i++];
		}
		assert isSorted(a, lo, hi);
	}
	
	/**
	 * merge方法优化。在merge()方法中的归并过程需要判断i和j是否已经越界，即某半边已经用尽。可以用另一种方式，
	 * 去掉检测是否某半边已经用尽的代码
	 */
	void merges(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
		for (int k = lo; k <= mid; k++) {
			aux[k] = a[k];
		}
		for(int k = mid+1; k <= hi; k++) {
			aux[k] = a[hi-k+mid+1];
		}
		int i = lo, j = hi;
		for (int k = lo; k <= hi; k++) {
			if (less(aux[j], aux[i])) {
				a[k] = aux[j--];
			}
			else {
				a[k] = aux[i++];
			}
		}
	}

	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	private static boolean isSorted(Comparable[] a) {
		return isSorted(a, 0, a.length - 1);
	}

	private static boolean isSorted(Comparable[] a, int lo, int hi) {
		for (int i = lo + 1; i <= hi; i++)
			if (less(a[i], a[i - 1]))
				return false;
		return true;
	}

	private static void show(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			StdOut.println(a[i]);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[] a = StdIn.readAllStrings();
		Merge.sort(a);
		Merge.show(a);
	}
}
