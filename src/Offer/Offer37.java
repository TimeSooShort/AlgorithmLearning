package Offer;

import java.util.ArrayDeque;

/**
 * 二叉树的序列化与反序列化
 */
public class Offer37 {

    public void serialize(TreeNode root, StringBuilder builder) {
        if (root == null) {
            builder.append("#!");
            return;
        }
        builder.append(root.val);
        builder.append("!");
        serialize(root.left, builder);
        serialize(root.right, builder);
    }

    public TreeNode deserialize(String s) {
        if (s == null || s.length() == 0) return null;
        String[] arr = s.split("!");
        ArrayDeque<String> queue = new ArrayDeque<>();
        for (String item : arr) queue.addLast(item);
        return reconPreOrder(queue);
    }

    private TreeNode reconPreOrder(ArrayDeque<String> queue) {
        String value = queue.pollFirst();
        if (value.equals("#")) return null;
        TreeNode head = new TreeNode(Integer.valueOf(value));
        head.left = reconPreOrder(queue);
        head.right = reconPreOrder(queue);
        return head;
    }

    private void printInOrder(TreeNode head) {
        if (head == null) return;
        printInOrder(head.left);
        System.out.print(head.val + " ");
        printInOrder(head.right);
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);TreeNode node10 = new TreeNode(10);
        TreeNode node11 = new TreeNode(11);
        node6.left = node4;node6.right = node10;node4.left = node1;node4.right = node5;
        node1.right = node2;node2.right = node3;node10.left = node7;node10.right = node11;
        node7.right = node9;node9.left = node8;

        Offer37 instance = new Offer37();
        StringBuilder builder = new StringBuilder();
        instance.serialize(node6, builder);
        System.out.println(builder.toString());

        TreeNode root = instance.deserialize(builder.toString());
        instance.printInOrder(root);
    }
}
