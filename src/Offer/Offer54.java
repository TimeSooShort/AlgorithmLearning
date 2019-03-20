package Offer;

public class Offer54 {
    private int k;
    private TreeNode kthNode;
    public TreeNode solve(TreeNode root, int k) {
        if (root == null || k <= 0) return null;
        this.k = k;
        inOrder(root);
        return kthNode;
    }

    private void inOrder(TreeNode root) {
        if (root == null || k == 0) return;
        inOrder(root.left);
        if (--k == 0) kthNode = root;
        inOrder(root.right);
    }
}
