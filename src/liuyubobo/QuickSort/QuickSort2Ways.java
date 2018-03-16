package liuyubobo.QuickSort;

import liuyubobo.InsertSort;
import liuyubobo.SortTestHelper;

public class QuickSort2Ways {

    public QuickSort2Ways() {
    }

    private static void swap(Object[] arr, int i, int j){
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void sort(Comparable[] arr){
        int n = arr.length;
        sort(arr, 0, n-1);
    }
    private static void sort(Comparable[] arr, int lo, int hi){
        if (lo + 15 >= hi) {
            InsertSort.sort(arr, lo, hi);
            return;
        }

        int partition = partition(arr, lo, hi);
        sort(arr, lo, partition-1);
        sort(arr, partition+1, hi);
    }

    private static int partition(Comparable[] arr, int lo, int hi){
        swap(arr, lo, (int) (Math.random()*(hi-lo+1))+lo);

        Comparable v = arr[lo];

        int lt = lo+1, rt = hi;
        while (lt <= rt){
            while (lt <= hi && arr[lt].compareTo(v) < 0){
                lt++;
            }
            while (rt >= lo+1 && arr[rt].compareTo(v) > 0){
                rt--;
            }
            if (lt > rt) break;
            swap(arr, lt, rt);
            lt++;
            rt--;
        }
        swap(arr, lo, rt);
        return rt;
    }

    public static void main(String[] args) {
        int n = 200000;
        Integer[] arr = SortTestHelper.randomArray(n, 0, 10);
        SortTestHelper.testSort("liuyubobo.QuickSort.QuickSort2Ways", arr);
    }
}
