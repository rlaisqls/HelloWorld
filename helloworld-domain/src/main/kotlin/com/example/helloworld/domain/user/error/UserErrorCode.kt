package com.example.helloworld.domain.user.error

import com.example.helloworld.global.error.ErrorProperty

enum class UserErrorCode(
    private val status: Int,
    private val message: String
): ErrorProperty {

    USER_NOT_FOUND(404, "User Not Found"),
    USER_ALREADY_EXIST(409, "User Already Exist"),
    PASSWORD_MISMATCH(401, "Password Mismatch");

    override fun status(): Int = status

    override fun message(): String = message

}