package LeetCode.tree;

import LeetCode.TreeNode;

/**
 * 将二叉树变为链表，要求原地算法，即不适用辅助空间
 */
public class LeetCode114 {

    // 思路：cur1代表当前节点，让其左子树的最右节点指向cur1的右子树，
    // 然后让其左子树变为其右子树，左子树为null；cur1顺着右链接往下重复上述过程
    public void flatten(TreeNode root) {
        TreeNode cur1 = root;
        while(cur1 != null){
            TreeNode cur2 = cur1.left;
            if(cur2 != null){
                while(cur2.right != null) cur2 = cur2.right;
                cur2.right = cur1.right;
                cur1.right = cur1.left;
                cur1.left = null;
            }
            cur1 = cur1.right;
        }
    }

    // 解法二：使用递归；如何将上述解法转化为递归
    // 上述解法是：沿着右链接往下，每一个节点都找到其左子树的最右节点，然后进行三步改造操作
    private TreeNode pre;
    public void flatten2(TreeNode root){
        if (root == null) return;
        flatten2(root.right);
        flatten2(root.left);
        root.right = pre;
        root.left = null;
        pre = root;
    }

    // 另一种递归
    public void flatten3(TreeNode root) {
        if(root==null)
            return;
        flatten3(root.left);
        flatten3(root.right);
        TreeNode left  = root.left;
        TreeNode right = root.right;
        root.left  = null;
        root.right = left;
        while(root.right!=null)
            root = root.right;
        root.right = right;
    }
}
