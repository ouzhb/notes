### 类型 Either
 
[参考](http://wiki.jikexueyuan.com/project/guides-to-scala-book/chp7-the-either-type.html)
 
### 泛型
 
- scala的类和方法、函数都可以是泛型。
- 关于对**类型边界**的限定分为上边界和下边界（对类进行限制）
  - 上边界：表达了泛型的类型必须是"某种类型"或某种类型的"子类"，语法为“**<:**”,
  - 下边界：表达了泛型的类型必须是"某种类型"或某种类型的"父类"，语法为“**>:**”,
 
 
- "**<%**" : **view bounds**可以进行某种神秘的转换，把你的类型在没有知觉的情况下转换成目标类型，其实你可以认为view bounds是上下边界的加强和补充，语法为："<%"，要用到implicit进行隐式转换（见下面例子）
- **"T:classTag"：相当于动态类型**，你使用时传入什么类型就是什么类型，（spark的程序的编译和运行是区分了Driver和Executor的，只有在运行的时候才知道完整的类型信息）， 语法为："[T:ClassTag]"下面有列子
- **逆变和协变**：-T和+T（下面有具体例子）+T可以传入其子类和本身（与继承关系一至）-T可以传入其父类和本身（与继承的关系相反），
 
 
- **"T:Ordering"：**表示将T变成Ordering[T],可以直接用其方法进行比大小,可完成排序等工作
 
``` scala
class Person(val name: String) {
  def talk(person: Person) {
    println(this.name + " speak to " + person.name)
  }
}
class Worker(name: String) extends Person(name)
class Dog(val name: String)
class Club[T <: Person](p1: T, p2: T) {
  def comminicate() = p1.talk(p2)
}
class Club2[T <% Person](p1: T, p2: T) {
  def comminicate = p1.talk(p2)
}
object Main {
  def main(args: Array[String]): Unit = {
 
    /***
      * 使用模板上限的例子
      */
    val p = new Person("Spark")
    val w = new Worker("Scala")
    new Club(p, w).comminicate
 
    /***
      * 使用<% 的例子：
      * 实际上<% 会把参数转换成Object类型(称为对象擦除)，
      * 因此我们需要提供Object类型到Person类型的一个隐式转换
      */
 
    implicit def object2Person(o: Object): Person = {
      if (o.isInstanceOf[Dog]) {
        return new Person(o.asInstanceOf[Dog].name)
      }
      else
        return new Person("default name")
    }
    val d = new Dog("Dog")
    new Club2(p, d).comminicate
  }
}
 
```
 
```scala
class Engineer
class Expert extends Engineer
class Meeting[+T]//可以传入T或T的子类
object Main {
  def main(args: Array[String]): Unit = {
    /***
      * -T +T例子,下面的participateMeeting方法指定具体是什么泛型
      */
    val p1=new Meeting[Engineer]
    val p2=new Meeting[Expert]
    participateMeeting(p1)
    participateMeeting(p2)
    def participateMeeting(meeting:Meeting[Engineer])=  println("welcome")
  }
}
```
 
```scala
class Maximum[T:Ordering](val x:T,val y:T){
  def bigger(implicit ord:Ordering[T])={
    if(ord.compare(x, y)>0)x else y
  }
}
object Main {
  def main(args: Array[String]): Unit = {
    /***
      * T:Ordering 的例子
      */
    println(new Maximum(3,5).bigger)
    println(new Maximum("Scala","Java").bigger)
  }
}
```
 
### 动态类型
 
```scala
class Person(name:String){
  override def toString: String = name.toString
}
object Main {
  def main(args: Array[String]): Unit = {
    myPrint(new Person("Lin"))
  }
  def myPrint[T: ClassTag](elems: T) = println(elems)
}
```
 