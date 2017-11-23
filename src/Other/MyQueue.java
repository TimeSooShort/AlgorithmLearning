package Other;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyQueue<Item> implements Iterable<Item> {
	private Node<Item> first;
	private Node<Item> last;
	private int n;

	public MyQueue() {
		first = null;
		last = null;
		n = 0;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	public int size() {
		return n;
	}
	
	private static class Node<Item> {
		private Item item;
		private Node<Item> next;
	}
	
	public void enqueue(Item item) {
		Node<Item> oldLast = last;
		last = new Node<>();
		last.item = item;
		last.next = null;
		if (isEmpty()) first = last;
		else oldLast.next = last;
		n++;
	}
	
	public Item dequeue() {
		if (isEmpty()) throw new NoSuchElementException("call dequeue() with empty queue");
		Item item = first.item;
		first = first.next;
		n--;
		if (isEmpty()) last = null;
		return item;
	}
	
	public Item peek() {
		if (isEmpty()) throw new NoSuchElementException("call peek() with empty queue");
		return first.item;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Item item: this) {
			builder.append(item);
			builder.append(" ");
		}
		return builder.toString();
	}

	@Override
	public Iterator<Item> iterator() {
		return new QueueIterator<Item>(first);
	}
	private class QueueIterator<Item> implements Iterator<Item> {
		private Node<Item> current;
		
		public QueueIterator(Node<Item> first) {
			current = first;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
		
	}

}
