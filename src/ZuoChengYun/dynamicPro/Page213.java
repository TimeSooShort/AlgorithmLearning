package ZuoChengYun.dynamicPro;

/**
 * 最长公共子串
 */
public class Page213 {

    public String solve(String s1, String s2) {
        if (s1 == null || s2 == null || s1.equals("") || s2.equals("")) {
            return "";
        }
        char[] s1Arr = s1.toCharArray();
        char[] s2Arr = s2.toCharArray();
        int[][] dp = getDp(s1Arr, s2Arr);
        int max = 0;
        int endIndex = 0;
        for (int i = 0; i < s1Arr.length; i++) {
            for (int j = 0; j < s2Arr.length; j++) {
                if (dp[i][j] > max) {
                    max = dp[i][j];
                    endIndex = i;
                }
            }
        }
        if (max == 0) return "没有公共子串";
        char[] res = new char[max];
        for (int i = max-1; i >= 0; i--) {
            res[i] = s1Arr[endIndex--];
        }
        return String.valueOf(res);
    }

    // dp[i][j]代表以s1Arr[i] 与 s2Arr[j]结尾的最长公共子串
    private int[][] getDp(char[] s1Arr, char[] s2Arr) {
        int[][] dp = new int[s1Arr.length][s2Arr.length];

        for (int i = 0; i < s2Arr.length; i++) {
            if (s1Arr[0] == s2Arr[i]) dp[0][i] = 1;
        }

        for (int i = 1; i < s1Arr.length; i++) {
            if (s2Arr[0] == s1Arr[i]) dp[i][0] = 1;
        }

        for (int i = 1; i < s1Arr.length; i++) {
            for (int j = 1; j < s2Arr.length; j++) {
                if (s1Arr[i] != s2Arr[j]) {
                    dp[i][j] = 0;
                }else {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
            }
        }

        return dp;
    }

    public static void main(String[] args) {
        String s1 = "1ab2345vf";
        String s2 = "1ba54gf2345vgfab2345";
        Page213 instance = new Page213();
        System.out.println(instance.solve(s1, s2));
    }
}
