package Offer;

public class Offer55 {
    // 题目一：二叉树深度
    private int depth;
    public int TreeDepth(TreeNode root) {
        depth(root, 0);
        return depth;
    }

    public void depth(TreeNode root, int dp) {
        if(root == null) {
            depth = Math.max(depth, dp);
            return;
        }
        depth(root.left, dp+1);
        depth(root.right, dp+1);
    }

    // 解法二：利用递归来解
    public int TreeDepth2(TreeNode root) {
        return root == null ? 0 : 1 + Math.max(TreeDepth2(root.left), TreeDepth2(root.right));
    }

    //=========================================
    // 题目二：平衡树的判断
    private boolean isBalanced = true;
    public boolean isBalancedTree(TreeNode root) {
        depthDiff(root);
        return isBalanced;
    }

    private int depthDiff(TreeNode root) {
        if (root == null || !isBalanced) {
            return 0;
        }
        int left = depthDiff(root.left);
        int right = depthDiff(root.right);
        if (Math.abs(left-right) > 1) isBalanced = false;
        return Math.max(left, right)+1;
    }
}
