package com.example.helloworld.domain.room.model

import com.example.helloworld.domain.user.model.User

data class RoomUser (
    val id: Long = 0,
    val roomId: Long,
    val userId: Long
)