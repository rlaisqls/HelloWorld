package com.example.helloworld.domain.room.spi

import com.example.helloworld.domain.user.model.User

interface SocketRoomPort {

    fun sendJoinMessage(roomId: Long, username: String)
    fun sendLeaveMessage(roomId: Long, username: String)

}