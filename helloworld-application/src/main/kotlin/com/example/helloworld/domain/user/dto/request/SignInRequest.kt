package com.example.helloworld.domain.user.dto.request

data class SignInRequest(
    val email: String,
    val password: String
)