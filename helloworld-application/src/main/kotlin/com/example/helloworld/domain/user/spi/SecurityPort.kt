package com.example.helloworld.domain.user.spi

interface SecurityPort {

    fun checkPassword(password: String, encryptedPassword: String): Boolean

    fun encode(password: String): String

    fun getCurrentUserEmail(): String

}