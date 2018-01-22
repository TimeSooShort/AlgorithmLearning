package LeetCode.LinkedList;

/**
 * Created by Administrator on 2018/1/22.
 */
public class LeetCode206 {
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode pre = null, next = head;
        while(next != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
