package com.example.helloworld.chat.dto.request

import javax.validation.constraints.Size

data class SendChatWebRequest (
    @field:Size(max = 255)
    val message: String
)