package com.project.oauthtemplate.global.exception

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionAdvice {

    val logger = LoggerFactory.getLogger(ExceptionAdvice::class.java)

    data class ErrorResponse(
        val status: Int,
        val message: String
    )

    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(ex: BusinessException): ResponseEntity<ErrorResponse> {
        println("Handling BusinessException: ${ex.message}")
        val response = ex.error.message?.let {
            ErrorResponse(
                status = ex.error.status.value(),
                message = it
            )
        }
        return ResponseEntity(response, ex.error.status)
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<ErrorResponse> {
        println("Handling Generic Exception: ${ex.message}")
        logger.error(ex.message, ex)
        val response = ErrorResponse(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            message = "An unexpected error occurred."
        )
        return ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(BindException::class)
    fun handleMethodArgumentNotValidException(ex: BindException): ResponseEntity<ErrorResponse> {
        val errorMessage = ex.bindingResult.allErrors.map { it.defaultMessage }.joinToString(", ")
        return ResponseEntity(
            ErrorResponse(status = 400, message = errorMessage ?: "Validation failed"),
            HttpStatus.BAD_REQUEST
        )
    }

}