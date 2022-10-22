package com.example.helloworld.domain.user.spi

import com.example.helloworld.domain.user.model.User

interface QueryUserPort {

    fun existsUserByUsername(username: String): Boolean

    fun queryUserByUsername(username: String): User?

    fun queryUserById(id: Long): User?

}