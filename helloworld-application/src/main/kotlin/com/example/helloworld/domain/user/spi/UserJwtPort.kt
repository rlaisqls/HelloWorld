package com.example.helloworld.domain.user.spi

import com.example.helloworld.domain.auth.dto.response.TokenResponse

interface UserJwtPort {

    fun generateToken(username: String): TokenResponse
    
    fun updateToken(refreshToken: String, username: String): TokenResponse

}