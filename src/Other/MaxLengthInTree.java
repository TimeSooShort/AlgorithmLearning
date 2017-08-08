package Other;

import java.util.HashMap;

import javax.xml.soap.Node;

/**
 * 一颗节点类型为整型的二叉树，给定一值k，求累加和为k的最长路径长度
 * 路径是指从某个节点往下，选择一个孩子节点或不选所形成的节点链
 * @author Administrator
 *思路同上一个题目maxLength一样
 */
public class MaxLengthInTree {

	public class TreeNode {
		public TreeNode left;
		public TreeNode right;
		public int val;
		public TreeNode(int val) {
			// TODO Auto-generated constructor stub
			this.val = val;
		}
	}
	public int getMaxLength(TreeNode head, int sum) {
		if (head == null) {
			return 0;
		}
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(0, 0);
		return preOrder(head, sum, 0, 0, 1, map);
	}
	
	public int preOrder(TreeNode head, int sum,
			int preSum, int maxLength, int level, HashMap<Integer, Integer> map){
		if (head == null) {
			return maxLength;
		}
		int curSum = head.val + preSum;
		if (!map.containsKey(curSum)) {
			map.put(curSum, level);
		}
		if (map.containsKey(curSum-sum)) {
			maxLength = Math.max(level - map.get(curSum-sum), maxLength);
		}
		maxLength = preOrder(head.left, sum, curSum, maxLength, level+1, map);
		maxLength = preOrder(head.right, sum, curSum, maxLength, level+1, map);
		if (map.get(curSum) == level) {
			map.remove(curSum);
		}
		return maxLength;
	}
}
