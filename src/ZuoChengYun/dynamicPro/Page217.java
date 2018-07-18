package ZuoChengYun.dynamicPro;

/**
 * 最小编辑代价
 */
public class Page217 {

    /**
     * s1变为s2利用插入，删除，替换操作所要花费的最小代价；显然时间与空间复杂度都为O（M * N)
     * @param s1 字符串
     * @param s2 字符串
     * @param ic 插入代价
     * @param dc 删除代价
     * @param rc 替换代价
     * @return 返回最小代价
     */
    public int solve(String s1, String s2, int ic, int dc, int rc) {
        if (s1 == null || s2 == null) {
            return -1;
        }
        char[] s1Arr = s1.toCharArray();
        char[] s2Arr = s2.toCharArray();

        // dp[i][j] 代表s1[0...i]变为s2[0...j]的最小代价
        // 在s1与s2前加''来形成dp，这样是为了更容易的确定dp第一行与第一列的值
        int[][] dp = new int[s1Arr.length+1][s2Arr.length+1];

        // s1[0] (即'') 变为s2[0....]的代价， 即dp的第一行
        for (int i = 0; i <= s2Arr.length; i++) {
            dp[0][i] = i * ic;
        }

        // s1[1...] 变为s2[0](即'')的代价， 即dp的第一列
        for (int i = 1; i <= s1Arr.length; i++) {
            dp[i][0] = i * dc;
        }

        for (int i = 1; i <= s1Arr.length; i++) {
            for (int j = 1; j <= s2Arr.length; j++) {
                int minC = dp[i-1][j] + dc;
                minC = Math.min(minC, dp[i][j-1] + ic);
                // 为什么要将这两种情况进行二选一？
                // 因为若相等则代价肯定小于交换，dp[i-1][j-1] + rc > dp[i-1][j-1]
                if (s1Arr[i-1] != s2Arr[j-1]) {
                    minC = Math.min(minC, dp[i-1][j-1] + rc);
                }else {
                    minC = Math.min(minC, dp[i-1][j-1]);
                }
                dp[i][j] = minC;
            }
        }
        return dp[s1Arr.length][s2Arr.length];
    }

    // 空间压缩，优化空间复杂度为 O（min{M, N}）
    public int solve2(String s1, String s2, int ic, int dc, int rc) {
        if (s1 == null || s2 == null) {
            return -1;
        }
        char[] s1Arr = s1.toCharArray();
        char[] s2Arr = s2.toCharArray();
        int[] dp;
        if (s1Arr.length >= s2Arr.length) {
            dp = new int[s2Arr.length+1];

            for (int i = 0; i <= s2Arr.length; i++) {
                dp[i] = i * ic;
            }

            for (int i = 1; i <= s1Arr.length; i++) {
                int pre = dp[0];
                dp[0] = i * dc;
                for (int j = 1; j <= s2Arr.length; j++) {
                    int minC = dp[j] + dc;
                    minC = Math.min(minC, dp[j-1] + ic);
                    if (s1Arr[i-1] == s2Arr[j-1]) {
                        minC = Math.min(minC, pre);
                    }else {
                        minC = Math.min(minC, pre + rc);
                    }
                    pre = dp[j];
                    dp[j] = minC;
                }
            }
            return dp[s2Arr.length];
        }else {
            dp = new int[s1Arr.length+1];

            for (int i = 0; i <= s1Arr.length; i++) {
                dp[i] = i * dc;
            }

            for (int i = 1; i <= s2Arr.length; i++) {
                int pre = dp[0];
                dp[0] = i * ic;
                for (int j = 1; j <= s1Arr.length; j++) {
                    int minC = dp[j-1] + dc;
                    minC = Math.min(minC, dp[j] + ic);
                    if (s1Arr[j-1] == s2Arr[i-1]) {
                        minC = Math.min(minC, pre);
                    }else {
                        minC = Math.min(minC, pre + rc);
                    }
                    pre = dp[j];
                    dp[j] = minC;
                }
            }
            return dp[s1Arr.length];
        }
    }

    public static void main(String[] args) {
        Page217 instance = new Page217();

        String s1 = "abc";
        String s2 = "adc";
        System.out.println(instance.solve(s1, s2, 5, 3, 2));
        System.out.println(instance.solve(s1, s2, 5, 3, 100));

        System.out.println(instance.solve2(s1, s2, 5, 3, 2));
        System.out.println(instance.solve2(s1, s2, 5, 3, 100));

        String s3 = "advance";
        String s4 = "advance";
        System.out.println(instance.solve(s3, s4, 5, 3, 2));
        System.out.println(instance.solve2(s3, s4, 5, 3, 2));

        String s5 = "acbjg89";
        String s6 = "cbvedg35hry";
        System.out.println(instance.solve(s5, s6, 5, 3, 2));
        System.out.println(instance.solve2(s5, s6, 5, 3, 2));
    }
}
