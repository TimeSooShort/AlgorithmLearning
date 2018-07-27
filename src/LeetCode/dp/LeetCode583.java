package LeetCode.dp;

/**
 * 该题就是求出最长公共子序列
 */
public class LeetCode583 {

    public int minDistance(String word1, String word2) {
        if (word1.length() == 0 && word2.length() == 0) {
            return 0;
        }
        if (word1.length() == 0) {
            return word2.length();
        }else if (word2.length() == 0) {
            return word1.length();
        }

        char[] w1 = word1.toCharArray();
        char[] w2 = word2.toCharArray();
        int[][] dp = new int[w1.length][w2.length];

        dp[0][0] = w1[0] == w2[0] ? 1 : 0;
        for (int i = 1; i < w2.length; i++) {
            if (w1[0] == w2[i]) {
                dp[0][i] = 1;
                continue;
            }
            dp[0][i] = dp[0][i-1];
        }

        for (int i = 1; i < w1.length; i++) {
            if (w2[0] == w1[i]) {
                dp[i][0] = 1;
                continue;
            }
            dp[i][0] = dp[i-1][0];
        }

        for (int i = 1; i < w1.length; i++) {
            for (int j = 1; j < w2.length; j++) {
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                if (w1[i] == w2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1]+1);
                }
            }
        }

        return w1.length+w2.length-2*dp[w1.length-1][w2.length-1];
    }

    // ===================================================================================

    // 空间压缩
    public int minDistance2(String word1, String word2) {
        if (word1.length() == 0 && word2.length() == 0) {
            return 0;
        }
        if (word1.length() == 0) {
            return word2.length();
        }else if (word2.length() == 0) {
            return word1.length();
        }

        char[] w1 = word1.toCharArray();
        char[] w2 = word2.toCharArray();

        int[] dp;
        if (w1.length >= w2.length) {
            dp = new int[w2.length];

            dp[0] = w1[0] == w2[0] ? 1 : 0;
            for (int i = 1; i < w2.length; i++) {
                dp[i] = Math.max(dp[i-1], w1[0] == w2[i] ? 1 : 0);
            }

            for (int i = 1; i < w1.length; i++) {
                int last = dp[0];
                dp[0] = Math.max(dp[0], w2[0] == w1[i] ? 1 : 0);
                for (int j = 1; j < w2.length; j++) {
                    int temp = dp[j];
                    dp[j] = Math.max(dp[j], dp[j-1]);
                    if (w1[i] == w2[j]) {
                        dp[j] = Math.max(dp[j], last+1);
                    }
                    last = temp;
                }
            }
            return w1.length+w2.length-2*dp[w2.length-1];
        }else {
            dp = new int[w1.length];

            dp[0] = w1[0] == w2[0] ? 1 : 0;
            for (int i = 1; i < w1.length; i++) {
                dp[i] = Math.max(dp[i-1], w1[i] == w2[0] ? 1 : 0);
            }
            for (int i = 1; i < w2.length; i++) {
                int last = dp[0];
                dp[0] = Math.max(dp[0], w1[0] == w2[i] ? 1 : 0);
                for (int j = 1; j < w1.length; j++) {
                    int temp = dp[j];
                    dp[j] = Math.max(dp[j], dp[j-1]);
                    if (w1[j] == w2[i]) {
                        dp[j] = Math.max(dp[j], last+1);
                    }
                    last = temp;
                }
            }
            return w1.length+w2.length-2*dp[w1.length-1];
        }
    }

    // ===================================================================================

    // 对上面空间压缩代码进行优化
    public int minDistance3(String word1, String word2) {
        if (word1.length() == 0 && word2.length() == 0) {
            return 0;
        }
        if (word1.length() == 0) {
            return word2.length();
        }else if (word2.length() == 0) {
            return word1.length();
        }

        char[] w1 = word1.toCharArray();
        char[] w2 = word2.toCharArray();

        if (w1.length < w2.length) {
            char[] temp = w1;
            w1 = w2;
            w2 = temp;
        }
        int[] dp = new int[w2.length];
        dp[0] = w1[0] == w2[0] ? 1 : 0;
        for (int i = 1; i < w2.length; i++) {
            dp[i] = Math.max(dp[i-1], w1[0] == w2[i] ? 1 : 0);
        }
        for (int i = 1; i < w1.length; i++) {
            int last = dp[0];
            dp[0] = Math.max(dp[0], w2[0] == w1[i] ? 1 : 0);
            for (int j = 1; j < w2.length; j++) {
                int temp = dp[j];
                dp[j] = Math.max(dp[j], dp[j-1]);
                if (w1[i] == w2[j]) {
                    dp[j] = Math.max(dp[j], last+1);
                }
                last = temp;
            }
        }
        return w1.length+w2.length-2*dp[w2.length-1];
    }

    public static void main(String[] args) {
        LeetCode583 instance = new LeetCode583();
        String w1 = "horse";
        String w2 = "ros";
        System.out.println(instance.minDistance(w2,w1));
        System.out.println(instance.minDistance2(w2,w1));
        System.out.println(instance.minDistance3(w2,w1));
    }
}
