package Second;

/**
 * Created by tomasz.biniecki on 15/08/2017.
 */
public class Processor extends Thread {

    //careful with caching this variable on some OS
    //make it volatile so is not cached
    private volatile boolean running = true;

    public void run(){
        while(running){
            System.out.println("Hello");

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown(){
        running = false;
    }
}
