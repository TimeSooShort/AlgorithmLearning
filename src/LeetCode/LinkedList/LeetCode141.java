package LeetCode.LinkedList;

/**
 * Created by Administrator on 2018/1/22.
 */
public class LeetCode141 {
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode oneStep = head;
        ListNode twoStep = head.next;
        while (twoStep != null && twoStep.next != null && twoStep.next.next != null) {
            if (oneStep == twoStep) {
                return true;
            }
            oneStep = oneStep.next;
            twoStep = twoStep.next.next;
        }
        return false;
    }
}
