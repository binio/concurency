package ThreadLocalExample;

/**
 * Created by tomasz.biniecki on 13/09/2017.
 */
/*
The ThreadLocal class in Java enables you to create variables that can only be read and written by the same thread.
Thus, even if two threads are executing the same code, and the code has a reference to a ThreadLocal variable,
then the two threads cannot see each other's ThreadLocal variables.
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        ThreadLocal myThreadLocal = new ThreadLocal();
        myThreadLocal.set("A thread local value");
        String threadLocalValue = (String) myThreadLocal.get();
        System.out.println("ThreadLocal value: " + threadLocalValue);

        //Generic ThreadLocal
        //no cast needed as above
        ThreadLocal<String> myThreadLocalGeneric = new ThreadLocal<String>();
        myThreadLocalGeneric.set("Generic value");
        System.out.println("ThreadLocal generic value: " + myThreadLocalGeneric.get());


        /*
            full example
            All threads share MyRunnable and through it its ThreadLocal
            run() will change value of ThreadLocal
         */

        MyRunnable sharedRunnableInstance = new MyRunnable();

        Thread thread1 = new Thread(sharedRunnableInstance);
        Thread thread2 = new Thread(sharedRunnableInstance);

        thread1.start();
        thread2.start();

        thread1.join(); //wait for thread 1 to terminate
        thread2.join(); //wait for thread 2 to terminate

    }

    public static class MyRunnable implements Runnable {

        private ThreadLocal<Integer> threadLocal =  new ThreadLocal<Integer>();

        public void run() {
            threadLocal.set( (int) (Math.random() * 100D) );

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }

            System.out.println(threadLocal.get());
        }
    }
}