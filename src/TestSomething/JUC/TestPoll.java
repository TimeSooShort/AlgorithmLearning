package TestSomething.JUC;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class TestPoll {

    private final Map<Thread, Integer> readerCountThread = new HashMap<>();
    private int reader;
    private int writer;
    private int writeRequest;

    public synchronized void lockRead() throws InterruptedException {
        Thread currentThread = Thread.currentThread();
        if (!canGetReader(currentThread)) {
            wait();
        }
        readerCountThread.put(currentThread, getTheReaderReentrantCount(currentThread)+1);
    }

    public synchronized void unlockRead() {
        Thread currentRead = Thread.currentThread();
        int currentReaderThreadCount = getTheReaderReentrantCount(currentRead);
        if (currentReaderThreadCount == 1) {
            readerCountThread.remove(currentRead);
        }else{
            readerCountThread.put(currentRead, currentReaderThreadCount-1);
        }
        notifyAll();
    }

    public synchronized void lockWrite() throws InterruptedException {
        writeRequest++;
        if (canGetWriter()) {
            wait();
        }
        writeRequest--;
        writer++;
    }

    public synchronized void unlockWrite() {
        writer--;
        notifyAll();
    }

    private boolean canGetReader(Thread currentThread) {
        if (writer > 0) return false;
        if (isReading(currentThread)) return true;
        if (writeRequest > 0) return false;
        return true;
    }

    private boolean isReading(Thread currentThread) {
        return readerCountThread.get(currentThread) != null;
    }

    private int getTheReaderReentrantCount(Thread readerThread) {
        Integer readerCount = readerCountThread.get(readerThread);
        if (readerCount == null) return 0;
        return readerCount;
    }

    private boolean canGetWriter() {
        if (reader > 0) return false;
        if (writer > 0) return false;
        return true;
    }
}
