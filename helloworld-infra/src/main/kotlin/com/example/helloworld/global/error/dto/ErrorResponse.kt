package com.example.helloworld.global.error.dto

import com.example.helloworld.global.error.ErrorProperty
import com.example.helloworld.global.error.GlobalErrorCode
import org.springframework.validation.BindException
import org.springframework.web.bind.MethodArgumentNotValidException

data class ErrorResponse(
    val status: Int,
    val message: String
) {
    companion object {
        fun of(errorCode: ErrorProperty) = ErrorResponse(
            status = errorCode.status(),
            message = errorCode.message()
        )

        fun of(bindException: BindException): ErrorResponse = of(
            errorCode = GlobalErrorCode.BAD_REQUEST
        )

        fun of(methodArgumentException: MethodArgumentNotValidException): ErrorResponse = of(
            errorCode = GlobalErrorCode.BAD_REQUEST
        )
    }
}