package Algorithms4th.search;
import java.util.LinkedList;
import java.util.Queue;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BinarySearchST<Key extends Comparable<Key>, Value> {

	private static final int CAPACITY = 2;
	private Key[] keys;
	private Value[] values;
	private int n = 0;

	public BinarySearchST() {
		this(CAPACITY);
	}

	public BinarySearchST(int capacity) {
		keys = (Key[]) new Comparable[capacity];
		values = (Value[]) new Object[capacity];
	}

	private void reSize(int capacity) {

		assert capacity >= n;

		Key[] tempK = (Key[]) new Comparable[capacity];
		Value[] tempV = (Value[]) new Object[capacity];
		for (int i = 0; i < n; i++) {
			tempK[i] = keys[i];
			tempV[i] = values[i];
		}
		keys = tempK;
		values = tempV;
	}

	private int rank(Key key) {
		if (key == null)
			throw new IllegalArgumentException("key can't be null");
		int lo = 0, hi = n - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int cmp = key.compareTo(keys[mid]);
			if (cmp < 0) {
				hi = mid - 1;
			} else if (cmp > 0) {
				lo = mid + 1;
			} else {
				return mid;
			}
		}
		return lo;
	}

	public void put(Key key, Value value) {
		if (key == null)
			throw new IllegalArgumentException("key can't be null");
		if (value == null) {
			delete(key);
			return;
		}

		int i = rank(key);
		
		if(i < n && keys[i].compareTo(key) == 0){
			values[i] = value;
			return;
		}
		
		if (n == keys.length) {
			reSize(2 * keys.length);
		}
		
		for (int j = n; j > i; j--) {
			keys[j] = keys[j - 1];
			values[j] = values[j - 1];
		}
		keys[i] = key;
		values[i] = value;
		n++;
	}

	public Value get(Key key) {
		if (key == null)
			throw new IllegalArgumentException("key can't be null");
		if (isEmpty())
			return null;
		int i = rank(key);
		//为什么要判断i == n？ key值大于所有情况下i为n，该情况不能进行compareTo判断
		if (i == n || keys[i].compareTo(key) != 0)
			return null;
		return values[i];
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public int size() {
		return n;
	}

	public void delete(Key key) {
		if (key == null)
			throw new IllegalArgumentException("key can't be null");
		if (isEmpty())
			return;
		int i = rank(key);
		if (i == n || keys[i].compareTo(key) != 0)
			return;
		for (int j = n - 1; j > i; j--) {
			keys[j - 1] = keys[j];
			values[j - 1] = values[j];
		}
		n--;
		keys[n] = null;
		values[n] = null;

		if (n > 0 && n == keys.length / 4)
			reSize(keys.length / 2);
	}

	public Iterable<Key> keys() {
		return keys(0, n - 1);
	}

	public Iterable<Key> keys(int lo, int hi) {
		Queue<Key> queue = new LinkedList<>();
		for (int i = lo; i <= hi; i++) {
			queue.offer(keys[i]);
		}

		return queue;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BinarySearchST<String, Integer> st = new BinarySearchST<>();
		for (int i = 0; !StdIn.isEmpty(); i++) {
//			if(!StdIn.readString().equals("exist")){
//				String key = StdIn.readString();
//				st.put(key, i);
//			}
			String key = StdIn.readString();
			st.put(key, i);
		}
		for (String string : st.keys()) {
			StdOut.println(string + " " + st.get(string));
		}
	}

}
