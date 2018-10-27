package Offer;

public class Offer25 {

    public Node solve(Node n1, Node n2) {
        if (n1 == null || n2 == null) return n1 == null ? n2 : n1;
        Node small = n1.val <= n2.val ? n1 : n2;
        Node result = small;
        Node big = n1.val <= n2.val ? n2 : n1;
        while (big != null) {
            while (small.next != null && small.next.val <= big.val) {
                small = small.next;
            }
            Node nextBig = small.next;
            small.next = big;
            big = nextBig;
        }
        return result;
    }

    public static void main(String[] args) {
        Node node11 = new Node(2);
        Node node12 = new Node(3);
        Node node13 = new Node(6);
        Node node14 = new Node(8);
        node11.next = node12;
        node12.next = node13;
        node13.next = node14;

        Node node21 = new Node(1);
        Node node22 = new Node(2);
        Node node23 = new Node(4);
        Node node24 = new Node(5);
        Node node25 = new Node(9);
        node21.next = node22;
        node22.next = node23;
        node23.next = node24;
        node24.next = node25;

        Offer25 instance = new Offer25();
        Node result = instance.solve(node11, node21);
        while (result != null) {
            System.out.print(result.val + "->");
            result = result.next;
        }
    }
}
