package LeetCode.dp;

import java.util.Arrays;

public class LeetCode279 {
    public static int numSquares(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = 1;
        for(int i = 2; i <= n; i++) {
            for(int j = 1; j*j <= n; j++) {
                dp[i] = Math.min(dp[i], dp[i-j]+1);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(numSquares(4));
    }
}
