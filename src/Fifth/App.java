package Fifth;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by tomasz.biniecki on 15/08/2017.
 */
public class App {
    public static void main(String[] args){
        ExecutorService executor = Executors.newFixedThreadPool(2);
        for(int i=0; i<5; i++){
            executor.submit(new Processor(i));
        }
        executor.shutdown();
        System.out.println("All task submitted");

        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All task completed");
    }
}
