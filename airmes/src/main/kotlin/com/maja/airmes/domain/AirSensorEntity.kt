package com.maja.airmes.domain

import com.maja.airmes.dtos.AirSensorDto
import java.sql.Timestamp
import javax.persistence.*

/**
 * Entity for air sensor data in database
 *
 * @author Maja Filakovic
 * @created 30.08.17.
 */
@Entity
@Table(name = "air_sensor_data")
class AirSensorEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private val id: Long? = null,

        private val timestamp: Timestamp,

        private val temperature: Int,

        private val humidity: Int
) {
    @Suppress("unused")
    private constructor() : this(timestamp = Timestamp(0), temperature = 0, humidity = 0)

    fun toDto(): AirSensorDto = AirSensorDto(
            id = this.id!!,
            time = this.timestamp,
            temperature = this.temperature,
            humidity = this.humidity
    )
}