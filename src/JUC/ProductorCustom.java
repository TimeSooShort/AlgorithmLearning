package JUC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者
 * @param <T>
 */
public class ProductorCustom<T> {

    private final ReentrantLock lock = new ReentrantLock();
    private Condition putButFull = lock.newCondition();
    private Condition tackButEmpty = lock.newCondition();
    private int head, tail, count;
    private final T[] items;

    public ProductorCustom() {
        this(10);
    }

    public ProductorCustom(int maxSize) {
        items = (T[]) new Object[maxSize];
    }

    public void put(T item) throws InterruptedException {
        lock.lock();
        try {
            while(count == items.length) {
                putButFull.await();
            }
            items[tail] = item;
            if (++tail == items.length) {
                tail = 0;
            }
            ++count;
            tackButEmpty.signal();
        }
        finally {
            lock.unlock();
        }
    }

    public T tack() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                tackButEmpty.await();
            }
            T item = items[head];
            if (++head == items.length) {
                head = 0;
            }
            --count;
            putButFull.signal();
            return item;
        }
        finally {
            lock.unlock();
        }
    }
}
