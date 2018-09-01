package LeetCode.tree;

import LeetCode.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode104 {

    public int maxDepth(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        recursion(root, 0, list);
        return list.size();
    }
    private void recursion(TreeNode root, int level, List<Integer> list){
        if(root != null){
            if (level == list.size()) list.add(1);
            recursion(root.left, level+1, list);
            recursion(root.right, level+1, list);
        }
    }

    //-----------------------解法二
    public int maxDepth2(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth2(root.left), maxDepth2(root.right));
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(1);
        TreeNode n3 = new TreeNode(1);
        TreeNode n4 = new TreeNode(1);
        TreeNode n5 = new TreeNode(1);
        n1.left = n2;
        n1.right = n3;
        n3.left = n4;
        n3.right = n5;

        LeetCode104 instance = new LeetCode104();
        System.out.println(instance.maxDepth(n1));
    }
}
