package LeetCode.backtacking;

import java.util.ArrayList;
import java.util.Arrays;
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
            //交换意味着打乱，意味着回溯深度优先搜索所形成的树不同
            //这里是相邻交换，每次将i与i-1位置原始值（此时它被之前的操作交换到了start位置）
            //交换后start+1节点后的树就完全不同，也就是一种新的组合
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

    public static void main(String[] args) {
        LeetCode46 test = new LeetCode46();
        int[] nums = {1,2,3,4};
        test.permute(nums);
        System.out.println(Arrays.toString(nums));
    }
}
