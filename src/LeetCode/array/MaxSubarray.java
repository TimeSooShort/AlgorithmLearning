package LeetCode.array;

/**
 * 给定一个数组array，返回子数组的最大累加和。
 * Kadane's Algorithm
 * @author Administrator
 *
 */
public class MaxSubarray {

	public int maxSubarray(int[] array) {
		int max = 0;
		int cur = 0;
		for(int i = 0; i < array.length; i++) {
			cur += cur + array[i];
			max = Math.max(cur, max);
			cur = cur < 0 ? 0 : cur;
		}
		return max;
	}

}
