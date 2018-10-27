package Offer;

public class Offer26 {

    // 判断n2是否是n1的子结构
    public boolean solve(TreeNode n1, TreeNode n2) {
        return process(n1, n2) || solve(n1.left, n2) || solve(n1.right, n2);
    }

    private boolean process(TreeNode n1, TreeNode n2) {
        if (n2 == null) return true;
        else if (n1 == null || n1.val != n2.val) return false;
        else  return process(n1.left, n2.left) || process(n1.right, n2.right);
    }
}
