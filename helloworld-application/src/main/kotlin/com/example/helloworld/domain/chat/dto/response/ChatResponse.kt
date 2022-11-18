package com.example.helloworld.domain.chat.dto.response

data class ChatResponse (
    val email: String,
    val message: String,
    val sentAt: String
)