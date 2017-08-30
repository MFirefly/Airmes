package com.maja.airmes.domain

import com.maja.airmes.dtos.AirSensorDto
import java.sql.Timestamp
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

/**
 * Entity for air sensor data in database
 *
 * @author Maja Filakovic
 * @created 30.08.17.
 */
@Table(name = "air_sensor_data")
class AirSensorEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private val id: Long? = null,

        private val timestamp: Timestamp,

        private val temperature: Int?,

        private val humidity: Int?
) {
    fun toDto(): AirSensorDto = AirSensorDto(
            id = this.id!!,
            time = this.timestamp,
            temperature = this.temperature,
            humidity = this.humidity
    )
}