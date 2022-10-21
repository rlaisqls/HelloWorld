package com.example.helloworld.domain.auth.error

import com.example.helloworld.global.error.ErrorProperty

enum class AuthErrorCode(
    private val status: Int,
    private val message: String
): ErrorProperty {

    REFRESH_TOKEN_NOT_FOUND(404, "RefreshToken Not Found");

    override fun status(): Int = status

    override fun message(): String = message

}