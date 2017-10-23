package com.maja.airmes.dtos

/**
 * Wrapper around AirSensorDto class for web response
 *
 * @author Maja Filakovic
 * @created 07.09.17.
 */
class AirSensorWrapper(val result: AirSensorDto)

class AirSensorListWrapper(val result: List<AirSensorDto>)