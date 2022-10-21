package com.example.helloworld.domain.room.exception

import com.example.helloworld.domain.room.error.RoomErrorCode
import com.example.helloworld.global.error.BusinessException

class RoomParticipatingException private constructor() : BusinessException(RoomErrorCode.ROOM_NOT_PARTICIPATING) {

    companion object {
        @JvmField
        val EXCEPTION = RoomParticipatingException()
    }

}