package com.example.helloworld.domain.chat.dto.response

import java.time.LocalDateTime

data class ChatResponse (
    val username: String,
    val message: String,
    val sentAt: LocalDateTime
)