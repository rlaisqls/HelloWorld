package com.example.helloworld.domain.room.dto.request

data class CreateRoomRequest(
    val roomName: String,
    val maxPeople: Int
)