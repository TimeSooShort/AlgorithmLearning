package Offer;

import java.util.Arrays;

/**
 * 即LeetCode 3
 */
public class Offer48 {

    public static int solve(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] arr = s.toCharArray();
        int l1 = 0, max = 0;
        int[] count = new int[26];
        Arrays.fill(count, -1);
        for (int i = 0; i < arr.length; i++) {
            int index = arr[i] - 'a';
            if (count[index] != -1 && count[index] >= l1) {
                max = Math.max(max, i-l1);
                l1 = count[index]+1;
            }
            count[index] = i;
        }
        return Math.max(max, arr.length-l1);
    }

    public static void main(String[] args) {
//        System.out.println(solve("arabcacfr"));
//        System.out.println(solve("pwwkew"));
//        System.out.println(solve("bbbbb"));
//        System.out.println(solve("abcabcbb"));
    }
}
