package LeetCode.tree;

import LeetCode.TreeNode;

import java.util.ArrayDeque;

public class LeetCode538 {

    // 解法一：利用中序遍历
    private int sum;

    public TreeNode convertBST(TreeNode root) {
        if(root == null) return null;
        convertBST(root.right);
        sum += root.val;
        root.val = sum;
        convertBST(root.left);
        return root;
    }

    // 解法二：利用栈

    public TreeNode convertBST2(TreeNode root) {
        int sum = 0;
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        TreeNode res = root;
        while (!stack.isEmpty() || root != null) {
            while(root != null) {
                stack.addLast(root);
                root = root.right;
            }

            root = stack.pollLast();
            sum += root.val;
            root.val = sum;
            root = root.left;
        }
        return res;
    }

    // 解法三：morris 算法，前面两种解法的时间与空间复杂度均为O(n)
    // morris算法的空间复杂度为O( 1 )
    public TreeNode convertBST3(TreeNode root) {
        int sum = 0;
        TreeNode node = root;
        while (node != null) {
            if (node.right == null) {
                sum += node.val;
                node.val = sum;
                node = node.left;
            }
            else {
                TreeNode succ = getSuccessor(node);
                if (succ.left == null) {
                    succ.left = node;
                    node = node.right;
                }
                else {
                    succ.left = null;
                    sum += node.val;
                    node.val = sum;
                    node = node.left;
                }
            }
        }
        return root;
    }

    private TreeNode getSuccessor(TreeNode node) {
        TreeNode succ = node.right;
        while(succ != null && succ.left != null) {
            succ = succ.left;
        }
        return succ;
    }
}
