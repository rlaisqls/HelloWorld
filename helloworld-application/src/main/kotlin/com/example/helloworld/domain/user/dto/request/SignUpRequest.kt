package com.example.helloworld.domain.user.dto.request

data class SignUpRequest(
    val email: String,
    val name: String,
    val password: String
)