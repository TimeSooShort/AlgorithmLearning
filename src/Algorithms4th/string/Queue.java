package Algorithms4th.string;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Administrator on 2018/2/22.
 */
public class Queue<Item> implements Iterable<Item> {

    private Node<Item> first;
    private Node<Item> last;
    private int n;

    private static class Node<Item>{
        private Item value;
        private Node<Item> next;
    }

    //isEmpty, size, peek, enqueue, dequeue, toString

    public boolean isEmpty(){
        return first == null;
    }
    public int size(){
        return n;
    }
    public Item peek(){
        if (isEmpty()) throw new NoSuchElementException("queue underflow");
        return first.value;
    }

    //queue先进先出，对应链表的插入到最后和删除first
    public void equeue(Item item){
        Node oldLast = last;
        last = new Node<Item>();
        last.value = item;
        last.next = null;
        if (oldLast == null) first = last;
        else oldLast.next = last;
        n++;
    }
    public Item dequeue(){
        if (isEmpty()) throw new NoSuchElementException("queue underflow");
        Node oldFirst = first;
        first = first.next;
        n--;
        if (isEmpty()) last = null;
        return (Item) oldFirst.value;
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();
        for (Item item : this){
            builder.append(item);
            builder.append(" ");
        }
        return builder.toString();
    }

    private class ListIterator<Item> implements Iterator<Item>{
        private Node<Item> current;

        ListIterator(Node<Item> first) {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
//            if (!hasNext()) throw new NoSuchElementException("empty");
            Item item = current.value;
            current = current.next;
            return item;
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator<>(first);
    }
}
