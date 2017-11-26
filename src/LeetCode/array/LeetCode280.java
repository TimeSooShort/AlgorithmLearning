package LeetCode.array;

/**
 * Wiggle Sort（摇摆排序）
 * Given an unsorted array nums, reorder it in-place such that 
 * nums[0] <= nums[1] >= nums[2] <= nums[3]....
 * For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
 * @author Administrator
 *
 */
public class LeetCode280 {

	public int[] wiggleSort(int[] nums) {
		for(int i = 1; i < nums.length; i++) {
			if((i%2 == 0 && nums[i] > nums[i-1]) || 
					(i%2 == 1 && nums[i] < nums[i-1])) {
				exch(nums, i, i-1);
			}
		}
		return nums;
	}
	
	private void exch(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

}
