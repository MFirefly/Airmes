package com.maja.airmes.beans.repositories

import com.maja.airmes.domain.AirSensorEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional
import java.sql.Timestamp

/**
 * Repository for AirSensor entity
 *
 * @author Maja Filakovic
 * @created 30.08.17.
 */
@Transactional
interface AirSensorRepository : JpaRepository<AirSensorEntity, Long> {

    @Modifying
    @Transactional(readOnly = false)
    @Query(value = "delete from AirSensorEntity a where a.timestamp < ?1")
    fun purgeData(timestamp: Timestamp)
}