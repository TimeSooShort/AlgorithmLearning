package Offer;

/**
 * 即LeetCode91
 */
public class Offer46 {

    public static int solve(String s) {
        if (s == null || s.length() == 0) return -1;
        char[] arr = s.toCharArray();
        return process(arr, 0);
    }

    private static int process(char[] arr, int index) {
        if (index >= arr.length) return 1;
        if (arr[index] == '0') return 0;
        if (index+1 < arr.length && arr[index+1] == '0') {
            if (arr[index]-'0' > 2 || index+2 < arr.length && arr[index+2] == '0') return 0;
            else return process(arr, index+2);
        }
        int res = 0;
        res += process(arr, index+1);
        if (index+1 < arr.length && arr[index]-'0' <= 2
                && (arr[index]-'0')*10+(arr[index+1]-'0') <= 26) {
            res += process(arr, index+2);
        }
        return res;
    }

    // 动态规划
    public static int solve2(String s) {
        if (s == null || s.length() == 0) return -1;
        int ln = s.length();
        int[] dp = new int[ln+1];
        dp[ln] = 1;
        dp[ln-1] = s.charAt(ln-1) == '0' ? 0 : 1;
        for (int i = ln-2; i >= 0; i--) {
            if (s.charAt(i) == '0') dp[i] = 0;
            else {
                if (Integer.parseInt(s.substring(i, i+2)) <= 26)
                    dp[i] = dp[i+1] + dp[i+2];
                else
                    dp[i] = dp[i+1];
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println(solve("110"));
    }
}
