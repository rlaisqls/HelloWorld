package com.example.helloworld.domain.user.error

import com.example.helloworld.global.error.ErrorProperty

enum class UserErrorCode(
    private val status: Int,
    private val message: String
): ErrorProperty {

    PASSWORD_MISMATCH(401, "Password Mismatch"),
    USER_NOT_FOUND(404, "User Not Found"),
    SIGNUP_TYPE(403, "Signup Type is invalid"),
    USER_ALREADY_EXIST(409, "User Already Exist");

    override fun status(): Int = status

    override fun message(): String = message

}