package com.example.helloworld.domain.auth.usecase

import com.example.helloworld.domain.auth.dto.response.TokenResponse
import com.example.helloworld.domain.auth.spi.AuthInfoPort
import com.example.helloworld.domain.auth.spi.dto.request.CodeRequest
import com.example.helloworld.domain.user.exception.UserAlreadyExistException
import com.example.helloworld.domain.user.model.User
import com.example.helloworld.domain.user.spi.CommandUserPort
import com.example.helloworld.domain.user.spi.QueryUserPort
import com.example.helloworld.domain.user.spi.UserJwtPort
import com.example.helloworld.global.annotation.UseCase

@UseCase
class GoogleAuthUseCase(
    private val authInfoPort: AuthInfoPort,
    private val userJwtPort: UserJwtPort,
    private val queryUserPort: QueryUserPort,
    private val commandUserPort: CommandUserPort
) {
    fun execute(request: CodeRequest): TokenResponse {

        val authInfo = authInfoPort.getGoogleAuthInfo(request.code)

        if (queryUserPort.existsUserByEmail(authInfo.email)) {
            throw UserAlreadyExistException
        } else {
            commandUserPort.saveUser(
                User(
                    name = authInfo.name,
                    email = authInfo.email,
                    profileImageUrl = authInfo.profileImageUrl,
                    password = null
                )
            )
        }

        return userJwtPort.generateToken(authInfo.name)
    }
}