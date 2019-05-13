package Offer;

import java.util.ArrayDeque;
import java.util.Arrays;

public class Offer59 {

    public static int[] window(int[] arr, int l) {
        assert arr != null && arr.length >= l;
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        int[] res = new int[arr.length-l+1];
        int resI = 0;
        deque.add(0);
        for (int i = 1; i < arr.length; i++) {
            if (i - deque.peekFirst() < l) {
                while (!deque.isEmpty() && arr[deque.peekLast()] < arr[i]) {
                    deque.pollLast();
                }
                deque.addLast(i);
                if (i < l-1) continue;
                res[resI++] = arr[deque.peekFirst()];
            }else {
                deque.pollFirst();
                while (!deque.isEmpty() && arr[deque.peekLast()] < arr[i]) {
                    deque.pollLast();
                }
                deque.addLast(i);
                res[resI++] = arr[deque.peekFirst()];
            }
        }
        return res;
    }

    // 第二题：队列，max为O(1)
    private ArrayDeque<Integer> queue = new ArrayDeque<>();
    private ArrayDeque<Integer> max = new ArrayDeque<>();

    public void push(int num) {
        queue.addLast(num);
        if (max.isEmpty()) max.addLast(num);
        else if (max.peekLast() >= num) max.addLast(num);
        else {
            int count = 0;
            while (max.peekLast() < num) {
                max.pollLast();
                count++;
            }
            while (count-- != 0) {
                max.addLast(num);
            }
        }
    }

    public int pop() {
         int res = queue.pollFirst();
        max.pollFirst();
        return res;
    }

    public int max() {
        return max.peekFirst();
    }

    public static void main(String[] args) {
        int[] arr = {2,3,4,2,6,2,5,1};
        int[] arr2 = {2,2,2,2,2,2,2,2};
        int[] arr3 = {2,3,4,6,6,2,5,1,5,5,6,3,6,7,8,9,11,5,3,7,3,7,8,3,8,6,8};
        System.out.println(Arrays.toString(window(arr,3)));
        System.out.println(Arrays.toString(window(arr2,3)));
        System.out.println(Arrays.toString(window(arr3,6)));
    }
}
