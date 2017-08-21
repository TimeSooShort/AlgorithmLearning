package Algorithms4th.Search;

import edu.princeton.cs.algs4.StdOut;

public class OrderedArrayMaxPQ<Key extends Comparable<Key>> {

	private int n;
	private Key[] keys;
	
	public OrderedArrayMaxPQ(int capacity) {
		// TODO Auto-generated constructor stub
		keys = (Key[]) new Comparable[capacity];
		n = 0;
	}
	
	public Key deleteMax()  {
		return keys[--n];
	}
	
	public void insert(Key key) {
		int i = n - 1;
		for (; i >= 0 && less(key, keys[i]); i--) {
			keys[i+1] = keys[i];
		}
		keys[i+1] = key;
		n++;
	}
	
	private boolean isEmpty() {
		return n == 0;
	}
	
	private boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	public static void main(String[] args) {
		OrderedArrayMaxPQ<String> pq = new OrderedArrayMaxPQ<>(10);
		pq.insert("this");
		pq.insert("is");
		pq.insert("a");
		pq.insert("test");
		while (!pq.isEmpty()) {
			StdOut.println(pq.deleteMax());
		}
	}
}
