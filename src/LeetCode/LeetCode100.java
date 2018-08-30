package LeetCode;

public class LeetCode100 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == q) return true;
        else if(p == null || q == null) return false;
        else if(p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
