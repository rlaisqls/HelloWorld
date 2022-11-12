package com.example.helloworld.domain.room.exception

import com.example.helloworld.domain.room.error.RoomErrorCode
import com.example.helloworld.global.error.BusinessException

object AlreadyJoinedRoomException: BusinessException(RoomErrorCode.ALREADY_JOINED_ROOM)