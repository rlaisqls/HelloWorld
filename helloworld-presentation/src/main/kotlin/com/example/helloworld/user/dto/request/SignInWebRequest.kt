package com.example.helloworld.user.dto.request

import org.jetbrains.annotations.NotNull
import javax.validation.constraints.Size
import kotlin.math.min

data class SignInWebRequest(

    @field:NotNull
    @field:Size(max=15)
    val username: String,

    @field:NotNull
    @field:Size(max=30)
    val password: String
)