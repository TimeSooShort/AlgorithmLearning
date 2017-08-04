package Algorithms4th.Search;

public class MergeBU {

	public void sort(Comparable[] a) {
		int n = a.length;
		Comparable[] aux = new Comparable[n];
		for (int len = 1; len < n; len *= 2) {
			// ע��lo<n-len, Math.min()�����������Ϊ�˽��nΪ���������
			for (int lo = 0; lo < n - len; lo += len * 2) {
				int hi = Math.min(lo + len * 2 - 1, n - 1);
				int mid = lo + len - 1;
				merge(a, aux, lo, mid, hi);
			}
		}
	}

	public void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
		for (int k = lo; k < hi; k++) {
			aux[k] = a[k];
		}

		int i = lo, j = mid + 1;
		for (int k = lo; k < hi; k++) {
			if (i > mid)
				a[k] = aux[j++];
			else if (j > hi)
				a[k] = aux[i++];
			else if (less(aux[j], aux[i]))
				a[k] = aux[j++];
			else
				a[k] = aux[i++];
		}
	}

	private boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
}
