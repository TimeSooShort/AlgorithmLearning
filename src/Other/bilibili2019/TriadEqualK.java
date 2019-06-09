package Other.bilibili2019;

import java.util.Scanner;

// 左程云P352
public class TriadEqualK {

    public static void triad(int[] arr, int k) {
        sort(arr, 0, arr.length-1);
        for (int i = 0; i < arr.length-2; i++) {
            if (i != 0 && arr[i] == arr[i-1]) continue;
            rest(arr, i+1, arr.length-1, k-arr[i], i);
        }
    }

    private static void rest(int[] arr, int left, int right, int target, int third) {
        while (left < right) {
            int sum = arr[left]+arr[right];
            if (sum < target) left++;
            else if (sum > target) right--;
            else {
                if (left == third+1 || arr[left] != arr[left-1]) {
                    System.out.println(arr[third]+","+arr[left]+","+arr[right]);
                }
                left++;
                right--;
            }
        }
    }

    private static void sort(int[] arr, int lo, int hi) {
        if (lo + 3 >= hi) {
            insertionSort(arr, lo, hi);
            return;
        }
        int partition = partition(arr, lo, hi);
        sort(arr, lo, partition-1);
        sort(arr, partition+1, hi);
    }

    private static int partition(int[] arr, int lo, int hi) {
        swap(arr, lo, (int) (Math.random()*(hi-lo+1)+lo));
        int lt = lo+1, rt = hi;
        while (lt <= rt) {
            while (lt <= rt && arr[lt] <= arr[lo]) lt++;
            while (lt <= rt && arr[rt] > arr[lo]) rt--;
            if (lt > rt) break;
            swap(arr, lt++, rt--);
        }
        swap(arr, lo, rt);
        return rt;
    }

    private static void insertionSort(int[] arr, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            int e = arr[i];
            int j = i-1;
            for(;j >= lo && e < arr[j]; j--) {
                arr[j+1] = arr[j];
            }
            arr[j+1] = e;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int[] arr = {7,13,12,6,8,11,13,9,6,4,2,66,22,25,11,14,15,11,22,11,11,16,34,36,57};
        triad(arr, 55);
    }
}
