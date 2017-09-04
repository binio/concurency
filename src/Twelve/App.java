package Twelve;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

/**
 * Created by tomasz.biniecki on 04/09/2017.
 */
public class App {

    public static void main(String[] args) throws Exception {
//        Semaphore semaphore = new Semaphore(1);
//        System.out.println("Semaphore count: "+ semaphore.availablePermits());
//        try {
//            semaphore.acquire();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Semaphore count: "+ semaphore.availablePermits());

        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i=0; i<200; i++) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    //System.out.println("Thread "+currentThread()+" runs!");
                    Connection.getInstance().connect();
                }
            });
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
