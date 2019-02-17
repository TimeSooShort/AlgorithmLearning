package LeetCode.dp;

public class LeetCode343 {
    public int integerBreak(int n) {
        if (n < 2) return 0;
        int[] dp = new int[n+1];
        dp[1] = 1;
        for(int i = 2; i <= n; i++) {
            int max = 0;
            for(int j = i-1; j >= (i+1)/2; j--) {
                if (j < 4 && i-j < 4) max = Math.max(max, j*(i-j));
                else if (j < 4) max = Math.max(max, j * dp[i-j]);
                else if (i-j < 4) max = Math.max(max, dp[j]*(i-j));
                else max = Math.max(max, dp[j]*dp[i-j]);
            }
            dp[i] = max;
        }
        return dp[n];
    }
}
