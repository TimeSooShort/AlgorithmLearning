package ZuoChengYun.PrintBinaryTree.postOrder;

import ZuoChengYun.PrintBinaryTree.Node;

import java.util.ArrayDeque;

public class NonRecur {

    public void solve(Node head) {
        ArrayDeque<Node> stack1 = new ArrayDeque<>();
        ArrayDeque<Node> stack2 = new ArrayDeque<>();

        stack1.push(head);
        while (!stack1.isEmpty()) {
            Node node = stack1.pop();
            stack2.push(node);
            if (node.left != null) {
                stack1.push(node.left);
            }
            if (node.right != null) {
                stack1.push(node.right);
            }
        }
        while (!stack2.isEmpty()) {
            Node node = stack2.pop();
            System.out.print(node.val + " ");
        }
    }

    public static void main(String[] args) {
        NonRecur instance = new NonRecur();

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

        instance.solve(node1);
    }
}
