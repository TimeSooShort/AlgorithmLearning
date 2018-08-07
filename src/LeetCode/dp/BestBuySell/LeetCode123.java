package LeetCode.dp.BestBuySell;

import java.util.Arrays;

public class LeetCode123 {

    // 动态规划dp[i][j]表示在j天完成的第i次交易
    // 时间复杂度为O（kn^2)，空间复杂度为o(kn)
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int[][] dp = new int[3][prices.length];
        for (int i = 1; i < 3; i++) {
            int max = dp[i][0]-prices[0];
            for (int j = 1; j < prices.length; j++) {
                for (int k = 0; k < j; k++) {
                    max = Math.max(max, dp[i-1][k]-prices[k+1]);
                }
                dp[i][j] = Math.max(dp[i][j-1], prices[j]+max);
            }
        }
        return dp[2][prices.length-1];
    }

    // 使用变量对上面进行优化，使时间与空间复杂度为O（kn）
    public int maxProfit2(int[] prices) {
        if (prices.length == 0) return 0;
        int[][] dp = new int[3][prices.length];
        for (int i = 1; i < 3; i++) {
            int max = dp[i][0]-prices[0];
            for (int j = 1; j < prices.length; j++) {
                dp[i][j] = Math.max(dp[i][j-1], prices[j]+max);
                max = Math.max(max, dp[i-1][j]-prices[j]);
            }
        }
        return dp[2][prices.length-1];
    }

    // 空间压缩
    public int maxProfit3(int[] prices) {
        if (prices.length == 0) return 0;
        int[] dp = new int[3];
        int[] store = new int[3];
        Arrays.fill(store, -prices[0]);
        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j < 3; j++) {
                store[j] = Math.max(store[j], dp[j-1]-prices[i]);
                dp[j] = Math.max(dp[j], prices[i]+store[j]);
            }
        }
        return dp[2];
    }


        public static void main(String[] args) {
        LeetCode123 instance = new LeetCode123();
        int[] arr = {3,3,5,0,0,3,1,4};
        int[] arr1 = {1,2,3,4,5};
        System.out.println(instance.maxProfit2(arr1));
        System.out.println(instance.maxProfit3(arr1));
    }
}
