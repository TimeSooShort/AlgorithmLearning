package LeetCode.array;

import java.util.HashMap;

/**
 * Given an array of integers, return indices of the two numbers 
 * such that they add up to a specific target.
 * You may assume that each input would have exactly one solution, 
 * and you may not use the same element twice.
 * @author Administrator
 *
 */
public class TwoSum {

	public TwoSum() {
		// TODO Auto-generated constructor stub
	}
	
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int[] result = new int[2];
        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(target - nums[i])) {
                result[1] = i;
                result[0] = map.get(target - nums[i]);
                break;
            }
            map.put(nums[i], i);
        }
        return result;
    }

}
