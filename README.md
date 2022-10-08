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

