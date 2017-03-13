package com.hendisantika.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

/**
 * Created by hendisantika on 15/02/17.
 */
@ControllerAdvice
class ExceptionControllerAdvice

@ExceptionHandler(Exception::class)
fun exceptionHandler(ex: Exception): ResponseEntity<ErrorResponse> {
    val error = ErrorResponse()
//    error.errors = "Please contact your administrator"
    error.errors = ex.message
    return ResponseEntity(error, HttpStatus.BAD_REQUEST)
}