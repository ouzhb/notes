# Glusterfs Volumes

- 在所有K8S节点安装glusterfs-client
- 创建endpoint(注意endpoint和service应该同名的)

```yaml
kind: Endpoints
apiVersion: v1
metadata:
  name: glusterfs-cluster
  namespace: assurance
subsets:
  - addresses:
      - ip: 172.21.128.241
    ports:
      - port: 27000
  - addresses:
      - ip: 172.21.128.242
    ports:
      - port: 27000
  - addresses:
      - ip: 172.21.128.243
    ports:
      - port: 27000
```
- 创建endpoint对应的service
```yaml
kind: Service
apiVersion: v1
metadata:
  name: glusterfs-cluster
  namespace: assurance
spec:
  ports:
    - port: 27000
```
- 创建使用共享存储的pod
```yaml
apiVersion: v1
kind: Pod
metadata:
  name: glusterfs-vol-test
  namespace: assurance
spec:
  containers:
  - name: glusterfs-vol-test
    command:
    - /etc/bootstrap.sh
    env:
    - name: DAEMON
      value: DAEMON
    image: harbor.mig.ruijie.net/rgibns-snapshot/spark:0.9.1-1.6.3
    volumeMounts:
    - mountPath: /mnt/glusterfs
      name: glusterfs-vol
  volumes:
  - glusterfs:
      endpoints: glusterfs-cluster
      path: gv0
      readOnly: true
    name: glusterfs-vol
```
## Glusterfs Persistent Volumes
通过Heketi服务以及K8S的PV和PVC机制，可以实现在pod向GlusterFs集群动态申请存储资源。

Heketi提供了一个RESTful管理界面，可以用来管理GlusterFS卷的生命周期。

通过Heketi，就可以像使用OpenStack Manila，Kubernetes和OpenShift一样申请可以动态配置GlusterFS卷。

Heketi会动态在集群内选择bricks构建所需的volumes，这样以确保数据的副本会分散到集群不同的故障域内。同时Heketi还支持任意数量的ClusterFS集群，以保证接入的云服务器不局限于单个GlusterFS集群。

##参考：

https://kubernetes.io/docs/concepts/storage/volumes/#glusterfs
https://github.com/kubernetes/examples/tree/master/staging/volumes/glusterfs