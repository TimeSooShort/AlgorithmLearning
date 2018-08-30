package LeetCode;

public class LeetCode101 {
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return isSame(root.left, root.right);
    }

    private boolean isSame(TreeNode n1, TreeNode n2){
        if(n1 == n2) return true;
        else if(n1 == null || n2 == null) return false;
        else if(n1.val != n2.val) return false;
        return isSame(n1.left, n2.right) && isSame(n1.right, n2.left);
    }
}
