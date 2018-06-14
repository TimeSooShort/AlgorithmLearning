package ZuoChengYun.PrintBinaryTree.inOrder;

import ZuoChengYun.PrintBinaryTree.Node;

public class Recursion {

    public void inOrder(Node node) {
        if (node == null) return;

        inOrder(node.left);
        System.out.println(node.val + " ");
        inOrder(node.right);
    }
}
