package LeetCode.array;

import java.util.ArrayList;
import java.util.List;

public class LeetCode39 {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        // Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        recursive(candidates, new ArrayList<>(), target, result, 0);
        return result;
    }
    private void recursive(int[] nums, List<Integer> list, int target, List<List<Integer>> result, int start){
        if(target > 0){
            for(int i = start; i < nums.length; i++){  //不对数组进行排序，这里的target >= nums[i]判断去除
                list.add(nums[i]);
                recursive(nums, list, target-nums[i], result,i);
                list.remove(list.size()-1);
            }
        }
        else if(target == 0){
            result.add(new ArrayList<>(list));
        }
    }
}
