package Offer;

import java.util.Comparator;

/**
 * 实现一个栈，要求pop, push, min三个操作皆为O（1）复杂度
 * @param <Value>
 */
public class Offer30<Value> {

    private class Node<Value> {
        Node<Value> next;
        Value val;

        public Node(Value val) {
            this.val = val;
        }
    }

    private Node<Value> head; // 主栈
    private Node<Value> minHead; // 辅助栈，若新元素比栈顶小则压入，否则压入与栈顶相同的值。
    private Comparator<? super Value> comparator; // 比较器

    public Offer30(Comparator<? super Value> comparator) {
        this.comparator = comparator;
    }

    public Offer30() {
    }

    public void push(Value value) {
        Value minValue = value; // 代表新值与栈顶值中小的那个
        if (minHead != null) {
            if (comparator == null) {
                @SuppressWarnings("unchecked")
                Comparable<? super Value> val = (Comparable<? super Value>) value;
                minValue = val.compareTo(minHead.val) > 0 ? minHead.val : minValue;
            }else {
                minValue = comparator.compare(value, minHead.val) <= 0 ? minValue : minHead.val;
            }
        }

        Node<Value> old = head;
        head = new Node<>(value);
        head.next = old;

        Node<Value> minOld = minHead;
        minHead = new Node<>(minValue);
        minHead.next = minOld;
    }

    public Value pop() {
        if (head == null) throw new IllegalStateException("stake is empty");

        // 辅助站也同步删除栈顶元素
        Node<Value> minNext = minHead;
        minHead.next = null;
        minHead = minNext;

        Node<Value> res = head;
        Node<Value> next = head.next;
        head.next = null; // help GC
        head = next;
        return res.val;
    }

    public Value min() {
        if (minHead == null) {
            throw new IllegalStateException("stack is empty");
        }
        return minHead.val;
    }
}
