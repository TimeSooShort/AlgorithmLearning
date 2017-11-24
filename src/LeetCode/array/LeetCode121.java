package LeetCode.array;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
   If you were only permitted to complete at most one transaction 
   (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 * @author Administrator
 *
 */
public class LeetCode121 {

/**
	public int maxProfit(int[] prices) {
        int maxProfile = 0;
        for(int i = 0; i < prices.length-1; i++) {
            int buy = prices[i];
            for(int j = i+1; j < prices.length; j++) {
                int profile = prices[j] - buy;
                if (maxProfile < profile) {
                    maxProfile = profile;
                }
            }
        }
        return maxProfile;
    }
*/
	
	public int maxProfit(int[] prices) {
        int max = 0;
        int cur = 0;
        for(int i =  1; i < prices.length; i++) {
            cur = Math.max(0, cur += prices[i] - prices[i-1]);
            max = Math.max(cur, max);
        }
        return max;
    }
}
