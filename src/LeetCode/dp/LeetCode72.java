package LeetCode.dp;

public class LeetCode72 {

    public int minDistance(String word1, String word2) {

        char[] w1 = word1.toCharArray();
        char[] w2 = word2.toCharArray();
        int[][] dp = new int[w1.length+1][w2.length+1];

        dp[0][0] = 0;
        for (int i = 1; i <= w2.length; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= w1.length; i++) {
            dp[i][0] = i;
        }

        for (int i = 1; i <= w1.length; i++) {
            for (int j = 1; j <= w2.length; j++) {
                int min = Math.min(dp[i-1][j]+1, dp[i][j-1]+1);
                if (w1[i-1] == w2[j-1]) min = Math.min(min, dp[i-1][j-1]);
                else min = Math.min(min, dp[i-1][j-1]+1);
                dp[i][j] = min;
            }
        }
        return dp[w1.length][w2.length];
    }

    public static void main(String[] args) {
        LeetCode72 instance = new LeetCode72();
        System.out.println(instance.minDistance("horse", "ros"));
    }
}
