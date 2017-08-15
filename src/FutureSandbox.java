import java.util.concurrent.CompletableFuture;

/**
 * Created by tomasz.biniecki on 27/07/2017.
 */
public class FutureSandbox {

    public static void main(String[] args)throws Exception{

        CompletableFuture completableFutureToBeCompleted2 = CompletableFuture.supplyAsync( ( ) -> {
            int l = 0;
            for( int i = 0; i < 23; i++ )
            {
                System.out.println( "i " + i );
                l++;

            }
            return l;
        } );

        CompletableFuture actionOnColplete = CompletableFuture.supplyAsync(() -> {
            String complete = "I am complete !!!";
            return complete;
        });

        CompletableFuture completor = CompletableFuture.supplyAsync( ( ) -> {
            System.out.println( "completing the other" );
            completableFutureToBeCompleted2.complete( 222 );
            return 10;
        } );

        System.out.println( "VALUE 1:" + completableFutureToBeCompleted2.whenComplete((o, o2) -> { actionOnColplete.whenComplete((o1, o21) -> {System.out.println(o1);});}) );
        //System.out.println( "VALUE 2:" +completor.get() );
    }
}
