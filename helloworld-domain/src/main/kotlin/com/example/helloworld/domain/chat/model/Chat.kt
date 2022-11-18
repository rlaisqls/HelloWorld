package com.example.helloworld.domain.chat.model

import java.time.LocalDateTime

data class Chat (
    val id: Long = 0,
    val roomId: Long,
    val email: String,
    val message: String,
    val sentAt: LocalDateTime
)