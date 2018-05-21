package TestSomething.JUC;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueTest {

    private static class Producer implements Runnable {

        private final BlockingQueue<Integer> queue;
        private volatile boolean stop;
        private final Random random;

        Producer(BlockingQueue<Integer> queue) {
            this.queue = queue;
            this.stop = false;
            this.random = new Random();
        }

        @Override
        public void run() {
            int message;
            try {
                while (!stop) {
                    message = random.nextInt(100);
                    System.out.println("data " + message + "in queue");
                    if (!queue.offer(message, 2, TimeUnit.SECONDS)) {
                        System.out.println("加入队列失败");
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        private void stop() {
            stop = true;
        }
    }

    private static class Taker implements Runnable {

        private final BlockingQueue<Integer> queue;
        private volatile boolean stop;

        Taker(BlockingQueue<Integer> queue) {
            this.queue = queue;
            this.stop = false;
        }

        @Override
        public void run() {
            while (!stop) {
                Integer message;
                try {
                    message = queue.poll(2, TimeUnit.SECONDS);
                    if (message != null) {
                        System.out.println(Thread.currentThread().getName() + " take element " + message);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        private void stop() {
            stop = true;
        }
    }

    public static void main(String[] args) {
        final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
        Producer producer = new Producer(queue);
        Taker taker = new Taker(queue);

        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                new Thread(producer).start();
            }else {
                new Thread(taker).start();
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        producer.stop();
        taker.stop();
    }

}
