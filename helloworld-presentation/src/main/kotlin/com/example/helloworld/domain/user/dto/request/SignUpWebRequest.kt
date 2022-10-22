package com.example.helloworld.domain.user.dto.request

import org.jetbrains.annotations.NotNull
import javax.validation.constraints.Size

data class SignUpWebRequest(

    @field:NotNull("username은 null이어선 안됩니다")
    @field:Size(max = 15, message = "username은 15자 이하여야합니다.")
    val username: String,

    @field:NotNull("password는 null이어선 안됩니다")
    @field:Size(max = 30, message = "password은 30자 이하여야합니다.")
    val password: String
)