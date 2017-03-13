package com.hendisantika

import com.hendisantika.exception.DriversException
import com.hendisantika.exception.ErrorResponse
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid




/**
 * Created by hendisantika on 15/02/17.
 */
@ResponseBody
@RestController
class DriversController @Autowired constructor(val driversDao: DriversDao) {

    companion object : KLogging()

    @ExceptionHandler(Exception::class)
    @RequestMapping("/drivers")
    @ResponseBody
    @Throws(DriversException::class)
    fun getAllDrivers(
            @RequestParam(value = "latitude", defaultValue = "12.97161923", required = true) @Valid latitude: Double,
            @RequestParam(value = "longitude", defaultValue = "77.59463452", required = true) longitude: Double,
            @RequestParam(value = "radius", defaultValue = "500", required = false) radius: Int,
            @RequestParam(value = "limit", defaultValue = "10", required = false) limit: Int
    ): ResponseEntity<Collection<Drivers>> {

        if ((latitude > 90) || (latitude < -90)) {
            logger.debug("Latitude should be between +/- 90")
            throw DriversException("Latitude should be between +/- 90")
        } else if (latitude == null) {
            logger.debug("Latitude should not be empty")
            throw DriversException("Latitude should not be empty")
        } else if ((longitude > 180) || (longitude <= 0)) {
            logger.debug("Longitude should be between +/- 180")
            throw DriversException("Longitude should be between +/- 180")
        } else if (longitude == null) {
            logger.debug("Longitude should not be empty")
            throw DriversException("Longitude should not be empty")
        } else run {
            logger.info("Sukses....!")
            return ResponseEntity(driversDao.findByLatitudeAndLongitudeAndDistance(latitude, longitude, radius, PageRequest(0, limit)), HttpStatus.OK)
        }

    }

    @RequestMapping(value = "/drivers/all", method = arrayOf(RequestMethod.GET))
    fun cariDrivers(page: Pageable): Page<Drivers> {
        return driversDao.findAll(page)
    }


    protected fun configureRepositoryRestConfiguration(config: RepositoryRestConfiguration) {
        config.setPageParamName("p")
                .setLimitParamName("l")
                .setSortParamName("q")
    }


    @ExceptionHandler(DriversException::class)
    fun exceptionHandler(ex: Exception): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse()
        error.errors = ex.message
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

}
