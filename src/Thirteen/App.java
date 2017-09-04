package Thirteen;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by tomasz.biniecki on 04/09/2017.
 */
public class App {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

//        executor.submit(new Runnable() {
//            @Override
//            public void run() {
//                Random random = new Random();
//                int duration = random.nextInt(4000);
//                System.out.println("Starting...");
//                try {
//                    Thread.sleep(duration);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("Finished.");
//            }
//        });
        Future<Integer> future = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Random random = new Random();
                int duration = random.nextInt(4000);

                if(duration > 2000){
                    throw new IOException("Sleeping for to long");
                }

                System.out.println("Starting...");
                try {
                    Thread.sleep(duration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Finished.");

                return duration;
            }
        });
        executor.shutdown();
        try {
            System.out.println("Future value: " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
}
