# local模式的几种配置
    local模式提交任务时包括：local与local-cluster两种方式，前者只启动一个线程，后者是一个伪分布的方式会启动多个线程。

## local本地单线程
    local[*,M]、local[N,M] 指定线程个数以及失败重试次数的本地模式，指定方式及最终的线程数如下：
    默认情况下：*表示处理器个数，M为1即失败后不重试。

#local-cluster 模式
     