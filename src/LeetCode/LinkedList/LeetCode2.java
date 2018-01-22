package LeetCode.LinkedList;

/**
 * Created by Administrator on 2018/1/22.
 */
public class LeetCode2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // int sum = 0;
        // int further = 0;
        // while(l1 != null && l2 != null){
        //     int add = l1.val + l2.val + further;
        //     further = add >= 10 ? 1 : 0;
        //     sum = (add % 10) * 10 + sum;
        //     l1 = l1.next;
        //     l2 = l2.next;
        // }
        // ListNode rest = l1 == null ? l2 : l1;
        // while(rest != null) {
        //     sum = sum + rest.val * 10;
        //     rest = rest.next;
        // }
        // return sum;
//....................................................
        // int further = 0;
        // ListNode dummy = new ListNode(0);
        // ListNode cur = dummy;
        // while(l1 != null && l2 != null) {
        //     int add = l1.val + l2.val + further;
        //     further = add >= 10 ? 1 : 0;
        //     cur.next = new ListNode(add%10);
        //     cur = cur.next;
        //     l1 = l1.next;
        //     l2 = l2.next;
        // }
        // ListNode rest = l1 == null ? l2 : l1;
        // while (rest != null){
        //     int add = rest.val + further;
        //     further = add >= 10 ? 1 : 0;
        //     cur.next = new ListNode(add%10);
        //     cur = cur.next;
        //     rest = rest.next;
        // }
        // if (further == 1){
        //     cur.next = new ListNode(1);
        // }
        // return dummy.next;
//..........................................
        int further = 0;
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null || l2 != null || further != 0) {
            int sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + further;
            cur.next = new ListNode(sum%10);
            further = sum / 10;
            cur = cur.next;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        return dummy.next;
    }
}
