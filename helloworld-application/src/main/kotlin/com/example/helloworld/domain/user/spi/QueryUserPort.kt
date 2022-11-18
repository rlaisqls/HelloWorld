package com.example.helloworld.domain.user.spi

import com.example.helloworld.domain.user.model.User

interface QueryUserPort {

    fun existsUserByEmail(email: String): Boolean

    fun queryUserByEmail(email: String): User?

    fun queryUserById(id: Long): User?

}