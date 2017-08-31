package com.maja.airmes.beans.rest

import com.maja.airmes.beans.services.AirSensorService
import com.maja.airmes.dtos.AirSensorDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Controller for fetching air sensor information
 *
 * @author Maja Filakovic
 * @created 30.08.17.
 */
@RestController
class AirSensorController(private val airSensorService: AirSensorService) {

    /**
     * Returns all measurements stored from the sensor
     * @return List of all measurements from the sensor
     */
    @GetMapping("/air-sensor")
    fun getData(): List<AirSensorDto> {
        return airSensorService.getStoredData()
    }
}