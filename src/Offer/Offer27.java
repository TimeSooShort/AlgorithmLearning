package Offer;

public class Offer27 {

    // 二叉树的镜像，画图后就很清晰了
    public void solve(TreeNode node) {
        if (node == null) return;
        if (node.left == null && node.right == null) return;
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
        if (node.left != null) solve(node.left);
        if (node.right != null) solve(node.right);
    }
}
