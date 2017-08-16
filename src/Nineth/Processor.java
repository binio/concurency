package Nineth;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by tomasz.biniecki on 16/08/2017.
 */
public class Processor {

    private LinkedList<Integer> list = new LinkedList<Integer>();
    private final int LIMIT = 10;
    private Object lock = new Object();

    public void produce() throws InterruptedException {
        int value = 0;
        while(true) {
            synchronized (lock) {
                while (list.size() == LIMIT) {
                    lock.wait();
                    System.out.println("full");
                }
                list.add(value++);
                System.out.println("added value: " + value);
                lock.notify();
            }
        }
    }

    public void consume() throws InterruptedException {
        Random random = new Random();

        while(true) {
            synchronized (lock) {
                while(list.size() == 0) {
                    System.out.println("Empty list in consumer");
                    lock.wait();
                }
                System.out.println("List size is: "+ list.size());
                int value = list.removeFirst();
                System.out.println("Removed value is: "+ value);
                lock.notify();
            }

            Thread.sleep(1000);
        }
    }
}
