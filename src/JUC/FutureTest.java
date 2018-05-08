package JUC;

import java.util.concurrent.*;

/**
 * FutureTask一般配合ExecutorService使用
 */
public class FutureTest {

    static class Task implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            System.out.println("Thread " + Thread.currentThread().getName() + " is running");
            int result = 0;
            for(int i = 0; i < 100; i++){
                result += i;
            }
            Thread.sleep(3000);
            return result;
        }
    }

    public static void main(String[] args) {
//        第一种方式：Future + ExecutorService
//        Task task = new Task();
//        ExecutorService executor = Executors.newCachedThreadPool();
//        Future<Integer> future = executor.submit(task);
//        executor.shutdown();

//        第二种方式: FutureTask + ExecutorService
//        Task task = new Task();
//        FutureTask<Integer> futureTask = new FutureTask<>(task);
//        ExecutorService executor = Executors.newCachedThreadPool();
//        executor.submit(futureTask); //将其用作Runnable
//        executor.shutdown();

        //第三种方式：Thread + FutureTask
        Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<>(task);
        Thread thread = new Thread(futureTask);
        thread.setName("task thread");
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread " + Thread.currentThread().getName() + " is running");

        if (!futureTask.isDone()){
            System.out.println("task is not done");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int result = 0;
        try {
            result = futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            throw Throwable2RunEx.launderThrowable(e.getCause());
        }
        System.out.println("result: " + result);
    }
}
