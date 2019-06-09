package LeetCode.array;

import java.util.Arrays;

public class LeetCode910 {

    public int smallestRangeII(int[] A, int k) {
        int n = A.length;
        Arrays.sort(A); // 先排序
        int res = A[n-1]-A[0];

        // 首先 -k +k 可以转化为 0 2k
        // 全部加 0/2k 结果是不变的，那么剩下的可能就是某一位置i加2k时的结果
        // 最大值在A[i]+2k 与 A[ln-1] 中，最大的最小值在A[0]+2k 与 A[i+1] 中
        for(int i = 0; i < n-1; i++) {
            int max = Math.max(A[n-1], A[i]+2*k);
            int min = Math.min(A[0]+2*k, A[i+1]);
            res = Math.min(res, max-min);
        }
        return res;
    }
}
