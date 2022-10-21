package com.example.helloworld.room.dto.request

import javax.validation.constraints.Size

data class CreateRoomWebRequest (
    @field:Size(max = 15)
    val roomName: String,

    @field:Size(max = 50)
    val maxPeople: Int
)