# CommandMapper

The CommandMapper class implements a domain specific language (DSL) with next interfaces:

```java
CommandCollection
CommandConfigurator
CommandDictionary
CommandExecutable
CommandUnmapper
```
which provides one method for mapping commands and they are run  asynchronously or synchronously.

#Manual

Statements
```java

final CommandMapperDictionary commandMapperDictionary = CommandMapperDictionaryImpl.create(Executors.newSingleThreadExecutor());
final CommandMapper commandMapper = CommandMapper.create(commandMapperDictionary);
```



[![Bitdeli Badge](https://d2weczhvl823v0.cloudfront.net/vicboma1/commandmapper/trend.png)](https://bitdeli.com/free "Bitdeli Badge")

