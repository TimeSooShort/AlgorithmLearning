package liuyubobo.merge;

import liuyubobo.InsertSort;
import liuyubobo.SortTestHelper;

import java.util.Arrays;

public class MergeSortBU {

    public static void sort(Comparable[] arr){

        //方法一
        int n = arr.length;
//        for (int sz = 1; sz < n; sz*=2){
//            for (int j = 0; j < n-sz; j += 2*sz){  //j < n-sz因为merge里的mid = j+sz-1
//                merge(arr, j, j+sz-1, Math.min(n-1, j+sz*2-1));
//            }
//        }

        //优化
        for (int i = 0; i < n; i+=16){
            InsertSort.sort(arr, i, Math.min(n-1, i+15));
        }
        for (int sz = 16; sz < n; sz *= 2){
            for (int j = 0; j < n-sz; j += sz*2){
                merge(arr,j, j+sz-1, Math.min(n-1, j+2*sz-1));
            }
        }

    }
    //需要注意，mid值需要传过来，让aux的auxMid与其保持一致，因为在归并到最后时双方长度不一定会一致，
    //所以不能采取之前重新计算aux中间值问题；推荐以后写归并，之间将mid值传过来，根据它来计算aux的mid值
    private static void merge(Comparable[] arr, int lo, int mid, int hi){
        Comparable[] aux = Arrays.copyOfRange(arr, lo, hi+1);

        int left = 0, auxMid = mid-lo, right = mid-lo+1;
        for (int i = lo; i <= hi; i++){
            if (left > auxMid) arr[i] = aux[right++];
            else if (right > hi-lo) arr[i] = aux[left++];
            else if (aux[left].compareTo(aux[right]) < 0) arr[i] = aux[left++];
            else arr[i] = aux[right++];
        }
    }

    public static void main(String[] args) {
        int n = 1000000;
        Integer[] arr = SortTestHelper.randomArray(n, 0, n);
        SortTestHelper.testSort("liuyubobo.merge.MergeSortBU", arr);
    }
}
