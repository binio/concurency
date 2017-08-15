import java.time.Clock;
import java.util.concurrent.CompletableFuture;
import java.util.function.BooleanSupplier;

public class Main {

    public static void main(String[] args) {
//        System.out.println("Eager computation!");
//        System.out.println(eagerMatch(compute("bb"), compute("aa")));
//        System.out.println("Lazy computation!");
//        System.out.println(lazyMatch(() -> compute("bb"), () -> compute("aa")));

        //CompletableFuture completableFuture = new CompletableFuture();
        CompletableFuture completableFuture = CompletableFuture.supplyAsync( ( ) -> {
            // big computation task
            Clock clock = Clock.systemDefaultZone();
            long time = clock.millis();
            System.out.println("Clock started: " + time);
            return String.valueOf(time);
            //return "100";
        } );

        String msg = null;
        try{
            msg = (String)completableFuture.get();
        } catch(Exception ex){

        }
        Clock clock = Clock.systemDefaultZone();
        long time = clock.millis();
        System.out.println( "get1 : " + String.valueOf(time));


        System.out.println( "get2 : " + msg);

    }

    static boolean compute(String str) {
        System.out.println("executing...");
        // expensive computation here
        return str.contains("a");
    }

    static String eagerMatch(boolean b1, boolean b2) {
        return b1 && b2 ? "match" : "incompatible!";
    }

    static String lazyMatch(BooleanSupplier a, BooleanSupplier b) {
        return a.getAsBoolean() && b.getAsBoolean()? "match" : "incompatibile!" ;
    }

    public static void pause(){
       try {
           Thread.sleep(5000);
       }catch(Exception ex){}
    }
}
