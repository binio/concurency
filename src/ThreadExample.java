import static java.lang.Thread.*;

/**
 * Created by tomasz.biniecki on 15/08/2017.
 */
public class ThreadExample extends Thread {
    public void run(){
        for(int i=0; i<10; i++){
            System.out.println("Hello "+i);
            try{
                sleep(500);

            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}


