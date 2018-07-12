package ZuoChengYun.dynamicPro;

import java.util.Arrays;

/**
 * 最长递增子序列
 */
public class Page202 {

    // 采用辅助数组dp，dp[i]代表以arr[i]为结尾的最长递增子序列的长度
    // 所以dp[0] = 1; dp[i] = max{ dp[j] } + 1 (0<=j<i && arr[j]<arr[i])
    // 时间复杂度为O(N^2)
    public int[] solve(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[] dp = getDP(arr);
        return getMaxFromDp(dp, arr);
    }

    private int[] getDP(int[] arr) {
        int[] dp = new int[arr.length];

        dp[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            int max = 0;
            for (int j = i-1; j >= 0; j--) {
                if (arr[j] < arr[i]) {
                    max = Math.max(max, dp[j]+1);
                }
            }
            dp[i] = max == 0 ? 1 : max;
        }
        return dp;
    }

    private int[] getMaxFromDp(int[] dp, int[] arr) {
        int maxSub = dp[0];
        int maxIndex = 0;
        for (int i = 1; i < dp.length; i++) {
            if (dp[i] > maxSub) {
                maxSub = dp[i];
                maxIndex = i;
            }
        }

        int[] res = new int[maxSub];
        res[--maxSub] = arr[maxIndex];
        for (int j = maxIndex-1; j >= 0; j--) {
            if (arr[j] < arr[maxIndex]) {
                maxIndex= j;
                res[--maxSub] = arr[maxIndex];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {2,1,5,3,6,4,8,9,7};
        Page202 instance = new Page202();
        int[] res = instance.solve(arr);
        System.out.println(Arrays.toString(res));

        int[] arr2 = {3};
        int[] res2 = instance.solve(arr2);
        System.out.println(Arrays.toString(res2));
    }
}
