package LeetCode.tree;

import LeetCode.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class LeetCode437{

    private int num;
    public int pathSum(TreeNode root, int sum) {
        if(root == null) return num;
        recur(root, sum, 0);
        pathSum(root.left, sum);
        pathSum(root.right, sum);
        return num;
    }
    private void recur(TreeNode root, int sum, int add) {
        if(root == null) {
            return;
        }
        add += root.val;
        if (add == sum) num++;
        recur(root.left, sum, add);
        recur(root.right, sum, add);
    }

    // ======================改进一下代码=============================
    public int pathSum2(TreeNode root, int sum) {
        if(root == null) return 0;
        return recur(root, sum) + pathSum2(root.left, sum) + pathSum2(root.right, sum);
    }

    private int recur(TreeNode node, int sum) {
        if (node == null) return 0;
        return (node.val == sum ? 1: 0) + recur(node.left, sum-node.val) +
                recur(node.right, sum - node.val);
    }

    // 方法二
    // 上述方法时间复杂度是O(N^2)，利用HashMap来将时间复杂度为O(N)
    public int pathSum3(TreeNode root, int sum) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, 1);
        return recur(root, 0, sum, map);
    }
    private int recur(TreeNode node, int sum, int target, Map<Integer, Integer> map){
        if(node == null) return 0;
        sum += node.val;
        int count = map.getOrDefault(sum-target,0);
        map.put(sum, map.getOrDefault(sum, 0)+1);
        count += recur(node.left, sum, target, map) + recur(node.right, sum, target, map);
        map.put(sum, map.get(sum)-1);
        return count;
    }

}
