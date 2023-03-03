package clear.dao

/**
 * The Data Access Object interface for health checks.
 */
abstract class AbstractHealthDao {
  /**
   * Test if database is available.
   */
  def databaseHealthy: Boolean
}
