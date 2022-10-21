package com.example.helloworld.domain.room.exception

import com.example.helloworld.domain.room.error.RoomErrorCode
import com.example.helloworld.domain.user.error.UserErrorCode
import com.example.helloworld.global.error.BusinessException

class RoomNotFoundException private constructor() : BusinessException(RoomErrorCode.ROOM_NOT_FOUND) {

    companion object {
        @JvmField
        val EXCEPTION = RoomNotFoundException()
    }

}