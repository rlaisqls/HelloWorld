package com.example.helloworld.domain.room.spi

import com.example.helloworld.domain.room.model.Room
import com.example.helloworld.domain.user.model.User

interface QueryRoomPort {

    fun queryRoomById(roomId: Long): Room?

    fun queryMyRoomList(user: User): List<Room>

    fun queryAllRoomList(): List<Room>

}