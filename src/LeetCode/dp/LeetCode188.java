package LeetCode.dp;

import java.util.Arrays;

public class LeetCode188 {
    public int maxProfit(int k, int[] prices) {
        if (prices == null || k == 0) return 0;
        // 为了防止k过大导致的Memory Limit Exceeded
        // 数组可以看成是升升降降的曲线
        // 当k大于等于数组一半时，所谓最大利润就是所有升序曲线高度和
        if (k >= prices.length/2) return quick(prices);

        int[] buy = new int[k];
        int[] sell = new int[k];
        Arrays.fill(buy, Integer.MIN_VALUE);
        for(int price : prices) {
            sell[0] = Math.max(sell[0], buy[0]+price);
            buy[0] = Math.max(buy[0], -price);
            for(int i = 1; i < k; i++) {
                sell[i] = Math.max(sell[i], buy[i]+price);
                buy[i] = Math.max(buy[i], sell[i-1]-price);
            }
        }
        return sell[k-1];
    }

    private int quick(int[] prices) {
        int profit = 0, len = prices.length;
        for(int i = 1; i < len; i++) {
            if (prices[i] > prices[i-1]) profit += prices[i]-prices[i-1];
        }
        return profit;
    }
}
