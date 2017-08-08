package Other;

import java.util.HashMap;

/**
 * 无需数组arr，元素可正，可负，可0，给定一数k，求arr所有子数组中累加和为k的最长子数组的长度
 * 该题还可以变身为：
 * 	1：无需数组arr，元素可正，可负，可0，求arr所有子数组中正数，负数相等的最长子数组长度
 *    思路：将正变为1，负数变为-1，求累加和为0的最长子数组长度
 *  2：无需数组arr，元素为1或0， 求1，0个数相等的最长子数组长度
 *    思路：将0变为-1， 求累加和为0的最长子数组长度
 * @author Administrator
 *解题思路：一个数组假设第i位的总和为sum（i)，若存在0<=j<=i,sum(j)=sum-k;则[j+1, i]的和为k
 *需要注意的是map要添加（0，-1），因为按照上述的思路，符合要求的字数组是从j+1开始的，该值>1，它排除了
 *从0开始的情况
 */
public class MaxLength {

	public int maxLength(int[] arr, int k) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(0, -1);
		int len = 0;
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			if (map.containsKey(sum-k)) {
				len = Math.max(i - map.get(sum-k), len);
			}
			//map中value指的是该累加和最早出现的位置
			if (!map.containsKey(sum)) {
				map.put(sum, i);
			}
		}
		return len;
	}
}
