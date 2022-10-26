package com.example.helloworld.domain.auth.usecase

import com.example.helloworld.domain.auth.dto.response.TokenResponse
import com.example.helloworld.domain.auth.exception.RefreshTokenNotFoundException
import com.example.helloworld.domain.auth.spi.QueryRefreshTokenPort
import com.example.helloworld.domain.user.spi.UserJwtPort
import com.example.helloworld.global.annotation.UseCase

@UseCase
class ReissueTokenUseCase(
    private val userJwtPort: UserJwtPort,
    private val queryRefreshTokenPort: QueryRefreshTokenPort
) {
    fun execute(request: String): TokenResponse {
        val token = queryRefreshTokenPort.queryRefreshTokenByToken(request)
            ?: throw RefreshTokenNotFoundException.EXCEPTION

        return userJwtPort.updateToken(
            refreshToken = token.token,
            username = token.username
        )
    }
}