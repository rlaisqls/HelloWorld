package com.example.helloworld.domain.auth.spi.dto.response

data class AuthInfoResponse(
    val email: String,
    val name: String,
    val profileImageUrl: String
)