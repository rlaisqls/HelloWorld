package com.example.helloworld.domain.auth

import com.example.helloworld.domain.auth.dto.response.AuthLinkResponse
import com.example.helloworld.domain.auth.dto.response.TokenResponse
import com.example.helloworld.domain.auth.spi.dto.request.CodeRequest
import com.example.helloworld.domain.auth.usecase.GoogleAuthUseCase
import com.example.helloworld.domain.auth.usecase.QueryGoogleAuthLinkUseCase
import com.example.helloworld.domain.auth.usecase.ReissueTokenUseCase
import com.example.helloworld.global.annotation.WebAdapter
import org.springframework.web.bind.annotation.*


@WebAdapter
@RequestMapping("/users")
class AuthWebAdapter(
    private val reissueTokenUseCase: ReissueTokenUseCase,
    private val queryGoogleAuthLinkUseCase: QueryGoogleAuthLinkUseCase,
    private val googleAuthUseCase: GoogleAuthUseCase
) {
    @PutMapping("/auth")
    fun tokenRefresh(@RequestHeader("X-Refresh-Token") refreshToken: String): TokenResponse {
        return reissueTokenUseCase.execute(refreshToken)
    }

    @GetMapping("/google")
    fun queryGoogleAuthLink(): AuthLinkResponse {
        return queryGoogleAuthLinkUseCase.execute()
    }

    @PostMapping("/google")
    fun googleAuthInfo(@ModelAttribute request: CodeRequest): TokenResponse {
        return googleAuthUseCase.execute(request)
    }

}