package clear.dao

import clear.db.Adapter

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.jdbc.{StaticQuery => Q}

/**
 * The Data Access Object interface for health checks.
 */
case class HealthDao(clearDb:Adapter) // mirthDb:Adapter TODO: Check all databases
      extends AbstractHealthDao {

  /**
   * Test if database is available.
   */
  override def databaseHealthy: Boolean = {
    def test(s: Session): Boolean =
      Q.queryNA[(Int)]("select 1").firstOption(s).isDefined

    clearDb.connect.withSession(test(_))
  }

}
