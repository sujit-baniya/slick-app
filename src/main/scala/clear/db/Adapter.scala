package clear.db

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util._
import com.github.takezoe.slick.blocking.BlockingH2Driver.blockingApi._

abstract class Adapter {
  /**
   * Slick database object.
   */
  def connect:Database

  /**
   * Return the last id created by INSERT.
   */
  def lastInsertId(implicit session:Session):Int
}

object Adapter {
  /**
   * Current time in SQL format, UTC timezone.
   * Used to initialise timestamps.
   */
  def sqlDate(tzName:String):String = {
    val tz = TimeZone.getTimeZone(tzName)
    val df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    df.setTimeZone(tz)
    df.format(new Date())
  }

  val convert_tz = SimpleFunction.ternary[Option[Timestamp],String,Option[String],Option[String]]("CONVERT_TZ")

  // As long as the default JVM timezone has been forced to UTC,
  // it is correct to store this value in database DATETIME fields that
  // are meant to be interpreted as UTC.
  def nowUtc() = new Timestamp(System.currentTimeMillis)

  /**
   * @see https://github.com/edelberg-and-associates/clear2/wiki/Timezones
   */
  // @deprecated("Pacific time is used for timestamps while data remains hosted at Inmotion. On AWS we should standardise on UTC.", "")
  def nowPst() = sqlDate("PST")
}
