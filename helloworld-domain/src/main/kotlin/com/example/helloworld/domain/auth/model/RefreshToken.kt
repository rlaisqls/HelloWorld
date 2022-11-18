package com.example.helloworld.domain.auth.model

data class RefreshToken(
    val token: String,
    val email: String,
    val expiration: Long
)