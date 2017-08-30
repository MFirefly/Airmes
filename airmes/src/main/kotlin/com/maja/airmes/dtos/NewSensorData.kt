package com.maja.airmes.dtos

import java.sql.Timestamp

/**
 * Dto class for new data received from sensor
 *
 * @author Maja Filakovic
 * @created 30.08.17.
 */
class NewSensorData(
        private val time: Timestamp,
        private val humidity: Int?,
        private val temperature: Int?
)