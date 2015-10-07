package src;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

/**
 * Created by vicboma on 03/09/15.
 */
public class CommandMappingValueImpl<T,H> implements api.CommandMappingValue<T,H> {

    private CompletableFuture<H> completableFuture;
    private Callable<T> callable;
    private Runnable runnableFuture;

    public CommandMappingValueImpl(Callable<T> callable) {
        this.completableFuture = new CompletableFuture();
        this.callable = callable;
    }

    public CommandMappingValueImpl(Runnable runnableFuture) {
        this.completableFuture = new CompletableFuture();
        this.runnableFuture = runnableFuture;
    }

    public CompletableFuture<H> getCompletableFuture() {
        return completableFuture;
    }

    public Callable<T> getCallable() { return callable; }

    public Runnable getRunnable() { return runnableFuture; }
}
