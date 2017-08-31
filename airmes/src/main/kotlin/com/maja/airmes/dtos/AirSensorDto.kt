package com.maja.airmes.dtos

import java.sql.Timestamp

/**
 * Data retrieved from sensor
 *
 * @author Maja Filakovic
 * @created 30.08.17.
 */
class AirSensorDto(
        val id: Long,
        val time: Timestamp,
        val humidity: Int?,
        val temperature: Int?
)