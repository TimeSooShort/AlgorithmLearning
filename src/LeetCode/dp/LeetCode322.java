package LeetCode.dp;

public class LeetCode322 {
    // 完全背包
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount == 0) return 0;
        int[] dp = new int[amount+1];
        for (int coin : coins) {
            for(int j = coin; j <= amount; j++) {
                if (j == coin) dp[j] = 1;
                else if (dp[j-coin] != 0) {
                    if(dp[j] == 0) dp[j] = dp[j-coin]+1;
                    else dp[j] = Math.min(dp[j],dp[j-coin]+1);
                }
            }
        }
        return dp[amount] == 0 ? -1 : dp[amount];
    }
}
