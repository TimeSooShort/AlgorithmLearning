package Offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Offer57 {

    // 升序数组中找到一对和为s的数
    // 思路：前后两个指针
    public static List<Integer> solve(int[] nums, int s) {
        ArrayList<Integer> result = new ArrayList<>();
        if (nums == null || nums.length < 2) return result;
        int l = 0, r = nums.length-1;
        while (l < r) {
            // System.out.println("l:"+l+", r:"+r);
            int sum = nums[l]+nums[r];
            if (sum == s) {
                result.add(nums[l]);
                result.add(nums[r]);
                break;
            }
            else if (sum < s) l++;
            else r--;
        }
        return result;
    }

    // 找到和为s的所有连续正数序列
    // 思路：窗口移动
    public static List<List<Integer>> solve2(int s) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        int l = 1, r = 2;
        int sum = 3;
        while (r < s) {
            if (sum == s) {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = l; i <= r; i++) {
                    list.add(i);
                }
                result.add(list);
                r++;
                sum = sum-l+r;
                l++;
            }
            else if (sum < s) {
                r++;
                sum += r;
            }
            else {
                sum -= l;
                l++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        int[] arr = {33,65,67,111,22,23,45,85,53,48,123,325,231,157,93};
//        Arrays.sort(arr);
//        System.out.println(Offer57.solve(new int[]{154, 24}, 178));
        System.out.println(Offer57.solve2(33));
    }
}
