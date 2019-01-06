package LeetCode;

import java.util.Arrays;

public class LeetCode215 {

    // ===============堆排方式。 66.92%
//    public int findKthLargest(int[] nums, int k) {
//        int ln = nums.length;
//        for(int j = ln/2-1; j >= 0; j--) {
//            shiftDown(nums, j, ln);
//        }
//        for(int i = 0; i < k; i++) {
//            exch(nums, 0, --ln);
//            shiftDown(nums, 0, ln);
//        }
//        return nums[ln];
//    }

    public int findKthLargest(int[] nums, int k) {

        int[] heap = new int[k];
        for (int i = 0; i < k; i++) {
            heap[i] = nums[i];
            shiftUp(heap, i);
        }
        System.out.println("after shiftUp : " + Arrays.toString(heap));
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > heap[0]) {
                heap[0] = nums[i];
                shiftDown(heap, 0, k);
                System.out.print("shiftDown step: " + Arrays.toString(heap));
            }
        }
        System.out.println();
        System.out.println("after shiftDown : " + Arrays.toString(heap));
        return heap[0];
    }

    private void shiftUp(int[] arr, int i) {
        int e = arr[i];
        while((i+1)/2-1 >= 0) {
            int parent = (i+1)/2-1;
            if (arr[parent] <= e) break;
            arr[i] = arr[parent];
            i = parent;
        }
        arr[i] = e;
    }

    private void shiftDown(int[] arr, int i, int ln) {
        int e = arr[i];
        while(i*2+1 < ln) {
            int j = i*2+1;
            if(j+1 < ln && arr[j] > arr[j+1]) j++;
            if(e <= arr[j]) break;
            arr[i] = arr[j];
            i = j;
        }
        arr[i] = e;
    }

    private void exch(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // ========快排方法
    public int findKthLargest2(int[] nums, int k) {
        int ln = nums.length;
        int s = 0, e = ln-1, rln = 0, p = 0;
        while(rln != k) {
            // 这一句使得代码从12.99%提升到79.09%
            exch(nums, s, (int)(Math.random()*(e-s+1))+s);
            p = partition(nums, s, e);
            rln = ln-p; // 注意这里不是ln-p-1
            if(rln < k) {
                e = p - 1;
            }else if(rln > k) {
                s = p + 1;
            }
        }
        return nums[p];
    }

    private int partition(int[] nums, int s, int e) {
        int t = nums[s], i = s+1, j = e;
        while(i <= j) {
            while(i <= j && nums[i] <= t) i++;
            while(j >= i && nums[j] >= t) j--;
            if(i > j) break;
            exch(nums, i++, j--);
        }
        exch(nums, s, j);
        return j;
    }

    public static void main(String[] args) {
        LeetCode215 instance = new LeetCode215();
        int[] arr = {3,2,1,5,6,4};
        System.out.println("result: " + instance.findKthLargest(arr, 2));
    }
}
