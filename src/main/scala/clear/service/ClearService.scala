package clear.service

import clear.api.impl._
import clear.dao._
import clear.db.Adapter

case class ClearService(healthDao: AbstractHealthDao) {
	def service = HealthApi(healthDao).service
}

object ClearService {
	def create(db: Adapter): ClearService = ClearService(
		HealthDao(db),
	)
}