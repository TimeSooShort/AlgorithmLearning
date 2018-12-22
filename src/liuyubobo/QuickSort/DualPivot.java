package liuyubobo.QuickSort;

import liuyubobo.InsertSort;
import liuyubobo.SortTestHelper;

public class DualPivot {

    public static void sort(Comparable[] arr) {
        sort(arr, 0, arr.length-1);
    }

    public static void sort(Comparable[] arr, int lo, int hi) {
//        if (lo + 15 >= hi) {
//            InsertSort.sort(arr, lo, hi);
//            return;
//        }
        if (lo >= hi) return;
        if (less(arr[hi], arr[lo])) swap(arr, lo, hi);
        Comparable pivot1 = arr[lo];
        Comparable pivot2 = arr[hi];

        int lt = lo, gt = hi, k = lo+1;
        while (k < gt) {
            if (less(arr[k], pivot1)) swap(arr, ++lt, k++);
            else if (less(pivot2, arr[k])) swap(arr, --gt, k);
            else k++;
        }
        swap(arr, lo, lt);
        swap(arr, hi, gt);
        sort(arr, lo, lt-1);
        sort(arr, lt+1, gt-1);
        sort(arr, gt+1, hi);
    }

    private static void swap(Object[] arr, int i, int j) {
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static void main(String[] args) {
        int n = 100000;
        // 普通情况
        System.out.println("for normal situation :" + "arr.length=" + n);
        Integer[] arr1 = SortTestHelper.randomArray(n, 0, n);
        SortTestHelper.testSort("liuyubobo.QuickSort.DualPivot", arr1);

        // 有序性高
        System.out.println("nearly sorted array :" + "array size:" + n);
        arr1 = SortTestHelper.neralyOrderedArr(n, 100);
        SortTestHelper.testSort("liuyubobo.QuickSort.DualPivot", arr1);

        //大量重复
        System.out.println("has more repeated item array: " + "array size: "+ n);
        arr1 = SortTestHelper.randomArray(n, 0, 20);
        SortTestHelper.testSort("liuyubobo.QuickSort.DualPivot", arr1);
    }
}
