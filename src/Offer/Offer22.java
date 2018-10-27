package Offer;

public class Offer22 {

    public Node solve(Node head, int k) {
        if (head == null || k <= 0) return null;
        Node slow = head;
        Node faster = head;
        for (int i = 1; i < k; i++) {
            if (faster.next == null) return null;
            faster = faster.next;
        }
        while (faster.next != null) {
            faster = faster.next;
            slow = slow.next;
        }
        return slow;
    }
}
