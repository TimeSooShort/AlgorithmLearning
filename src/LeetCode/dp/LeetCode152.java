package LeetCode.dp;

/**
 * Maximum Product Subarray: 一个数组，找出乘积最大的子数组
 */
public class LeetCode152 {

    public int maxProduct(int[] nums) {
        int res = nums[0];
        for (int i = 1, min = res, max = res; i < nums.length; i++) {
//            int temp = max;
//            max = Math.max(Math.max(max*nums[i], min*nums[i]), nums[i]);
//            min = Math.min(Math.min(temp*nums[i], min*nums[i]), nums[i]);

            if (nums[i] < 0) {
                int temp = max;
                max = min;
                min = temp;
            }
            max = Math.max(max*nums[i], nums[i]);
            min = Math.min(min*nums[i], nums[i]);

            if (max > res) {
                res = max;
            }
        }
        return res;
    }
}
