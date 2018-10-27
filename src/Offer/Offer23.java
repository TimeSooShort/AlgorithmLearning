package Offer;

/**
 * 返回链表环结构的入口节点
 */
public class Offer23 {

    public Node solve(Node head) {
        if (head == null || head.next == null) return null;
        Node slow = head; // 每次向前进一个
        Node fast = head.next.next; // 每次进两个
        // 若fast追上slow则表明环存在
        while (fast != null) {
            if (fast == slow) break;
            slow = slow.next;
            fast = fast.next;
            if (fast == null) return null;
            fast = fast.next;
        }
        if (fast == null) return null;
        Node mark = fast;
        fast = fast.next;
        int count = 1; // 环长
        // 计算环长度
        while(fast != mark) {
            fast = fast.next;
            count++;
        }
        slow = head;
        fast = head;
        while(--count >= 0) {
            fast = fast.next;
        }
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
