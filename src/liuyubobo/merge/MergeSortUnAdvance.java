package liuyubobo.merge;

import liuyubobo.SortTestHelper;

//未优化的归并排序
public class MergeSortUnAdvance {

    public static void sort(Comparable[] arr){
        int n = arr.length;
        Comparable[] aux = new Comparable[n];
        sort(arr, aux, 0, n-1);
    }
    private static void sort(Comparable[] arr, Comparable[] aux, int lo, int hi){
        if (lo >= hi) return;

        int mid = (lo+hi)/2;
        sort(arr, aux, lo, mid);
        sort(arr, aux, mid+1, hi);
        merge(arr, aux, lo, mid, hi);
    }
    private static void merge(Comparable[] arr, Comparable[] aux, int lo, int mid, int hi){
        System.arraycopy(arr, lo, aux, lo, hi-lo+1);
        int lt = lo, rt = mid+1;
        for (int i = lo; i <= hi; i++){
            if (lt > mid) arr[i] = aux[rt++];
            else if (rt > hi) arr[i] = aux[lt++];
            else if (aux[lt].compareTo(aux[rt]) < 0) arr[i] = aux[lt++];
            else arr[i] = aux[rt++];
        }
    }

    public static void main(String[] args) {
        int n = 1000000;
        Integer[] arr = SortTestHelper.randomArray(n , 0, n);
        SortTestHelper.testSort("liuyubobo.merge.MergeSortUnAdvance", arr);
    }
}
