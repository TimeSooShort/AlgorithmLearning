package LeetCode.dp;

import java.util.Arrays;

/**
 * 最长递增子序列
 */
public class LeetCode300 {

    public static int lengthOfLIS(int[] nums) {
        // if (nums.length == 0) return 0;
        // int[] dp = new int[nums.length];
        // for (int i = 0; i < nums.length; i++) {
        //     dp[i] = 1;
        //     for (int j = 0; j < i; j++) {
        //         if (nums[i] > nums[j]) {
        //             dp[i] = Math.max(dp[i], dp[j]+1);
        //         }
        //     }
        // }
        // int max = dp[0];
        // for (int i = 1; i < nums.length; i++) {
        //     if (dp[i] > max) max = dp[i];
        // }
        // return max;

        //定义一个数组 ends，ends[ i ] 表示长度为 i + 1 的递增子序列的最小值，
        // 这个最小值指的是同样长度的序列组合中，最后一个元素值最小的那个。
        // 由此可知 ends 数组还是递增的，那么在查找时就可使用二分法，从而将时间复杂度降低。
        //我们会遍历 nums 数组，将当前元素与 ends 中元素进行比较，
        // 当 ends[ i ] < num <= ends[ i+1 ] 时，ends[ i + 1 ] = num。
        if (nums.length == 0) return 0;
        int[] ends = new int[nums.length];
        ends[0] = nums[0];
        int right = 0;
        int l, m, r;
        for (int num : nums) {
            l = 0;
            r = right;
            while (l <= r) {
                m = l + (r - l) / 2;
                if (num > ends[m]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            right = Math.max(l, right);
            ends[l] = num;
        }
        return right+1;
    }

    public static void main(String[] args) {
        int[] num = {2,5,7,2,5,6,3,9,0,1,4,8};
        lengthOfLIS(num);
    }
}
