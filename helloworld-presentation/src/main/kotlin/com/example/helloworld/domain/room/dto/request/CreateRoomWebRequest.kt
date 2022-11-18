package com.example.helloworld.domain.room.dto.request

import org.jetbrains.annotations.NotNull
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.Size

data class CreateRoomWebRequest(

    @field:NotNull
    @field:Size(max = 15)
    val roomName: String,

    @field:NotNull
    @field:Max(value = 50)
    @field:Min(value = 1)
    val maxPeople: Int
)