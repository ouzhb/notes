# Helm包架构

|Helm包|角色|K8S资源类型|备注|
|----|----|----|----|
|hdfs-k8s|顶层Helm包|
|hdfs-namenode-k8s|NN|Statefulset|共享存储|
|hdfs-datanode-k8s|DN|Daemonset|本地存储|
|hdfs-config-k8s|Hadoop的配置文件|configmap|
|zookeeper|||不在当前Helm项目中|
|hdfs-journalnode-k8s|journalnode|Statefulset||
|hdfs-client-k8s|HDFS客户端|
|hdfs-krb5-k8s|krb服务||默认情况改服务关闭|
|hdfs-simple-namenode-k8s|单点NN|Statefulset|该模式下，节点绑定，数据本地存储|

# 备注

- NN，DN使用的DNS策略为：ClusterFirstWithHostNet，即这两种角色使用主机网络，并且能够对其他应用的service name进行DNS解析。
- 镜像地址