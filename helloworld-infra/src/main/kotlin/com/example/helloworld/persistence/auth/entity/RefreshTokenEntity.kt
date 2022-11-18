package com.example.helloworld.persistence.auth.entity

import org.hibernate.validator.constraints.Length
import org.jetbrains.annotations.NotNull
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive

@RedisHash("refresh_token")
data class RefreshTokenEntity(
    @Id
    val token: String,

    @field:NotNull
    val email: String,

    @field:NotNull
    @TimeToLive
    val expiration: Long
)