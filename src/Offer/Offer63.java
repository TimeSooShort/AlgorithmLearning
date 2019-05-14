package Offer;

/**
 * LeetCode121题
 * 统计股票买卖文章https://blog.csdn.net/sinat_34976604/article/details/88584674
 */
public class Offer63 {

    public static int maxProfit(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        int[] dp = new int[arr.length-1];
        dp[0] = arr[1]-arr[0];
        for (int i = 1; i < arr.length-1; i++) {
            dp[i] = Math.max(dp[i-1], arr[i+1]-arr[0]);
        }
        for (int row = 1; row < arr.length-1; row++) {
            dp[row-1] = Integer.MIN_VALUE;
            for (int column = row; column < arr.length-1; column++) {
                int max = Math.max(arr[column+1]-arr[row], dp[column-1]);
                dp[column] = Math.max(max, dp[column]);
            }
        }
        return dp[arr.length-2];
    }

    public static int maxProfit2(int[] arr) {
        if (arr == null || arr.length < 2) return -1;
        // 价格数组可以看成是升升降降的子数组组成，
        // 从最初的升序子数组开始，往后遇到升的就将高度相加，降的就减去相应高度，
        // 这样就能统计出这一段的最低与最高的差值，
        // 直到小于0代表这一段的结束，从下一个升序子数组开始继续统计
        int sum = 0, max = 0;
        for (int i = 1; i < arr.length; i++) {
            sum = Math.max(0, sum + arr[i] - arr[i - 1]);
            max = Math.max(sum, max);
        }
        return max;
    }

    public static int maxProfit3(int[] arr) {
        if (arr == null || arr.length < 2) return -1;
        // min 表示i之前最低的价格，arr[i]-min 代表i天卖出多能获得的最大利润
        int max = 0, min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(max, arr[i]-min);
            min = Math.min(min, arr[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {9,11,8,5,7,12,16,14};
        int[] arr2 = {7,1,5,3,6,4};
        int[] arr3 = {7,6,5,4,3,2,1};
        System.out.println(maxProfit3(arr));
        System.out.println(maxProfit3(arr2));
        System.out.println(maxProfit3(arr3));
    }
}
