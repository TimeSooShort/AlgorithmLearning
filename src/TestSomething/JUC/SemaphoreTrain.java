package TestSomething.JUC;

import java.util.concurrent.Semaphore;

public class SemaphoreTrain {

    static class Worker extends Thread {
        private int n;
        private Semaphore semaphore;

        public Worker(int n, Semaphore semaphore) {
            this.n = n;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("Worker num " + n + " use machine");
                Thread.sleep(2000);
                System.out.println("Worker num " + n + " stop use");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int worker = 6;    //工人数
        int machine = 4;  //机器数
        Semaphore semaphore = new Semaphore(machine);
        for (int i = 0; i < worker; i++) {
            new Worker(i, semaphore).start();
        }
    }
}
