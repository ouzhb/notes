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