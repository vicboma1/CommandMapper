# CommandMap Design Pattern - Behavioral

[![Build Status](https://travis-ci.org/vicboma1/CommandMapper.svg?branch=master)](https://travis-ci.org/vicboma1/CommandMapper) [![Coverage Status](https://coveralls.io/repos/vicboma1/CommandMapper/badge.svg?branch=master&service=github)](https://coveralls.io/github/vicboma1/CommandMapper?branch=master) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.eluder.coveralls/coveralls-maven-plugin/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.eluder.coveralls/coveralls-maven-plugin/) [![Analytics](https://ga-beacon.appspot.com/UA-68658653-1/CommandMapper/readme)](https://github.com/igrigorik/ga-beacon)


The CommandMapper class implements a domain specific language (DSL) with next interfaces:

```java
CommandCollection
CommandConfigurator
CommandDictionary
CommandExecutable
CommandUnmapper
```
which provides one method for mapping commands and they are run asynchronously or synchronously.

# Manual

Statements
```java

final CommandMapperDictionary commandMapperDictionary = CommandMapperDictionaryImpl.create(Executors.newSingleThreadExecutor());
final CommandMapper commandMapper = CommandMapper.create(commandMapperDictionary);
```

Configuration 
```java
this.commandMapper.one(true);  //only one execution by command
```
Add Command Runnable
```java
final String command = "commandRunnable";
final Runnable runnable = (Runnable) () -> { ... };
commandMapper.addCommand(command,runnable);
```
Add Command Callable<T>
```java
final String command = "commandCalable";
final Callable<T> callable = (Callable<T>) () -> { ...  return (T)... ; };
commandMapper.addCommand(command,callable);
```

Execute Command 
```java
final String command = e.getActionCommand();
final T valueCompleted = commandMapper.execute(command);   
```
ExecuteAsync Command 
```java
final String command = e.getActionCommand();
final CompletableFuture<T> completed = commandMapper.execute(command);   //Here, the promise is in second plane.
T value = completed.get();    // Waiting to resolution of the promise
```

UnMapper Command
```java
final String command = e.getActionCommand();
commandMapper.unMapper(command);
```

UnMapperAll Commands
```java
commandMapper.unMapperAll();
```

Register Commands
```java
final int size = commandMapper.size();
```


