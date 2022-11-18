package com.example.helloworld.domain.user.usecase

import com.example.helloworld.domain.user.dto.request.ChangeProfileImageRequest
import com.example.helloworld.domain.user.exception.UserNotFoundException
import com.example.helloworld.domain.user.spi.CommandUserPort
import com.example.helloworld.domain.user.spi.QueryUserPort
import com.example.helloworld.domain.user.spi.SecurityPort
import com.example.helloworld.global.annotation.UseCase

@UseCase
class ChangeProfileImageUseCase(
    private val queryUserPort: QueryUserPort,
    private val securityPort: SecurityPort,
    private val commandUserPort: CommandUserPort
) {
    fun execute(request: ChangeProfileImageRequest) {

        val currentUserEmail = securityPort.getCurrentUserEmail()
        val user = queryUserPort.queryUserByEmail(currentUserEmail) ?: throw UserNotFoundException

        commandUserPort.saveUser(
            user.copy(
                profileImageUrl = request.profileImageUrl
            )
        )
    }
}