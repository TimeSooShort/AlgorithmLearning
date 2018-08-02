package LeetCode.dp.BestBuySell;

/**
 *   Best Time to Buy and Sell Stock系列共有四题
 */
public class LeetCode122 {

    // 动态规划解法；buy[i]：代表到i天为止，以buy行为结尾的最大利润；sell[i]与rest[i]类似
    // buy[i] = max{ buy[i-1], sell[i-1]-price}
    // sell[i] = max{ sell[i-1], buy[i-1]+price
    public int maxProfit(int[] prices) {
        int buy = Integer.MIN_VALUE, sell = 0;
        for (int price : prices) {
            int pre_buy = buy;
            buy = Math.max(buy, sell-price);
            sell = Math.max(sell, pre_buy+price);
        }
        return sell;
    }

    // 仔细分析这题，发现其实题目求的是数组中各个递增子数组的最大差值的和
    public int maxProfit2(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i-1]) res += prices[i]-prices[i-1];
        }
        return res;
    }
}
