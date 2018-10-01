# Tip 
## 远程调试
```
export SPARK_JAVA_OPTS+="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=18888"

JAVA_OPTS="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=18888"
```

## sbt添加中央仓库
```
[repositories]
  local
  maven-local: file://D:\developer\apache-maven-3.5.4\repo
  aliyun: http://maven.aliyun.com/nexus/content/groups/public
```