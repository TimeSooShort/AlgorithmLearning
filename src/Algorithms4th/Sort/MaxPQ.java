package Algorithms4th.Sort;

public class MaxPQ<Key extends Comparable<Key>> {

	private int n;
	private Key[] keys;
	
	public MaxPQ(int capacity) {
		// TODO Auto-generated constructor stub
		keys = (Key[]) new Comparable[capacity+1];
		n = 0;
	}
	
	private boolean less(int i, int j) {
		return keys[i].compareTo(keys[j]) < 0;
	}
	
	private void exch(int i, int j) {
		Key temp = keys[i];
		keys[i] = keys[j];
		keys[j] =  temp;
	}
	
	private void swim(int k) {
		while (k > 1 && less(k/2, k)) {
			exch(k/2, k);
			k /= 2;
		}
	}
	
	private void sink(int k) {
		while (k*2 <= n) {
			int j = k*2;
			if (j < n && less(j, j+1)) j++;
			if (less(k, j)) break;
			exch(k, j);
			k = j;
		}
	}
	
	private void resize(int newCapacity) {
		Key[] copy = (Key[]) new Comparable[newCapacity];
		for(int i = 1; i <= n; i++) {
			copy[i] = keys[i];
		}
		keys = copy;
	}
	
	public void insert(Key key) {
		if (n == keys.length-1) resize(2*keys.length);
		keys[++n] = key;
		swim(n);
	}
	
	public Key delMax() {
		Key max = keys[1];
		exch(1, n--);
		sink(1);
		keys[n+1] = null;
		if ((n > 0) && (n == (keys.length-1)/4)) resize(keys.length/2);
		return max;
	}

}
