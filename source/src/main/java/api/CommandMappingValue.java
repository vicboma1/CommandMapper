package api;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

/**
 * Created by vicboma on 03/09/15.
 */
public interface CommandMappingValue<H,T> {

    CompletableFuture<T> getCompletableFuture();
    Callable<H> getCallable();
    Runnable getRunnable();

}
