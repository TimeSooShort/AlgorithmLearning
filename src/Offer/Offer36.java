package Offer;

public class Offer36 {

    private TreeNode head;
    private TreeNode tail;

    public TreeNode solve(TreeNode root) {
        if (root == null) return null;
        inOrder(root);
        return head;
    }

    private void inOrder(TreeNode cur) {
        if (cur == null) return;
        inOrder(cur.left);
        cur.left = tail;
        if (tail != null) {
            tail.right = cur;
        }else {
            head = cur;
        }
        tail = cur;
        inOrder(cur.right);
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);
        TreeNode node10 = new TreeNode(10);
        TreeNode node11 = new TreeNode(11);
        node6.left = node4;
        node6.right = node10;
        node4.left = node1;
        node4.right = node5;
        node1.right = node2;
        node2.right = node3;
        node10.left = node7;
        node10.right = node11;
        node7.right = node9;
        node9.left = node8;

        Offer36 instance = new Offer36();

        TreeNode listHead = instance.solve(node6);
//        System.out.println("listHead : " + listHead.val);
        System.out.println();
        while (listHead != null) {
            System.out.print(listHead.val + " ");
            listHead = listHead.right;
        }
        System.out.println();
        listHead = instance.tail;
        while (listHead != null) {
            System.out.print(listHead.val + " ");
            listHead = listHead.left;
        }

    }
}
