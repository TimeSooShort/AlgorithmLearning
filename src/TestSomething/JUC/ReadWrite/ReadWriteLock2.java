package TestSomething.JUC.ReadWrite;

import java.util.HashMap;
import java.util.Map;

/**
 * 读重入：没有写操作或写操作请求，则读操作获得权限；如果是一个运行中的读操作可以再此获得读操作权限，无视写请求
 * 写重入：如果没有读操作或者写操作，则写操作获得权限；
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
        int count = readingThreads.get(callingThread);
        if (count == 1) {
            readingThreads.remove(callingThread);
        }else {
            readingThreads.put(callingThread, count - 1);
        }
        notifyAll();
    }

    private boolean canGetReadAccess(Thread thread) {
        if (writeAccess > 0) return false;
        if (isReading(thread)) return true;
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
        writeAccess--;
        if (writeAccess == 0) {
            writingThread = null;
        }
        notifyAll();
    }

    private boolean canGetWriteAccess(Thread callingThread) {
        if (hasReader()) return false;
        if (writingThread == null) return true;
        if (isWriting(callingThread)) return true;
        return true;
    }

    private boolean isWriting(Thread callingThread) {
        return callingThread == writingThread;
    }

    private boolean hasReader() {
        return readingThreads.size() > 0;
    }
}
