package immutable
// pattern (immutable)
//
// purpose (creates new object when change happens, does not change existing object, fundamental)
//
// pros (immutables are easier to use, works better with parallel apps (less chance of deadlock))
//
// cons (not that many except that more objects are used)
trait Product extends Immutable {
  val price : Double
}

case class Version(val number : Int) extends Immutable {
  override def toString = "v" + number
  def next = copy(number + 1)
}

case class Invoice[T <: Product](val id : Int, val name : String, val contents : List[T], val version : Version = Version(0)) extends Immutable {
  // Invoice[T <: Product] uses Product trait
  // val contents: List[t] list of traits
  def total = contents.map(_.price).sum
  def +(p : T) = Invoice(id, name, p :: contents, version.next)
  def -(p : T) = Invoice(id, name, contents diff List(p), version.next)
}

case class Book(val title : String, val author : String, val price : Double) extends Product

object Test extends App {
  val books = List(Book("Java", "John", 12.99))
  var invoice = Invoice(0, "books", books)

  invoice = invoice + Book("Scala", "John", 15.99)

  println(invoice)

  println(invoice.total)
}