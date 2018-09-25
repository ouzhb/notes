## 环境变量配置
 
Windows：
 
SCALA_HOME = "安装目录"
Path = %SCALA_HOME%\bin;%SCALA_HOME%\jre\bin;
ClassPath = %SCALA_HOME%\bin;%SCALA_HOME%\lib\dt.jar;%SCALA_HOME%\lib\tools.jar.;
 
## 基础语法
 
- **区分大小写** -  Scala是大小写敏感的，这意味着标识Hello 和 hello在Scala中会有不同的含义。
- **类名** - 对于所有的类名的第一个字母要大写。
  如果需要使用几个单词来构成一个类的名称，每个单词的第一个字母要大写。
  示例：*class MyFirstScalaClass*
- **方法名称** - 所有的方法名称的第一个字母用小写。
  如果若干单词被用于构成方法的名称，则每个单词的第一个字母应大写。
  示例：*def myMethodName()*
- **程序文件名** - 程序文件的名称应该与对象名称完全匹配。
  保存文件时，应该保存它使用的对象名称（记住Scala是区分大小写），并追加".scala"为文件扩展名。 （如果文件名和对象名称不匹配，程序将无法编译）。
  示例: 假设"HelloWorld"是对象的名称。那么该文件应保存为'HelloWorld.scala"
- **def main(args: Array[String])** - Scala程序从main()方法开始处理，这是每一个Scala程序的强制程序入口部分。
 
##  Scala 包
 
### 定义包
 
Scala 使用 package 关键字定义包，在Scala将代码定义到某个包中有两种方式：
 
第一种方法和 Java 一样，在文件的头定义包名，这种方法就后续所有代码都放在该包中。 比如：
 
```scala
package com.runoob
class HelloWorld
```
 
第二种方法有些类似 C#，如：
 
```scala
package com.runoob {
  class HelloWorld
}
```
 
第二种方法，可以在一个文件中定义多个包。
 
### 引用
 
Scala 使用 import 关键字引用包。
 
```scala
import java.awt.Color  // 引入Color
 
import java.awt._  // 引入包内所有成员
 
def handler(evt: event.ActionEvent) { // java.awt.event.ActionEvent
  ...  // 因为引入了java.awt，所以可以省去前面的部分
}
```
 
import语句可以出现在任何地方，而不是只能在文件顶部。**import的效果从开始延伸到语句块的结束**。这可以大幅减少名称冲突的可能性。
 
如果想要引入包中的几个成员，可以使用selector（选取器）：
 
```scala
import java.awt.{Color, Font}
 
// 重命名成员
import java.util.{HashMap => JavaHashMap}
 
// 隐藏成员
import java.util.{HashMap => _, _} // 引入了util包的所有成员，但是HashMap被隐藏了
```
 
> **注意：**默认情况下，Scala 总会引入 java.lang._ 、 scala._ 和 Predef._，这里也能解释，为什么以scala开头的包，在使用时都是省去scala.的。
 
 
 
## 符号字面量
 
符号字面量被写成： **'<标识符>** ，这里 **<标识符>** 可以是任何字母或数字的标识（注意：不能以数字开头）。这种字面量被映射成预定义类scala.Symbol的实例。
 
如： 符号字面量
 
'x
 
是表达式
 
scala.Symbol("x")
 
的简写，符号字面量定义如下：
 
```scala
package scala
final case class Symbol private (name: String) {
   override def toString: String = "'" + name
}
```
 
## 变量声明
 
在 Scala 中，使用关键词 **"var"** 声明变量，使用关键词 **"val"** 声明常量。
 
如下：
 
```scala
var VariableName : DataType [=  Initial Value]
val VariableName : DataType [=  Initial Value]
```
 
**不能修改"val" 声明的常量。**如果程序尝试修改常量 myVal 的值，程序将会在编译时报错。
 
在 Scala 中声明变量和常量不一定要指明数据类型，**在没有指明数据类型的情况下，其数据类型是通过变量或常量的初始值推断出来的。**
 
**所以，如果在没有指明数据类型的情况下声明变量或常量必须要给出其初始值，否则将会报错。**
 
**只有Class对象能够有只声明不赋值的对象。**
 
```scala
var myVar = 10;
val myVal = "Hello, Scala!";
```
 
scala 支持多个变量的声明：
 
```scala
val xmax, ymax = 100  // xmax, ymax都声明为100
```
 
如果方法返回值是元组，我们可以使用 val 来声明一个元组：
 
```scala
scala> val pa = (40,"Foo")
pa: (Int, String) = (40,Foo)
```
 
## Scala 访问修饰符
 
Scala 访问修饰符基本和Java的一样，分别有：private，protected，public。
 
**如果没有指定访问修饰符符，默认情况下，Scala对象的访问级别都是 public。**
 
### 私有(Private)成员
 
**Scala 中的 private 限定符，比 Java 更严格，在嵌套类情况下，外层类甚至不能访问被嵌套类的私有成员。（外部类不允许访问内部类的私有成员，但是内部类可以访问外部类的私有成员）**
 
```scala
class Outer{
    class Inner{
    private def f(){println("f")}
    class InnerMost{
        f() // 正确，内部类可以访问外部类的私有成员
        }
    }
    (new Inner).f() //错误，外部类不允许访问内部类的私有成员
}
```
 
### 保护(Protected)成员
 
