package com.example.helloworld.domain.room.spi

import com.example.helloworld.domain.room.model.Room
import com.example.helloworld.domain.user.model.User

interface CommandRoomPort {

    fun saveRoom(room: Room): Room

}