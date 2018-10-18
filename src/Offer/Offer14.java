package Offer;

public class Offer14 {

    public int solve(int n) {
        if (n < 2) return 0;
        else if (n == 2) return 1;
        else if (n == 3) return 2;

        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 4; i <= n; i++) {
            int max = 0;
            for (int j = 1; j <= i/2; j++) {
                int product = dp[j] * dp[i-j];
                if (max < product) max = product;
            }
            dp[i] = max;
        }
        return dp[n];
    }

    // 解法二：贪婪算法
    public int solve2(int n) {
        if (n < 2) return 0;
        else if (n == 2) return 1;
        else if (n == 3) return 2;

        int ln3 = n/3;
        if ((n%3) == 1) ln3--;
        int ln2 = (n - ln3*3)/2;
        return (int) (Math.pow(3, ln3) * Math.pow(2, ln2));
    }
}
