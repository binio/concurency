package Second;

import java.util.Scanner;

/**
 * Created by tomasz.biniecki on 15/08/2017.
 */
public class App {
    public static void main(String[] args){
        Processor p = new Processor();
        p.start();

        System.out.println("hit return to stop...");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        p.shutdown();
    }
}
