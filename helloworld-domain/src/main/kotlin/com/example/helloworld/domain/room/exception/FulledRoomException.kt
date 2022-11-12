package com.example.helloworld.domain.room.exception

import com.example.helloworld.domain.room.error.RoomErrorCode
import com.example.helloworld.global.error.BusinessException

object FulledRoomException : BusinessException(RoomErrorCode.FULLED_ROOM)