在 scala 中，对保护（Protected）成员的访问比 java 更严格一些。因为它只**允许保护成员在定义了该成员的的类的子类中被访问**。**而在java中，用protected关键字修饰的成员，除了定义了该成员的类的子类可以访问，同一个包里的其他类也可以进行访问。**
 
```scala
package p{
class Super{
    protected def f() {println("f")}
    }
    class Sub extends Super{
        f()
    }
    class Other{
        (new Super).f() //错误，
    }
}
```
 
上例中，Sub 类对 f 的访问没有问题，因为 f 在 Super 中被声明为 protected，而 Sub 是 Super 的子类。相反，Other 对 f 的访问不被允许，因为 other 没有继承自 Super。而后者在 java 里同样被认可，因为 Other 与 Sub 在同一包里。
 
## 作用域保护
 
Scala中，访问修饰符可以通过使用限定词强调。格式为:
 
```scala
private[x]
或
protected[x]
```
 
这里的x指代某个所属的包、类或单例对象。如果写成private[x],读作"这个成员除了对[…]中的类或[…]中的包中的类及它们的伴生对像可见外，对其它所有类都是private。
 
这种技巧在横跨了若干包的大型项目中非常有用，它允许你定义一些在你项目的若干子包中可见但对于项目外部的客户却始终不可见的东西。
 
```scala
package bobsrocckets{
    package navigation{
        private[bobsrockets] class Navigator{
         protected[navigation] def useStarChart(){}
         class LegOfJourney{
             private[Navigator] val distance = 100
             }
            private[this] var speed = 200
            }
        }
        package launch{
        import navigation._
        object Vehicle{
        private[launch] val guide = new Navigator
        }
    }
}
```
 
上述例子中，类Navigator被标记为private[bobsrockets]就是说这个类对包含在bobsrockets包里的所有的类和对象可见。
 
比如说，从Vehicle对象里对Navigator的访问是被允许的，因为对象Vehicle包含在包launch中，而launch包在bobsrockets中，相反，所有在包bobsrockets之外的代码都不能访问类Navigator。
 
## Scala for循环
 
```scala
for( var x <- Range ){
   statement(s);
}
```
 
你可以使用分号(;)来为表达式添加一个或多个的过滤条件。
 
以下是 for 循环中过滤的实例：
 
```scala
object Test {
   def main(args: Array[String]) {
      var a = 0;
      val numList = List(1,2,3,4,5,6,7,8,9,10);
 
      // for 循环
      for( a <- numList
           if a != 3; if a < 8 ){
         println( "Value of a: " + a );
      }
   }
}
```
 
### for 循环过滤
 
Scala 可以使用一个或多个 **if** 语句来过滤一些元素。
 
以下是在 for 循环中使用过滤器的语法。
 
```scala
for( var x <- List if condition1; if condition2... ){
   statement(s);
```
 
你可以使用分号(;)来为表达式添加一个或多个的过滤条件。
 
以下是 for 循环中过滤的实例：
 
```scala
object HelloWorld {
  def main(args: Array[String]) {
    for (x <- 1 to 10 if x % 2 == 0; if x % 3 == 0)
      println(x)
  }
}
```
 
### for 使用 yield
 
你可以将 for 循环的返回值作为一个变量存储。语法格式如下：
 
```scala
var retVal = for{ var x <- List
     if condition1; if condition2...
}yield x
```
 
注意大括号中用于保存变量和条件，*retVal* 是变量， 循环中的 yield 会把当前的元素记下来，保存在集合中，循环结束后将返回该集合。
 
以下实例演示了 for 循环中使用 yield：
 
```scala
object Test {
   def main(args: Array[String]) {
      var a = 0;
      val numList = List(1,2,3,4,5,6,7,8,9,10);
 
      // for 循环
      var retVal = for{ a <- numList
                        if a != 3; if a < 8
                      }yield a
 
      // 输出返回值
      for( a <- retVal){
         println( "Value of a: " + a );
      }
   }
}
```
 
## Scala 函数
 
函数是一组一起执行一个任务的语句。 您可以把代码划分到不同的函数中。如何划分代码到不同的函数中是由您来决定的，但在逻辑上，划分通常是根据每个函数执行一个特定的任务来进行的。
 
Scala 有函数和方法，二者在语义上的区别很小。**Scala 方法是类的一部分，而函数是一个对象可以赋值给一个变量。**换句话来说在类中定义的函数即是方法。
 
我们可以在任何地方定义函数，甚至可以在函数内定义函数（内嵌函数）。更重要的一点是 Scala 函数名可以有以下特殊字符：**+, ++, ~, &,-, -- , \, /, :** 等。
 
### 函数声明
 
Scala 函数声明格式如下：
 
```
def functionName ([参数列表]) : [return type]
```
 
如果你不写等于号和方法主体，那么方法会被隐式声明为"抽象(abstract)"，包含它的类型于是也是一个抽象类型。
 
### 函数定义
 
方法定义由一个def 关键字开始，紧接着是可选的参数列表，一个冒号"：" 和方法的返回类型，一个等于号"="，最后是方法的主体。
 
Scala 函数定义格式如下：
 
```
def functionName ([参数列表]) : [return type] = {
   function body
   return [expr]
}
```
 
以上代码中 **return type** 可以是任意合法的 Scala 数据类型。参数列表中的参数可以使用逗号分隔。
 
