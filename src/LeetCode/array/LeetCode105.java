package LeetCode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 前序+中序 恢复成树
 */
public class LeetCode105 {

    class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null || inorder == null) return null;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < inorder.length; i++){
            map.put(inorder[i], i);
        }
        return preIn(map, preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);
    }
    private TreeNode preIn(Map<Integer, Integer> map, int[] preorder, int pi, int pj, int[] in, int ni, int nj){
        if(pi > pj) return null;
        int index = map.get(preorder[pi]);
        TreeNode node = new TreeNode(preorder[pi]);
        node.left = preIn(map, preorder, pi+1, pi+index-ni, in, ni, index-1);
        node.right = preIn(map, preorder, pi+index-ni+1, pj, in, index+1, nj);
        return node;
    }
}
