
# Spark-Context 过程分析
    SparkContext作为整个Spark的入口，不管是spark、sparkstreaming、spark sql都需要首先创建一个SparkContext对象，然后基于这个SparkContext进行后续操作。

    对一个JVM，默认只允许创建一个SparkContext。通过spark.driver.allowMultipleContexts，允许JVM中运行多个SparkContext，但是默认场景下只允许激活一个（参考：org.apache.spark.SparkContext$#markPartiallyConstructed）

    启动过程进行的操作：

### 创建SparkEnv

    根据SparkContext的构造入参SparkConf创建SparkEnv --> org.apache.spark.SparkContext#createSparkEnv

    local模式下只有driver会创建SparkEnv，local-cluster部署模式或者Standalone部署模式下Worker另起的CoarseGrainedExecutorBackend进程中也会创建Executor，所以SparkEnv存在于Driver或者CoarseGrainedExecutorBackend进程中。
    
    Createnv的创建对象包括：
            - 创建安全管理器SecurityManager
            - 创建RpcEnv
            - 创建基于Akka的分布式消息系统ActorSystem（注意：Spark 1.4.0之后已经废弃了？？？？？）
            - 创建spark.serializer、closureSerializer
            - 创建Map任务输出跟踪器MapOutputTracker
            - 创建ShuffleManager
            - 内存管理器MemoryManager
            - 创建块传输服务NettyBlockTransferService
            - 创建BlockManagerMaster
            - 创建块管理器BlockManager
            - 创建广播管理器BroadcastManager
            - 创建缓存管理器CacheManager
            - 创建测量系统MetricsSystem
            - 创建OutputCommitCoordinator
            - 创建SparkEnv
## 初始化SparkUI
## 创建TaskScheduler
## 创建DAGScheduler
## 启动taskScheduler

