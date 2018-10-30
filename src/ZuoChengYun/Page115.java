package ZuoChengYun;

import Offer.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 在二叉树中找到累加和为指定值的最长路径长度
 */
public class Page115 {

    public int solve(TreeNode head, int target) {
        if (head == null) return -1;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0); //重要
        return preOrder(head, target, 0, 1, 0, map);
    }

    private int preOrder(TreeNode head, int target, int preSum, int level,
                         int maxLen, Map<Integer, Integer> map) {
        if (head == null) return maxLen;
        int curSum = preSum + head.val;
        if (!map.containsKey(curSum)) {
            map.put(curSum, level);
        }
        if (map.containsKey(curSum-target)) {
            maxLen = Math.max(maxLen, level-map.get(curSum-target));
        }
        maxLen = preOrder(head.left, target, curSum, level+1, maxLen, map);
        maxLen = preOrder(head.right, target, curSum, level+1, maxLen, map);
        if (level == map.get(curSum)) {
            map.remove(curSum);
        }
        return maxLen;
    }
}
