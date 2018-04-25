package LeetCode.backtacking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Combination Sum II
 */
public class LeetCode40 {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        recursive(candidates, result, new ArrayList<>(), target, 0);
        return result;
    }
    private void recursive(int[] nums, List<List<Integer>> result, List<Integer> list, int remain, int start){
        if(remain < 0) return;
        else if(remain == 0) result.add(new ArrayList<>(list));
        else {
            for(int i = start; i < nums.length && remain-nums[i] >= 0; i++){
                if(i > start && nums[i] == nums[i-1]) continue;
                list.add(nums[i]);
                recursive(nums, result, list, remain-nums[i], i+1);
                list.remove(list.size()-1);
            }
        }
    }
}
