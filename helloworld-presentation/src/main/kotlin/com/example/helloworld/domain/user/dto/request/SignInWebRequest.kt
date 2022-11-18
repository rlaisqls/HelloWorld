package com.example.helloworld.domain.user.dto.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class SignInWebRequest(

    @field:NotBlank
    @field:Size(max = 40)
    val email: String,

    @field:NotBlank
    @field:Size(max = 30)
    val password: String

)