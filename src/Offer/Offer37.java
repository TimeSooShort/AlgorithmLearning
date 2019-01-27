package Offer;

import java.util.ArrayDeque;

/**
 * 二叉树的序列化与反序列化
 */
public class Offer37 {

    String Serialize(TreeNode root) {
        if(root == null) return "#!";
        String res = root.val + "!";
        res += Serialize(root.left);
        res += Serialize(root.right);
        return res;
    }
    TreeNode Deserialize(String str) {
        String[] values = str.split("!");
        ArrayDeque<String> queue = new ArrayDeque<>();
        for(String ele : values) {
            queue.addLast(ele);
        }
        return reconPreOrder(queue);
    }

    private TreeNode reconPreOrder(ArrayDeque<String> queue) {
        String ele = queue.pollFirst();
        if(ele.equals("#")) return null;
        TreeNode head = new TreeNode(Integer.valueOf(ele));
        head.left = reconPreOrder(queue);
        head.right = reconPreOrder(queue);
        return head;
    }
}
