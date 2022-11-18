package com.example.helloworld.domain.user.dto.request

import org.jetbrains.annotations.NotNull
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class SignUpWebRequest(

    @field:NotBlank
    @field:Size(max = 40)
    val email: String,

    @field:NotNull
    @field:Size(max = 15)
    val name: String,

    @field:NotBlank
    @field:Size(max = 30)
    val password: String

)