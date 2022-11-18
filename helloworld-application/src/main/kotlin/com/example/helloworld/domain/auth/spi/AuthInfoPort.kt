package com.example.helloworld.domain.auth.spi

import com.example.helloworld.domain.auth.spi.dto.response.AuthInfoResponse

interface AuthInfoPort {
    fun getGoogleAuthInfo(code: String): AuthInfoResponse
}