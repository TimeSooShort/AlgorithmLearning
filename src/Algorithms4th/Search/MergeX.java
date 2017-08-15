package Algorithms4th.Search;

public class MergeX {

	private static final int CUTOFF = 7;
	
	public void sort(Comparable[] a) {
		Comparable[] aux = a.clone();
		//将aux放在第一位
		sort(aux,a,0,a.length-1);
		assert isSorted(a);
	}

	private void sort(Comparable[] src, Comparable[] dst, int lo, int hi) {
		if (hi <= lo + CUTOFF) {
			insertionSort(dst, lo, hi);
			return;
		}
		int mid = (lo + hi)/2;
		sort(dst, src, lo, mid);
		sort(dst, src, mid+1, hi);
		
//		if (less(src[mid], src[mid+1])) {
//			for (int i = lo; i <= hi; i++) dst[i] = src[i];
//			return;
//		}
		
		//更快
		if (less(src[mid], src[mid+1])) {
			System.arraycopy(src, lo, dst, lo, hi-lo+1);
			return;
		}
		merge(src, dst, lo, mid, hi);
	}
	
	private void merge(Comparable[] src, Comparable[] dst, int lo, int mid, int hi) {
		assert isSorted(src, lo, mid);
		assert isSorted(src, mid+1, hi);
		
		int i = lo, j = mid+1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid) 	 			    dst[k] = src[j++];
			else if (j > hi) 			    dst[k] = src[i++];
			else if (less(src[i], src[j]))  dst[k] = src[i++];
			else 							dst[k] = src[j++];
		}
		assert isSorted(dst, lo, hi);
	}

	private void insertionSort(Comparable[] a, int lo, int hi) {
		for (int i = lo; i <= hi; i++) {
			for (int j = i; j>lo && less(a[j], a[j-1]); j--) {
				exch(a, j, j-1);
			}
		}
	}
	
	private void exch(Object[] a, int i, int j) {
		Object swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	
	private boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	private boolean isSorted(Comparable[] a) {
		return isSorted(a, 0, a.length - 1);
	}

	private boolean isSorted(Comparable[] a, int lo, int hi) {
		for (int i = lo + 1; i <= hi; i++)
			if (less(a[i], a[i - 1]))
				return false;
		return true;
	}
}
