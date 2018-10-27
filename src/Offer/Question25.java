package Offer;

import java.util.ArrayList;
import java.util.Stack;

/**
 *输入一颗二叉树和一个整数，
 *打印出二叉树中结点值的和为输入整数的所有路径。
 *路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 * @author Administrator
 *
 */
public class Question25 {

	class BinaryTreeNode{
		BinaryTreeNode left;
		BinaryTreeNode right;
		int val;
		
		public BinaryTreeNode(int val) {
			// TODO Auto-generated constructor stub
			this.val = val;
		}
	}
	
	public void findPath(BinaryTreeNode root, int expectNum) {
		if (root == null) {
			return;
		}
		Stack<Integer> stack = new Stack<>();
		int curNum = 0;
		findPath(root, expectNum, stack, curNum);
	}

	private void findPath(BinaryTreeNode root, int expectNum, Stack<Integer> stack, int curNum) {
		curNum += root.val;
		stack.push(root.val);
		if (root.left == null && root.right == null && expectNum == curNum) {
			for (int path : stack) { // 不会删除stack中元素
				System.out.print(path + " ");
			}
			System.out.println();
		}
		if (root.left != null && expectNum > curNum) {
			findPath(root.left, expectNum, stack, curNum);
		}
		if (root.right != null && expectNum > curNum) {
			findPath(root.right, expectNum, stack, curNum);
		}
		stack.pop();
	}
}
