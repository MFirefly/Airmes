package com.maja.airmes.beans.services

import com.maja.airmes.beans.configs.AirSensorConfig
import com.maja.airmes.beans.repositories.AirSensorRepository
import com.maja.airmes.beans.utils.TempUtil
import com.maja.airmes.beans.websocket.AirmesWebSocketEndpoint
import com.maja.airmes.dtos.AirSensorListWrapper
import com.maja.airmes.dtos.AirSensorWrapper
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
        val sensorConfig: AirSensorConfig,
        val airSensorRepo: AirSensorRepository,
        val webSocketEndpoint: AirmesWebSocketEndpoint
) {

    private final val LOG = LoggerFactory.getLogger(AirSensorService::class.java)

    /**
     * Retrieves data from sensor and stores it into database every 1 minute
     * Also sends new data to all open webSocket sessions
     */
    @Scheduled(fixedRate = GET_NEW_DATA_INTERVAL)
    fun retrieveSensorData() {
        // Real data from sensor
        val sensorData = sensorConfig.readSensor()
        LOG.debug("Data retrieved from sensor: " + sensorData)

        // Dummy data
//        val sensorData = TempUtil.readSensor()

        webSocketEndpoint.sendNewDataToActiveSessions(sensorData)

        airSensorRepo.save(NewSensorData.fromDto(sensorData))
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
     * Returns all stored data from database
     * @return List of all data stored in database
     */
    fun getStoredData(): AirSensorListWrapper {
        return AirSensorListWrapper(airSensorRepo.findAll().map { it.toDto() })
    }

    /**
     * Returns all stored data about temperature from database
     * @return List of all temperature data stored in database
     */
    fun getTemperatureHistory(): AirSensorListWrapper {
        return AirSensorListWrapper(airSensorRepo.findAll().map { it.toDtoTemperature() })
    }

    /**
     * Returns last temperature from database
     * @return last temperature from database
     */
    fun getCurrentTemperature(): AirSensorWrapper {
        return AirSensorWrapper(airSensorRepo.findFirstByOrderByIdDesc().toDtoTemperature())
    }

    /**
     * Returns all stored data about humidity from database
     * @return List of all humidity data stored in database
     */
    fun getHumidityHistory(): AirSensorListWrapper {
        return AirSensorListWrapper(airSensorRepo.findAll().map { it.toDtoHumidity() })
    }

    /**
     * Returns last humidity from database
     * @return last humidity from database
     */
    fun getCurrentHumidity(): AirSensorWrapper {
        return AirSensorWrapper(airSensorRepo.findFirstByOrderByIdDesc().toDtoHumidity())
    }

    /**
     * Companion object that contains relevant constants
     */
    companion object {
        // Defines how long the data will be stored in database (In seconds)
        const val KEEP_DATA_INTERVAL = 259200L // 3 days

        // Defines how frequently will old data be pruged from database (In milliseconds)
        const val PURGE_DATA_INTERVAL = 3600000L // 1 hour

        // Defines how frequently will new data be read from the sensor. (In milliseconds)
        const val GET_NEW_DATA_INTERVAL = 60000L // 1 minute
    }
}