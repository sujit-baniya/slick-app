package clear.service

import clear.db.MySQLAdapter
import org.http4s.server.blaze.BlazeBuilder
import scalaz.concurrent.Task
import org.http4s.util.ProcessApp
import scalaz.stream

import java.util.TimeZone

object Local extends ProcessApp {
	// Make JDBC treat database date/times as UTC
	TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
	
	val appSecret = "local"
	
	val db = MySQLAdapter(url = "jdbc:mysql:///cleardb?useSSL=false", user = "service", password = "")
	var clear = ClearService.create(db)
	
	override def process(args: List[String]): stream.Process[Task, Nothing] = BlazeBuilder
		.withNio2(true)
		.bindHttp(8080)
		.mountService(clear.service, "/api")
		.serve
}