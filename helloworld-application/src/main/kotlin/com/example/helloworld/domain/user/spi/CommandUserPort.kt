package com.example.helloworld.domain.user.spi

import com.example.helloworld.domain.user.model.User

interface CommandUserPort {
    fun saveUser(user: User): User

}