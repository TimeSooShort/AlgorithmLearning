package Offer;

import java.util.List;

public class TryAgain {

    int solve3(int[] arr) {
        int i = 0;
        while (i < arr.length) {
            int a = arr[i], b = arr[arr[i]];
            if (a == i) i++;
            else if (a == b) return a;
            else {
                arr[i] = b;
                arr[a] = a;
            }
        }
        return -1;
    }

    int solve3With2(int[] arr) {
        int lo = 1, hi = arr.length-1;
        while (lo <= hi) {
            int mid = ((hi-lo) >> 2)+lo;
            int leftLength = mid-lo+1;
            int lessNum = countLessEqualNum(arr, lo, mid);
            if (lo == hi) {
                if (lessNum > 1) return lo;
                break;
            }
            if (lessNum > leftLength) hi = mid;
            else lo = mid+1;
        }
        return -1;
    }

    private int countLessEqualNum(int[] arr, int lo, int mid) {
        int count = 0;
        for (int num : arr) {
            if (num >= lo && num <= mid) count++;
        }
        return count;
    }

    boolean solve4(int[][] matrix, int target) {
        int row = matrix.length, col = matrix[0].length;
        int rowI = 0, colI = col-1;
        while (rowI < row && colI > 0) {
            int num = matrix[rowI][colI];
            if (num < target) rowI++;
            else if (num > target) colI--;
            else return true;
        }
        return false;
    }


    void solve6(Node head) {
        Node newHead = reverse(head);
        Node cur = newHead;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        reverse(newHead);
    }

    private Node reverse(Node head) {
        Node pre = null;
        while (head != null) {
            Node next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }



    public static void main(String[] args) {
        TryAgain instance = new TryAgain();
        int[] arr = {2,3,5,4,3,2,6,7};
        System.out.println(instance.solve3With2(arr));
        System.out.println(instance.solve3(arr));
    }
}
