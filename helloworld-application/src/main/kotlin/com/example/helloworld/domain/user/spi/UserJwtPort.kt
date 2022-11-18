package com.example.helloworld.domain.user.spi

import com.example.helloworld.domain.auth.dto.response.TokenResponse

interface UserJwtPort {

    fun generateToken(email: String): TokenResponse
    
    fun updateToken(refreshToken: String, email: String): TokenResponse

}