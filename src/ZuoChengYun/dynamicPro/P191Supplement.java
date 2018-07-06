package ZuoChengYun.dynamicPro;

/**
 * 找零钱问题：arr数组中都为正数
 * 不同的值代表不同面值大小的货币，需要找的钱为aim，问组成aim的最少货币数
 * 如{5，2，5, 3},aim = 10,则返回4; aim = 0则返回0，代表不用找钱；找不开则返回-1
 *
 * dp[i][j]；代表arr[0....i]中组成j所需的最小张数
 *
 * dp[i][j]的值两种情况：1，等于dp[i-1][j]
 * 2，等于dp[i-1][j-arr[i]] + 1前提是j - arr[i] > 0，否则等于dp[i-1][j]。
 *
 * 前一题 (Page191) 我们推到得
 * dp[i][j]=dp[i][j-arr[i]] + 1，不同于这一题，因为前一题允许重复，意味着你可以用arr[i]去组成j-arr[i]
 * 而该题不允许重复使用。
 */
public class P191Supplement {

    //时间复杂度为O（N * aim)，空间复杂度为O（N * aim)
    public int solve(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return -1;
        }
        int[][] dp = new int[arr.length][aim+1];

        for (int i = 1; i <= aim; i++) {
            if (i == arr[0]) dp[0][i] = 1;
            else dp[0][i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) {
                int t = Integer.MAX_VALUE;
                if (j-arr[i] >= 0 && dp[i-1][j-arr[i]] != Integer.MAX_VALUE) {
                    t = dp[i-1][j-arr[i]] + 1;
                }
                dp[i][j] = Math.min(dp[i-1][j], t);
            }
        }
        return dp[arr.length-1][aim] == Integer.MAX_VALUE ? -1 : dp[arr.length-1][aim];
    }

    public int solve2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return -1;
        }
        int[] dp = new int[aim+1];
        dp[0] = 0;

        for (int i = 1; i <= aim; i++) {
            if (i == arr[0]) dp[i] = 1;
            else dp[i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = aim; j >= 1 ; j--) {  //要从后往前，因为以来的是i-1是dp[]
                int t = Integer.MAX_VALUE;
                if (j-arr[i] >= 0 && dp[j-arr[i]] != Integer.MAX_VALUE) {
                    t = dp[j-arr[i]] + 1;
                }
                dp[j] = Math.min(t, dp[j]);
            }
        }
        return dp[aim] == Integer.MAX_VALUE ? -1 : dp[aim];
    }

    public static void main(String[] args) {
        P191Supplement instance = new P191Supplement();
        int[] arr = {5,2,5,3};
        System.out.println(instance.solve2(arr, 5));
        System.out.println(instance.solve2(arr, 10));
        System.out.println(instance.solve2(arr, 11));
        System.out.println(instance.solve2(arr, 12));
        System.out.println(instance.solve2(arr, 17));
        System.out.println(instance.solve2(arr, 0));
        System.out.println(instance.solve2(arr, 1));
    }
}
