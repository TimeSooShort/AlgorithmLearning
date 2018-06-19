package ZuoChengYun.PrintBinaryTree.inOrder;

import ZuoChengYun.PrintBinaryTree.Node;

/**
 * 非递归，不同辅助栈的Morris遍历二叉树的算法
 * 两个指针，cur2代表cur1的左儿子，二者保持着用的关系往左移动，每次移动后让cur2的最右节点指向cur1；
 * 直到cur2 == null，打印cur1，之后利用右指针使cur1往上，cur2 = cur1,找到cur2的最右节点，
 * 将它的右指针恢复null，打印cur1 ，cur1 = cur1.right重负上述过程；最终cur1 == null代表结束。
 * 这里选择的打印时机是：1，左节点为null，及是中序遍历中的”左“。2，利用右指针往上移动，打印移动到的节点。
 * 即是中序遍历中的“中”。而“右”即是1与2。
 */
public class MorrisIn {

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
                }
            }
            System.out.print(cur1.val + " ");
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

        MorrisIn instance = new MorrisIn();
        instance.solve(node1);
    }
}
