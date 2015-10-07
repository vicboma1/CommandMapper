package src;

import api.CommandMapperDictionary;
import dsl.*;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by vicboma on 07/10/15.
 */
public class CommandMapper implements CommandCollection, CommandUnmapper, CommandConfigurator, CommandDictionary, CommandExecutable {


    private CommandMapperDictionary commandMapperDictionary;

    public static CommandMapper create(CommandMapperDictionary commandMapperDictionary){
        return new CommandMapper(commandMapperDictionary);
    }

    public CommandMapper(CommandMapperDictionary commandMapperDictionary) {
        this.commandMapperDictionary = commandMapperDictionary;
    }

    @Override
    public <T,H> CompletableFuture<T> executeAsync(String name) throws ExecutionException, InterruptedException {
        return commandMapperDictionary.executeAsync(name);
    }

    @Override
    public <T,H> CompletableFuture<T> execute(String name) {
        return commandMapperDictionary.execute(name);
    }

    @Override
    public CommandMapper addCommand(String command, Runnable runnable){
        commandMapperDictionary.addCommand(command, runnable);
        return this;
    }

    @Override
    public <T,H> CommandMapper addCommand(String command, Callable<T> callable){
        commandMapperDictionary.addCommand(command, callable);
        return this;
    }

    @Override
    public void umMapper(String command) {
        commandMapperDictionary.remove(command);
    }

    @Override
    public void umMapperAll() {
        commandMapperDictionary.clear();
    }

    @Override
    public int size() { return commandMapperDictionary.size(); }

    @Override
    public CommandConfigurator once(boolean value) {
        commandMapperDictionary.once(value);
        return this;
    }

}