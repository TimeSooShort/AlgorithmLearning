package LeetCode.LinkedList;

/**
 * Created by Administrator on 2018/1/22.
 */
public class LeetCode148 {
    public ListNode sortList(ListNode head) {
        if(head==null || head.next==null) return head;
        ListNode preMid = null, mid = head, last = head;
        while(last!=null && last.next!=null) {
            preMid = mid;
            mid = mid.next;
            last = last.next.next;
        }
        preMid.next = null;
        mid.next = null;
        ListNode halfBefore = sortList(head);
        ListNode halfAfter = sortList(mid);
        return merge(halfBefore, halfAfter);
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode auxHead = new ListNode(0);
        ListNode temp = auxHead;
        while(left != null && right != null) {
            if(left.val < right.val) {
                temp.next = left;
                left = left.next;
            }
            else {
                temp.next = right;
                right = right.next;
            }
            temp = temp.next;
        }
        if(left != null) {
            temp.next = left;
        }
        if(right != null) {
            temp.next = right;
        }
        return auxHead.next;
    }
}

