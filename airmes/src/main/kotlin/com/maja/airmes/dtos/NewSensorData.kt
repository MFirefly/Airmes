package com.maja.airmes.dtos

import com.maja.airmes.domain.AirSensorEntity
import java.sql.Timestamp

/**
 * Dto class for new data received from sensor
 *
 * @author Maja Filakovic
 * @created 30.08.17.
 */
class NewSensorData(
        private val time: Timestamp,
        private val humidity: Int,
        private val temperature: Int
) {

    override fun toString(): String = "NewSensorData=[time=${this.time}, humidity=${this.humidity}, " +
            "temperature=${this.temperature}]"

    companion object {
        fun fromDto(dto: NewSensorData) = AirSensorEntity(
                timestamp = dto.time,
                temperature = dto.temperature,
                humidity = dto.humidity
        )
    }
}