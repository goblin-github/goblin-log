> 这是一个日志配置小工具，基于logback提供了logback-spring配置文件，同时实现了日志级别的简单脱敏功能。依赖jar包简单，配置简单，各个项目依赖后无需关注日志相关配置。

# 依赖导入

```xml

<dependency>
    <groupId>com.goblin</groupId>
    <artifactId>goblin-log</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

# 项目配置

```yaml
logback:
  //日志配置文件输入位置，默认值 data/logs
  path: data/logs/${spring.application.name}
  //是否进行日志脱敏，默认值 false
  sensitive: true
```

# 其他

1. 由于日志脱敏依赖正则表达式进行匹配，所以依赖底层日志进行脱敏并不是最佳处理方案，若想脱敏规则完全符合预期，则应在业务应用层进行处理
2. 日志通过多环境进行配置，`default`,`prod`环境指定为`INFO`等级，只输出`info.log`和`error.log`;其他环境下均为`DEBUG`
   等级，输出`console`、`info.log`、`error.log`、`warn.log`、`debug.log`
