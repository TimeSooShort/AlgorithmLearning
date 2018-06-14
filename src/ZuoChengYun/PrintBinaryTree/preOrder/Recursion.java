package ZuoChengYun.PrintBinaryTree.preOrder;

import ZuoChengYun.PrintBinaryTree.Node;

public class Recursion {

    public void preOrder(Node head) {
        if (head == null) return;;

        System.out.println(head.val + " ");
        preOrder(head.left);
        preOrder(head.right);
    }
}
