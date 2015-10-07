package dsl;

import impl.CommandMapper;

import java.util.concurrent.Callable;

/**
 * Created by vicboma on 07/10/15.
 */
public interface CommandDictionary {
    CommandMapper addCommand(String command, Runnable runnable);
    <T,H> CommandMapper addCommand(String command, Callable<T> callable);
}
