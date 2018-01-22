package LeetCode.TwoPointer;

/**
 * Created by Administrator on 2018/1/22.
 */
public class LeetCode142 {
    class ListNode{
        ListNode next;
        int val;

        public ListNode(int val) {
            this.val = val;
        }
    }
    public ListNode detectCycle(ListNode head) {
        //     ListNode low = head, faster = head;
        //     while (faster != null && faster.next != null){
        //         faster = faster.next.next;
        //         low = low.next;
        //         if (faster == low) break;
        //     }
        //     if (faster == null || faster.next == null) return null;
        //     faster = faster.next.next;
        //     low = low.next;
        //     int length = 1;
        //     while (faster != low) {
        //         faster = faster.next.next;
        //         low = low.next;
        //         length++;
        //     }
        //     low = faster = head;
        //     for (int i = 0; i < length; i++) {
        //         faster = faster.next;
        //     }
        //     while (low != faster) {
        //         low = low.next;
        //         faster = faster.next;
        //     }
        //     return low; //faster == low
        // }
        if (head == null || head.next == null) return null;
        ListNode low = head, faster = head;
        do {
            low = low.next;
            faster = faster.next.next;
            if (faster == null || faster.next == null) return null;
        }while (low != faster);
        while (head != low) {
            head = head.next;
            low = low.next;
        }
        return head;
    }
}
