package Offer;

import java.util.ArrayDeque;
import java.util.Stack;

public class Offer9<T> {

    ArrayDeque<T> stackPush = new ArrayDeque<>();
    ArrayDeque<T> stackPop = new ArrayDeque<>();

    public void appendTail(T value) {
        stackPush.addLast(value);
    }

    public T deleteHead() {
        if (stackPush.isEmpty() && stackPop.isEmpty()) {
            throw new RuntimeException("queue is empty");
        }
        if (stackPop.isEmpty()) {
            while (!stackPush.isEmpty()) {
                stackPop.addLast(stackPush.pollLast());
            }
        }
        return stackPop.pollLast();
    }
}
