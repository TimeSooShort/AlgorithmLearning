package Offer;

import java.util.HashMap;
import java.util.Map;

public class Offer7 {

    public TreeNode preInToTree(int[] pre, int[] in) {
        if (pre == null || in == null) return null;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            map.put(in[i], i);
        }
        return preIn(pre, 0, pre.length-1, in, 0, in.length-1, map);
    }

    // 第4，6个参数并没有用到，只是这样便于理解
    private TreeNode preIn(int[] pre, int pi, int pj, int[] in, int ni, int nj,
                           Map<Integer, Integer> map) {
        if (pi > pj) return null;
        TreeNode head = new TreeNode(pre[pi]);
        int index = map.get(pre[pi]);
        head.left = preIn(pre, pi+1, pi+index-ni, in, ni, index-1, map);
        head.right = preIn(pre, pi+index-ni+1,pj, in, index+1, nj, map);
        return head;
    }
}
