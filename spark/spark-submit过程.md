# Spark-Submit过程分析
    通过spark-submit提交任务后，org.apache.spark.deploy.SparkSubmit根据master参数以及deploy-mode参数的配置，启动不同的子进程（当然启动进程之前，会动态加载jar等准备工作）。

    子进程的MainClass有以下几种情况
    参考org.apache.spark.deploy.SparkSubmit#prepareSubmitEnvironment ：

    client模式：用户指定Class参数
    yarn + cluster模式： org.apache.spark.deploy.yarn.Client
    mesos + cluster模式：org.apache.spark.deploy.rest.RestSubmissionClient
    standalone + cluster模式： org.apache.spark.deploy.Client
    standalone + cluster模式 + 使用REST地址：org.apache.spark.deploy.rest.RestSubmissionClient