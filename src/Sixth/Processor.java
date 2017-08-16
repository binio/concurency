package Sixth;

import java.util.concurrent.CountDownLatch;

/**
 * Created by tomasz.biniecki on 16/08/2017.
 */
public class Processor implements Runnable{

    private CountDownLatch latch;

    public Processor(CountDownLatch latch){
        this.latch = latch;
    }
    @Override
    public void run() {
        System.out.println("Started..."+ latch.getCount());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
        System.out.println("Counted down..."+ latch.getCount());
    }
}
