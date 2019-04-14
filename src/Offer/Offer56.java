package Offer;

import java.util.*;

public class Offer56 {

    /**
     * 数组中只有两个数是单独的，其它都是成对的，找出这两个；
     * 思路：一个数异或自己为0，异或一遍数组，则到一个数，检查其二进制中第一个1的位置，
     * 以此来将数组元素分成两份，这两个单独的数各在一边，然后异或每一边，分别得到答案。
     */
    public static List<Integer> solve(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;
        int sumXor = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sumXor ^= nums[i];
        }
        int firstOne = 1;
        while ((sumXor & firstOne) != firstOne) {
            firstOne <<= 1;
        }
        int left = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & firstOne) == firstOne) exch(nums, left++, i);
        }
        int leftOne = nums[0];
        for (int i = 1; i < left; i++) {
            leftOne ^= nums[i];
        }
        result.add(leftOne);
        int rightOne = nums[left];
        for (int i = left+1; i < nums.length; i++) {
            rightOne ^= nums[i];
        }
        result.add(rightOne);
        return result;
    }

    private static void exch(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    // 数组中每个数重复三遍，只有一个数是单独的，找出来
    // 统计二进制中每一位出现的次数，不能被3整除的位组合起来就是该值
    public static int solve2(int[] nums) {
        int[] count = new int[32];
        for (int num : nums) {
            int index = 0;
            while (num != 0) {
                if ((num & 1) == 1) count[index]++;
                num >>>= 1;
                index++;
            }
        }
        int result = 0;
        for (int i = 0; i < 32; i++) {
            if (count[i] % 3 != 0) result += pow(i);
        }
        return result;
    }

    private static int pow(int i) {
        int num = 1;
        while (i != 0) {
            num *= 2;
            i--;
        }
        return num;
    }

    public static void main(String[] args) {
//        int[] arr = {3,5,8,9,5,2,2,1,3,8,1,7,9,10}; // 7 ,10
//        System.out.println(Offer56.solve(arr));
        int[] arr2 = {235,456,123421,4574,67867,780978,23423,3463,457645,3453,3453,2342,56745,4574,
                3436,67567,676,123,23,457,67,89,234,25,67,54,23,4,76,9,32,9,0,1};
        Set<Integer> set = new HashSet<>();
        for (int num : arr2) {
            set.add(num);
        }
        int[] test = new int[set.size()*3+1];
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int num : set) {
                test[index++] = num;
            }
        }
        test[set.size()] = test[25];
        test[25] = 664235;
        System.out.println(Offer56.solve2(test));

        int[] arr3 = {1,4,6,8,4,8,33,6,1,1,6,4,8};
        System.out.println(Offer56.solve2(arr3));
    }
}
