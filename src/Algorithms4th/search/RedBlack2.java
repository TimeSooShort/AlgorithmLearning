package Algorithms4th.search;

import java.util.NoSuchElementException;

public class RedBlack2<Key extends Comparable<Key>, Value> {
	
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	private Node root;

	public RedBlack2() {
		// TODO Auto-generated constructor stub
	}
	
	private class Node {
		private int size;
		private Key key;
		private Value value;
		private Node left;
		private Node right;
		private boolean color;
		
		public Node(Key key, Value value, boolean color, int size) {
			this.color = color;
			this.key = key;
			this.value = value;
			this.size = size;
		}
	}
	private Node rotateLeft(Node x) {
		Node r = x.right;
		x.right = r.left;
		r.left = x;
		r.color = r.left.color;
		r.left.color = RED;
		r.size = x.size;
		x.size = size(x.left) + size(x.right) + 1;
		return r;
	}
	private Node rotateRight(Node x) {
		Node l = x.left;
		x.left = l.right;
		l.right = x;
		l.color = l.right.color;
		l.right.color = RED;
		l.size = x.size;
		x.size = size(x.left) + size(x.right) + 1;
		return l;
	}
	private int size(Node x) {
		if (x == null) return 0;
		return x.size;
	}
	private boolean isRed(Node x) {
		if (x == null) return false;
		return x.color == RED;
	}
	private boolean isEmpty() {
		return root == null;
	}
	private void flipColors(Node x) {
		x.color = !x.color;
		x.left.color = !x.left.color;
		x.right.color = !x.right.color;
	}
	private Node moveRedLeft(Node x) {
		flipColors(x);
		if(isRed(x.right.left)) {
			x.right = rotateRight(x.right);
			x = rotateLeft(x);
			flipColors(x);
		}
		return x;
	}
	private Node moveRedRight(Node x) {
		flipColors(x);
		if (isRed(x.left.left)) {
			x = rotateRight(x);
			flipColors(x);
		}
		return x;
	}
	private Node balance(Node x) {
		if (isRed(x.right) &&!isRed(x.left)) x = rotateLeft(x);
		if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
		if (isRed(x.left) && isRed(x.right)) flipColors(x);
		
		x.size = size(x.left) + size(x.right) + 1;
		return x;
	}
	public boolean contains(Key key) {
		return get(key) != null;
	}
	
	/** ************************************************************************ */
	
	public void put(Key key, Value value) {
		if (key == null) throw new IllegalArgumentException("first argument to put() is null");
		if (value == null) {
			delete(key);
		}
		root = put(root, key, value);
		root.color = BLACK;
	}
	private Node put(Node x, Key key, Value value) {
		if (x == null) return new Node(key, value, RED, 1);
		
		int cmp = key.compareTo(x.key);
		if (cmp < 0)  		x.left = put(x.left, key, value);
		else if (cmp > 0)   x.right = put(x.right, key, value);
		else 				x.value = value;
		
		if (isRed(x.right) && !isRed(x.left)) rotateLeft(x);
		if (isRed(x.left) && isRed(x.left.left)) rotateRight(x);
		if (isRed(x.left) && isRed(x.right)) flipColors(x);
		x.size = size(x.left) + size(x.right) + 1;
		
		return x;
	}
	public void deleteMin() {
		if (isEmpty()) throw new NoSuchElementException("the tree is empty");
		if (!isRed(root.left) && !isRed(root.right))
			root.color = RED;
		root = deleteMin(root);
		if (!isEmpty()) root.color = BLACK;
	}
	private Node deleteMin(Node x) {
		if (x.left == null)
			return null;
		if (!isRed(x.left) && isRed(x.left.left))
			x = moveRedLeft(x);
		x.left = deleteMin(x.left);
		return balance(x);
	}
	public void deleteMax() {
		if (isEmpty()) throw new NoSuchElementException("tree is null");
		if (!isRed(root.left) && !isRed(root.right))
			root.color = RED;
		root = deleteMax(root);
		if (!isEmpty()) root.color = BLACK;
	}
	private Node deleteMax(Node x) {
		if (isRed(x.left))
			rotateRight(x);
		if (x.right == null)
			return null;
		if (!isRed(x.right) && isRed(x.right.left))
			x = moveRedRight(x);
		x.right = deleteMax(x.right);
		return balance(x);
	}
	public Value get(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to get() is null");
		return get(root, key);
	}
	private Value get(Node x, Key key) {
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if (cmp < 0) x = x.left;
			else if(cmp > 0) x = x.right;
			else return x.value;
		}
		return null;
	}
	public void delete(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to delete is null");
		if (!contains(key)) return;
		if (!isRed(root.left) && !isRed(root.right))
			root.color = RED;
		root = delete(root, key);
		if (!isEmpty()) root.color = BLACK;
	}
	private Node delete(Node x, Key key) {
		if (key.compareTo(x.key) < 0) {
			if (!isRed(x.left) && !isRed(x.right))
				x = moveRedLeft(x);
			x.left = delete(x.left, key);
		}
		else {
			if (isRed(x.left))
				x = rotateRight(x);
			
		}
		return x;
	}

}
