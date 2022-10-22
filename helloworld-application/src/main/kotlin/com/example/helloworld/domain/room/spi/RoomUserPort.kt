package com.example.helloworld.domain.room.spi

import com.example.helloworld.domain.room.model.Room
import com.example.helloworld.domain.user.model.User

interface RoomUserPort {

    fun roomUserExists(room: Room, user: User): Boolean

    fun isFulledRoom(room: Room): Boolean

    fun addRoomUser(room: Room, user: User)

    fun deleteRoomUser(room: Room, user: User)

}