package Offer;

import java.util.Arrays;

public class Offer40 {

    public static int[] getKMinNum(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k > nums.length) return null;
        int pivot = 0;
        int start = 0, end = nums.length-1;
        while (pivot != k-1) {
            pivot = partition(nums, start, end);
            if (pivot < k - 1) start = pivot;
            else if (pivot > k - 1) end = pivot;
            else break;
        }
        return Arrays.copyOfRange(nums, 0, k);
    }

    private static int partition(int[] nums, int start, int end) {
        swap(nums, start, (int) (Math.random()* (end-start+1))+start);
        int e = nums[start];
        int less = start+1, more = end;
        while (less <= more) {
            while (less <= more && nums[less] <= e) less++;
            while (more >= less && nums[more] > e) more--;
            if (less > more) break;
            swap(nums, less++, more--);
        }
        swap(nums, start, more);
        return more;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {3,5,7,8,4,7,8,0,2,6,7,9,1,23,4,2,5,37,7,48,8,48,4,1,1,2};
        int[] arr1 = Arrays.copyOf(arr, arr.length);
        System.out.println(Arrays.toString(getKMinNum(arr, 8)));
        Arrays.sort(arr1);
        System.out.println(Arrays.toString(arr1));
    }
}
