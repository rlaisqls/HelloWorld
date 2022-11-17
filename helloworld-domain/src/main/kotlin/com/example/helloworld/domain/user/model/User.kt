package com.example.helloworld.domain.user.model

data class User (
    val id: Long = 0,
    val profileImageUrl: String,
    val username: String,
    val password: String
)