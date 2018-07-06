package ZuoChengYun.dynamicPro;

/**
 * 找零钱问题：arr数组中都为正数且不重复
 * 不同的值代表不同面值大小的货币，每种货币数量不限，需要找的钱为aim，问组成aim的最少货币数
 * 如{5，2，3},aim = 20,则返回4; aim = 0则返回0，代表不用找钱；找不开则返回-1
 *
 * dp[i][j]；代表arr[0....i]中组成j所需的最小张数
 *
 * 不需要arr[i]的值则 dp[i][j] = dp[i-1][j]；需要一张arr[i]，dp[i][j] = dp[i-1][j] + 1；.......
 * dp[i][j] = min{dp[i-1][j-k*arr[i]]+k}(k>=0)
 * dp[i][j] = min{dp[i-1][j], dp[i-1][j-x*arr[i]]+x}(x>=1)
 * = min{dp[i-1][j], dp[i-1][j-arr[i]-y*arr[i]]+y+1}(y>=0)
 *  而dp[i-1][j-arr[i]-y*arr[i]]+y+1 (y>=0) = dp[i][j-arr[i]]
 *  所以得dp[i][j] = min{dp[i-1][j], dp[i][j-arr[i]] (j >= arr[i])
 */
public class Page191 {

    //时间复杂度为O（N * aim)，空间复杂度为O（N * aim)
    public int solve(int[] arr, int aim) {

        if (arr == null || arr.length == 0 || aim < 0) {
            return -1;
        }

        int[][] dp = new int[arr.length][aim+1];

        //dp[0][1....aim]
        for (int i = 1; i <= aim; i++) {
            if (i%arr[0] == 0) {
                dp[0][i] = i/arr[0];
            }else {
                dp[0][i] = Integer.MAX_VALUE;
            }
        }

        //dp[0....N-1][0] = 0;
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) {
                int t = Integer.MAX_VALUE;
                if (j-arr[i] >= 0 && dp[i][j-arr[i]] != Integer.MAX_VALUE) {
                    t = dp[i][j-arr[i]] + 1;
                }
                dp[i][j] = Math.min(dp[i-1][j], t);
            }
        }
        return dp[arr.length-1][aim] == Integer.MAX_VALUE ? -1 : dp[arr.length-1][aim];
    }

    //空间压缩 空间复杂度为O(aim)
    public int solve2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return -1;
        }

        int[] dp = new int[aim+1];
        dp[0] = 0;
        for (int i = 1; i <= aim ; i++) {
            if (i % arr[0] == 0) {
                dp[i] = i/arr[0];
            }else {
                dp[i] = Integer.MAX_VALUE;
            }
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim ; j++) {
                int t = Integer.MAX_VALUE;
                if (j-arr[i] >= 0 && dp[j-arr[i]] != Integer.MAX_VALUE) {
                    t = dp[j-arr[i]] + 1;
                }
                dp[j] = Math.min(dp[j], t);
            }
        }

        return dp[aim] == Integer.MAX_VALUE ? -1 : dp[aim];
    }

    public static void main(String[] args) {
        Page191 instance = new Page191();
        int[] arr = {5,2,3};
        System.out.println(instance.solve2(arr, 5));
        System.out.println(instance.solve2(arr, 10));
        System.out.println(instance.solve2(arr, 11));
        System.out.println(instance.solve2(arr, 12));
        System.out.println(instance.solve2(arr, 17));
        System.out.println(instance.solve2(arr, 0));
        System.out.println(instance.solve2(arr, 1));
        int[] arr1 = {3,5};
        System.out.println(instance.solve2(arr1, 2));
    }
}
