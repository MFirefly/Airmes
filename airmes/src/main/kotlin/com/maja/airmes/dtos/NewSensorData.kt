package com.maja.airmes.dtos

import com.maja.airmes.domain.AirSensorEntity
import java.sql.Time
import java.sql.Timestamp

/**
 * Dto class for new data received from sensor
 *
 * @author Maja Filakovic
 * @created 30.08.17.
 */
data class NewSensorData (
        private val timestamp: Long,
        private val humidity: Int,
        private val temperature: Double
) {

    override fun toString(): String = "{\"timestamp\": \"$timestamp\", \"humidity\": \"$humidity\", " +
            "\"temperature\": \"$temperature\"}"

    companion object {
        fun fromDto(dto: NewSensorData) = AirSensorEntity(
                timestamp = Timestamp(dto.timestamp),
                temperature = dto.temperature,
                humidity = dto.humidity
        )
    }
}