以下函数的功能是将两个传入的参数相加并求和：
 
```
object add{
   def addInt( a:Int, b:Int ) : Int = {
      var sum:Int = 0
      sum = a + b
 
      return sum
   }
}
```
 
如果函数没有返回值，可以返回为 **Unit**，这个类似于 Java 的 **void**, 实例如下：
 
```
object Hello{
   def printMe( ) : Unit = {
      println("Hello, Scala!")
   }
}
```
 
### Scala 函数传名调用(call-by-name)
 
Scala的解释器在解析函数参数(function arguments)时有两种方式：
 
- 传值调用（call-by-value）：先计算参数表达式的值，再应用到函数内部；
- 传名调用（call-by-name）：将未计算的参数表达式直接应用到函数内部
 
**在进入函数内部前，传值调用方式就已经将参数表达式的值计算完毕，而传名调用是在函数内部进行参数表达式的值计算的。**
 
这就造成了一种现象，每次使用传名调用时，解释器都会计算一次表达式的值。
 
```
object Test {
   def main(args: Array[String]) {
        delayed(time());
   }
 
   def time() = {
      println("获取时间，单位为纳秒")
      System.nanoTime
   }
   def delayed( t: => Long ) = { // 这里t的计算是在引用该变量时进行的
      println("在 delayed 方法内")
      println("参数： " + t)     // 计算t的值，调用time()
      t
   }
}
```
 
以上实例中我们声明了 delayed 方法， 该方法在变量名和变量类型使用 => 符号来设置传名调用。执行以上代码，输出结果如下：
 
```
$ scalac Test.scala
$ scala Test
在 delayed 方法内
获取时间，单位为纳秒
参数： 241550840475831
获取时间，单位为纳秒
```
 
实例中 delay 方法打印了一条信息表示进入了该方法，接着 delay 方法打印接收到的值，最后再返回 t。
 
### Scala 指定函数参数名
 
一般情况下函数调用参数，就按照函数定义时的参数顺序一个个传递。但是我们也可以通过指定函数参数名，并且不需要按照顺序向函数传递参数，实例如下：
 
```
object Test {
   def main(args: Array[String]) {
        printInt(b=5, a=7);
   }
   def printInt( a:Int, b:Int ) = {
      println("Value of a : " + a );
      println("Value of b : " + b );
   }
}
```
 
执行以上代码，输出结果为：
 
```
$ scalac Test.scala
$ scala Test
Value of a :  7
Value of b :  5
```
 
### Scala 函数 - 可变参数
 
Scala 允许你指明函数的最后一个参数可以是重复的，即我们不需要指定函数参数的个数，可以向函数传入可变长度参数列表。
 
Scala 通过在参数的类型之后放一个星号来设置可变参数(可重复的参数)。例如：
 
```
object Test {
   def main(args: Array[String]) {
        printStrings("Runoob", "Scala", "Python");
   }
   def printStrings( args:String* ) = {
      var i : Int = 0;
      for( arg <- args ){
         println("Arg value[" + i + "] = " + arg );
         i = i + 1;
      }
   }
}
```
 
执行以上代码，输出结果为：
 
```
$ scalac Test.scala
$ scala Test
Arg value[0] = Runoob
Arg value[1] = Scala
Arg value[2] = Python
```
 
### Scala 递归函数
 
递归函数在函数式编程的语言中起着重要的作用。
 
Scala 同样支持递归函数。
 
递归函数意味着函数可以调用它本身。
 
以上实例使用递归函数来计算阶乘：
 
```
object Test {
   def main(args: Array[String]) {
      for (i <- 1 to 10)
         println(i + " 的阶乘为: = " + factorial(i) )
   }
 
   def factorial(n: BigInt): BigInt = { 
      if (n <= 1)
         1 
      else   
      n * factorial(n - 1)
   }
}
```
 
### Scala 函数 - 默认参数值
 
Scala 可以为函数参数指定默认参数值，使用了默认参数，你在调用函数的过程中可以不需要传递参数，这时函数就会调用它的默认参数值，如果传递了参数，则传递值会取代默认值。实例如下：
 
```
object Test {
   def main(args: Array[String]) {
        println( "返回值 : " + addInt() );
   }
   def addInt( a:Int=5, b:Int=7 ) : Int = {
      var sum:Int = 0
      sum = a + b
 
      return sum
   }
}
```
 
### Scala 高阶函数
 
**高阶函数（Higher-Order Function）就是操作其他函数的函数。（可以理解为函数传名调用)**
 
Scala 中允许使用高阶函数, 高阶函数可以使用其他函数作为参数，或者使用函数作为输出结果。
 
以下实例中，apply() 函数使用了另外一个函数 f 和 值 v 作为参数，而函数 f 又调用了参数 v：
 
```
object Test {
   def main(args: Array[String]) {
 
      println( apply( layout, 10) )
 
   }
   // 函数 f 和 值 v 作为参数，而函数 f 又调用了参数 v
   def apply(f: Int => String, v: Int) = f(v)
 
   def layout[A](x: A) = "[" + x.toString() + "]"
 
}
```
 
执行以上代码，输出结果为：
 
```
$ scalac Test.scala
$ scala Test
[10]
```
 
### Scala 函数嵌套
 
我们可以在 Scala 函数内定义函数，定义在函数内的函数称之为局部函数。
 
