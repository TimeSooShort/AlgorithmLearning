package ZuoChengYun.dynamicPro;

/**
 * 字符串的交错组成;
 */
public class Page220 {

    public boolean solve(String s1, String s2, String aim) {
        if (s1 == null || s2 == null || aim == null) {
            return false;
        }
        if (aim.length() != s1.length() + s2.length()) {
            return false;
        }

        char[] s1Arr = s1.toCharArray();
        char[] s2Arr = s2.toCharArray();
        char[] aimArr = aim.toCharArray();

        // dp[i][j] 表示aim[i+j-1]能否被s1[0..i-1]与s2[0..j-1]交错组成
        // 跟上题一样，把dp大小设为[s1.length()+1][s2.length()+1]，与之前一些dp不同
        // 一方面字符串要考虑到空串问题，另一方面空串的引入也简化了dp第一行与第一列的情况
        // 而这种简化并不会影响结果，因为dp[i][j]的语义没变
        boolean[][] dp = new boolean[s1.length()+1][s2.length()+1];

        // s1,s2,aim皆为空串（ "" ）；之所以为true，是因为若aim不为空则在上面的长度判断就pass掉了
        // 所以在三者皆为空的情况下，会返回dp[0][0]
        dp[0][0] = true;

        // dp第一行，此时s1=""，即空串，dp[0][...]代表s2[..]与aim[..]是否相等
        for (int i = 1; i <= s2Arr.length; i++) {
            if (s2Arr[i-1] != aimArr[i-1]) {
                break;
            }
            dp[0][i] = true;
        }

        // dp第一列，此时s2=""，即空串，dp[..][0]代表s1[..]与aim[..]是否相等
        for (int i = 1; i <= s1Arr.length; i++) {
            if (s1Arr[i-1] != aimArr[i-1]) {
                break;
            }
            dp[i][0] = true;
        }

        // dp[i][j] : 1) dp[i-1][j] == true，若s1[i-1] == aim[i+j-1],则dp[i][j]为true；
        // 2）dp[i][j-1] == true, 若s2[j-1] == aim[i+j-1],则dp[i][j]为true；
        for (int i = 1; i <= s1Arr.length; i++) {
            for (int j = 1; j <= s2Arr.length; j++) {
                if (dp[i-1][j] && s1Arr[i-1] == aimArr[i+j-1]) {
                    dp[i][j] = true;
                }else if (dp[i][j-1] && s2Arr[j-1] == aimArr[i+j-1]) {
                    dp[i][j] = true;
                }
            }
        }

        return dp[s1Arr.length][s2Arr.length];
    }

    // ------------------------------------------------------------------------

    // 空间压缩，空间复杂度为O（min{M， N}）
    public boolean solve2(String s1, String s2, String aim) {
        if (s1 == null || s2 == null || aim == null) {
            return false;
        }

        if (s1.length() < s2.length()) {
            String temp = s1;
            s1 = s2;
            s2 = temp;
        }
        char[]  s1Arr = s1.toCharArray();
        char[] s2Arr = s2.toCharArray();
        char[] aimArr = aim.toCharArray();

        if (aimArr.length != s1Arr.length + s2Arr.length) {
            return false;
        }

        boolean[] dp = new boolean[s2Arr.length+1];

        dp[0] = true;
        for (int i = 1; i <= s2Arr.length; i++) {
            if (aimArr[i-1] != s2Arr[i-1]) {
                break;
            }
            dp[i] = true;
        }

        for (int i = 1; i <= s1Arr.length; i++) {
            if (dp[0] && s1Arr[i-1] == aimArr[i-1]) {
                dp[0] = true;
            }
            for (int j = 1; j <= s2Arr.length; j++) {
                if (dp[j-1] && s2Arr[j-1] == aimArr[i+j-1]) {
                    dp[j] = true;
                }else if (dp[j] && s1Arr[i-1] == aimArr[i+j-1]) {
                    dp[j] = true;
                }
            }
        }
        return dp[s2Arr.length];
    }

    public static void main(String[] args) {
        Page220 instance = new Page220();

        String s1 = "agsdyrsb";
        String s2 = "1sh875gh2";
        String aim = "ag1sshdy87r5ghsb2";

        System.out.println(instance.solve(s1, s2, aim));
        System.out.println(instance.solve2(s1, s2, aim));

        String s3 = "abgeo78";
        String s4 = "oiuoibz098761ag";
        String aim2 = "fixang098iujk356lxmn12";

        System.out.println(instance.solve(s1, s2, aim2));
        System.out.println(instance.solve2(s3, s4, aim2));
    }

}
