import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by tomasz.biniecki on 11/08/2017.
 */
public class SimpleExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException{
        CompletableFuture<String> ask = ask();
        ask.thenApply(Integer::parseInt);
        ask.thenApply(t -> decorate(t));
        ask.thenAcceptAsync(t -> System.out.println(t));
        System.out.println(ask);
        System.out.println(ask.complete("42"));
        System.out.println(ask.completedFuture("3"));
        System.out.println(ask);

        System.out.println("---------------------------------");

        CompletableFuture<String> secondFuture = secondFuture();
        secondFuture.thenApply(r -> {System.out.println("get finishes"+r); return r;});
        secondFuture.get();
        System.out.println(secondFuture);
        System.out.println(secondFuture.complete("42"));
        Thread.sleep(500);
        System.out.println(secondFuture);
        System.out.println(secondFuture.completedFuture("3"));
    }

    public static String decorate(String a){
        return "---"+a+"---";
    }
    public static CompletableFuture<String> ask() {
        final CompletableFuture<String> future = new CompletableFuture<>();
        //...
        return future;
    }

    public static CompletableFuture<String> secondFuture() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        final CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            //...long running...
            int a = 100;
            double z = 1;
            for(int i =0; i<a ; i++){
                double k = 1234.645678;
                z = z + (k/z*i);
            }
            return Double.toString(z);
        }, executorService);
        return future;
    }
}
