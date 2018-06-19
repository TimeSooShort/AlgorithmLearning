package ZuoChengYun.PrintBinaryTree.preOrder;

import ZuoChengYun.PrintBinaryTree.Node;

/**
 * 具体解释在inOrder/MorrisIn。
 * 不同在于打印时机。这里的打印时机在两处：1，cur1不断压着左往下移动，期间将其左儿子的最右节点指向cur1
 * 在此过程中打印cur1，即“中”
 * 2，在cur2 == null，cur1是最左节点时，打印cur1，即“左”
 *
 * 其实前序与中序处理手段相同，都是cur1先沿着最左边移动，到底往上跳回，再跳到右儿子，重复之前。
 */
public class MorrisPre {

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
                    System.out.print(cur1.val + " ");
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                }
            } else {
                System.out.print(cur1.val + " ");
            }
            cur1 = cur1.right;
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

        node1.left = node2;
        node1.right = node5;
        node2.right = node3;
        node3.right = node4;
        node5.left = node6;
        node6.left = node7;
        node6.right = node8;

        MorrisPre instance = new MorrisPre();
        instance.solve(node1);
    }
}
