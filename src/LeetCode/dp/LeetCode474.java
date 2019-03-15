package LeetCode.dp;

public class LeetCode474 {
    public int findMaxForm(String[] strs, int m, int n) {
        if (strs == null || strs.length == 0) return 0;
        // dp[i][j] 代表 i-1个0，j-1个1 数组能够组成的最大个数
        int[][] dp = new int[m+1][n+1];
        for(String s : strs) {
            int zeros = 0, ones = 0;
            for(char c : s.toCharArray()) {
                if(c == '0') zeros++;
                else ones++;
            }
            for (int i = m; i >= zeros; i--) {
                for (int j = n; j >= ones; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-zeros][j-ones]+1);
                }
            }
        }
        return dp[m][n];
    }
}
