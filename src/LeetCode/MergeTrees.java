package LeetCode;

/**
 * Given two binary trees and imagine that when you put one of them to cover the other, 
 * some nodes of the two trees are overlapped while the others are not.
 * You need to merge them into a new binary tree.
 *  The merge rule is that if two nodes overlap, 
 *  then sum node values up as the new value of the merged node. 
 *  Otherwise, the NOT null node will be used as the node of new tree.
 * @author Administrator
 *
 */
public class MergeTrees {

	public MergeTrees() {
		// TODO Auto-generated constructor stub
	}
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		public TreeNode(int val) {
			this.val = val;
		}
	}
	
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return null;
        if (t1 == null || t2 == null) {
            return t1 == null ? t2 : t1;
        }
        TreeNode newRoot = recursion(t1, t2);
        return newRoot;
    }
    
    private TreeNode recursion(TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 == null)
            return null;
        if (n1 == null || n2 == null) {
            return n1 == null ? n2 : n1;
        }
        TreeNode current = new TreeNode(n1.val + n2.val);
        current.left = recursion(n1.left, n2.left);
        current.right = recursion(n1.right, n2.right);
        return current;
    }

}
