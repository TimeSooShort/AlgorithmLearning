package LeetCode.dp;

/**
 * 最长递增子序列
 */
public class LeetCode300 {

    public int lengthOfLIS(int[] nums) {
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
}
