package com.example.helloworld.domain.auth.dto.response

import java.time.LocalDateTime
import java.util.*

data class TokenResponse(
    val accessToken: String,
    val accessTokenExp: LocalDateTime,
    val refreshToken: String
)