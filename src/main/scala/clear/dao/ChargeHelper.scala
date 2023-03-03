package clear.dao

import scalaz.Scalaz._

import scala.slick.jdbc.StaticQuery.interpolation
import scala.slick.jdbc.{StaticQuery => Q}

/**
 * Some of these helpers probably should be DAO's in their own right.
 */
trait ChargeHelper {
  def chargeQuery(workItemId: Int,
                  date: String,
                  proc: String,
                  mod1: Option[String],
                  mod2: Option[String],
                  chargeCodeSelect: Option[String],
                  chargeType: String): Q[Unit,ChargeCode] = {
    val m1 = mod1.orZero
    val m2 = mod2.orZero
    sql"""
      SELECT charge_master_uid, client_internal_code, client_proc_desc, charge_amt, client_mnemonic, code
      FROM (
          SELECT work_item_uid, charge_type, MAX(effective_date) AS effective_date, patient_status_uid
          FROM tbl_charge_master
          WHERE work_item_uid = $workItemId
                AND charge_type = ${chargeType}
                AND effective_date <= ${date}
        ) AS EffectiveDate
        JOIN tbl_charge_master USING (work_item_uid,charge_type,effective_date)
        LEFT JOIN tbl_work_item_patient_status ON tbl_charge_master.patient_status_uid = tbl_work_item_patient_status.patient_status_uid
          AND EffectiveDate.work_item_uid = tbl_work_item_patient_status.work_item_uid
        WHERE cpt_hcpcs_code IN (${proc+m1+m2}, ${proc+m2+m1}, ${proc+m1}, ${proc+m2}, $proc)
          AND client_internal_code <> ''
        ORDER BY LENGTH(cpt_hcpcs_code) DESC,
                 client_internal_code = ${chargeCodeSelect.orZero} DESC
        LIMIT 1
       """
      .as[ChargeCode]
  }
}
