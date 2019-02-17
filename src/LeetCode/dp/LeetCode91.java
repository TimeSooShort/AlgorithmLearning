package LeetCode.dp;

public class LeetCode91 {

    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return -1;
        int ln = s.length();
        int[] dp = new int[ln+1];
        dp[ln] = 1; // dp[n]=1，处理最后一位是0的情况
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
}
