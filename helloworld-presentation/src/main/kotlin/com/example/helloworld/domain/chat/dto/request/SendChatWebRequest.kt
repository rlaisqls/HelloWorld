package com.example.helloworld.domain.chat.dto.request

import java.beans.ConstructorProperties
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class SendChatWebRequest @ConstructorProperties("message") constructor(
    
    @field:NotNull
    @field:Size(max = 255)
    val message: String
)