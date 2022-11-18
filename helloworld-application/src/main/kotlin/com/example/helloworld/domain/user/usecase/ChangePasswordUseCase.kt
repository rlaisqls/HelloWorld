package com.example.helloworld.domain.user.usecase

import com.example.helloworld.domain.user.dto.request.ChangePasswordRequest
import com.example.helloworld.domain.user.exception.PasswordMismatchException
import com.example.helloworld.domain.user.exception.SignupTypeException
import com.example.helloworld.domain.user.exception.UserNotFoundException
import com.example.helloworld.domain.user.spi.CommandUserPort
import com.example.helloworld.domain.user.spi.QueryUserPort
import com.example.helloworld.domain.user.spi.SecurityPort
import com.example.helloworld.global.annotation.UseCase

@UseCase
class ChangePasswordUseCase(
    private val queryUserPort: QueryUserPort,
    private val securityPort: SecurityPort,
    private val commandUserPort: CommandUserPort
) {
    fun execute(request: ChangePasswordRequest) {

        val currentUserEmail = securityPort.getCurrentUserEmail()
        val user = queryUserPort.queryUserByEmail(currentUserEmail) ?: throw UserNotFoundException

        if(user.password.isNullOrBlank()) throw SignupTypeException

        if (!securityPort.checkPassword(request.oldPassword, user.password!!)) {
            throw PasswordMismatchException
        }

        commandUserPort.saveUser(
            user.copy(
                password = securityPort.encode(request.newPassword)
            )
        )
    }
}