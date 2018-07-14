package ZuoChengYun.dynamicPro;

/**
 * 最长公共子序列
 */
public class Page210 {

    // 得到dp二维数组后如何得到子序列？dp[m][n]为最大子序列的大小值，
    // dp的值大致可理解为是左，上，左上值中最大的一个
    public String solve(String s1, String s2) {
        if (s1 == null || s2 == null || s1.equals("") || s2.equals("")) {
            return "";
        }
        char[] s1Arr = s1.toCharArray();
        char[] s2Arr = s2.toCharArray();
        int[][] dp = getDp(s1Arr, s2Arr);
        int m = s1Arr.length-1;
        int n = s2Arr.length-1;
        char[] res = new char[dp[m][n]];
        int index = res.length-1;
        while (index >= 0) {
            if (m > 0 && dp[m][n] == dp[m-1][n]) {
                m--;
            }else if (n > 0 && dp[m][n] == dp[m][n-1]) {
                n--;
            }else {
                res[index--] = s1Arr[m];
                m--;
                n--;
            }
        }
        return String.valueOf(res);
    }

    // dp[i][j] 表示s1Arr[0....i]与s2Arr[0....j]的最长公共子序列
    public int[][] getDp(char[] s1Arr, char[] s2Arr) {
        int[][] dp = new int[s1Arr.length][s2Arr.length];

        for (int i = 0; i < s2Arr.length; i++) {
            if (s1Arr[0] == s2Arr[i]) {
                for (; i < s2Arr.length; i++) {
                    dp[0][i] = 1;
                }
                break;
            }
        }

        for (int i = 1; i < s1Arr.length; i++) {
            if (s2Arr[0] == s1Arr[i]) {
                for (; i < s1Arr.length; i++) {
                    dp[i][0] = 1;
                }
                break;
            }
        }

        for (int i = 1; i < s1Arr.length; i++) {
            for (int j = 1; j < s2Arr.length; j++) {
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                if (s1Arr[i] == s2Arr[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1] + 1);
                }
            }
        }
        return dp;
    }

    public static void main(String[] args) {
        String s1 = "1a2c3d4b56";
        String s2 = "b1d23ca45b6a";
        Page210 instance = new Page210();
        System.out.println(instance.solve(s1,s2));

        String s3 = "j8d3o6soe9d4g8";
        String s4 = "f9a9slg1so04e8kg75i3o";
        System.out.println(instance.solve(s3,s4));
    }
}
