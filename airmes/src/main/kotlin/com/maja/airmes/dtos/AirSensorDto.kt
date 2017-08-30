package com.maja.airmes.dtos

import java.sql.Timestamp

/**
 * Data retrieved from sensor
 *
 * @author Maja Filakovic
 * @created 30.08.17.
 */
class AirSensorDto(
        private val id: Long,
        private val time: Timestamp,
        private val humidity: Int?,
        private val temperature: Int?
)