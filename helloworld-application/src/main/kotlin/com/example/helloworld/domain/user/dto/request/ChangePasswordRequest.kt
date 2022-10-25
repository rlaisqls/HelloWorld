package com.example.helloworld.domain.user.dto.request

data class ChangePasswordRequest(
    val oldPassword: String,
    val newPassword: String
)