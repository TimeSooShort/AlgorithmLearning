package Algorithms4th.Search;

/**
 * 无序数组组成的优先队列（最大数）
 * @author Administrator
 *
 * @param <Key>
 */
public class UnOrderedArrayMaxPQ<Key extends Comparable<Key>> {

	private int n;
	private Key[] keys;
	
	public UnOrderedArrayMaxPQ(int capacity) {
		keys =(Key[]) new Comparable[capacity];
		n = 0;
	}
	
	public void insert(Key key) {
		keys[n++] = key;
	}
	
	public Key deleteMax() {
		int max = 0;
		for (int i = 0; i < n; i++) {
			if (less(keys[max], i)) max = i;
		}
		exch(max, n-1);
		return keys[--n];
	}
	
	private void exch(int i, int j) {
		Key swap = keys[i];
		keys[i] = keys[j];
		keys[j] = swap;
	}
	
	private boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

}
