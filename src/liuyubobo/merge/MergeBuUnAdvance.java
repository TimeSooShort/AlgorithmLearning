package liuyubobo.merge;

import liuyubobo.SortTestHelper;

import java.util.Arrays;

public class MergeBuUnAdvance {

    public MergeBuUnAdvance() {
    }

    public static void sort(Comparable[] arr){
        int n = arr.length;
        for (int sz = 1; sz < n; sz *= 2){
            for (int i = 0; i < n-sz; i += sz*2){
                merge(arr, i, i+sz-1, Math.min(n-1, i+sz*2-1));
            }
        }
    }
    private static void merge(Comparable[] arr, int lo, int mid, int hi){
        Comparable[] aux = Arrays.copyOfRange(arr, lo, hi+1);

        int lt = 0, newMid = mid-lo, rt = mid-lo+1;
        for (int i = lo; i <= hi; i++){
            if (lt > newMid) arr[i] = aux[rt++];
            else if (rt > hi-lo) arr[i] = aux[lt++];
            else if (aux[lt].compareTo(aux[rt]) < 0) arr[i] = aux[lt++];
            else arr[i] = aux[rt++];
        }
    }

    public static void main(String[] args) {
        int n = 10000;
        Integer[] arr = SortTestHelper.randomArray(n, 0, n);
        SortTestHelper.testSort("liuyubobo.merge.MergeBuUnAdvance", arr);
    }
}
