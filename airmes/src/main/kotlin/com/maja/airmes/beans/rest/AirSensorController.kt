package com.maja.airmes.beans.rest

import com.maja.airmes.beans.services.AirSensorService
import com.maja.airmes.dtos.AirSensorDto
import com.maja.airmes.dtos.AirSensorListWrapper
import com.maja.airmes.dtos.AirSensorWrapper
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Controller for fetching air sensor information
 *
 * @author Maja Filakovic
 * @created 30.08.17.
 */
@RestController
@RequestMapping("/airmes")
@CrossOrigin(origins = arrayOf("*"))
class AirSensorController(private val airSensorService: AirSensorService) {

    /**
     * Returns all measurements stored from the sensor
     * @return List of all measurements from the sensor
     */
    @GetMapping("/air-sensor", produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
    fun getData(): AirSensorListWrapper {
        return airSensorService.getStoredData()
    }

    /**
     * Returns the current temperature
     * @return Current temperature
     */
    @GetMapping("/temperature/current", produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
    fun getCurrentTemperature(): AirSensorWrapper {
        return airSensorService.getCurrentTemperature()
    }

    /**
     * Returns the temperature history
     * @return List of all temperature stored in database
     */
    @GetMapping("/temperature/history", produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
    fun getTemperatureHistory(): AirSensorListWrapper {
        return airSensorService.getTemperatureHistory()
    }

    /**
     * Returns the current humidity
     * @return Current humidity
     */
    @GetMapping("/humidity/current", produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
    fun getCurrentHumidity(): AirSensorWrapper {
        return airSensorService.getCurrentHumidity()
    }

    /**
     * Returns the humidity history
     * @return List of all humidity stored in database
     */
    @GetMapping("/humidity/history", produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
    fun getHumidityHistory(): AirSensorListWrapper {
        return airSensorService.getHumidityHistory()
    }
}