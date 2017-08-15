/**
 * Created by tomasz.biniecki on 15/08/2017.
 */
public class App {


        public static void main(String[] args){
            //First way of starting threads
            ThreadExample te = new ThreadExample();
            ThreadExample te2 = new ThreadExample();
            //te.start();
            //te2.start();

            //Second way of starting threads
            Thread t1 = new Thread(new Runner());
            Thread t2 = new Thread(new Runner());
            t1.start();
            t2.start();

            //Third way of starting threads
            Thread t3 = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i=0; i<10; i++){
                        System.out.println("Hello from method 3: "+i);
                        try{
                            Thread.sleep(600);

                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }
            });
            t3.start();

        }

}
