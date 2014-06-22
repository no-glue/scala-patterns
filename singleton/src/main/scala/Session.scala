package single
// pattern (singleton)
//
// purpose (manages access to common resource (e.g. session, db connection pool))
//
// pros (more control over objects (compared to plain singletons))
//
// cons (bit more writing)

trait Session {
  val id : String
}

private trait InternalSession extends Session {
  def postCreate : Unit
  def preDestroy : Unit
}

private class SessionImplemented(val id : String) extends InternalSession {
  def postCreate : Unit = println("after created")
  def preDestroy : Unit = println("before destroy")
  override def toString = "session <" + id + "> singleton"
}

object SessionManager {
  private[this] var _instance : Option[InternalSession] = None
  private[single] def instance : Session = {
    // is this how private functions are made?
    if(_instance isEmpty) {
      _instance = Option(new SessionImplemented("this is running " + System.currentTimeMillis))
      _instance.get.postCreate
    }
    return _instance.get
  }
  def destroy = {
    if(_instance nonEmpty) {
      _instance.get.preDestroy
      _instance = None
    }
  }
}

private class Client {
  override def toString = SessionManager.instance.toString
}

object Test extends App {
  println(new Client())

  println(new Client())
}