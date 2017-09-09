package Algorithms4th.search;

import javax.xml.transform.Templates;

public class RedBlackTree<Key extends Comparable<Key>, Value> {

	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	private int size;
	private Node root;
	
	private class Node {
		private Key key;
		private Value value;
		private Node parent;
		private Node left;
		private Node right;
		private boolean color = BLACK;
		
		public Node(Key key, Value value, Node parent) {
			this.key = key;
			this.value = value;
			this.parent = parent;
		}
		
		public Value setValue(Value value) {
			Value oldValue = this.value;
			this.value = value;
			return oldValue;
		}
	}
	
	//只有在key重复时返回旧的value值，一般情况下返回null；
	public Value put(Key key, Value value) {
		Node t = root;
		if (t == null) {
			root = new Node(key, value, null);
			size = 1;;
			return null;
		}
		Node parent = null;
		int cmp = 0;
		while (t != null) {
			parent = t;
			cmp = key.compareTo(t.key);
			if (cmp < 0) t= t.left;
			else if (cmp > 0) t= t.right;
			else return t.setValue(value);
		}
		Node e = new Node(key, value, parent);
		if (cmp < 0) t.left = e;
		else t.right = null;
		fixAfterInsertioin(e);
		size++;
		return null;
	}
	
	public Value delete(Key key) {
		Node keyNode = getNode(key);
		Value oldValue = keyNode.value;
		if (keyNode == null) return null;
		delete(keyNode);
		return oldValue;
	}
	
	private Node getNode(Key key) {
		Node result = root;
		while (result != null) {
			int cmp = key.compareTo(result.key);
			if (cmp < 0) result = result.left;
			else if (cmp > 0) result = result.right;
			else return result;
		}
		return null;
	}
	
	private void delete(Node x) {
		size--;
		if (x.left != null && x.right != null) {
			Node successor = successor(x);
			x.key = successor.key;
			x.value = successor.value;
			x = successor;
		}
		
		Node replacement = x.left != null ? x.left : x.right;
		if (replacement != null) {
			replacement.parent = x.parent;
			if (x.parent == null) 			root = replacement;
			else if (x.parent.left == x) 	x.parent.left = replacement;
			else 							x.parent.right = replacement;
			x.left = x.right = x.parent = null;
			if (x.color == BLACK)
				fixAfterDeletion(replacement);
		}
		else if (x.parent == null) {
			root = null;
		}
		else {
			if (x.color == BLACK)
				fixAfterDeletion(x);  //调用后x这一支高度会增加一
			if (x.parent != null) {
				if (x.parent.left == x) x.parent.left = null;
				else if (x.parent.right == x) x.parent.right = null;
				x.parent = null;
			}
		}
	}
	
	//后继结点；右子树不为空则选择右子树中的最小节点；否则沿着右侧往上寻找第一个大于它的值
	private Node successor(Node x) {
		if (x == null) {
			return null;
		}
		else if (rightOf(x) != null) {
			Node temp = x.right;
			while (temp != null) {
				temp = temp.right;
			}
			return temp;
		}
		else {
			Node parent = parentOf(x);
			Node temp = x;
			while (parent != null && parent.right == temp) {
				temp = parent;
				parent = parent.parent;
			}
			return parent;
		}
	}

