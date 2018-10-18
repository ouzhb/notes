# 配置项

有多种方式为Connector配置相关配置项，包括（参考）：

- $SPARK_HOME/conf/spark-default.conf 
- ReadConfig和WriteConfig
- 通过DataFrameReader和DataFrameWriter的配置项，多为Map[String,String]
- 部分参数可以通过System Property

# BSON documents
通过BSON documents存储json格式的数据