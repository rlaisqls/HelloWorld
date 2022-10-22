package com.example.helloworld.domain.room.dto.request

import org.jetbrains.annotations.NotNull
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.Size

data class CreateRoomWebRequest(

    @field:NotNull("room_name은 null이어선 안됩니다")
    @field:Size(max = 15, message = "room_name은 15자 이하여야합니다.")
    val roomName: String,

    @field:NotNull("max_people은 null이어선 안됩니다")
    @field:Max(value = 50, message = "max_people은 50 이하여야합니다.")
    @field:Min(value = 1, message = "max_people은 1 이상이여야합니다.")
    val maxPeople: Int
)