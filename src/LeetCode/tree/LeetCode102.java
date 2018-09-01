package LeetCode.tree;

import LeetCode.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class LeetCode102 {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        List<Integer> level = new ArrayList<>();
        TreeNode last = root;
        TreeNode nLast = null;
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            level.add(node.val);
            if(node.left != null){
                queue.add(node.left);
                nLast = node.left;
            }
            if(node.right != null){
                queue.add(node.right);
                nLast = node.right;
            }
            if(node == last && !queue.isEmpty()){
                last = nLast;
                result.add(level);
                level = new ArrayList<>();
            }
        }
        result.add(level);
        return result;
    }
}
