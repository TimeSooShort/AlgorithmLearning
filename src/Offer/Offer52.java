package Offer;

public class Offer52 {
    public Node solve(Node n1,Node n2) {
        if (n1 == null || n2 == null) return null;
        Node h1 = n1, h2 = n2;
        int l1 = 0, l2 = 0;
        while(h1 != null) {
            h1 = h1.next;
            l1++;
        }
        while(h2 != null) {
            h2 = h2.next;
            l2++;
        }
        if (l1 > l2) {
            for(int i = 0; i < l1-l2; i++) {
                n1 = n1.next;
            }
        }else if (l1 < l2) {
            for(int i = 0; i < l2-l1; i++) {
                n2 = n2.next;
            }
        }
        while (n1 != null && n1 != n2) {
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1;
    }
}
