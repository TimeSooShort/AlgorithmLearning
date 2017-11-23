package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
 * Find all unique triplets in the array which gives the sum of zero.
 * Note: The solution set must not contain duplicate triplets.
 * 
 * For example, given array S = [-1, 0, 1, 2, -1, -4],

     A solution set is:
  [
   [-1, 0, 1],
   [-1, -1, 2]
  ]
 * @author Administrator
 *
 */
public class ThreeSum {
/** 所耗时间太长
	class Solution {
	    public List<List<Integer>> threeSum(int[] nums) {
	        int length = nums.length;
	        List<Integer> surplusLeft = new ArrayList<>();
	        List<Integer> surplusLeftMedium = new ArrayList<>();
	        List<Integer> surplusMediumRight = new ArrayList<>();
	        List<List<Integer>> result = new ArrayList<>();
	        for(int i = 0; i < length; i++) {
	            if (surplusLeft.contains(nums[i])) {
	                continue;
	            }
	            int left = nums[i];
	            for(int j = i+1; j < length; j++) {
	                if (surplusLeft.contains(nums[j]) || surplusLeftMedium.contains(nums[j])) {
	                    continue;
	                }
	                int medium = nums[j];
	                int merge = medium + left;
	                for (int k = j+1; k < length; k++) {
	                    if (surplusLeftMedium.contains(nums[k]) || surplusLeft.contains(nums[k]) || surplusMediumRight.contains(nums[k])) {
	                        continue;
	                    }
	                    int right = nums[k];
	                    if (merge + right == 0) {
	                        List<Integer> match = new ArrayList<>();
	                        match.add(left);
	                        match.add(medium);
	                        match.add(right);
	                        result.add(match);
	                    }
	                    surplusMediumRight.add(nums[k]);
	                }
	                surplusMediumRight.clear();
	                surplusLeftMedium.add(nums[j]);
	                
	            }
	            surplusLeftMedium.clear();
	            surplusLeft.add(nums[i]);
	        }
	        return result;
	    }
	}
*/
	
	//下面是O(n^2)
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i < nums.length-2; i++) {
            if(i == 0 || nums[i] != nums[i-1]) {
                int lo = i+1;
                int hi = nums.length-1;
                int sum = 0 - nums[i];
                while(lo < hi) {
                    if (nums[lo] + nums[hi] == sum) {
                        result.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                        while(lo < hi && nums[lo] == nums[lo+1]) lo++;
                        while(lo < hi && nums[hi] == nums[hi-1]) hi--;
                        lo++;
                        hi--;
                    }
                    else if(nums[lo] + nums[hi] < sum) {
                        lo++;
                    }
                    else {
                        hi--;
                    }
                }
            }
        }
        return result;
    }
}
