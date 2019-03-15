package LeetCode.dp;

/**
 * 分析后该题转化为：sum( p ) 代表正数的和，sum( n ) 代表负数的和，
 * 满足条件：sum( p ) = sum( n ) + S；这就和 416 题 分割等和子集 相同。
 */
public class LeetCode494 {

    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null) return 0;
        int sum = 0;
        for(int num : nums) sum += num;
        if (sum < S || ((sum+S) & 1) == 1) return 0;
        int target = (sum+S) >> 1;
        int[] dp = new int[target+1];
        dp[0] = 1;
        for(int num : nums) {
            for(int i = target; i >= num; i--) {
                dp[i] = dp[i] + dp[i-num];
            }
        }
        return dp[target];
    }

    //当然本题还可用 DFS，毕竟动态规划来源于暴力递归，是采用空间换时间的方式来节省提高效率。
    public int findTargetSumWays2(int[] nums, int S) {
        return findTargetSumWays(nums, 0, S);
    }

    private int findTargetSumWays(int[] nums, int start, int S) {
        if (start == nums.length) {
            return S == 0 ? 1 : 0;
        }
        // 每一步无非两种选择，正或负
        return findTargetSumWays(nums, start + 1, S + nums[start])
                + findTargetSumWays(nums, start + 1, S - nums[start]);
    }
}
