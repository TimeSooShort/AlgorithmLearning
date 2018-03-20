package liuyubobo.QuickSort;

import liuyubobo.SortTestHelper;

//求第k个最小数
public class SelectKth {

    public SelectKth() {
    }

    private static int partition(Comparable[] arr, int lo, int hi){
        swap(arr, lo, (int) (Math.random()*(hi-lo+1))+lo);

        Comparable e = arr[lo];
        int j = lo;
        for (int i = lo+1; i <= hi; i++){
            if (arr[i].compareTo(e) < 0){
                j++;
                swap(arr, j, i);
            }
        }
        swap(arr, j, lo);
        return j;
    }
    private static void swap(Object[] arr, int i, int j){
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //这里最小是第0位
    public static Comparable  solve(Comparable[] arr, int k){
        assert arr != null && k >= 0 && k < arr.length;
        return solve(arr, 0, arr.length-1, k);
    }

    //这里最小是第一位
    public static Comparable solve2(Comparable[] arr, int k){
        return solve(arr, k-1);
    }
    private static Comparable solve(Comparable[] arr, int lo, int hi, int k){
        if (lo == hi) return arr[lo];

        int p = partition(arr, lo, hi);

        if (p == k){
            return arr[p];  //这里最小是第0位
        } else if (k < p){
            return solve(arr, lo, p-1, k);
        } else {
            return solve(arr, p+1, hi, k);
        }
    }

    public static void main(String[] args) {
        int n = 1000;
        Integer[] arr = SortTestHelper.orderedArray(n);
        SortTestHelper.shuffleArray(arr);

        boolean result = true;
        for (int i = 0; i < 20; i++){
            if (solve(arr, i).compareTo(i) == 0){
//                result = false;
//                break;
                System.out.println("the "+i+"th is :" + i);
            }
        }
//        if (result){
//            System.out.println("success");
//        }else {
//            System.out.println("failed");
//        }
    }
}
