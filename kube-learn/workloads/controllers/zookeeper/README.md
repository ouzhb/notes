
```shell
# 查看所有的主机名
for i in 0 1 2; do kubectl exec zk-$i -- hostname; done

# ZK的myid
for i in 0 1 2; do echo "myid zk-$i";kubectl exec zk-$i -- cat /var/lib/zookeeper/data/myid; done

# FQDN信息
for i in 0 1 2; do kubectl exec zk-$i -- hostname -f; done

# ZK的配置文件
kubectl exec zk-0 -- cat /opt/zookeeper/conf/zoo.cfg
```


# 存在问题

- 由于使用的是共享存储导致应用无法scale，原因是：ZK-x不会运行在同一个节点，导致PV中的数据无法访问。