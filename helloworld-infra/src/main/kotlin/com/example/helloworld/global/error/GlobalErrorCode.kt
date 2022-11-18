package com.example.helloworld.global.error

import org.springframework.http.HttpStatus

enum class GlobalErrorCode(
    private val status: HttpStatus,
    private val message: String
) : ErrorProperty {

    BAD_REQUEST(HttpStatus.BAD_REQUEST, "Bad Request"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "Method Not Allowed"),

    OTHER_BAD_REQUEST(HttpStatus.BAD_REQUEST, "Other Bad Request"),
    OTHER_EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "Other Expired Token"),
    OTHER_FORBIDDEN(HttpStatus.FORBIDDEN, "Other Forbidden"),
    OTHER_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "Other Unauthorized"),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");

    override fun status(): Int = status.value()
    override fun message(): String = message
}