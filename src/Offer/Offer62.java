package Offer;

public class Offer62 {

    public Node josephousKill(Node head, int m) {
        if (head == null || head.next == head || m < 1) return head;
        Node last = head;
        while (last.next != head) {
            last = last.next;
        }
        while (head != last) {
            for (int i = 1; i <= m; i++) {
                if (i == m) {
                    head = head.next;
                    last.next = head;
                }else {
                    last = head;
                    head = head.next;
                }
            }
        }
        return head;
    }

    //                   0              n=1
    // 解法二:f(n,m) = {
    //                   [f(n-1)+m]%n   n>1
    public int josephousKill2(int n, int m) {
        if (n < 1 || m < 1) return -1;
        int res = 0;
        for (int i = 2; i <= n; i++) {
            res = (res+m)%i;
        }
        return res;
    }
}
