package LeetCode.array;

import java.util.ArrayList;
import java.util.List;

//backtacking
public class LeetCode46 {

    //方法一：采用在递归过程中交换
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permute(nums, result, 0);
        return result;
    }

    private void permute(int[] nums, List<List<Integer>> result, int start) {
        if (start == nums.length - 1) {
            result.add(asList(nums));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            swap(nums, i, start);
            permute(nums, result, start + 1);
            swap(nums, i, start);
        }

    }

    private static List<Integer> asList(int[] nums) {
        List<Integer> list = new ArrayList<>(nums.length);
        for (int num : nums) {
            list.add(num);
        }

        return list;
    }

    private static void swap(int[] nums, int i, int j) {
        if (i != j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }


    //方法二：递归中用list存储，删除数据
    public List<List<Integer>> permute2(int[] nums) {
        //backtraking
        List<List<Integer>> result = new ArrayList<>();
        recursive(result, new ArrayList<>(), nums);
        return result;
    }
    private void recursive(List<List<Integer>> result, List<Integer> list, int[] nums){
        if(list.size() == nums.length){
            result.add(new ArrayList<>(list));
        }else {
            for(int i = 0; i < nums.length; i++){
                if(list.contains(nums[i])) continue;
                list.add(nums[i]);
                recursive(result, list, nums);
                list.remove(list.size()-1);
            }
        }
    }
}
