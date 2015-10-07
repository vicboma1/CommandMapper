import api.CommandMapperDictionary;
import impl.CommandMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by vicboma on 07/10/15.
 */
public class CommandMapperTest {

    private CommandMapper commandMapper;
    private CommandMapperDictionary commandMapperDictionary;

    @Before
    public void setUp() throws Exception {
        commandMapperDictionary = mock(CommandMapperDictionary.class);
        commandMapper = CommandMapper.create(commandMapperDictionary);
    }



    @After
    public void tearDown() throws Exception {
        commandMapper = null;
        commandMapperDictionary = null;
    }

    @Test
    public void testExecuteNull() throws Exception {
        final CompletableFuture<Object> command = commandMapper.execute("command");
        assertTrue(command == null);
    }

    @Test
    public void testExecuteRunnableWithParams() throws Exception {
        final String command = "command";
        final Object result = new Object();
        final CompletableFuture<Object> completableFuture = CompletableFuture.completedFuture(result);
        final Runnable runnable = (Runnable) () -> {
        };

        CommandMapper mapperSpy = spy(commandMapper);
        when(mapperSpy.execute(command)).thenReturn(completableFuture);

        mapperSpy.addCommand(command, runnable);
        final CompletableFuture<Object> _completableFuture = mapperSpy.execute(command);
        final Object res = _completableFuture.get();

        assertTrue(command != null);
        assertTrue(res == result);
    }

    @Test
    public void testExecuteCallableWithParams() throws Exception {
        final String command = "command";
        final Integer result = 1;

        final CompletableFuture<Object> completableFuture = CompletableFuture.completedFuture(result);
        final Callable<Integer> callable = (Callable<Integer>) () -> {
            return 1;
        };

        CommandMapper mapperSpy = spy(commandMapper);
        when(mapperSpy.execute(command)).thenReturn(completableFuture);

        mapperSpy.addCommand(command, callable);
        final CompletableFuture<Object> _completableFuture = mapperSpy.execute(command);
        final Object res = _completableFuture.get();

        assertTrue(command != null);
        assertTrue(res == result);
    }

    @Test
    public void testExecuteRunnableWithParamsAsync() throws Exception {
        final String command = "command";
        final Object result = new Object();
        final CompletableFuture<Object> completableFuture = CompletableFuture.completedFuture(result);
        final Runnable runnable = (Runnable) () -> {
        };

        CommandMapper mapperSpy = spy(commandMapper);
        when(mapperSpy.executeAsync(command)).thenReturn(completableFuture);

        mapperSpy.addCommand(command, runnable);
        final CompletableFuture<Object> _completableFuture = mapperSpy.executeAsync(command);
        final Object res = _completableFuture.get();

        assertTrue(command != null);
        assertTrue(res == result);
    }

    @Test
    public void testExecuteCallableWithParamsAsync() throws Exception {
        final String command = "command";
        final Integer result = 1;

        final CompletableFuture<Object> completableFuture = CompletableFuture.completedFuture(result);
        final Callable<Integer> callable = (Callable<Integer>) () -> {
            return 1;
        };

        CommandMapper mapperSpy = spy(commandMapper);
        when(mapperSpy.executeAsync(command)).thenReturn(completableFuture);

        mapperSpy.addCommand(command, callable);
        final CompletableFuture<Object> _completableFuture = mapperSpy.executeAsync(command);
        final Object res = _completableFuture.get();

        assertTrue(command != null);
        assertTrue(res == result);
    }

    @Test
    public void testAddCommandRunnable() throws Exception {
        final String command = "command";
        final Runnable runnable = () -> {
        };

        CommandMapper mapperSpy = spy(commandMapper);

        mapperSpy.addCommand(command, runnable);
        verify(mapperSpy).addCommand(command, runnable);
    }

    @Test
    public void testAddCommandCallable() throws Exception {
        final String command = "command";
        final Callable callable = (Callable) () -> {
            return 1;
        };

        CommandMapper mapperSpy = spy(commandMapper);

        mapperSpy.addCommand(command, callable);
        verify(mapperSpy).addCommand(command, callable);
    }

    @Test
    public void testUmMapper() throws Exception {
        final String command = "command";
        final int expected = 1;

        CommandMapper mapperSpy = spy(commandMapper);;

        mapperSpy.umMapper(command + expected);
        verify(mapperSpy).umMapper(command + expected);
    }

    @Test
    public void testUmMapperAll() throws Exception {
        CommandMapper mapperSpy = spy(commandMapper);;

        mapperSpy.umMapperAll();
        verify(mapperSpy).umMapperAll();
    }

    @Test
    public void testExecuteRunnableWithParamsOne() throws Exception {
        final String command = "command";
        final Object result = new Object();
        final CompletableFuture<Object> completableFuture = CompletableFuture.completedFuture(result);
        final Runnable runnable = (Runnable) () -> {
        };

        commandMapper.once(true);
        CommandMapper mapperSpy = spy(commandMapper);
        when(mapperSpy.execute(command)).thenReturn(completableFuture);

        mapperSpy.addCommand(command, runnable);
        final CompletableFuture<Object> _completableFuture = mapperSpy.execute(command);
        final Object res = _completableFuture.get();

        assertTrue(command != null);
        assertTrue(res == result);
    }

    @Test
    public void testExecuteCallableWithParamsOne() throws Exception {
        final String command = "command";
        final Integer result = 1;

        final CompletableFuture<Object> completableFuture = CompletableFuture.completedFuture(result);
        final Callable<Integer> callable = (Callable<Integer>) () -> {
            return result;
        };

        commandMapper.once(true);
        CommandMapper mapperSpy = spy(commandMapper);
        when(mapperSpy.execute(command)).thenReturn(completableFuture);

        mapperSpy.addCommand(command, callable);
        final CompletableFuture<Object> _completableFuture = mapperSpy.execute(command);
        final Object res = _completableFuture.get();

        assertTrue(command != null);
        assertTrue(res == result);
    }

    @Test
    public void testExecuteRunnableWithParamsOneAsync() throws Exception {
        final String command = "command";
        final Object result = new Object();
        final CompletableFuture<Object> completableFuture = CompletableFuture.completedFuture(result);
        final Runnable runnable = (Runnable) () -> {
        };

        commandMapper.once(true);
        CommandMapper mapperSpy = spy(commandMapper);
        when(mapperSpy.executeAsync(command)).thenReturn(completableFuture);

        mapperSpy.addCommand(command, runnable);
        final CompletableFuture<Object> _completableFuture = mapperSpy.executeAsync(command);
        final Object res = _completableFuture.get();

        assertTrue(command != null);
        assertTrue(res == result);
    }

    @Test
    public void testExecuteCallableWithParamsOneAsync() throws Exception {
        final String command = "command";
        final Integer result = 1;

        final CompletableFuture<Object> completableFuture = CompletableFuture.completedFuture(result);
        final Callable<Integer> callable = (Callable<Integer>) () -> {
            return result;
        };

        commandMapper.once(true);
        CommandMapper mapperSpy = spy(commandMapper);
        when(mapperSpy.executeAsync(command)).thenReturn(completableFuture);

        mapperSpy.addCommand(command, callable);
        final CompletableFuture<Object> _completableFuture = mapperSpy.executeAsync(command);
        final Object res = _completableFuture.get();

        assertTrue(command != null);
        assertTrue(res == result);
    }
}