package clear.dao

sealed trait ChargeType {
  def chargeKey: String  // corresponding value of charge_type field in tbl_charge_master
}