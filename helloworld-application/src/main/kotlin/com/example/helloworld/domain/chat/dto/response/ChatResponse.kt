package com.example.helloworld.domain.chat.dto.response

data class ChatResponse (
    val username: String,
    val message: String,
    val sentAt: String
)