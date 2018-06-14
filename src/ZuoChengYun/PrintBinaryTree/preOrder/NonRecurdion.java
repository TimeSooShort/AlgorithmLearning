package ZuoChengYun.PrintBinaryTree.preOrder;

import ZuoChengYun.PrintBinaryTree.Node;

import java.util.ArrayDeque;

/**
 * 非递归方式的前序遍历，采用辅助栈ArrayDeque（比Stack要快）
 * 先将头节点压入，while循环，当stack非空，先弹出顶部节点，打印，
 * 若该节点右孩子非空先压入，左孩子非空后压入
 */
public class NonRecurdion {

    public void preOrder(Node head) {
        assert head != null;

        ArrayDeque<Node> stack = new ArrayDeque<>();

        stack.push(head);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.print(node.val + " ");

            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
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

//        node1.left = node2;
//        node1.right = node3;
//        node2.left = node4;
//        node2.right = node5;
//        node3.left = node6;
//        node3.right = node7;

        node1.left = node2;
        node1.right = node5;
        node2.right = node3;
        node3.right = node4;
        node5.left = node6;
        node6.left = node7;
        node6.right = node8;

        NonRecurdion instance = new NonRecurdion();
        instance.preOrder(node1);
    }
}
