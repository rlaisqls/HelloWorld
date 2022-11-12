package com.example.helloworld.domain.user.usecase

import com.example.helloworld.domain.user.dto.response.UserInfoResponse
import com.example.helloworld.domain.user.exception.UserNotFoundException
import com.example.helloworld.domain.user.spi.QueryUserPort
import com.example.helloworld.domain.user.spi.SecurityPort
import com.example.helloworld.domain.user.spi.UserPort
import com.example.helloworld.global.annotation.ReadOnlyUseCase

@ReadOnlyUseCase
class UserInfoUseCase(
    private val queryUserPort: QueryUserPort,
    private val securityPort: SecurityPort
) {
    fun execute(): UserInfoResponse {

        val currentUsername = securityPort.getCurrentUserUsername()
        val user = queryUserPort.queryUserByUsername(currentUsername) ?: throw UserNotFoundException

        return UserInfoResponse(
            username = user.username
        )
    }
}