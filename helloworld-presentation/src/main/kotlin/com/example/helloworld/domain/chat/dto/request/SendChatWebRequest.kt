package com.example.helloworld.domain.chat.dto.request

import java.beans.ConstructorProperties
import javax.validation.constraints.Size

data class SendChatWebRequest @ConstructorProperties("message") constructor(
    @field:Size(max = 255, message = "room_name은 15자 이하여야합니다.")
    val message: String
)