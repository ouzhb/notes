# Kubernetes PV&PVC 

## 参考资料
- PVC&PV的生命周期
    - Provisioning：PVC和PV的匹配阶段，包括静态、动态。
        - 静态：预先创建多个PV
        - 动态：当前的PV都和PVC不匹配时，PVC请求指定的storageClass自动创建PV。管理员可以配置值DefaultStorageClass指定默认storageClass
    - Binding：绑定后PVC等到的容量不小于申请的值（可能超过）！pvc和pv是一对一绑定的。如果找不到匹配PV，PVC会一直保持未绑定状态。
    - Using：Pod使用PVC和使用Volume一致
    - Storage Object in Use Protection：当PVC状态为active时，用户删除PVC的命令不会立即生效。当PV被绑定到PVC时，用户删除PV的命令不会立即生效。
    - Reclaiming：删除PVC引发的资源回收动作，回收策略包括以下。
        - Retain：手工回收，PV保留，状态为“released”，但是其他PVC不能使用这个PV。用户需要手工删除数据，并删除PV。
        - Delete：同时删除PV
        - Recycle：已经被废弃，
- 卷扩展
    - 部分类型的PVC能够进行卷扩展，需要在对应的StorageClass中配置allowVolumeExpansion为true。如果需要扩展正在使用的PVC，那么需要配置ExpandInUsePersistentVolumes功能。
  
- 部分类型的PV，支持快照功能
  
## 相关参数

[PV参考](https://kubernetes.io/docs/concepts/storage/persistent-volumes/#persistent-volumes)
[PVC参考](https://kubernetes.io/docs/concepts/storage/persistent-volumes/#persistentvolumeclaims)
## A Quick Start 

[参考](https://kubernetes.io/docs/tasks/configure-pod-container/configure-persistent-volume-storage/)

使用本地目录创建一个PV

```yaml
kind: PersistentVolume
apiVersion: v1
metadata:
  name: task-pv-volume
  labels:
    type: local
spec:
  storageClassName: manual
  capacity:
    storage: 10Gi
  accessModes:
    - ReadWriteOnce
  hostPath:
    path: "/tmp/data"
```
```yaml
kind: PersistentVolumeClaim
apiVersion: v1
metadata:
  name: task-pv-claim
spec:
  storageClassName: manual
  accessModes:
    - ReadWriteOnce
  resources:
    requests:
      storage: 3Gi
```
```yaml
kind: Pod
apiVersion: v1
metadata:
  name: task-pv-pod
spec:
  volumes:
    - name: task-pv-storage
      persistentVolumeClaim:
       claimName: task-pv-claim
  containers:
    - name: task-pv-container
      image: nginx
      ports:
        - containerPort: 80
          name: "http-server"
      volumeMounts:
        - mountPath: "/usr/share/nginx/html"
          name: task-pv-storage
```