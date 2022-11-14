package com.example.helloworld.domain.user.usecase

import com.example.helloworld.domain.auth.dto.response.TokenResponse
import com.example.helloworld.domain.user.dto.request.SignUpRequest
import com.example.helloworld.domain.user.exception.UserAlreadyExistException
import com.example.helloworld.domain.user.model.User
import com.example.helloworld.domain.user.spi.CommandUserPort
import com.example.helloworld.domain.user.spi.QueryUserPort
import com.example.helloworld.domain.user.spi.SecurityPort
import com.example.helloworld.domain.user.spi.UserJwtPort
import com.example.helloworld.global.annotation.UseCase

@UseCase
class SignUpUseCase(
    private val userJwtPort: UserJwtPort,
    private val queryUserPort: QueryUserPort,
    private val securityPort: SecurityPort,
    private val commandUserPort: CommandUserPort
) {
    fun execute(request: SignUpRequest): TokenResponse {

        if (queryUserPort.existsUserByUsername(request.username)) {
            throw UserAlreadyExistException
        }

        val user = commandUserPort.saveUser(
            User(
                username = request.username,
                password = securityPort.encode(request.password),
            )
        )

        return userJwtPort.generateToken(
            username = user.username
        )
    }
}