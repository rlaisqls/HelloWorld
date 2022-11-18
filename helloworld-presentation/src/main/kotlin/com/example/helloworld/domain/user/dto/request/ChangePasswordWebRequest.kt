package com.example.helloworld.domain.user.dto.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class ChangePasswordWebRequest (

    @field:NotBlank
    @field:Size(max = 30)
    val oldPassword: String,

    @field:NotBlank
    @field:Size(max = 30)
    val newPassword: String

)