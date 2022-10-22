package com.example.helloworld.global.error

import com.example.helloworld.global.error.dto.ErrorResponse
import org.springframework.data.mapping.PropertyReferenceException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.multipart.MultipartException
import java.rmi.ServerException
import javax.validation.UnexpectedTypeException


@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException::class)
    fun serverExceptionHandler(e: BusinessException): ErrorResponse = ErrorResponse.of(
        errorCode = e.errorProperty
    )

    @ExceptionHandler(BindException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun bindExceptionHandler(e: BindException): ErrorResponse = ErrorResponse.of(
        errorCode = GlobalErrorCode.BAD_REQUEST
    )

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun methodArgumentNotValidExceptionHandler(e: MethodArgumentNotValidException): ErrorResponse = ErrorResponse.of(
        errorCode = GlobalErrorCode.BAD_REQUEST
    )

    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected fun handleIllegalArgumentException(e: IllegalArgumentException): ErrorResponse = ErrorResponse.of(
        errorCode = GlobalErrorCode.BAD_REQUEST
    )

    @ExceptionHandler(Exception::class)
    fun exceptionHandler(e: Exception): ErrorResponse {

        println(e.javaClass)
        println(e.message)
        println(e.cause)
        e.stackTrace.map { println(it) }

        return ErrorResponse.of(
            errorCode = GlobalErrorCode.INTERNAL_SERVER_ERROR
        )
    }
}