package Offer;

/**
 * 丑数
 */
public class Offer49 {
    public static int solve(int n) {
        if (n < 6) return n;
        int[] dp = new int[n];
        dp[0] = 1;
        int i2 = 0, i3 = 0, i5 = 0;
        for (int i = 1; i < n; i++) {
            int next2 = dp[i2]*2, next3 = dp[i3]*3, next5 = dp[i5]*5;
            dp[i] = Math.min(next2, Math.min(next3, next5));
            if (dp[i] == next2) i2++;
            if (dp[i] == next3) i3++;
            if (dp[i] == next5) i5++;
        }
        return dp[n-1];
    }
}
