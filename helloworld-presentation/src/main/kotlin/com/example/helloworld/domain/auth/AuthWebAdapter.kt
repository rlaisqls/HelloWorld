package com.example.helloworld.domain.auth

import com.example.helloworld.domain.auth.dto.response.TokenResponse
import com.example.helloworld.domain.auth.usecase.ReissueTokenUseCase
import com.example.helloworld.global.annotation.WebAdapter
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@WebAdapter
@RequestMapping("/users")
class AuthWebAdapter(
    private val reissueTokenUseCase: ReissueTokenUseCase
) {

    @PutMapping("/auth")
    fun tokenRefresh(@RequestHeader("X-Refresh-Token") refreshToken: String): TokenResponse {
        return reissueTokenUseCase.execute(refreshToken)
    }
}