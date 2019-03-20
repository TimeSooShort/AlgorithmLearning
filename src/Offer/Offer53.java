package Offer;

public class Offer53 {
    public int solve1(int[] arr, int k) {
        if (arr == null || arr.length == 0) return -1;
        int leftBorder = binarySearch(arr, k);
        int rightBorder = binarySearch(arr, k+1);
        return (leftBorder == arr.length || arr[leftBorder] != k) ? 0 : rightBorder-leftBorder;
    }

    private int binarySearch(int[] arr, int k) {
        int l = 0, h = arr.length;
        while (l < h) {
            int mid = l+(h-l)/2;
            if (arr[mid] >= k) h = mid; // h在由右向左滑行直到>=k的最小位置
            else l = mid+1; // l从左向右滑行直到等于h
        }
        return l;
    }

    // 题目二
    public static int solve2(int[] arr) {
        int l = 0, h = arr.length;
        while (l < h) {
            int mid = l+(h-l)/2;
            if (arr[mid] == mid) l = mid+1;
            else h = mid;
        }
        if (l == arr.length) {
            return -1;
        }
        return arr[l]-1;
    }

    public static void main(String[] args) {
        int[] arr1 = {1,2,3,4};
        int[] arr2 = {0,1,2,3,4,6};
        int[] arr3 = {0};
        int[] arr4 = {0,1,2,4,5};
        System.out.println(solve2(arr1));
        System.out.println(solve2(arr2));
        System.out.println(solve2(arr3));
        System.out.println(solve2(arr4));
    }
}
