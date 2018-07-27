package LeetCode.dp;

public class LeetCode62 {

    public int uniquePaths(int m, int n) {

        int[][] dp = new int[m][n];
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    // 空间压缩
    public int uniquePaths2(int m, int n) {
        int[] dp;
        if (m >= n) {
            dp = new int[n];
            for (int i = 0; i < n; i++) {
                dp[i] = 1;
            }
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    dp[j] = dp[j-1] + dp[j];
                }
            }
            return dp[n-1];
        }else {
            dp = new int[m];
            for (int i = 0; i < m; i++) {
                dp[i] = 1;
            }
            for (int i = 1; i < n; i++) {
                for (int j = 1; j < m; j++) {
                    dp[j] = dp[j-1] + dp[j];
                }
            }
            return dp[m-1];
        }

    }

    // 优化空间压缩的重复逻辑
    public int uniquePaths3(int m, int n) {
        if (m < n) {
            int temp = m;
            m = n;
            n = temp;
        }
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j-1] + dp[j];
            }
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
        LeetCode62 instance = new LeetCode62();
        System.out.println(instance.uniquePaths(3,2));
        System.out.println(instance.uniquePaths(7,3));

        System.out.println(instance.uniquePaths2(3,2));
        System.out.println(instance.uniquePaths2(7,3));

        System.out.println(instance.uniquePaths3(3,2));
        System.out.println(instance.uniquePaths3(7,3));
    }
}
