package liuyubobo.heap;

import liuyubobo.SortTestHelper;

import java.util.Arrays;
import java.util.Comparator;

public class HeapSort {

    public static void sort(Comparable[] arr){
        int n = arr.length;
        for (int i = (n-1-1)/2; i >= 0; i--){
            shiftDownAdvance(arr, i, n);
        }
        for (int j = n-1; j > 0; j--){
            swap(arr, 0, j);
//            shiftDown(arr, 0, j);
            shiftDownAdvance(arr, 0, j);
        }
    }

    private static void shiftDown(Comparable[] arr, int k, int n){
        while(k*2+1 <= n-1){
            int j = k*2+1;
            if (j+1 < n && arr[j].compareTo(arr[j+1]) < 0){
                j++;
            }
            if (arr[k].compareTo(arr[j]) >= 0){
                break;
            }
            swap(arr, k, j);
            k = j;
        }
    }
    private static void swap(Comparable[] arr, int i, int j){
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    //优化方法插入排序类似
    private static void shiftDownAdvance(Comparable[] arr, int k, int n){
        Comparable t = arr[k];
        while (k*2+1 < n){
            int j = k*2+1;
            if (j+1 < n && arr[j].compareTo(arr[j+1]) < 0){
                j++;
            }
            // 注意这里别写成arr[k].compareTo(arr[j])
            if (t.compareTo(arr[j]) >= 0){
                break;
            }
            arr[k] = arr[j];
            k = j;
        }
        arr[k] = t;
    }

    public static void main(String[] args) {
        int n = 10000;
        Integer[] arr= SortTestHelper.randomArray(n, 0, n);
        SortTestHelper.testSort("liuyubobo.heap.HeapSort", arr);
    }
}
