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

    // 这种方法是什么意思呢？例如abc，在第0位有三种可能，axx,bxx,cxx, xx代表剩下的子数组，处理方法相同
    // 下面：start代表第start位，将nums[start+1~length-1]就是剩下的子数组，递归处理
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


    //方法二：递归中用list存储，删除数据
    public List<List<Integer>> permute2(int[] nums) {
        //backtraking
        List<List<Integer>> result = new ArrayList<>();
        recursive(result, new ArrayList<>(), nums);
        return result;
    }
    // 思路与上面相同，只不过是采用list存储
    private void recursive(List<List<Integer>> result, List<Integer> list, int[] nums){
        if(list.size() == nums.length){
            System.out.println("list complete : " + list);
            result.add(new ArrayList<>(list));
        }else {
            for(int i = 0; i < nums.length; i++){
                if(list.contains(nums[i])) continue;
                list.add(nums[i]);
                System.out.println("list add :" + list);
                recursive(result, list, nums);
                list.remove(list.size()-1);
                System.out.println("list remove :" + list);
            }
        }
    }

    public static void main(String[] args) {
        LeetCode46 test = new LeetCode46();
        int[] nums = {1,2,3,4,5};
        test.permute2(nums);
//        System.out.println(Arrays.toString(nums));
    }
}
