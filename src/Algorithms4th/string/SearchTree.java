package Algorithms4th.string;

import java.util.NoSuchElementException;

public class SearchTree<Key extends Comparable<Key>, Value> {

	private Node root;

	public SearchTree() {
	}
	
	private class Node {
		private Key key;
		private Value value;
		private Node left;
		private Node right;
		private int size;
		
		public Node(Key key, Value value, int size) {
			this.key = key;
			this.value = value;
			this.size = size;
		}
	}
	
	public void put(Key key, Value value) {
		if (key == null) throw new IllegalArgumentException("called put() with a null key");
		if (value == null) {
			delete(key);
			return;
		}
		root = put(root, key, value);
	}

	private Node put(Node node, Key key, Value value) {
		if (node == null) return new Node(key, value, 1);
		
		int cmp = key.compareTo(node.key);
		if (cmp < 0) 		node.left = put(node.left, key, value);
		else if (cmp > 0) 	node.right = put(node.right, key, value);
		else 				node.value = value;
		
		node.size = size(node.left) + size(node.right) + 1;
		return node;
	}
	
	public Value get(Key key) {
		return get(root, key);
	}
	
	private Value get(Node node, Key key) {
		if (node == null) return null;
		int cmp = key.compareTo(node.key);
		if (cmp < 0) 		return get(node.left, key);
		else if (cmp > 0) 	return get(node.right, key);
		else 				return node.value;
	}

	public void delete(Key key) {
		if (key == null) throw new IllegalArgumentException("called delete() with a null key");
		root = delete(root, key);
	}
	
	private Node delete(Node node, Key key) {
		if (node == null) return null;
		
		int cmp = key.compareTo(node.key);
		if (cmp < 0) node.left = delete(node.left, key);
		else if (cmp > 0) node.right = delete(node.right, key);
		else {
			if (node.left == null) return node.right;
			if (node.right == null) return node.left;
			Node temp = node;
			node = min(node.right);
			node.right = deleteMin(temp.right);
			node.left = temp.left;
		}
		node.size = size(node.left) + size(node.right) + 1;
		return node;
	}
	
	public void deleteMax() {
		if (isEmpty()) throw new NoSuchElementException("called deleteMax() with empty symbol table");
		root = deleteMax(root);
	}
	
	private Node deleteMax(Node node) {
		if (node.right == null) return node.left;
		node.right = deleteMax(node.right);
		node.size = size(node.right) + size(node.left) + 1;
		return node;
	}
	
	public void deleteMin() {
		if (isEmpty()) throw new NoSuchElementException("called deleteMin() with empty symbol table");
		root = deleteMax(root);
	}
	
	private Node deleteMin(Node node) {
		if (node.left == null) return node.right;
		node.left = deleteMin(node.left);
		node.size = size(node.left) + size(node.right) + 1;
		return node;
	}
	
	public int size() {
		return size(root);
	}
	
	private int size(Node node) {
		if (node == null)   return 0;
		else return 		node.size;
	}
	
	private boolean isEmpty() {
		return size() == 0;
	}
	
	public Key min() {
		if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
		return min(root).key;
	}

	private Node min(Node node) {
		if (node.left == null)  return node;
		else 					return min(node.left);
	}
	
	public Key max() {
		if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
		return max(root).key;
	}
	
	private Node max(Node node) {
		if (node.right == null) return node;
		else 					return max(node.right);
	}
	
	/**
	 * 小于key的最大key或等于key
	 * @param key
	 * @return
	 */
	public Key floor(Key key) {
		if (key == null) throw new IllegalArgumentException("called floor() with a null key");
		if (isEmpty()) throw new NoSuchElementException("called floor() with empty symbol table");
		Node result = floor(root, key);
		if (result == null) return null;
		else return result.key;
	}
	
	private Node floor(Node node, Key key) {
		if (node == null) return null;
		int cmp = key.compareTo(node.key);
		if (cmp == 0) return node;
		if (cmp < 0) return floor(node.left, key);
		Node temp = floor(node.right, key);
		if (temp != null) return temp;
		else return node;
	}
	
	/**
	 * 大于key的最小key值或者等于key
	 * @param key
	 * @return
	 */
	public Key ceiling(Key key) {
		if (key == null) throw new IllegalArgumentException("called ceiling() with a null key");
		if (isEmpty()) throw new NoSuchElementException("called ceiling() with empty symbol table");
		Node result = ceiling(root, key);
		if (result == null) return null;
		else return result.key;
	}
	
	private Node ceiling(Node x, Key key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0) return x;
		if (cmp > 0) return ceiling(x.right, key);
		Node temp = ceiling(x.left, key);
		if (temp != null) return temp;
		else return x;
	}
	
	/**
	 * 返回的这个键在树中正好有k个小于它的键
	 * @param k
	 * @return
	 */
	public Key select(int k) {
		if (k < 0 || k >= size()) 
			throw new IllegalArgumentException("called select() with wrong number : " + k);
		Node result = select(root, k);
		return result.key;
	}
	
	private Node select(Node x, int k) {
		int size = size(x.left);
		if (k < size) return select(x.left, k);
		else if (k > size) return select(x.right, k-size-1);
		else return x;
	}
	
	public int rank(Key key) {
		if (key == null) throw new IllegalArgumentException("argument rank() is null");
		return rank(root, key);
	}
	
	private int rank(Node x, Key key) {
		if (x == null) return 0;
		int cmp = key.compareTo(x.key);
		if (cmp < 0) return rank(x.left, key);
		else if (cmp > 0) return 1 + size(x.left) + rank(x.right, key);
		else return size(x.left);
	}

}
