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

            val randomData = NewSensorData(
                    Timestamp(System.currentTimeMillis()),
                    random.nextInt(Short.MAX_VALUE - Short.MIN_VALUE) + Short.MIN_VALUE,
                    random.nextInt(Short.MAX_VALUE - Short.MIN_VALUE) + Short.MIN_VALUE
            )
            LOG.info("Data: $randomData")

            return randomData
        }
    }
}