以下实例我们实现阶乘运算，并使用内嵌函数：
 
```
object Test {
   def main(args: Array[String]) {
      println( factorial(0) )
      println( factorial(1) )
      println( factorial(2) )
      println( factorial(3) )
   }
 
   def factorial(i: Int): Int = {
      def fact(i: Int, accumulator: Int): Int = {
         if (i <= 1)
            accumulator
         else
            fact(i - 1, i * accumulator)
      }
      fact(i, 1)
   }
}
```
 
### Scala 匿名函数
 
Scala 中定义匿名函数的语法很简单，箭头左边是参数列表，右边是函数体。
 
使用匿名函数后，我们的代码变得更简洁了。
 
下面的表达式就定义了一个接受一个Int类型输入参数的匿名函数:
 
```
var inc = (x:Int) => x+1
```
 
上述定义的匿名函数，其实是下面这种写法的简写：
 
```
def add2 = new Function1[Int,Int]{ 
    def apply(x:Int):Int = x+1; 
}
```
 
以上实例的 inc 现在可作为一个函数，使用方式如下：
 
```
var x = inc(7)-1
```
 
同样我们可以在匿名函数中定义多个参数：
 
```
var mul = (x: Int, y: Int) => x*y
```
 
mul 现在可作为一个函数，使用方式如下：
 
```
println(mul(3, 4))
```
 
我们也可以不给匿名函数设置参数，如下所示：
 
```
var userDir = () => { System.getProperty("user.dir") }
```
 
userDir 现在可作为一个函数，使用方式如下：
 
```
println( userDir() )
```
 
```
object Demo {
   def main(args: Array[String]) {
      println( "multiplier(1) value = " +  multiplier(1) )
      println( "multiplier(2) value = " +  multiplier(2) )
   }
   var factor = 3
   val multiplier = (i:Int) => i * factor
}
```
 
将以上代码保持到 Demo.scala 文件中，执行以下命令：
 
```
$ scalac Demo.scala
$ scala Demo
```
 
输出结果为：
 
```
multiplier(1) value = 3
multiplier(2) value = 6
```
 
### Scala 偏应用函数
 
Scala 偏应用函数是一种表达式，你不需要提供函数需要的所有参数，只需要提供部分，或不提供所需参数。
 
如下实例，我们打印日志信息：
 
```
import java.util.Date
 
object Test {
   def main(args: Array[String]) {
      val date = new Date
      log(date, "message1" )
      Thread.sleep(1000)
      log(date, "message2" )
      Thread.sleep(1000)
      log(date, "message3" )
   }
 
   def log(date: Date, message: String)  = {
     println(date + "----" + message)
   }
}
```
 
执行以上代码，输出结果为：
 
```
$ scalac Test.scala
$ scala Test
Mon Dec 02 12:52:41 CST 2013----message1
Mon Dec 02 12:52:41 CST 2013----message2
Mon Dec 02 12:52:41 CST 2013----message3
```
 
实例中，log() 方法接收两个参数：date 和 message。我们在程序执行时调用了三次，参数 date 值都相同，message 不同。
 
### Scala 函数柯里化(Currying)
 
**柯里化(Currying)指的是将原来接受两个参数的函数变成新的接受一个参数的函数的过程。**新的函数返回一个以原有第二个参数为参数的函数。
 
首先我们定义一个函数:
 
```
def add(x:Int,y:Int)=x+y
```
 
那么我们应用的时候，应该是这样用：add(1,2)
 
现在我们把这个函数变一下形：
 
```
def add(x:Int)(y:Int) = x + y
```
 
那么我们应用的时候，应该是这样用：add(1)(2),最后结果都一样是3，这种方式（过程）就叫柯里化。
 
add(1)(2) 实际上是依次调用两个普通函数（非柯里化函数），第一次调用使用一个参数 x，返回一个函数类型的值，第二次使用参数y调用这个函数类型的值。
 
实质上最先演变成这样一个方法：
 
```
def add(x:Int)=(y:Int)=>x+y
```
 
**那么这个函数是什么意思呢？ 接收一个x为参数，返回一个匿名函数，该匿名函数的定义是：接收一个Int型参数y，函数体为x+y。现在我们来对这个方法进行调用。**
 
```
val result = add(1)
```
 
**返回一个result，那result的值应该是一个匿名函数：(y:Int)=>1+y**
 
所以为了得到结果，我们继续调用result。
 
```
val sum = result(2)
```
 
最后打印出来的结果就是3。
 
下面是一个完整实例：
 
```
object Test {
   def main(args: Array[String]) {
      val str1:String = "Hello, "
      val str2:String = "Scala!"
      println( "str1 + str2 = " +  strcat(str1)(str2) )
   }
 
   def strcat(s1: String)(s2: String) = {
      s1 + s2
   }
}
```
 
执行以上代码，输出结果为：
 
```
$ scalac Test.scala
$ scala Test
str1 + str2 = Hello, Scala!
```
 
**我们可以使用偏应用函数优化以上方法，绑定第一个 date 参数，第二个参数使用下划线(_)替换缺失的参数列表，并把这个新的函数值的索引的赋给变量。以上实例修改如下：**
 
