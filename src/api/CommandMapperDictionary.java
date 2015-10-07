package api;

import impl.CommandMapperDictionaryImpl;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by vicboma on 07/10/15.
 */
public interface CommandMapperDictionary {
    <T,H> CompletableFuture<T> executeAsync(String name) throws ExecutionException, InterruptedException;

    <T,H> T execute(String name);

    CommandMapperDictionaryImpl addCommand(String command, Runnable runnable);

    <T,H> CommandMapperDictionaryImpl addCommand(String command, Callable<T> callable);

    void remove(String command);

    void clear();

    int size();

    CommandMapperDictionaryImpl once(boolean value);
}
