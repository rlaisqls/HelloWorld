package com.example.helloworld.domain.user.model

import com.example.helloworld.global.image.DefaultImages

data class User (
    val id: Long = 0,
    val profileImageUrl: String = DefaultImages.USER_IMAGE,
    val email: String,
    val name: String,
    val password: String?
)