package com.example.helloworld.domain.user.spi

import com.example.helloworld.domain.user.model.User

interface UserPort : QueryUserPort, CommandUserPort {

    fun getCurrentUser() : User

}