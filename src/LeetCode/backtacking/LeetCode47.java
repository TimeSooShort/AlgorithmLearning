package LeetCode.backtacking;

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
            //在回溯构成的搜索树中，i-1节点已被搜索过，而i节点值等于i-1，也就是重复了，需要剔除这一分支
            if(used[i] || i > 0 && nums[i-1] == nums[i] && !used[i-1]) continue;
            list.add(nums[i]);
            used[i] = true;
            recursive(result, list, nums, used);
            list.remove(list.size()-1);
            used[i] = false;
        }
    }

    // 方法二
    public List<List<Integer>> permuteUnique2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, nums, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, int[] nums, int start) {
        if (start == nums.length-1) {
            result.add(asList(nums));
        }
        else {
            for (int i = start; i < nums.length; i++) {
                boolean flag = false;
                for (int j = i-1; j >= start; j--) {
                    if (nums[j] == nums[i]) {
                        flag = true;
                        break;
                    }
                }
                if (flag) continue;
                swap(nums, start, i);
                backtrack(result, nums, start+1);
                swap(nums, start, i);
            }
        }
    }

    private List<Integer> asList(int[] nums) {
        List<Integer> list = new ArrayList<>(nums.length);
        for (int num : nums) {
            list.add(num);
        }

        return list;
    }

    private void swap(int[] nums, int i, int j) {
        if (i != j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }
}
