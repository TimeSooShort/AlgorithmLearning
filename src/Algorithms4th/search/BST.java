package Algorithms4th.search;
import java.util.LinkedList;
import java.util.Queue;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class BST<Key extends Comparable<Key>, Value> {
	
	private Node root;
	
	private class Node{
		private Key key;
		private Value value;
		private Node left, right;
		private int n;
		
		public Node(Key key, Value value, int n){
			this.key = key;
			this.value = value;
			this.n = n;
		}
	}
	
	public void put(Key key, Value value){
		root = put(key, value, root);
	}
	
	private Node put(Key key, Value value, Node x){
		if(x == null) return new Node(key, value, 1);
		int cmp = x.key.compareTo(key);
		if(cmp < 0) {
			x.right = put(key, value, x.right);
		}else if(cmp > 0){
			x.left = put(key, value, x.left);
		}else{
			x.value = value;
		}
		x.n = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	public Value get(Key key){
		return get(root, key);
	}
	
	private Value get(Node x, Key key){
		if(x == null) return null;
		int cmp = key.compareTo(x.key);
		if		(cmp < 0) return get(x.left, key);
		else if (cmp > 0) return get(x.right, key);
		else 			  return x.value;
	}
	
	public void delete(Key key){
		root = delete(root, key);
	}
	
	private Node delete(Node x, Key key){
		if(x == null) return null;
		int cmp = key.compareTo(x.key);
		if(cmp < 0) 	 x.left = delete(x.left, key);
		else if(cmp > 0) x.right = delete(x.right, key);
		else {
			if(x.left == null) return x.right;
			if(x.right == null) return x.left;
			Node temp = x;
			x = min(temp.right);
			x.right = deleteMin(temp.right);
			x.left = temp.left;
		}
		x.n = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	public void deleteMin(){
		root = deleteMin(root);
	}
	
	private Node deleteMin(Node x){
		if(x.left == null) return x.right;
		x.left = deleteMin(x.left);
		x.n = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	public Key min() {
		return min(root).key;
	}
	
	private Node min(Node x){
		if(x.left == null) return x;
		else 		  return min(x.left);
	}
	
	
	private int size(){
		return size(root);
	}
	
	private int size(Node node){
		if(node == null) return 0;
		else 			 return node.n;
	}
	
	public Iterable<Key> keys(){
		Queue<Key> queue = new LinkedList<>();
		keys(root, queue);
		return queue;
	}
	
	
	private void keys(Node x, Queue<Key> queue){
		if(x == null) return;
		keys(x.left, queue);
		queue.offer(x.key);
		keys(x.right, queue);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BST<String, Integer> bst = new BST<String, Integer>();
		for(int i = 0; !StdIn.isEmpty(); i++){
			String s = StdIn.readString();
			bst.put(s, i);
		}
		for (String string : bst.keys()) {
			StdOut.println(string + " " + bst.get(string));
		}

	}

}
