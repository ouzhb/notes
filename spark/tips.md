# Tip 
## 远程调试
```
export SPARK_JAVA_OPTS+="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=18888"

JAVA_OPTS="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=18888"
```

Spark版本SPARK_JAVA_OPTS这个配置已经被废弃了！
如果要调试本地进程，建议修改spark-class脚本，添加JAVA_OPTS

如果要调试Master,或者Worker建议修改
SPARK_MASTER_OPTS以及SPARK_MASTER_OPTS
## sbt添加中央仓库
```
[repositories]
  local
  maven-local: file://D:\developer\apache-maven-3.5.4\repo
  aliyun: http://maven.aliyun.com/nexus/content/groups/public
```
## GC日志配置

–conf spark.driver.extraJavaOptions="-XX:+PrintGCDetails -XX:+PrintGCTimeStamps "
-conf spark.executor.extraJavaOptions="-XX:+PrintGCDetails -XX:+PrintGCTimeStamps"

## Spark 推测执行

推测任务是指对于一个Stage里面拖后腿的Task，会在其他节点的Executor上再次启动这个task，如果其中一个Task实例运行成功则将这个最先完成的Task的计算结果作为最终结果，同时会干掉其他Executor上运行的实例。
spark推测式执行默认是关闭的，可通过spark.speculation属性来开启。
```shell
spark.speculation = false
spark.speculation.quantile = 0.75 # 开始进行推测时，必须完成的task百分比
spark.speculation.multiplier = 1.5 #Task 推测阈值，中位数的1.5倍
spark.speculation.interval = 100ms # 推测检查间隔
```

## ClassPath
SPARK_CLASSPATH 在spark2中已经失效，

spark-submit时需要添加外部依赖可以用 --driver-class-path 以及 spark.executor.extraClassPath/spark.driver.extraClassPath
或者使用--jars（该参数后的jar包会自动被添加到classpath）

对worker和master添加依赖可以用环境变量SPARK_DAEMON_CLASSPATH