package com.example.helloworld.domain.room.error

import com.example.helloworld.global.error.ErrorProperty

enum class RoomErrorCode(
    private val status: Int,
    private val message: String
): ErrorProperty {

    ROOM_NOT_FOUND(404, "Room Not Found"),
    ROOM_NOT_PARTICIPATING(403, "Room Not Participating"),
    FULLED_ROOM(409, "Fulled Room");

    override fun status(): Int = status

    override fun message(): String = message

}