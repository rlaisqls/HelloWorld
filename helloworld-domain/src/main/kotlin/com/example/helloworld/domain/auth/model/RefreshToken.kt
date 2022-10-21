package com.example.helloworld.domain.auth.model

data class RefreshToken(
    val token: String,
    val username: String,
    val expiration: Long
)