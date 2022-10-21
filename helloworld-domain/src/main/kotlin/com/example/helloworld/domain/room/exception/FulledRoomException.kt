package com.example.helloworld.domain.room.exception

import com.example.helloworld.domain.room.error.RoomErrorCode
import com.example.helloworld.global.error.BusinessException

class FulledRoomException private constructor() : BusinessException(RoomErrorCode.FULLED_ROOM) {

    companion object {
        @JvmField
        val EXCEPTION = FulledRoomException()
    }

}