package clear.dao

import clear.db.Adapter
import com.github.takezoe.slick.blocking.BlockingH2Driver.blockingApi._

/**
 * The Data Access Object interface for health checks.
 */
case class HealthDao(clearDb:Adapter) // mirthDb:Adapter TODO: Check all databases
      extends AbstractHealthDao {

  /**
   * Test if database is available.
   */
  override def databaseHealthy: Boolean = {
    def test(s: Session): Boolean = {
      sql"""select 1""".as[Int].firstOption(s).isDefined
    }
  
    clearDb.connect.withSession(test)
  }

}
