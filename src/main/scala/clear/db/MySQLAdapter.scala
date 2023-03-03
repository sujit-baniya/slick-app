package clear.db

import com.github.takezoe.slick.blocking.BlockingH2Driver.blockingApi._

case class MySQLAdapter(url:String, user:String, password:String)
    extends Adapter {

  // TODO: Connection pooling (Hikari?)
  override def connect = Database.forURL(url, user, password, null, "com.mysql.cj.jdbc.Driver")

  //type Serial = Int  // type of ids (primary keys) in the schema
  //type Serial = Long  // the type aliased by MySQL's SERIAL type

  // https://groups.google.com/d/topic/scalaquery/-qfT5YQtqC4/discussion
  val lastInsertIdFunc = SimpleFunction.nullary[Long]("LAST_INSERT_ID")

  override def lastInsertId(implicit session:Session):Int = Query(lastInsertIdFunc).first.toInt
}
