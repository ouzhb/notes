# Dataflow编程模型
## 1. 抽象层次

Flink提供的API层次由高到低，如下：
- SQL：SQL操作需要在Table定义上执行
- Table API（说明性DSL）：可以在表和DataStream / DataSet之间无缝转换，允许程序混合Table API以及DataStream和DataSet API。
- DataStream/DataSet API(Core APIs)：绝大数APP，通过Core APIs来构建业务逻辑。DataStream API内部嵌入了ProcessFunction，有良好的性能。
- Stateful Stream Processing：由一系列的ProcessFunction 组成；

## 2. Programs and Dataflows

streams：数据流
transformations：输入/输出是一个或者多个流
sources：数据流起点 
sinks：数据流终点

## 3. 并行数据流
- 并行度取决于Operator的Subtasks数目，而这个值通常取决于producing operator

参考[说明](https://ci.apache.org/projects/flink/flink-docs-release-1.6/dev/parallel.html)

## 4. Windows
- windows函数允许时间驱动、数据驱动两种方式
- tumbling windows（翻滚窗，无数据重叠）、sliding windows（推拉窗，有数据重叠）、session windows
  
参考[说明]（https://ci.apache.org/projects/flink/flink-docs-release-1.6/dev/stream/operators/windows.html）

## 5. Time
- Event Time：数据的实际产生时间 ---- 关键
- Ingestion time：数据进入Dataflow的时间
- Processing Time：数据的实际处理时间

## 6. Stateful Operations
状态是根据Key分区存储的
## 7. Batch on Streaming
略

## 8. Checkpoints for Fault Tolerance
[参考]（https://ci.apache.org/projects/flink/flink-docs-release-1.6/dev/stream/state/checkpointing.html）
## 9. 参考

[streaming connectors](https://ci.apache.org/projects/flink/flink-docs-release-1.6/dev/connectors/index.html)

[batch connectors](https://ci.apache.org/projects/flink/flink-docs-release-1.6/dev/batch/connectors.html) 

[DataStream Operators](https://ci.apache.org/projects/flink/flink-docs-release-1.6/dev/stream/operators/index.html)

[DataSet Transformations](https://ci.apache.org/projects/flink/flink-docs-release-1.6/dev/batch/dataset_transformations.html)

[参考](https://ci.apache.org/projects/flink/flink-docs-release-1.6/concepts/programming-model.html)