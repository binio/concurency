package Twelve;

import java.util.concurrent.Semaphore;

/**
 * Created by tomasz.biniecki on 04/09/2017.
 */
public class Connection {
    private Semaphore semaphore = new Semaphore(10, true);
    private static Connection instance = new Connection();
    private int connections = 0;

    private Connection() {

    }

    public static Connection getInstance() {
        return instance;
    }

    public void connect(){
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try{
            doConnect();
        }
        finally{
            semaphore.release();
        }
    }
    public void doConnect(){

        synchronized (this) {
            connections++;
            System.out.println("Connctions: "+connections);
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this) {
            connections--;
        }

    }
}
