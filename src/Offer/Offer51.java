package Offer;

import java.util.Arrays;

public class Offer51 {

    public int reversePairs(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] aux = new int[nums.length];
        return mergeSort(nums, aux, 0, nums.length-1);
    }

    private int mergeSort(int[] src, int[] aux, int start, int end){
        if (start >= end) return 0;
        int mid = (end-start)/2+start;
        int cnt = mergeSort(src, aux, start, mid)+mergeSort(src, aux, mid+1, end);
        for(int i = start, j = mid+1; i <= mid; i++) {
            while(j <= end && src[i] > src[j]) j++;
            cnt += j-(mid+1);
        }
        merge(src, aux, start, mid, end);
        return cnt;
    }

    private void merge(int[] arr, int[] aux, int start, int mid, int end) {
        System.arraycopy(arr, start, aux, start, end-start+1);
        int lt = start, rt = mid+1;
        for (int i = start; i <= end; i++) {
            if (rt > end){
                arr[i] = aux[lt++];
            } else if (lt > mid) {
                arr[i] = aux[rt++];
            } else if (aux[lt] > aux[rt]) {
                arr[i] = aux[rt++];
            } else {
                arr[i] = aux[lt++];
            }
        }
    }
}
