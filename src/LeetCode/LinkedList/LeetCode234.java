package LeetCode.LinkedList;

/**
 * Created by Administrator on 2018/1/22.
 */
public class LeetCode234 {
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) return true;
        ListNode cur = head;
        ListNode mid = head;
        while(cur.next != null && cur.next.next != null) {
            mid = mid.next;
            cur = cur.next.next;
        }
        ListNode right = mid.next;
        mid.next = null;
        ListNode pre = mid, next = right;
        while(next != null) {
            next = next.next;
            right.next = pre;
            pre = right;
            right = next;
        }
        ListNode leftStart = head, rightStart = pre;
        while(leftStart != null){
            if(leftStart.val != rightStart.val) return false;
            leftStart = leftStart.next;
            rightStart = rightStart.next;
        }
        return true;
    }
}
