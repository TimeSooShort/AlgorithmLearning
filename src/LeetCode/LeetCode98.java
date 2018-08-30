package LeetCode;

import java.util.ArrayDeque;
import java.util.LinkedList;

/**
 * 是否是合格的二叉查找树。合格的二叉查找树中序遍历得到的结果应是递增序列
 */
public class LeetCode98 {
    public boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        LinkedList<Integer> list = new LinkedList<>();
        stack.addLast(root);
        root = root.left;
        while(!stack.isEmpty() || root != null){
            if (root != null){
                stack.addLast(root);
                root= root.left;
            }
            else {
                TreeNode top = stack.pollLast();
                Integer last = list.peekLast();
                if(last != null && last >= top.val) return false;
                list.add(top.val);
                root = top.right;
            }
        }
        return true;
    }

    // 利用morris算法
    public boolean isValidBST2(TreeNode root) {
        TreeNode cur1 = root;
        TreeNode cur2;
        LinkedList<Integer> list = new LinkedList<>();
        while(cur1 != null) {
            cur2 = cur1.left;
            if(cur2 != null) {
                while(cur2.right != null && cur2.right != cur1){
                    cur2 = cur2.right;
                }
                if(cur2.right == null) {
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                }
                else {
                    cur2.right = null;
                    if(cur2.val >= cur1.val) return false;
                }
            }
            Integer last = list.peekLast();
            if(last != null && last >= cur1.val) return false;
            list.add(cur1.val);
            cur1 = cur1.right;
        }
        return true;
    }

    // ----------------------------------------------------
    public boolean isValidBST3(TreeNode root) {
        return isValid(root, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    private boolean isValid(TreeNode root, Integer max, Integer min) {
        if (root == null) return true;
        if (root.val <= min || root.val >= max) return false;
        return isValid(root.left, Math.min(root.val, max), min) &&
                isValid(root.right, max, Math.max(root.val, min));
    }
}
