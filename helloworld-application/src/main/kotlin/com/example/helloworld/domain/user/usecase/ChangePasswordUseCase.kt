package com.example.helloworld.domain.user.usecase

import com.example.helloworld.domain.auth.dto.response.TokenResponse
import com.example.helloworld.domain.user.dto.request.ChangePasswordRequest
import com.example.helloworld.domain.user.exception.PasswordMismatchException
import com.example.helloworld.domain.user.model.User
import com.example.helloworld.domain.user.spi.*
import com.example.helloworld.global.annotation.UseCase

@UseCase
class ChangePasswordUseCase(
    private val userPort: UserPort,
    private val securityPort: SecurityPort,
    private val commandUserPort: CommandUserPort
) {
    fun execute(request: ChangePasswordRequest) {

        val user = userPort.getCurrentUser()

        if (!securityPort.checkPassword(request.oldPassword, user.password)) {
            throw PasswordMismatchException.EXCEPTION
        }

        commandUserPort.saveUser(
            user.copy(
                password = securityPort.encode(request.newPassword),
            )
        )
    }
}