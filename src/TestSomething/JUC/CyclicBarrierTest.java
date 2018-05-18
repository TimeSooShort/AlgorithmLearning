package TestSomething.JUC;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    static class Writer extends Thread {
        private CyclicBarrier barrier;

        public Writer(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            System.out.println("Thread name " + Thread.currentThread().getName() + " is writing");
            try {
                Thread.sleep(5000);
                System.out.println("Thread name " + Thread.currentThread().getName() + " write over");
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("All thread is done," +Thread.currentThread().getName() + " can start forward");
        }
    }

    public static void main(String[] args) {
        final int n = 4;
        final CyclicBarrier barrier = new CyclicBarrier(n,
                () -> System.out.println("Choose " + Thread.currentThread().getName() +" thread to do this mission"));
        for (int i = 0; i < n; i++) {
            new Writer(barrier).start();
        }
    }
}
