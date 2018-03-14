package liuyubobo;

import java.util.Arrays;

public class MergeSort {

    public static void sort(Comparable[] arr){
        int n = arr.length;
        Comparable[] aux = Arrays.copyOf(arr, n);
        sort(arr,aux, 0, n-1);
    }

    private static void sort(Comparable[] arr, Comparable[] aux, int lo, int hi){
        if (lo >= hi) return;

        int mid = (lo+hi)/2;
        sort(arr,aux, lo, mid);
        sort(arr,aux, mid+1, hi);
        merge(arr, aux, lo, mid, hi);
    }

    private static void merge(Comparable[] arr,Comparable[] aux, int lo, int mid, int hi){

        for (int i = lo; i <= hi; i++){
            aux[i] = arr[i];
        }
        int arrIndex = lo;
        int left = lo, right = mid+1;
        while(arrIndex <= hi){
            if (left > mid){
                arr[arrIndex++] = aux[right++];
            } else if (right > hi){
                arr[arrIndex++] = aux[left++];
            } else if (aux[left].compareTo(aux[right]) < 0){
                arr[arrIndex++] = aux[left++];
            } else {
                arr[arrIndex++] = aux[right++];
            }
        }
    }

    public static void main(String[] args) {
        int n = 200000;
        Integer[] arr = SortTestHelper.randomArray(n, 0, n);
        SortTestHelper.testSort("liuyubobo.MergeSort", arr);
    }
}
