package Offer;

/**
 * 是否是对称的二叉树。对称的二叉树的前序遍历队列与对称的前序遍历序列相同（序列包括null节点）
 * 下面的算法就是该实现
 */
public class Offer28 {

    public boolean solve(TreeNode head) {
        if (head == null) return false;
        return compare(head.left, head.right);
    }

    private boolean compare(TreeNode t1, TreeNode t2) {
        if (t1 == t2) return true; // null == null -> true
        else if (t1 == null || t2 == null || t1.val != t2.val) return false;
        else return compare(t1.left, t2.right) && compare(t1.right, t2.left);
    }

    public static void main(String[] args) {
        System.out.println(null == null); //true
    }
}
