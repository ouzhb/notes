# Tip 
## 远程调试
```
export SPARK_JAVA_OPTS+="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=18888"

JAVA_OPTS="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=18888"
```