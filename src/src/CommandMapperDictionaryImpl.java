package src;

import api.CommandMapperDictionary;
import api.CommandMappingValue;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by vicboma on 07/10/15.
 */
public class CommandMapperDictionaryImpl implements CommandMapperDictionary {

    private ExecutorService executor;
    private boolean one;

    private Map<String,CommandMappingValue> mapping =  new HashMap();

    public static CommandMapperDictionaryImpl create(ExecutorService executor){
        return new CommandMapperDictionaryImpl(executor);
    }

    public CommandMapperDictionaryImpl(ExecutorService executor) {
        this.executor = executor;
    }

    @Override
    public <T,H> CompletableFuture<T> executeAsync(String name) throws ExecutionException, InterruptedException {
        final CommandMappingValue<T,H>  deferredAsync  = getDeferredAsync(name);
        if(deferredAsync == null)
            return null;

        final Callable<T> callable = deferredAsync.getCallable();

        final Future future = (callable != null)
            ? executor.submit(callable)
            : executor.submit(deferredAsync.getRunnable());

        return CompletableFuture.completedFuture((T)future.get());
    }

    @Override
    public <T,H> T execute(String name) {
        final CommandMappingValue<T,H>  deferredAsync  = getDeferredAsync(name);
        if(deferredAsync == null)
            return null;

        final Callable<T> callable = deferredAsync.getCallable();

        T result = null;
        if(callable != null)
            try {
                result =  callable.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        else
        {
            deferredAsync.getRunnable().run();
            result = (T) new Object();
        }

        return result;
    }

    private <T, H> CommandMappingValue<T, H> getDeferredAsync(String name) {
        return (one)
                ? mapping.remove(name)
                : (CommandMappingValue<T,H>)mapping.get(name);
    }


    @Override
    public CommandMapperDictionaryImpl addCommand(String command, Runnable runnable){
        final CommandMappingValue commandMappingValue = new CommandMappingValueImpl(runnable);
        mapping.put(command, commandMappingValue);
        return this;
    }

    @Override
    public <T,H> CommandMapperDictionaryImpl addCommand(String command, Callable<T> callable){
        final CommandMappingValue commandMappingValue = new CommandMappingValueImpl(callable);
        mapping.put(command, commandMappingValue);
        return this;
    }

    @Override
    public void remove(String command) {
          mapping.remove(command);
    }

    @Override
    public void clear() {
        mapping.clear();
    }

    @Override
    public int size() { return this.mapping.size(); }

    @Override
    public CommandMapperDictionaryImpl once(boolean value) {
        one = value;
        return this;
    }

}