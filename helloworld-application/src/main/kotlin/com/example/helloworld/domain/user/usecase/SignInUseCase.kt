package com.example.helloworld.domain.user.usecase

import com.example.helloworld.domain.auth.dto.response.TokenResponse
import com.example.helloworld.domain.user.dto.request.SignInRequest
import com.example.helloworld.domain.user.exception.PasswordMismatchException
import com.example.helloworld.domain.user.exception.SignupTypeException
import com.example.helloworld.domain.user.exception.UserNotFoundException
import com.example.helloworld.domain.user.spi.QueryUserPort
import com.example.helloworld.domain.user.spi.SecurityPort
import com.example.helloworld.domain.user.spi.UserJwtPort
import com.example.helloworld.global.annotation.UseCase

@UseCase
class SignInUseCase(
    private val queryUserPort: QueryUserPort,
    private val securityPort: SecurityPort,
    private val userJwtPort: UserJwtPort
) {
    fun execute(request: SignInRequest): TokenResponse {

        val user = queryUserPort.queryUserByEmail(request.email) ?: throw UserNotFoundException

        if(user.password.isNullOrBlank()) throw SignupTypeException

        if (!securityPort.checkPassword(request.password, user.password!!)) {
            throw PasswordMismatchException
        }

        return userJwtPort.generateToken(
            email = user.email
        )
    }
}