package com.maja.airmes.beans.repositories

import com.maja.airmes.domain.AirSensorEntity
import org.springframework.data.jpa.repository.JpaRepository
import javax.transaction.Transactional

/**
 * Repository for AirSensor entity
 *
 * @author Maja Filakovic
 * @created 30.08.17.
 */
@Transactional(Transactional.TxType.MANDATORY)
interface AirSensorRepository : JpaRepository<AirSensorEntity, Long>