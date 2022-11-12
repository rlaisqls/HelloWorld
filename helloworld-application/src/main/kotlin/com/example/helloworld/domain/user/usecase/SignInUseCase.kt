package com.example.helloworld.domain.user.usecase

import com.example.helloworld.domain.auth.dto.response.TokenResponse
import com.example.helloworld.domain.user.dto.request.SignInRequest
import com.example.helloworld.domain.user.exception.PasswordMismatchException
import com.example.helloworld.domain.user.exception.UserNotFoundException
import com.example.helloworld.domain.user.spi.QueryUserPort
import com.example.helloworld.domain.user.spi.UserJwtPort
import com.example.helloworld.domain.user.spi.SecurityPort
import com.example.helloworld.global.annotation.UseCase

@UseCase
class SignInUseCase(
    private val queryUserPort: QueryUserPort,
    private val securityPort: SecurityPort,
    private val userJwtPort: UserJwtPort
) {
    fun execute(request: SignInRequest): TokenResponse {

        val user = queryUserPort.queryUserByUsername(request.username) ?: throw UserNotFoundException

        if (!securityPort.checkPassword(request.password, user.password)) {
            throw PasswordMismatchException
        }

        return userJwtPort.generateToken(
            username = user.username
        )
    }
}