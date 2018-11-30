# 使用spary-json

![avatar](https://images2015.cnblogs.com/blog/563063/201608/563063-20160826165202429-1187620979.png)

## 依赖
    libraryDependencies += "io.spray" %%  "spray-json" % "1.3.2"
## 使用
```scala
import spray.json._
import DefaultJsonProtocol._ 
```
### 字符串转换位AST（即树结构）
```scala
val source = """{ "some": "JSON source" }"""
val jsonAst = source.parseJson // or JsonParser(source)
// 得到jsonAst的类型为spray.json.JsValue
```
spray.json.JsValue可以直接打印输出，也可以用下面的方法格式化输出：
```scala
val json = jsonAst.prettyPrint //格式化输出
val json1 = jsonAst.compactPrint //输出一行
```
### scala的任意类型和Json AST转换
    调用其toJson方法将scala的任意类型转换为一个Json AST
    
    调用convertTo方法将JSON AST 转换为Scala object

上面的操作需要提供JsonFormat[T]进行类型转换

# JsonProtocol
 spray-json来自一个DefaultJsonProtocol,已经封装了所有的Scala值类型，以及最重要的参考和集合类型。
 只要你的代码没有超过这些内容就需要使用DefaultJsonProtocol

下面的类型已经被DefaultJsonProtocol使用：

- Byte, Short, Int, Long, Float, Double, Char, Unit, Boolean
- String, Symbol
- BigInt, BigDecimal
- Option, Either, Tuple1 - Tuple7
- List, Array
- immutable.{Map, Iterable, Seq, IndexedSeq, LinearSeq, Set, Vector}
- collection.{Iterable, Seq, IndexedSeq, LinearSeq, Set}
- JsValue

大多数情况下你也想不通过DefaultJsonProtocol转换类型，在这些情况下你需要提供JsonFormat[T]为您的自定义类型。这并不难。
