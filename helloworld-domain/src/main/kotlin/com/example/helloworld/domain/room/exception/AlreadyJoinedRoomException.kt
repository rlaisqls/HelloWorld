package com.example.helloworld.domain.room.exception

import com.example.helloworld.domain.room.error.RoomErrorCode
import com.example.helloworld.global.error.BusinessException

class AlreadyJoinedRoomException private constructor() : BusinessException(RoomErrorCode.ALREADY_JOINED_ROOM) {

    companion object {
        @JvmField
        val EXCEPTION = AlreadyJoinedRoomException()
    }

}