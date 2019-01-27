package LeetCode.backtacking;

import java.util.ArrayList;
import java.util.List;

public class LeetCode78 {

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        recursive(result, new ArrayList<>(), nums, 0);
        return result;
    }
    private static void recursive(List<List<Integer>> result, List<Integer> list, int[] nums, int start){
        result.add(new ArrayList<>(list));
        for(int i = start; i < nums.length; i++){
            list.add(nums[i]);
            //这里是i+1；因为我们要的是子集，不像全序列，让递归推进下去
            recursive(result, list, nums, i+1);
            list.remove(list.size()-1);
        }
    }

    public static void main(String[] args) {
        System.out.println(subsets(new int[]{1,2,3,4}));
    }
}
