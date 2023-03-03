package clear.dao

import slick.jdbc.GetResult

case class ChargeCode(charge_master_uid: Int,
                      client_internal_code: String,
                      client_proc_desc: String,
                      charge_amt: Option[BigDecimal],
                      client_mnemonic: Option[String],
                      patient_status_code: Option[String])

object ChargeCode {
  implicit val chargeCodeResult: GetResult[ChargeCode] =
    GetResult(r => ChargeCode(r.<<, r.<<, r.<<, r.<<, r.<<, r.<<))
}
