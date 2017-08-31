package com.maja.airmes.beans.services

import com.maja.airmes.beans.repositories.AirSensorRepository
import com.maja.airmes.beans.utils.TempUtil
import com.maja.airmes.dtos.AirSensorDto
import com.maja.airmes.dtos.NewSensorData
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.DependsOn
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.sql.Timestamp

/**
 * Service for receiving data from air sensor AM2302
 *
 * @author Maja Filakovic
 * @created 30.08.17.
 */
@Service
@DependsOn("tempUtil")
class AirSensorService(
        //        val sensorConfig: AirSensorConfig,
        val airSensorRepo: AirSensorRepository
) {

    private final val LOG = LoggerFactory.getLogger(AirSensorService::class.java)

    /**
     * Retrieves data from sensor and stores it into database every 1 minute
     */
    @Scheduled(fixedRate = GET_NEW_DATA_INTERVAL)
    fun retrieveSensorData() {
        // Replace random data with this, when sensor is connected and tested
        //val sensorData = sensorConfig.readSensor()

        val sensorData = TempUtil.readSensor()

        airSensorRepo.save(NewSensorData.fromDto(sensorData))
    }

    /**
     * Returns all stored data from database
     * @return List of all data stored in database
     */
    fun getStoredData(): List<AirSensorDto> {
        return airSensorRepo.findAll().map { it.toDto() }
    }

    /**
     * Purge sensor data from database, older then 3 days
     */
    @Scheduled(fixedDelay = PURGE_DATA_INTERVAL)
    fun purgeOldData() {
        val time = Timestamp(System.currentTimeMillis() - (KEEP_DATA_INTERVAL * 1000))
        LOG.info("Purging old data...")
        airSensorRepo.purgeData(time)
    }

    /**
     * Companion object that contains relevant constants
     */
    companion object {
        // In seconds. Current: 259200 s = 3 days
        const val KEEP_DATA_INTERVAL = 259200L

        // In miliseconds. Current 86400000 ms = 1 day
        const val PURGE_DATA_INTERVAL = 86400000L

        // In miliseconds. Current 60000 ms = 10 minutes
        const val GET_NEW_DATA_INTERVAL = 60000L
    }
}