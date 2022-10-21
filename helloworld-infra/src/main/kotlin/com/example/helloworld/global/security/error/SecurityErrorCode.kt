package com.example.helloworld.global.security.error

import com.example.helloworld.global.error.ErrorProperty

enum class SecurityErrorCode  (
    private val status: Int,
    private val message: String
) : ErrorProperty {

    EXPIRED_TOKEN(401 , "Expired Token"),
    INVALID_TOKEN(401, "Invalid Token");

    override fun status(): Int = status

    override fun message(): String = message

}