```
import java.util.Date
 
object Test {
   def main(args: Array[String]) {
      val date = new Date
      val logWithDateBound = log(date, _ : String)
 
      logWithDateBound("message1" )
      Thread.sleep(1000)
      logWithDateBound("message2" )
      Thread.sleep(1000)
      logWithDateBound("message3" )
   }
 
   def log(date: Date, message: String)  = {
     println(date + "----" + message)
   }
}
```
 
执行以上代码，输出结果为：
 
```
$ scalac Test.scala
$ scala Test
Mon Dec 02 12:53:56 CST 2013----message1
Mon Dec 02 12:53:56 CST 2013----message2
Mon Dec 02 12:53:56 CST 2013----message3
```
 
## Scala 闭包
 
***闭包是一个函数，返回值依赖于声明在函数外部的一个或多个变量。***
 
***闭包通常来讲可以简单的认为是可以访问一个函数里面局部变量的另外一个函数。***
 
如下面这段匿名的函数：
 
```
val multiplier = (i:Int) => i * 10 
```
 
函数体内有一个变量 i，它作为函数的一个参数。如下面的另一段代码：
 
```
val multiplier = (i:Int) => i * factor
```
 
在 multiplier 中有两个变量：i 和 factor。其中的一个 i 是函数的形式参数，在 multiplier 函数被调用时，i 被赋予一个新的值。然而，factor不是形式参数，而是自由变量，考虑下面代码：
 
```
var factor = 3 
val multiplier = (i:Int) => i * factor 
```
 
这里我们引入一个自由变量 factor，这个变量定义在函数外面。
 
这样定义的函数变量 multiplier 成为一个"闭包"，因为它引用到函数外面定义的变量，定义这个函数的过程是将这个自由变量捕获而构成一个封闭的函数。
 
完整实例
 
```
object Test { 
   def main(args: Array[String]) { 
      println( "muliplier(1) value = " +  multiplier(1) ) 
      println( "muliplier(2) value = " +  multiplier(2) ) 
   } 
   var factor = 3 
   val multiplier = (i:Int) => i * factor 
} 
```
 
## Scala 字符串
 
以下实例将字符串赋值给一个常量：
 
```
object Test {
   val greeting: String = "Hello,World!"
 
   def main(args: Array[String]) {
      println( greeting )
   }
}
```
 
以上实例定义了变量 greeting，为字符串常量，它的类型为 String (java.lang.String)。
 
**在 Scala 中，字符串的类型实际上是 Java String，它本身没有 String 类。**
 
**在 Scala 中，String 是一个不可变的对象，所以该对象不可被修改。这就意味着你如果修改字符串就会产生一个新的字符串对象。**
 
但其他对象，如数组就是可变的对象。接下来我们会为大家介绍常用的 java.lang.String 方法。
 
------
 
### 创建字符串
 
创建字符串实例如下：
 
```
var greeting = "Hello World!";
 
或
 
var greeting:String = "Hello World!";
```
 
你不一定为字符串指定 String 类型，因为 Scala 编译器会自动推断出字符串的类型为 String。
 
当然我们也可以直接显示的声明字符串为 String 类型，如下实例：
 
```
object Test {
   val greeting: String = "Hello, World!"
 
   def main(args: Array[String]) {
      println( greeting )
   }
}
```
 
执行以上代码，输出结果为：
 
```
$ scalac Test.scala
$ scala Test
Hello, world!
```
 
我们前面提到过 String 对象是不可变的，如果你需要创建一个可以修改的字符串，可以使用 **String Builder 类**，如下实例:
 
```scala
object Test {
   def main(args: Array[String]) {
      val buf = new StringBuilder;
      buf += 'a'  // 这里和字符相加使用+=
      buf ++= "bcdef" // 和字符串相加使用++=
      println( "buf is : " + buf.toString );
   }
}
```
 
执行以上代码，输出结果为：
 
### 字符串长度
 
我们可以使用 length() 方法来获取字符串长度：
 
```
object Test {
   def main(args: Array[String]) {
      var palindrome = "www.runoob.com";
      var len = palindrome.length();
      println( "String Length is : " + len );
   }
}
```
 
执行以上代码，输出结果为：
 
```
$ scalac Test.scala
$ scala Test
String Length is : 14
```
 
------
 
### 字符串连接
 
String 类中使用 concat() 方法来连接两个字符串：
 
```
string1.concat(string2);
```
 
实例演示：
 
```
scala> "菜鸟教程官网： ".concat("www.runoob.com");
res0: String = 菜鸟教程官网： www.runoob.com
```
 
同样你也可以使用加号(+)来连接：
 
```
scala> "菜鸟教程官网： " + " www.runoob.com"
res1: String = 菜鸟教程官网：  www.runoob.com
```
 
让我们看个完整实例:
 
```
object Test {
   def main(args: Array[String]) {
      var str1 = "菜鸟教程官网：";
      var str2 =  "www.runoob.com";
      var str3 =  "菜鸟教程的 Slogan 为：";
      var str4 =  "学的不仅是技术，更是梦想！";
      println( str1 + str2 );
      println( str3.concat(str4) );
   }
}
```
 
执行以上代码，输出结果为：
 
```
$ scalac Test.scala
$ scala Test
菜鸟教程官网：www.runoob.com
菜鸟教程的 Slogan 为：学的不仅是技术，更是梦想！
```
 
------
 
### 创建格式化字符串
 
