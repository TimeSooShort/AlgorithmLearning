package LeetCode.tree;

import LeetCode.TreeNode;

/**
 * 递归中规定好你对每个节点的操作
 */
public class LeetCode124 {
    private int max = Integer.MIN_VALUE;
     public int maxPathSum(TreeNode root) {
         recur(root);
         return max;
     }
     private int recur(TreeNode node) {
         if (node == null) return 0;
         int left = recur(node.left);
         int right = recur(node.right);
         int cur = node.val;
         if(left < 0) cur = Math.max(cur, cur + right);
         else if(right < 0) cur += left;
         else cur += left + right;
         if(cur > max) max = cur;
         return Math.max(node.val, Math.max(left, right)+node.val);
     }

     // =================解法2=============================
     public int maxPathSum2(TreeNode root) {
         recur(root);
         return max;
     }
     private int recur2(TreeNode node) {
        if(node == null) return 0;
        int left = Math.max(0, recur2(node.left));
        int right = Math.max(0, recur2(node.right));
        max = Math.max(max, left+right+node.val);
        return Math.max(left, right) + node.val;
    }
}
