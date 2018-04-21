package LeetCode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of numbers that might contain duplicates,
 * return all possible unique permutations.
 */
public class LeetCode47 {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);  //这里排序是必须的
        recursive(result, new ArrayList<>(), nums, used);
        return result;
    }
    private void recursive(List<List<Integer>> result, List<Integer> list, int[] nums, boolean[] used){
        if(list.size() == nums.length){
            result.add(new ArrayList(list));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(used[i]) continue;
            //在回溯构成的搜索树中，i-1节点已被搜索过，而i节点值等于i-1，也就是重复了，需要剔除这一分支
            if(i > 0 && nums[i-1] == nums[i] && !used[i-1]) continue;
            list.add(nums[i]);
            used[i] = true;
            recursive(result, list, nums, used);
            list.remove(list.size()-1);
            used[i] = false;
        }
    }
}
