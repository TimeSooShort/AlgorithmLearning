package Offer;

import java.util.Arrays;

public class Offer66 {
    private static long[] solve(int[] arr) {
        long[] mul = new long[arr.length];
        for (int i = 0, l = 1; i < arr.length; i++) {
            mul[i] = l;
            l *= arr[i];
        }
        for (int i = arr.length-1, r = 1; i >= 0; i--) {
            mul[i] *= r;
            r *= arr[i];
        }
        return mul;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,6,3,4};
        System.out.println(Arrays.toString(solve(arr)));
    }
}
