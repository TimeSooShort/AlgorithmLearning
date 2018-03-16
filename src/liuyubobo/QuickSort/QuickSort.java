package liuyubobo.QuickSort;

import liuyubobo.InsertSort;
import liuyubobo.SortTestHelper;

public class QuickSort {

    public QuickSort() {
    }

    public static void sort(Comparable[] arr){
        int n = arr.length;
        sort(arr, 0, n-1);
    }
    private static void sort(Comparable[] arr, int lo, int hi){
//        if (lo >= hi) return;
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

        int j = lo;
        for (int i = lo+1; i <= hi; i++){
            if (less(arr[i], v)){
                j++;
                swap(arr, j, i);
            }
        }
        swap(arr, j, lo);
        return j;
    }
    private static boolean less(Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }
    private static void swap(Object[] arr, int i, int j){
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int n = 1000000;
        Integer[] arr = SortTestHelper.randomArray(n, 0, n);
        SortTestHelper.testSort("liuyubobo.QuickSort.QuickSort", arr);
    }
}
