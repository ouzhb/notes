# 参考
https://www.jianshu.com/p/00b591c5f623


# 说明

当流处理任务配置checkpoint目录时，集群模式下spark要求checkpoint目录为HDFS目录。
org.apache.spark.SparkContext#setCheckpointDir 接口的注释说明了cluster模式下，checkpoint目录不能是本地目录。

官网没有叙述streaming任务必须配置checkpoint目录。
但是有博客的说法是DStream的有状态转换需要checkpoint，有状态转换算子包括：updateStatebyKey，window，特点是依赖之前的批次数据或者中间结果来计算当前批次的数据。

PS:WIS业务代码中没有使用updateStatebyKey、window算子。但是Kafka依赖库中有使用到updateStatebyKey。


