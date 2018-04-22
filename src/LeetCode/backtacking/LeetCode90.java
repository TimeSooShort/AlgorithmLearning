package LeetCode.backtacking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode90 {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); //一定要先进行排序
        recursive(result, new ArrayList<>(), nums, 0);
        return result;
    }
    private void recursive(List<List<Integer>> result, List<Integer> list, int[] nums, int start){
        result.add(new ArrayList<>(list));
        for(int i = start; i < nums.length; i++){
            if(i > start && nums[i-1] == nums[i]) continue;
            list.add(nums[i]);
            recursive(result, list, nums, i+1);
            list.remove(list.size()-1);
        }
    }
}
