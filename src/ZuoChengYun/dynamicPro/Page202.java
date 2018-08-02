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

    // 优化时间复杂度，O（NlogN）
    // ends[i]数组代表长度为i+1的子序列的最小值，求dp[i]就在ends[]数组中二分查找下界l，
    // dp[i] = l+1; ends[l] = arr[i]
    // 之所以能用二分，是因为ends数组是有序的，ends[i] > ends[i-1]，为什么？
    // 因为这是ends[]定义决定的

    public int[] solve2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[] dp = getDp2(arr);
        return getMaxFromDp(dp, arr);
    }

    private int[] getDp2(int[] arr) {
        int[] dp = new int[arr.length];
        int[] ends = new int[arr.length];
        int right = 0; // 代表ends[0..right]已计算出结果,ends[right+1....arr.length-1] = 0;
        dp[0] = 1;
        ends[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            int l = 0;
            int r = right;
            while (l <= r) {
                int m = l+(r-l)/2;
                if (arr[i] > ends[m]) {
                    l = m + 1;
                }else {
                    l = m+1;
                }
            }
            right = Math.max(right, l);
            ends[l] = arr[i];
            dp[i] = l+1;
        }
        return dp;
    }

    public static void main(String[] args) {
        int[] arr = {2,1,5,3,6,4,8,9,7};
        Page202 instance = new Page202();
        int[] res = instance.solve(arr);
        System.out.println(Arrays.toString(res));
        int[] res21 = instance.solve2(arr);
        System.out.println(Arrays.toString(res21));

        int[] arr2 = {3};
        int[] res2 = instance.solve(arr2);
        System.out.println(Arrays.toString(res2));
        int[] res22 = instance.solve2(arr2);
        System.out.println(Arrays.toString(res22));
    }
}
