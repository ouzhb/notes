# ReplicationController

ReplicationController确保一个pod或一组同类pod总是可用。

RC是最基础的控制器，其他高级控制器如ReplicaSet、Deployment 、DaemonSet是基于RC的高级API。

## 备注

- .spec.template下是Pod的模板信息
- 有两个地方可以配置labels，包括：.metadata.labels 和 .spec.template.metadata.labels。 前者无需配置（以后者为默认值），并且不会影响ReplicationController的默认行为。.spec.selector对后者产生影响。** RC 通过 .spec.selector 判断当前的Pod数目，所以不能出现量RC 他们 .spec.template.metadata.labels 的配置相同 **。 .spec.selector的默认配置和.spec.template.metadata.labels一致。
- 通过修改.spec.replicas = 0 来停止正在运行的实例。
- 通过--cascade=false 参数可以只删除控制器，而不删除pod实例！重建控制器时，只要.spec.selector一致，新的控制器可以重新接管旧的Pod
  
#  ReplicaSet

第二代RC，RpelicationController和RelicaSet的区别在于：RelicaSet支持新一代的选择器（set-based）！

# Deployments

Deployments 在RS的基础上提供了版本升级/版本回滚的功能，用户应该使用Deploy来部署应用，而不是使用RS！

[参考](https://kubernetes.io/docs/concepts/workloads/controllers/deployment/)