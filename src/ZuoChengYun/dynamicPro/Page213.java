package ZuoChengYun.dynamicPro;

/**
 * 最长公共子串
 */
public class Page213 {

    // 时间与空间复杂度都为：O(M * N)
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

        return s1.substring(endIndex-max+1, endIndex+1);
    }

    // dp[i][j]代表以s1Arr[i] 与 s2Arr[j]结尾的最长公共子串
    // 若相等则dp[i][j] = dp[i-1][j-1] + 1; 否则等于0
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

    // 优化, 使空间复杂度O（1）
    public String solve2(String s1,String s2) {
        if (s1 == null || s2 == null || s1.equals("") || s2.equals("")) {
            return null;
        }
        char[] s1Arr = s1.toCharArray();
        char[] s2Arr = s2.toCharArray();

        int maxEnd = 0; // 记录s1最大公共子串的结尾位置
        int maxLen = 0; // 子串长度最大值
        int col = 0, row = s1Arr.length-1; // 代表斜线开始的位置

        // dp[i][j]代表以s1Arr[i] 与 s2Arr[j]结尾的最长公共子串
        // 优化方法是并不采用二维数组，用一个指针current来代替

        while (col < s2Arr.length) {
            int i = row, j = col;
            int current = 0;
            while (i < s1Arr.length && j < s2Arr.length) {
                if (s1Arr[i] == s2Arr[j]) {
                    current += 1;
                    if (current > maxLen) {
                        maxLen = current;
                        maxEnd = i;
                    }
                }else {
                    current = 0;
                }
                i++;
                j++;
            }
            if (row == 0) {
                col++;
            }else {
                row--;
            }
        }

        if (maxLen == 0) return "没有公共子串";

        return s1.substring(maxEnd-maxLen+1, maxEnd+1);
    }

    public static void main(String[] args) {
        String s1 = "1ab2345vf";
        String s2 = "1ba54gf2345vgfab2345";
        Page213 instance = new Page213();
        System.out.println(instance.solve(s1, s2));
        System.out.println(instance.solve2(s1, s2));

        String s3 = "asbfe346";
        String s4 = "poi901yqw";

        System.out.println(instance.solve(s3, s4));
        System.out.println(instance.solve2(s3, s4));
    }
}
