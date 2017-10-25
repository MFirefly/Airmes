package com.maja.airmes.beans.configs

import com.maja.airmes.dtos.NewSensorData
import com.pi4j.io.i2c.I2CBus
import com.pi4j.io.i2c.I2CDevice
import com.pi4j.io.i2c.I2CFactory
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.IOException
import java.sql.Timestamp
import kotlin.experimental.and
import kotlin.experimental.or

/**
 * Configuration class for air sensor
 *
 * @author Maja Filakovic
 * @created 30.08.17.
 */
@Component
class AirSensorConfig {

    private final val LOG = LoggerFactory.getLogger(AirSensorConfig::class.java)

    /**
     * Reads data from sensor and returns converted values
     */
    fun readSensor(): NewSensorData {
        // Get I2C bus to communicate on
        val i2cBus = I2CFactory.getInstance(AM2302_I2CBUS)

        // Create an I2C device for an individual device on the bus
        val i2cDevice = i2cBus.getDevice(AM2302_ADDR)

        // Wake up sensor. It goes to sleep so it doesn't warm up, which could affect the humidity sensor
        wakeUpSensor(i2cDevice)

        // Wait some time, for sensor to wake up
        Thread.sleep(AM2302_TIMEOUT_WAKEUP.toLong())

        // Write to sensor that we want to read, from first register, 4 bytes:
        i2cDevice.write(byteArrayOf(AM2302_COMMAND_READ.toByte(), AM2302_REGISTER_HIGH_HUMIDITY.toByte(), 0x04))

        // Wait some time, for sensor to wake up
        Thread.sleep(AM2302_TIMEOUT_READ.toLong())

        /* Read out 8 bytes of result data:
         * Byte 0: Should be Modbud function code 0x03
         * Byte 1: Should be number of registers to read 0x04
         * Byte 2: Humidity MSB
         * Byte 3: Humidity LSB
         * Byte 4: Temperature MSB
         * Byte 5: Temperature LSB
         * Byte 6: CRC LSB byte
         * Byte 7: CRC MSB byte
         */
        val rawValues = ByteArray(8) //TODO test this!
        i2cDevice.read(rawValues, 0, 8)

//        OR: read one by one byte from specified registers
//        val humidityMSB = i2cDevice.read(AM2302_REGISTER_HIGH_HUMIDITY)
//        val humidityLSB = i2cDevice.read(AM2302_REGISTER_LOW_HUMIDITY)
//        val temepratureMSB = i2cDevice.read(AM2302_REGISTER_HIGH_TEMPERATURE)
//        val temepratureLSB = i2cDevice.read(AM2302_REGISTER_LOW_TEMPERATURE)

        LOG.info("Data collected and converted!")
        return convertFromRawData(rawValues)
    }

    /**
     * Wakes up sensor at the start of the reading process
     * @param i2cDevice I2C device on which sensor is connected
     */
    private fun wakeUpSensor(i2cDevice: I2CDevice) {
        try {
            i2cDevice.write(AM2302_COMMAND_WAKEUP.toByte())
        } catch (ex: IOException) {
            LOG.warn("Waking up exception happened! Continuing as usual...")
        }
    }

    /**
     * Converts raw data from sensor to readable data
     *
     * Temperature resolution is 16Bit,
     * temperature highest bit (Bit15) is equal to 1 indicates a
     * negative temperature, the temperature highest bit (Bit15)
     * is equal to 0 indicates a positive temperature;
     * temperature in addition to the most significant bit (Bit14 ~ Bit0)
     * indicates the temperature sensor string value.
     *
     * Temperature and humidity sensor value is a string of 10 times the actual temperature value.
     * @return Class with converted data
     */
    private fun convertFromRawData(byteArray: ByteArray): NewSensorData {
        var temperature = combineBytes(byteArray[4], byteArray[5])

        if ((temperature and 0x8000.toShort()) > 0) {
            // Temperature is negative
            temperature = (-(temperature and 0x7FFFF.toShort())).toShort()
        }

        val humidity = combineBytes(byteArray[2], byteArray[3])

        return NewSensorData(System.currentTimeMillis(), humidity / 10, temperature / 10.0)
    }

    /**
     * Combines MSB and LSB into one number, 2 bytes
     * @param msb MSB of the data
     * @param lsb LSB of the data
     */
    private fun combineBytes(msb: Byte, lsb: Byte): Short {
        return msb.shl(8) or lsb.toShort()
    }

    /**
     * Companion object with constants specific to the sensor
     */
    companion object {
        // Constants dependant upon connection of the sensor to RPi
        const val AM2302_ADDR = 0xB8 // or 0x5C
        const val AM2302_I2CBUS = I2CBus.BUS_2

        // Commands
        const val AM2302_COMMAND_WAKEUP = 0x00
        const val AM2302_COMMAND_READ = 0x03
        const val AM2301_COMMAND_WRITE = 0x01

        // Registers
        const val AM2302_REGISTER_HIGH_HUMIDITY = 0x00
        const val AM2302_REGISTER_LOW_HUMIDITY = 0x01
        const val AM2302_REGISTER_HIGH_TEMPERATURE = 0x02
        const val AM2302_REGISTER_LOW_TEMPERATURE = 0x03
        const val AM2302_REGISTER_MODEL_HIGH = 0x08
        const val AM2302_REGISTER_MODEL_LOW = 0x09
        const val AM2302_REGISTER_VERSION_NUMBER = 0x0A
        const val AM2302_REGISTER_DEVICE_ID_HIGHEST_BYTE = 0x0B
        const val AM2302_REGISTER_DEVICE_ID_SECOND_BYTE = 0x0C
        const val AM2302_REGISTER_DEVICE_ID_THIRD_BYTE = 0x0D
        const val AM2302_REGISTER_DEVICE_ID_LOWEST_BYTE = 0x0E
        const val AM2302_REGISTER_STATUS = 0x0F
        const val AM2302_REGISTER_USERS_HIGH = 0x10
        const val AM2302_REGISTER_USERS_LOW = 0x11
        const val AM2302_REGISTER_USERS_2HIGH = 0x12
        const val AM2302_REGISTER_USERS_2LOW = 0x13

        // Timeouts
        const val AM2302_TIMEOUT_WAKEUP = 1
        const val AM2302_TIMEOUT_READ = 2
    }
}

/**
 * Added method for left shift for Byte type
 */
infix inline fun Byte.shl(shift: Int): Short = (this.toInt() shl shift).toShort()