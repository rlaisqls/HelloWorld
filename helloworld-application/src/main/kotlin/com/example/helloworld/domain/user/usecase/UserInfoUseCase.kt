package com.example.helloworld.domain.user.usecase

import com.example.helloworld.domain.user.dto.response.UserInfoResponse
import com.example.helloworld.domain.user.spi.UserPort
import com.example.helloworld.global.annotation.ReadOnlyUseCase

@ReadOnlyUseCase
class UserInfoUseCase(
    private val userPort: UserPort
) {
    fun execute(): UserInfoResponse {

        val user = userPort.getCurrentUser()

        return UserInfoResponse(
            username = user.username
        )
    }
}