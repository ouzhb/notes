# Tip 
## 远程调试
```
export SPARK_JAVA_OPTS+="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=18888"

JAVA_OPTS="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=18888"
```

<<<<<<< HEAD
Spark版本SPARK_JAVA_OPTS这个配置已经被废弃了！
如果要调试本地进程，建议修改spark-class脚本，添加JAVA_OPTS

如果要调试Master,或者Worker建议修改
SPARK_MASTER_OPTS以及SPARK_MASTER_OPTS
=======
## sbt添加中央仓库
```
[repositories]
  local
  maven-local: file://D:\developer\apache-maven-3.5.4\repo
  aliyun: http://maven.aliyun.com/nexus/content/groups/public
```
>>>>>>> 8f3f397e93b8a49e9c3c27b705626bf2a562e540
