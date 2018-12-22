package liuyubobo.QuickSort;

import liuyubobo.InsertSort;
import liuyubobo.SortTestHelper;

public class QuickSort3Ways {

    public QuickSort3Ways() {
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
        if (lo+15 >= hi){
            InsertSort.sort(arr, lo, hi);
            return;
        }
        swap(arr, lo, (int) (Math.random()*(hi-lo+1))+lo);
        Comparable v = arr[lo];
        int lt = lo; //[lo+1, lt] < v
        int gt = hi+1;   //[gt, hi] > v
        int i = lo+1;  //[lt+1, i) == v

        while (i < gt){
            if (arr[i].compareTo(v) < 0){
                swap(arr, ++lt, i++);
            } else if (arr[i].compareTo(v) > 0){
                swap(arr, --gt, i);
            } else {
                i++;
            }
        }
        sort(arr, lo, lt);
        sort(arr, gt, hi);
    }

    public static void main(String[] args) {
        int n = 1000000;
        Integer[] arr = SortTestHelper.randomArray(n, 0, n);
        SortTestHelper.testSort("liuyubobo.QuickSort.QuickSort3Ways", arr);

        arr = SortTestHelper.randomArray(n, 0, 30);
        SortTestHelper.testSort("liuyubobo.QuickSort.QuickSort3Ways", arr);

        arr = SortTestHelper.neralyOrderedArr(n, 50);
        SortTestHelper.testSort("liuyubobo.QuickSort.QuickSort3Ways", arr);
    }
}
