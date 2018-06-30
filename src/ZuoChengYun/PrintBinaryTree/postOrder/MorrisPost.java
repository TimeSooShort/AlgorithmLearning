package ZuoChengYun.PrintBinaryTree.postOrder;

import ZuoChengYun.PrintBinaryTree.Node;

public class MorrisPost {

    public void solve(Node head) {
        Node cur1 = head;
        Node cur2;
        while (cur1 != null) {
            cur2 = cur1.left;
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }
                if (cur2.right == null) {
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                }else {
                    cur2.right = null;
                    printEdge(cur1.left);
                }
            }
            cur1 = cur1.right;
        }
        printEdge(head);
    }

    private void printEdge(Node node) {
        Node node1 = reverseEdge(node);
        Node cur = node1;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.right;
        }
        reverseEdge(node1);
    }

    private Node reverseEdge(Node node) {
        Node pre = null;
        Node next;
        while (node != null) {
            next = node.right;
            node.right = pre;
            pre = node;
            node = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        MorrisPost instance = new MorrisPost();

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
