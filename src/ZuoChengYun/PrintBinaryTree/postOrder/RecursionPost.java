package ZuoChengYun.PrintBinaryTree.postOrder;

import ZuoChengYun.PrintBinaryTree.Node;

public class RecursionPost {

    public void solve(Node head) {
        if (head == null) {
            return;
        }
        solve(head.left);
        solve(head.right);
        System.out.print(head.val + " ");
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);

        node1.left = node2;
        node1.right = node5;
        node2.right = node3;
        node3.right = node4;
        node5.left = node6;
        node6.left = node7;
        node6.right = node8;

        RecursionPost instance = new RecursionPost();
        instance.solve(node1);
    }
}
