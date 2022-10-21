package com.example.helloworld.domain.room.model

data class Room (
    val id: Long = 0,
    val roomName: String,
    val maxPeople: Int,
    val currentPeople: Int = 0
)