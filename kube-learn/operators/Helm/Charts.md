# Chart.yaml 文件字段参考


[参考](https://github.com/helm/helm/blob/master/docs/charts.md#the-chartyaml-file)

# Helm包依赖关系


  - conditions条件只要设定了（同时在values中能找到相应的值），必定覆盖tags条件；
  - 如果第一个Condition生效了，会忽略后续的Condition配置；
  - 如果任何chart的tag为真，则启用chart
  - tags和conditions的值必须在顶层chart中设定
  - tags的value推荐是顶层值

## 依赖安装顺序

所有chart中的Kubernetes Object对象合并成一个Set，按照<依赖关系>-<Type>排序，之后按照顺序创建。Kubernetes对象的排序是有顺序的，通常NS、ConfigMap等资源优先排序。

```yaml
# A 包含以下对象：
namespace "A-Namespace"
statefulset "A-StatefulSet"
service "A-Service"
# B 包含以下对象：
namespace "B-Namespace"
replicaset "B-ReplicaSet"
service "B-Service"
# B 依赖 A 对象的创建顺序如下：
A-Namespace
B-Namespace
A-StatefulSet
B-ReplicaSet
A-Service
B-Service

```

## exports format

如果子 chart中values顶层包含exports 字段，那么exports 字段的内容可以直接导入父chart的任意字段中。

```yaml
# 父chart在 requirements.yaml 中定义导入
    ...
    import-values:
      - data 
# 子chart values.yaml 中定义导出
...
exports:
  data:
    myint: 99

# 父 chart 实际能得到的values
...
myint: 99

```

## child-parent format

```yaml
# parent's requirements.yaml file
dependencies:
  - name: subchart1
    repository: http://localhost:10191
    version: 0.1.0
    ...
    import-values:
      - child: default.data
        parent: myimports

# parent's values.yaml file

myimports:
  myint: 0
  mybool: false
  mystring: "helm rocks!"

# subchart1's values.yaml file

default:
  data:
    myint: 999
    mybool: true

# parent's final values

myimports:
  myint: 999
  mybool: true
  mystring: "helm rocks!"
```
