package dsl;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by vicboma on 07/10/15.
 */
public interface CommandExecutable {
    <T,H> CompletableFuture<T> execute(String command);
    <T,H> CompletableFuture<T> executeAsync(String name) throws ExecutionException, InterruptedException;
}