	private void fixAfterInsertioin(Node x) {
		x.color = RED;
		while (x != null && x != root &&  x.parent.color == RED) {
			if (leftOf(parentOf(parentOf(x))) == parentOf(x)) {
				Node sib = rightOf(parentOf(parentOf(x)));
				if (colorOf(sib) == RED) {
					setColor(parentOf(x), BLACK);
					setColor(sib, BLACK);
					setColor(parentOf(parentOf(x)), RED);
					x = parentOf(parentOf(x));
				}
				else {
					if (rightOf(parentOf(x)) == x) {
						x = parentOf(x);
						rotateLeft(x);
					}
					setColor(parentOf(x), BLACK);
					setColor(parentOf(parentOf(x)), RED);
					rotateRight(parentOf(parentOf(x)));
				}
			}
			else {
				Node sib = leftOf(parentOf(parentOf(x)));
				if (colorOf(sib) == RED) {
					setColor(parentOf(x), BLACK);
					setColor(sib, BLACK);
					setColor(parentOf(parentOf(x)), RED);
					x = parentOf(parentOf(x));
				}
				else {
					if (leftOf(parentOf(x)) == x) {
						x = parentOf(x);
						rotateRight(x);
					}
					setColor(parentOf(x), BLACK);
					setColor(parentOf(parentOf(x)), RED);
					rotateLeft(parentOf(parentOf(x)));
				}
			}
		}
		root.color = BLACK;
	}
	
	private void fixAfterDeletion(Node x) {
		while(x != root && colorOf(x) == BLACK) {
			if (leftOf(parentOf(x)) == x) {
				Node sib = rightOf(parentOf(x));
				if (colorOf(sib) == RED) {
					setColor(sib, BLACK);
					setColor(parentOf(x), RED);
					rotateLeft(parentOf(x));
					sib = rightOf(parentOf(x));
				}
				if (colorOf(leftOf(sib)) == BLACK && colorOf(rightOf(sib)) == BLACK) {
					setColor(sib, RED);
					x = parentOf(x);
				}
				else {
					if (colorOf(rightOf(sib)) == BLACK) {
						setColor(leftOf(sib), BLACK);
						setColor(sib, RED);
						rotateRight(sib);
						sib = rightOf(parentOf(x));
					}
					setColor(sib, colorOf(parentOf(x)));
					setColor(parentOf(x), BLACK);
					setColor(rightOf(sib), BLACK);
					rotateLeft(sib);
					x = root;
				}
			}
			else {
				Node sib = leftOf(parentOf(x));
				if (colorOf(sib) == RED) {
					setColor(sib, BLACK);
					setColor(parentOf(x), RED);
					rotateRight(parentOf(x));
					sib = leftOf(parentOf(x));
				}
				if (colorOf(leftOf(sib)) == BLACK && colorOf(rightOf(sib))) {
					setColor(sib, RED);
					x = parentOf(x);
				}
				else {
					if (colorOf(leftOf(sib)) == BLACK) {
						setColor(rightOf(sib), BLACK);
                        setColor(sib, RED);
                        rotateLeft(sib);
                        sib = leftOf(parentOf(x));
					}
					setColor(sib, colorOf(parentOf(x)));
                    setColor(parentOf(x), BLACK);
                    setColor(leftOf(sib), BLACK);
                    rotateRight(parentOf(x));
                    x = root;
				}
			}
		}
		setColor(x, BLACK);
	}
	
	private Node parentOf(Node x) {
		return (x == null ? null : x.parent);
	}
	
	private Node leftOf(Node x) {
		return (x == null ? null : x.left);
	}
	
	private Node rightOf(Node x) {
		return (x == null ? null : x.right);
	}
	
	private boolean colorOf(Node x) {
		return (x == null ? null : x.color);
	}
	
	private void setColor(Node x, boolean color) {
		if (x != null) x.color = color;
	}
	
	private void rotateLeft(Node x) {
		if (x != null) {
			Node right = x.right;
			x.right = right.left;
			if (right.left != null) right.left.parent = x;
			right.parent = x.parent;
			if (x.parent == null) 		 root = right;
			else if (x.parent.left == x) x.parent.left = right;
			else 						 x.parent.right = right;
			right.left = x;
			x.parent = right;
		}
	}
	
	private void rotateRight(Node x) {
		if (x != null) {
			Node left = x.left;
			x.left = left.right;
			if (left.right != null) left.right.parent = x;
			left.parent = x.parent;
			if (x.parent == null) root = left;
			else if (x.parent.left == x) x.parent.left = left;
			else x.parent.right = left;
			left.right = x;
			x.parent = left;
		}
	}

}
