package Offer;

/**
 * 返回链表环结构的入口节点
 */
public class Offer23 {

    public Node solve(Node head) {
        if (head == null || head.next == null) return null;
        Node fast = head;
        Node slow = head;
        do {
            fast = fast.next;
            if (fast == null) return null;
            fast = fast.next;
            slow = slow.next;
        } while (slow != fast && fast != null);
        if (fast == null) return null;
        slow = head;
        while(slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