String 类中你可以使用 printf() 方法来格式化字符串并输出，String format() 方法可以返回 String 对象而不是 PrintStream 对象。以下实例演示了 printf() 方法的使用：
 
```
object Test {
   def main(args: Array[String]) {
      var floatVar = 12.456
      var intVar = 2000
      var stringVar = "菜鸟教程!"
      var fs = printf("浮点型变量为 " +
                   "%f, 整型变量为 %d, 字符串为 " +
                   " %s", floatVar, intVar, stringVar)
      println(fs)
   }
}
```
 
执行以上代码，输出结果为：
 
```
$ scalac Test.scala
$ scala Test
浮点型变量为 12.456000, 整型变量为 2000, 字符串为  菜鸟教程!()
```
 
------
 
### String 方法
 
下表列出了 java.lang.String 中常用的方法，你可以在 Scala 中使用：
 
| 序号 | 方法及描述                                                   |
| ---- | ------------------------------------------------------------ |
| 1    | **char charAt(int index)**返回指定位置的字符                 |
| 2    | **int compareTo(Object o)**比较字符串与对象                  |
| 3    | **int compareTo(String anotherString)**按字典顺序比较两个字符串 |
| 4    | **int compareToIgnoreCase(String str)**按字典顺序比较两个字符串，不考虑大小写 |
| 5    | **String concat(String str)**将指定字符串连接到此字符串的结尾 |
| 6    | **boolean contentEquals(StringBuffer sb)**将此字符串与指定的 StringBuffer 比较。 |
| 7    | **static String copyValueOf(char[] data)**返回指定数组中表示该字符序列的 String |
| 8    | **static String copyValueOf(char[] data, int offset, int count)**返回指定数组中表示该字符序列的 String |
| 9    | **boolean endsWith(String suffix)**测试此字符串是否以指定的后缀结束 |
| 10   | **boolean equals(Object anObject)**将此字符串与指定的对象比较 |
| 11   | **boolean equalsIgnoreCase(String anotherString)**将此 String 与另一个 String 比较，不考虑大小写 |
| 12   | **byte getBytes()**使用平台的默认字符集将此 String 编码为 byte 序列，并将结果存储到一个新的 byte 数组中 |
| 13   | **byte[] getBytes(String charsetName**使用指定的字符集将此 String 编码为 byte 序列，并将结果存储到一个新的 byte 数组中 |
| 14   | **void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin)**将字符从此字符串复制到目标字符数组 |
| 15   | **int hashCode()**返回此字符串的哈希码                       |
| 16   | **int indexOf(int ch)**返回指定字符在此字符串中第一次出现处的索引 |
| 17   | **int indexOf(int ch, int fromIndex)**返回在此字符串中第一次出现指定字符处的索引，从指定的索引开始搜索 |
| 18   | **int indexOf(String str)**返回指定子字符串在此字符串中第一次出现处的索引 |
| 19   | **int indexOf(String str, int fromIndex)**返回指定子字符串在此字符串中第一次出现处的索引，从指定的索引开始 |
| 20   | **String intern()**返回字符串对象的规范化表示形式            |
| 21   | **int lastIndexOf(int ch)**返回指定字符在此字符串中最后一次出现处的索引 |
| 22   | **int lastIndexOf(int ch, int fromIndex)**返回指定字符在此字符串中最后一次出现处的索引，从指定的索引处开始进行反向搜索 |
| 23   | **int lastIndexOf(String str)**返回指定子字符串在此字符串中最右边出现处的索引 |
| 24   | **int lastIndexOf(String str, int fromIndex)**返回指定子字符串在此字符串中最后一次出现处的索引，从指定的索引开始反向搜索 |
| 25   | **int length()**返回此字符串的长度                           |
| 26   | **boolean matches(String regex)**告知此字符串是否匹配给定的正则表达式 |
| 27   | **boolean regionMatches(boolean ignoreCase, int toffset, String other, int ooffset, int len)**测试两个字符串区域是否相等 |
| 28   | **boolean regionMatches(int toffset, String other, int ooffset, int len)**测试两个字符串区域是否相等 |
| 29   | **String replace(char oldChar, char newChar)**返回一个新的字符串，它是通过用 newChar 替换此字符串中出现的所有 oldChar 得到的 |
| 30   | **String replaceAll(String regex, String replacement**使用给定的 replacement 替换此字符串所有匹配给定的正则表达式的子字符串 |
| 31   | **String replaceFirst(String regex, String replacement)**使用给定的 replacement 替换此字符串匹配给定的正则表达式的第一个子字符串 |
| 32   | **String[] split(String regex)**根据给定正则表达式的匹配拆分此字符串 |
| 33   | **String[] split(String regex, int limit)**根据匹配给定的正则表达式来拆分此字符串 |
| 34   | **boolean startsWith(String prefix)**测试此字符串是否以指定的前缀开始 |
| 35   | **boolean startsWith(String prefix, int toffset)**测试此字符串从指定索引开始的子字符串是否以指定前缀开始。 |
| 36   | **CharSequence subSequence(int beginIndex, int endIndex)**返回一个新的字符序列，它是此序列的一个子序列 |
| 37   | **String substring(int beginIndex)**返回一个新的字符串，它是此字符串的一个子字符串 |
| 38   | **String substring(int beginIndex, int endIndex)**返回一个新字符串，它是此字符串的一个子字符串 |
| 39   | **char[] toCharArray()**将此字符串转换为一个新的字符数组     |
| 40   | **String toLowerCase()**使用默认语言环境的规则将此 String 中的所有字符都转换为小写 |
| 41   | **String toLowerCase(Locale locale)**使用给定 Locale 的规则将此 String 中的所有字符都转换为小写 |
| 42   | **String toString()**返回此对象本身（它已经是一个字符串！）  |
| 43   | **String toUpperCase()**使用默认语言环境的规则将此 String 中的所有字符都转换为大写 |
| 44   | **String toUpperCase(Locale locale)**使用给定 Locale 的规则将此 String 中的所有字符都转换为大写 |
| 45   | **String trim()**删除指定字符串的首尾空白符                  |
| 46   | **static String valueOf(primitive data type x)**返回指定类型参数的字符串表示形式 |
 
## Scala 数组
 
Scala 语言中提供的数组是用来存储固定大小的同类型元素，数组对于每一门编辑应语言来说都是重要的数据结构之一。
 
声明数组变量并不是声明 number0、number1、...、number99 一个个单独的变量，而是声明一个就像 numbers 这样的变量，然后使用 numbers[0]、numbers[1]、...、numbers[99] 来表示一个个单独的变量。数组中某个指定的元素是通过索引来访问的。
 
数组的第一个元素索引为0，最后一个元素的索引为元素总数减1。
 
------
 
### 声明数组
 
以下是 Scala 数组声明的语法格式：
 
```
var z:Array[String] = new Array[String](3)
 
或
 
var z = new Array[String](3)
```
 
以上语法中，z 声明一个字符串类型的数组，数组长度为 3 ，可存储 3 个元素。我们可以为每个元素设置值，并通过索引来访问每个元素，如下所示：
 
```
z(0) = "Runoob"; z(1) = "Baidu"; z(4/2) = "Google"
```
 
最后一个元素的索引使用了表达式 **4/2** 作为索引，类似于 **z(2) = "Google"**。
 
我们也可以使用以下方式来定义一个数组：---> 不指定元素类型时，通过推断的方式定义数组
 
```
var z = Array("Runoob", "Baidu", "Google")
```
 
下图展示了一个长度为 10 的数组 myList，索引值为 0 到 9：
 
![img](http://www.runoob.com/wp-content/uploads/2013/12/java_array.jpg)
 
------
 
### 处理数组
 
数组的元素类型和数组的大小都是确定的，所以当处理数组元素时候，我们通常使用基本的 for 循环。
 
以下实例演示了数组的创建，初始化等处理过程：
 
```
object Test {
   def main(args: Array[String]) {
      var myList = Array(1.9, 2.9, 3.4, 3.5)
 
      // 输出所有数组元素
      for ( x <- myList ) {
         println( x )
      }
 
      // 计算数组所有元素的总和
      var total = 0.0;
      for ( i <- 0 to (myList.length - 1)) {
         total += myList(i);
      }
      println("总和为 " + total);
 
      // 查找数组中的最大元素
      var max = myList(0);
      for ( i <- 1 to (myList.length - 1) ) {
         if (myList(i) > max) max = myList(i);
      }
      println("最大值为 " + max);
 
   }
}
```
 
执行以上代码，输出结果为：
 
```
$ scalac Test.scala
$ scala Test
1.9
2.9
3.4
3.5
总和为 11.7
最大值为 3.5
```
 
------
 
### 多维数组
 
多维数组一个数组中的值可以是另一个数组，另一个数组的值也可以是一个数组。矩阵与表格是我们常见的二维数组。
 
以上是一个定义了二维数组的实例：
 
```
var myMatrix = ofDim[Int](3,3)
```
 
实例中数组中包含三个数组元素，每个数组元素又含有三个值。
 
接下来我们来看一个二维数组处理的完整实例：
 
```
import Array._
 
object Test {
   def main(args: Array[String]) {
      var myMatrix = ofDim[Int](3,3)
 
      // 创建矩阵
      for (i <- 0 to 2) {
         for ( j <- 0 to 2) {
            myMatrix(i)(j) = j;
         }
      }
 
      // 打印二维阵列
      for (i <- 0 to 2) {
         for ( j <- 0 to 2) {
            print(" " + myMatrix(i)(j));
         }
         println();
      }
 
   }
}
```
 
[](http://www.runoob.com/try/runcode.php?filename=TestArray&type=scala)
 
执行以上代码，输出结果为：
 
```
$ scalac Test.scala
$ scala Test
0 1 2
0 1 2
0 1 2
```
 
------
 
### 合并数组
 
以下实例中，我们使用 concat() 方法来合并两个数组，concat() 方法中接受多个数组参数：
 
```
import Array._
 
object Test {
   def main(args: Array[String]) {
      var myList1 = Array(1.9, 2.9, 3.4, 3.5)
      var myList2 = Array(8.9, 7.9, 0.4, 1.5)
 
      var myList3 =  concat( myList1, myList2)
 
      // 输出所有数组元素
      for ( x <- myList3 ) {
         println( x )
      }
   }
}
```
 
执行以上代码，输出结果为：
 
```
$ scalac Test.scala
$ scala Test
1.9
2.9
3.4
3.5
8.9
7.9
0.4
1.5
```
 
------
 
### 创建区间数组
 
以下实例中，我们使用了 range() 方法来生成一个区间范围内的数组。range() 方法最后一个参数为步长，默认为 1：
 
```
import Array._
 
object Test {
   def main(args: Array[String]) {
      var myList1 = range(10, 20, 2)
      var myList2 = range(10,20)
 
      // 输出所有数组元素
      for ( x <- myList1 ) {
         print( " " + x )
      }
      println()
      for ( x <- myList2 ) {
         print( " " + x )
      }
   }
}
```
 
执行以上代码，输出结果为：
 
```
$ scalac Test.scala
$ scala Test
10 12 14 16 18
10 11 12 13 14 15 16 17 18 19
```
 
------
 
### Scala 数组方法
 
下表中为 Scala 语言中处理数组的重要方法，使用它前我们需要使用 **import Array._** 引入包。
 
| 序号 | 方法和描述                                                   |
| ---- | ------------------------------------------------------------ |
| 1    | **def apply( x: T, xs: T\* ): Array[T]**创建指定对象 T 的数组, T 的值可以是 Unit, Double, Float, Long, Int, Char, Short, Byte, Boolean。 |
| 2    | **def concat[T]( xss: Array[T]\* ): Array[T]**合并数组       |
| 3    | **def copy( src: AnyRef, srcPos: Int, dest: AnyRef, destPos: Int, length: Int ): Unit**复制一个数组到另一个数组上。相等于 Java's System.arraycopy(src, srcPos, dest, destPos, length)。 |
| 4    | **def empty[T]: Array[T]**返回长度为 0 的数组                |
| 5    | **def iterate[T]( start: T, len: Int )( f: (T) => T ): Array[T]**返回指定长度数组，每个数组元素为指定函数的返回值。以上实例数组初始值为 0，长度为 3，计算函数为**a=>a+1**：`scala> Array.iterate(0,3)(a=>a+1)res1: Array[Int] = Array(0, 1, 2)` |
| 6    | **def fill[T]( n: Int )(elem: => T): Array[T]**返回数组，长度为第一个参数指定，同时每个元素使用第二个参数进行填充。 |
| 7    | **def fill[T]( n1: Int, n2: Int )( elem: => T ): Array[Array[T]]**返回二数组，长度为第一个参数指定，同时每个元素使用第二个参数进行填充。 |
| 8    | **def ofDim[T]( n1: Int ): Array[T]**创建指定长度的数组      |
| 9    | **def ofDim[T]( n1: Int, n2: Int ): Array[Array[T]]**创建二维数组 |
| 10   | **def ofDim[T]( n1: Int, n2: Int, n3: Int ): Array[Array[Array[T]]]**创建三维数组 |
| 11   | **def range( start: Int, end: Int, step: Int ): Array[Int]**创建指定区间内的数组，step 为每个元素间的步长 |
| 12   | **def range( start: Int, end: Int ): Array[Int]**创建指定区间内的数组 |
| 13   | **def tabulate[T]( n: Int )(f: (Int)=> T): Array[T]**返回指定长度数组，每个数组元素为指定函数的返回值，默认从 0 开始。以上实例返回 3 个元素：`scala> Array.tabulate(3)(a => a + 5)res0: Array[Int] = Array(5, 6, 7)` |
| 14   | **def tabulate[T]( n1: Int, n2: Int )( f: (Int, Int ) => T): Array[Array[T]]**返回指定长度的二维数组，每个数组元素为指定函数的返回值，默认从 0 开始。 |
 
## Scala Collection
 
Scala提供了一套很好的集合实现，提供了一些集合类型的抽象。
 
***Scala 集合分为可变的和不可变的集合。***
 
可变集合可以在适当的地方被更新或扩展。这意味着你可以修改，添加，移除一个集合的元素。
 
而不可变集合类，相比之下，永远不会改变。不过，你仍然可以模拟添加，移除或更新操作。但是这些操作将在每一种情况下都返回一个新的集合，同时使原来的集合不发生改变。
 
接下来我们将为大家介绍几种常用集合类型的应用：
 
| 序号 | 集合及描述                                                   |
| ---- | ------------------------------------------------------------ |
| 1    | [Scala List(列表)](http://www.runoob.com/scala/scala-lists.html)List的特征是其元素以线性方式存储，集合中可以存放重复对象。参考 [API文档](http://www.scala-lang.org/api/current/scala/collection/immutable/List.html) |
| 2    | [Scala Set(集合)](http://www.runoob.com/scala/scala-sets.html)Set是最简单的一种集合。集合中的对象不按特定的方式排序，并且没有重复对象。参考 [API文档](http://www.scala-lang.org/api/current/scala/collection/immutable/Set.html) |
| 3    | [Scala Map(映射)](http://www.runoob.com/scala/scala-maps.html)Map 是一种把键对象和值对象映射的集合，它的每一个元素都包含一对键对象和值对象。参考 [API文档](http://www.scala-lang.org/api/current/scala/collection/immutable/Map.html) |
| 4    | [Scala 元组](http://www.runoob.com/scala/scala-tuples.html)元组是**不同类型的值**的集合 |
| 5    | [Scala Option](http://www.runoob.com/scala/scala-options.html)Option[T] 表示有可能包含值的容器，也可能不包含值。 |
| 6    | [Scala Iterator（迭代器）](http://www.runoob.com/scala/scala-iterators.html)迭代器不是一个容器，更确切的说是逐一访问容器内元素的方法。 |