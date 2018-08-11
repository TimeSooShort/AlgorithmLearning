package LeetCode.backtacking;

public class LeetCode10 {

    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        char[] sArr = s.toCharArray();
        char[] pArr = p.toCharArray();
        boolean[][] dp = new boolean[sArr.length+1][pArr.length+1];

        dp[0][0] = true;
        for (int i = 1; i <= pArr.length; i++) {
            if (pArr[i-1] == '*' && i >= 2) {
                dp[0][i] = dp[0][i-2];
            }
        }

        for (int i = 1; i <= sArr.length; i++) {
            for (int j = 1; j <= pArr.length; j++) {
                if (pArr[j-1] != '*') {
                    dp[i][j] = (pArr[j-1] == sArr[i-1] || pArr[j-1] == '.') && dp[i-1][j-1];
                }else {
                    if (j >= 2){
                        dp[i][j] = dp[i][j-2] || (dp[i-1][j] && (pArr[j-2] == sArr[i-1] || pArr[j-2] == '.'));
                    }
                }
            }
        }
        return dp[sArr.length][pArr.length];
    }

    public static void main(String[] args) {
        LeetCode10 instance = new LeetCode10();
        String s = "aa";
        String p = "a*";
        System.out.println(instance.isMatch(s, p));
    }
}
