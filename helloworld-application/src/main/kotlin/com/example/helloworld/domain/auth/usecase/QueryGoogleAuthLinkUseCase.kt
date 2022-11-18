package com.example.helloworld.domain.auth.usecase

import com.example.helloworld.domain.auth.dto.response.AuthLinkResponse
import com.example.helloworld.domain.auth.spi.QueryAuthLinkPort
import com.example.helloworld.global.annotation.UseCase

@UseCase
class QueryGoogleAuthLinkUseCase(
    private val queryAuthLinkPort: QueryAuthLinkPort
) {
    fun execute(): AuthLinkResponse {
        return AuthLinkResponse(
            queryAuthLinkPort.queryGoogleAuthLink()
        )
    }
}