package clear.api.impl

import clear.dao.AbstractHealthDao
import org.http4s._
import org.http4s.dsl._

case class HealthApi(dao:AbstractHealthDao) {
  def service: HttpService = HttpService {
      case GET -> Root / "ping" => Ok("Pong")
    }
}
