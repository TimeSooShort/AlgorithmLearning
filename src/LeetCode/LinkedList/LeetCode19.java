package LeetCode.LinkedList;

/**
 * Created by Administrator on 2018/1/22.
 */
public class LeetCode19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null) return head;
        ListNode node1 = head;
        int number = n;
        while(node1 != null) {
            number--;
            node1 = node1.next;
        }
        node1 = head;
        // if(number > 1) return null;
        if(number == 0) return head.next;
        while(number < -1) {
            node1 = node1.next;
            number++;
        }
        node1.next = node1.next.next;
        return head;
    }
}
