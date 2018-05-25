package TestSomething.JUC.ReadWrite;

import java.util.HashMap;
import java.util.Map;

/**
 * 读重入：没有写操作或写操作请求，则读操作获得权限；如果是一个运行中的读操作可以再此获得读操作权限，无视写请求
 * 写重入：如果没有读操作或者写操作，则写操作获得权限；
 * 读写重入：只有一个读线程的情况下，允许该线程获取写权限
 * 写读重入：写线程执行时，其他线程都得等待，所以并没有什么不安全
 *
 * 这里所设计的读与写获取的都是该类对象的锁，在JUC中的读写锁更加强大，它将锁的粒度分开，利用AQS
 */
public class ReadWriteLock2 {

    private final Map<Thread, Integer> readingThreads = new HashMap<>(); //存放的是获取读锁的线程
    private int writeAccess;  //记录获取写锁的线程个数
    private int writeRequests; //请求写锁的线程个数
    private Thread writingThread; //当前获取读锁线程

    public synchronized void lockRead() throws InterruptedException {
        Thread callingThread = Thread.currentThread();
        while (!canGetReadAccess(callingThread)) {
            wait();
        }
        readingThreads.put(callingThread, getReadAccessCount(callingThread) + 1);
    }

    public synchronized void unlockRead() {
        Thread callingThread = Thread.currentThread();
        if (!isReading(callingThread)) {
            throw new IllegalMonitorStateException("该线程并没有获得该实例的读锁");
        }
        int count = getReadAccessCount(callingThread);
        if (count == 1) {
            readingThreads.remove(callingThread);
        }else {
            readingThreads.put(callingThread, count - 1);
        }
        notifyAll();
    }

    private boolean canGetReadAccess(Thread thread) {
        if (isWriting(thread)) return true;  //写线程运行时一定只有它一个线程运行，所以并没有危险
        if (writeAccess > 0) return false;
        if (isReading(thread)) return true;  //读重入，放在写请求判断前，确保优先级比它高
        if (writeRequests > 0) return false;
        return true;
    }

    private boolean isReading(Thread thread) {
        return readingThreads.get(thread) != null;
    }

    private int getReadAccessCount(Thread thread) {
        Integer count = readingThreads.get(thread);
        if (count == null) return 0;
        return count;
    }

    //-----------------------写------------------------------------

    public synchronized void lockWrite() throws InterruptedException {
        writeRequests++;
        Thread callingThread = Thread.currentThread();
        while (!canGetWriteAccess(callingThread)) {
            wait();
        }
        writeRequests--;
        writeAccess++;
        writingThread = callingThread;
    }

    public synchronized void unlockWrite() {
        if (!isWriting(Thread.currentThread())) {
            throw new IllegalMonitorStateException("当前线程没有持有该对象的写锁");
        }
        writeAccess--;
        if (writeAccess == 0) {
            writingThread = null;
        }
        notifyAll();
    }

    private boolean canGetWriteAccess(Thread callingThread) {
        if (isOnlyReader(callingThread)) return true; //读写重入
        if (hasReader()) return false;
        if (writingThread == null) return true;
        if (isWriting(callingThread)) return true;
        return false;
    }

    private boolean isWriting(Thread callingThread) {
        return callingThread == writingThread;
    }

    private boolean hasReader() {
        return readingThreads.size() > 0;
    }

    private boolean isOnlyReader(Thread callingThread) {
        return readingThreads.size() == 1 &&
                readingThreads.get(callingThread) != null;
    }
}
