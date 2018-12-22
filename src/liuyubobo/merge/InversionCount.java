package liuyubobo.merge;

import liuyubobo.SortTestHelper;

import java.util.Arrays;

/**
 * 逆序数问题
 */
public class InversionCount {

    public static long solve(Comparable[] arr){
        int n = arr.length;
        return solve(arr, 0, n-1);
    }
    private static long solve(Comparable[] arr, int lo, int hi){
        if (lo >= hi) return 0L;

        int mid = lo+ (hi-lo)/2;  //lo+hi可能造成溢出
        long resultL = solve(arr, lo, mid);
        long resultR = solve(arr, mid+1, hi);
        return resultL+resultR+merge(arr, lo, mid, hi);
    }
    private static long merge(Comparable[] arr, int lo, int mid, int hi){
        Comparable[] aux = Arrays.copyOfRange(arr, lo, hi+1);

        long res = 0L;
        int lt = 0, newMid = mid-lo, rt = mid-lo+1;
        for (int i = lo; i <= hi; i++){
            if (lt > newMid) arr[i] = aux[rt++];
            else if (rt > hi-lo) arr[i] = aux[lt++];
            else if (aux[lt].compareTo(aux[rt]) <= 0) arr[i] = aux[lt++];
            else {
                arr[i] = aux[rt++];
                res += (long)(newMid-lt+1);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        //一般情况
        int n = 100000;
        Integer[] arr = SortTestHelper.randomArray(n, 0, n);
        System.out.println("normal situation : " + solve(arr));

        //完全有序;值为0
        arr = SortTestHelper.orderedArray(n);
        System.out.println("absolutely ordered array : "+solve(arr));

        //逆序;值为n(n-1)/2
        arr = SortTestHelper.inverstArray(n);
        System.out.println("inversion array :" + solve(arr));
    }
}
