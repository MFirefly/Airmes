package com.maja.airmes.beans.utils

import com.maja.airmes.dtos.NewSensorData
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.sql.Timestamp
import java.util.*

/**
 * Class for simulating data from sensor
 *
 * @author Maja Filakovic
 * @created 30.08.17.
 */
@Component
class TempUtil {

    companion object {
        private val LOG = LoggerFactory.getLogger(TempUtil::class.java)

        fun readSensor(): NewSensorData {
            val random = Random()
            LOG.info("Creating new random data...")

            // Humidity in percentage 0-100%
            // Temperature in °C 5-40
            val randomData = NewSensorData(
                    Timestamp(System.currentTimeMillis()),
                    random.nextInt(100),
                    random.nextDouble() * 35 + 5)
            LOG.info("Data: $randomData")

            return randomData
        }
    }
}