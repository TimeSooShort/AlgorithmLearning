package TestSomething.JUC.ReadWrite;

public class ReadWriteLock {

    private int reader;
    private int write;
    private int writeRequests;

    public synchronized void lockRead() throws InterruptedException {
        while (write > 0 || writeRequests > 0) {
            wait();
        }
        reader++;
    }

    public synchronized void unlockRead() {
        reader--;
        notifyAll();
    }

    public synchronized void lockWrite() throws InterruptedException {
        writeRequests++;
        while (reader > 0 || write > 0) {
            wait();
        }
        writeRequests--;
        write++;
    }

    public synchronized void unlockWrite() {
        write--;
        notifyAll();
    }
}
