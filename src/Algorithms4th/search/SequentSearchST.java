package Algorithms4th.search;

import edu.princeton.cs.algs4.Queue;

public class SequentSearchST<Key, Value> {
	
	private int n;
	private Node first;

	public SequentSearchST() {
		// TODO Auto-generated constructor stub
	}
	
	private class Node{
		private Key key;
		private Value value;
		private Node next;
		
		public Node(Key key, Value value, Node next) {
			this.next = next;
			this.value = value;
			this.key = key;
		}
	}
	
	public int size() {
		return n;
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public boolean contains(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to contains() is null");
		return get(key) != null;
	}
	
	public Value get(Key key) {
		for (Node x = first; x != null; x = x.next) {
			if (key.equals(x.key)) {
				return x.value;
			}
		}
		return null;
	}
	
	public void put(Key key, Value value) {
		if (key == null) throw new IllegalArgumentException("first argument in put() is null");
		if (value == null) {
			delete(key);
			return;
		}
		for (Node x = first; x != null; x = x.next) {
			if (key.equals(x.key)) {
				x.value = value;
				return;
			}
		}
		first = new Node(key, value, first);
		n++;
	}
	
	public void delete(Key key) {
		if (key == null) throw new IllegalArgumentException("argument in delete() is null");
		first = delete(first, key);
	}
	private Node delete(Node x, Key key) {
		if(x == null) return null;
		if (key.equals(x.key)) {
			n--;
			return x.next;
		}
		x.next = delete(x.next, key);
		return x;
	}
	
	public Iterable<Key> keys() {
		Queue<Key> queue = new Queue<>();
		for (Node x = first; x != null; x = x.next) {
			queue.enqueue(x.key);
		}
		return queue;
	}

}
