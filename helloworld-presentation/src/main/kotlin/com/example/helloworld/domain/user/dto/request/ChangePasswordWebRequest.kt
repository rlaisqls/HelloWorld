package com.example.helloworld.domain.user.dto.request

import org.jetbrains.annotations.NotNull
import javax.validation.constraints.Size

data class ChangePasswordWebRequest (

    @field:NotNull("old_password는 null이어선 안됩니다")
    @field:Size(max = 30, message = "old_password은 30자 이하여야합니다.")
    val oldPassword: String,

    @field:NotNull("new_password는 null이어선 안됩니다")
    @field:Size(max = 30, message = "new_password은 30자 이하여야합니다.")
    val newPassword: String
)