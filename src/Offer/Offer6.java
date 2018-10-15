package Offer;

import java.util.ArrayDeque;
import java.util.Iterator;

public class Offer6 {

    class Node {
        public Node next;
        public int val;

        public Node(int val) {
            this.val = val;
        }
    }

    public Node create(int val) {
        return new Node(val);
    }

    public void printReverseLinked(Node root) {
        if (root == null) return;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        while (root != null) {
            stack.add(root.val);
            root = root.next;
        }
        Iterator<Integer> iterator = stack.descendingIterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
    }

    // 方法2：用递归来实现
    public void printReverseLinked2(Node root) {
        if (root.next != null) printReverseLinked2(root.next);
        System.out.print(root.val + " ");
    }

    public static void main(String[] args) {
        Offer6 instance = new Offer6();
        Node root1 = instance.create(1);
        Node root2 = instance.create(2);
        Node root3= instance.create(3);
        root1.next = root2;
        root2.next = root3;
        instance.printReverseLinked2(root1);
    }
}
