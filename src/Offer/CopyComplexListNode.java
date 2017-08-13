package Offer;

import java.util.HashMap;

/**
 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），
 * 返回结果为复制后复杂链表的head。
 * @author Administrator
 *
 */
public class CopyComplexListNode {

	public class Node {
		public Node next;
		public Node rand;
		public int val;
		
		public Node(int val) {
			// TODO Auto-generated constructor stub
			this.val = val;
		}
	}
	
	//方法1 思路是利用HashMap
	public Node copy(Node head) {
		HashMap<Node, Node> map = new HashMap<>();
		Node tempNode = head;
		while (tempNode != null) {
			Node copyNode = new Node(tempNode.val);
			map.put(tempNode, copyNode);
			tempNode = tempNode.next;
		}
		Node tempLinkNode = head;
		while (tempLinkNode != null) {
			map.get(tempLinkNode).next = map.get(tempLinkNode.next);
			map.get(tempLinkNode).rand = map.get(tempLinkNode.rand);
			tempLinkNode = tempLinkNode.next;
		}
		return map.get(tempLinkNode);
	}
	//方法2:思路就是1→1'→2→2'→3→3'→null
	public Node copyNode(Node head) {
		Node tempNode = head;
		Node next = null;
		while(tempNode != null) {
			next = tempNode.next;
			tempNode.next = new Node(tempNode.val);
			tempNode.next.next = next;
			tempNode = next;
		}
		tempNode = head;
		while (tempNode != null) {
			tempNode.next.rand = tempNode.rand == null ? null : tempNode.rand.next;
			tempNode = tempNode.next.next;
		}
		tempNode = head;
		Node curCopy = null;
		Node res = head.next;
		while(tempNode != null) {
			next = tempNode.next.next;
			curCopy = tempNode.next;
			tempNode.next = next;
			curCopy.next = next == null ? null : next.next;
			tempNode = next;
		}
		return res;
	}
}
