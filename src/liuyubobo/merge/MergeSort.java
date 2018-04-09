package liuyubobo.merge;

import liuyubobo.InsertSort;
import liuyubobo.SortTestHelper;

public class MergeSort {

    //方法一
    public static void sort(Comparable[] arr){
        int n = arr.length;
        Comparable[] aux = new Comparable[n];
        sort(arr,aux, 0, n-1);
    }

    private static void sort(Comparable[] arr, Comparable[] aux, int lo, int hi){
        if (lo+15 >= hi){
            InsertSort.sort(arr, lo, hi);
            return;
        }

        int mid = (lo+hi)/2;
        sort(arr,aux, lo, mid);
        sort(arr,aux, mid+1, hi);
        if (arr[mid].compareTo(arr[mid+1]) > 0){
            merge(arr, aux, lo, mid, hi);
        }
    }

    private static void merge(Comparable[] arr,Comparable[] aux, int lo, int mid, int hi){
        System.arraycopy(arr, lo, aux, lo, hi-lo+1);
        int lt = lo, rt = mid+1;
        for (int i = lo; i <= hi; i++){
            if (lt > mid) arr[i] = aux[rt++];
            else if (rt > hi) arr[i] = aux[lt++];
            else if (aux[lt].compareTo(aux[rt]) < 0) arr[i] = aux[lt++];
            else arr[i] = aux[rt++];
        }
    }

    //方法二
//    public static void sort(Comparable[] arr){
//        int n = arr.length;
//        sort(arr, 0, n-1);
//    }
//    private static void sort(Comparable[] arr, int lo, int hi){
//        if (lo >= hi) return ;
//
//        int mid = (lo+hi)/2;
//        sort(arr, lo, mid);
//        sort(arr, mid+1, hi);
//        merge(arr, lo, hi);
//    }
//    private static void merge(Comparable[] arr, int lo, int hi){
//        Comparable[] aux = Arrays.copyOfRange(arr, lo, hi+1);
//
//        int n = aux.length;
//        int left = 0, auxMid = (n-1)/2, right = (n-1)/2+1;
//        for (int i = lo; i <= hi; i++){
//            if (left > auxMid) arr[i] = aux[right++];
//            else if (right > n-1) arr[i] = aux[left++];
//            else if (aux[left].compareTo(aux[right]) < 0) arr[i] = aux[left++];
//            else arr[i] = aux[right++];
//        }
//    }
    //
//    public static void sort(Comparable[] arr){
//        int n = arr.length;
//        sort(arr, 0, n-1);
//    }
//    private static void sort(Comparable[] arr, int lo, int hi){
//        if (lo + 15 >= hi){
//            InsertSort.sort(arr, lo, hi);
//            return;
//        }
//
//        int mid = (lo+hi)/2;
//        sort(arr, lo, mid);
//        sort(arr, mid+1, hi);
//        if (arr[mid].compareTo(arr[mid+1]) > 0){
//            merge(arr, lo, hi);
//        }
//    }
//    private static void merge(Comparable[] arr, int lo, int hi){
//        Comparable[] aux = Arrays.copyOfRange(arr, lo, hi+1);
//
//        int left = 0, mid = (hi-lo)/2, right = (hi-lo)/2+1;
//        for (int i = lo; i <= hi; i++){
//            if (left > mid) arr[i] = aux[right++];
//            else if (right > hi-lo) arr[i] = aux[left++];
//            else if (aux[left].compareTo(aux[right]) < 0) arr[i] = aux[left++];
//            else arr[i] = aux[right++];
//        }
//    }

    public static void main(String[] args) {
        int n = 1000000;
        Integer[] arr = SortTestHelper.randomArray(n, 0, n);
        SortTestHelper.testSort("liuyubobo.merge.MergeSort", arr);
    }
}
