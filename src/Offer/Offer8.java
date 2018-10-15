package Offer;

public class Offer8<T> {

    class Node<T> {
        Node<T> left;
        Node<T> right;
        Node<T> parent;
        T val;

        public Node(T val) {
            this.val = val;
        }
    }

    public Node<T> solve(Node<T> target) {
        assert target != null;

        if (target.right != null) {
            Node<T> head = target.right;
            while(head.left != null) head = head.left;
            return head;
        }
        else if (target.parent == null) return null;
        else if(target.parent.left == target) {
            return target.parent;
        }else {
            Node<T> parent = target.parent;
            Node<T> grand = parent.parent;
            while (grand.right == parent) {
                parent = grand;
                grand = grand.parent;
            }
            return grand;
        }
    }
}
