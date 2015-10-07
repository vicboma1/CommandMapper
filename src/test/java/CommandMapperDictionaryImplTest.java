import api.CommandMapperDictionary;
import impl.CommandMapperDictionaryImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * Created by vicboma on 07/10/15.
 */
public class CommandMapperDictionaryImplTest {

    private CommandMapperDictionary commandMapperDictionary;

    @Before
    public void setUp() throws Exception {
        commandMapperDictionary = CommandMapperDictionaryImpl.create(Executors.newSingleThreadExecutor());
    }

    @After
    public void tearDown() throws Exception {
        commandMapperDictionary = null;
    }

    @Test
    public void testExecuteNull() throws Exception {

        final CompletableFuture<Object> command = commandMapperDictionary.execute("command");
        assertTrue(command == null);
    }


    @Test
    public void testExecuteRunnableWithParams() throws Exception {
        final String command = "command";
        final int expected = 1;
        final Object expecterObj = new Object();
        int [] count = new int[]{0};

        final Runnable runnable = (Runnable) () -> {
            ++count[0];
        };
        commandMapperDictionary.addCommand(command, runnable);

        final Object execute = commandMapperDictionary.execute(command);

        assertTrue(command != null);
        assertTrue(count[0] == expected);
        assertEquals(execute.getClass(), expecterObj.getClass());
    }

    @Test
    public void testExecuteRunnableWithParamsAsync() throws Exception {
        final String command = "command";
        final int expected = 1;
        final Object expecterObj = new Object();
        int [] count = new int[]{0};

        commandMapperDictionary.addCommand(command, (Runnable) () -> {
            ++count[0];
        });

        final CompletableFuture<Object> completableFuture = commandMapperDictionary.executeAsync(command);
        final Object execute = completableFuture.get();

        assertTrue(command != null);
        assertTrue(count[0] == expected);
        assertNull(execute);
    }

    @Test
    public void testExecuteCallableWithParams() throws Exception {
        final String command = "command";
        final int expected = 1;
        int [] count = new int[]{0};

        commandMapperDictionary.addCommand(command, (Callable<Integer>) () -> {
            ++count[0];
            return count[0];
        });

        final Integer result = commandMapperDictionary.execute(command);

        assertTrue(command != null);
        assertTrue(result == expected);
    }

    @Test
    public void testExecuteCallableWithParamsAsync() throws Exception {
        final String command = "command";
        final int expected = 1;
        int [] count = new int[]{0};

        commandMapperDictionary.addCommand(command, (Callable<Integer>) () -> {
            ++count[0];
            return count[0];
        });

        final CompletableFuture<Integer> completableFuture = commandMapperDictionary.executeAsync(command);
        final Integer result = completableFuture.get();

        assertTrue(command != null);
        assertTrue(result == expected);
    }

    @Test
    public void testAddCommandRunnable() throws Exception {
        final String command = "command";
        final int expected = 1;

        commandMapperDictionary.addCommand(command, () -> {
        });
        assertTrue(commandMapperDictionary.size() == expected);
    }

    @Test
    public void testAddCommandCallable() throws Exception {
        final String command = "command";
        final int expected = 1;

        commandMapperDictionary.addCommand(command, () -> {
            return this;
        });
        commandMapperDictionary.addCommand(command, () -> {
            return this;
        });

        assertTrue(commandMapperDictionary.size() == expected);
    }

    @Test
    public void testExecuteRunnableWithParamsOne() throws Exception {
        final String command = "command";
        final int expected = 1;
        int [] count = new int[]{0};

        commandMapperDictionary.once(true);
        commandMapperDictionary.addCommand(command, (Runnable) () -> {
            ++count[0];
        });

        final Object result = commandMapperDictionary.execute(command);

        assertTrue(command != null);
        assertTrue(count[0] == expected);
        assertTrue(commandMapperDictionary.size() == 0);
        assertNotNull(result);
    }

    @Test
    public void testExecuteCallableWithParamsOne() throws Exception {
        final String command = "command";
        final int expected = 1;
        int [] count = new int[]{0};

        commandMapperDictionary.once(true);
        commandMapperDictionary.addCommand(command, (Callable<Integer>) () -> {
            ++count[0];
            return count[0];
        });

        final Integer result  = commandMapperDictionary.execute(command);

        assertTrue(command != null);
        assertTrue(result == expected);
        assertTrue(commandMapperDictionary.size() == 0);
    }


    @Test
    public void testExecuteCallableWithParamsOneAsync() throws Exception {
        final String command = "command";
        final int expected = 1;
        int [] count = new int[]{0};

        commandMapperDictionary.once(true);
        commandMapperDictionary.addCommand(command, (Callable<Integer>) () -> {
            ++count[0];
            return count[0];
        });

        final CompletableFuture<Integer> completableFuture = commandMapperDictionary.executeAsync(command);
        final Integer result  = completableFuture.get();

        assertTrue(command != null);
        assertTrue(result == expected);
        assertTrue(commandMapperDictionary.size() == 0);
    }

    @Test
    public void testExecuteRunnableWithParamsOneAsync() throws Exception {
        final String command = "command";
        final int expected = 1;
        int [] count = new int[]{0};

        commandMapperDictionary.once(true);
        commandMapperDictionary.addCommand(command, (Runnable) () -> {
            ++count[0];
        });

        final CompletableFuture<Object> completableFuture = commandMapperDictionary.executeAsync(command);
        final Object result = completableFuture.get();

        assertTrue(command != null);
        assertTrue(count[0] == expected);
        assertTrue(commandMapperDictionary.size() == 0);
        assertNull(result);
    }

    @Test
    public void testRemove() throws Exception {
        final String command = "command";
        final int expected = 1;
        final int exceptedResult = 9;


        IntStream.range(0, 10).boxed().forEach(x -> {
            commandMapperDictionary.addCommand(command + x, (Runnable) () -> {
            });
        });

        commandMapperDictionary.remove(command + expected);

        assertTrue(commandMapperDictionary.size() == exceptedResult);
    }

    @Test
    public void testClear() throws Exception {
        final String command = "command";
        final int expected = 0;

        IntStream.range(0,10).boxed().forEach(x -> {
            commandMapperDictionary.addCommand(command, (Runnable) () -> {
            });
        });

        commandMapperDictionary.clear();

        assertTrue(commandMapperDictionary.size() == expected);
    }
}