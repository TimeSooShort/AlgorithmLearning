package Offer;

import Offer.TreeNode;

/**
 * 打印二叉树
 */
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class Offer32 {

    public void solve(TreeNode head) {
        if (head == null) return;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(head);
        while (!queue.isEmpty()) {
            TreeNode node = queue.pollFirst();
            System.out.print(node.val + " ");
            if (node.left != null) queue.addLast(node.left);
            if (node.right != null) queue.addLast(node.right);
        }
    }

    // 一层一行打印
    public void solve2(TreeNode head) {
        if (head == null) return;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(head);
        TreeNode last = head; // 当前遍历层的最右边界节点
        TreeNode nLast = null; // 当前层的下一层的最右边界节点
        while (!queue.isEmpty()) {
            TreeNode node = queue.pollFirst();
            System.out.print(node.val + " ");
            if (node.left != null) {
                queue.addLast(node.left);
                nLast = head.left;
            }
            if (node.right != null) {
                queue.addLast(node.right);
                nLast = head.right;
            }
            if (node == last && !queue.isEmpty()) {
                System.out.print("\n");
                last = nLast;
            }
        }
    }

    // Z字型打印
    // Level 1 left to right: 1
    // Level 2 right to left: 3 2
    // Level 3 left to right: 4 5 6 7
    public void solve3(TreeNode head) {
        if (head == null) return;
        Deque<TreeNode> deque = new LinkedList<>(); // 双端队列
        TreeNode last = head;
        TreeNode nLast = null;
        int level = 1;
        boolean lToR = true;
        deque.add(head);
        printLevelAndOrientation(level, lToR);
        while (!deque.isEmpty()) {
            TreeNode node ;
            if (lToR) {
                node = deque.pollFirst();
                if (node.left != null) {
                    nLast = nLast == null ? node.left : nLast;
                    deque.addLast(node.left);
                }
                if (node.right != null) {
                    nLast = nLast == null ? node.right : nLast;
                    deque.addLast(node.right);
                }
            }
            else {
                node = deque.pollLast();
                if (node.right != null) {
                    nLast = nLast == null ? node.right : nLast;
                    deque.addFirst(node.right);
                }
                if (node.left != null) {
                    nLast = nLast == null ? node.left : nLast;
                    deque.addFirst(node.left);
                }
            }
            System.out.print(node.val + " ");
            if (node == last && !deque.isEmpty()) {
                last = nLast;
                nLast = null;
                System.out.println();
                lToR = !lToR;
                printLevelAndOrientation(level++, lToR);
            }
        }
    }

    private void printLevelAndOrientation(int level, boolean lToR) {
        System.out.println("Level " + level + " from ");
        System.out.println(lToR ? "left to right: " : "right to left: ");
    }
}
