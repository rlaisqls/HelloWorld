package com.example.helloworld.domain.room.usecase

import com.example.helloworld.domain.room.exception.AlreadyJoinedRoomException
import com.example.helloworld.domain.room.exception.FulledRoomException
import com.example.helloworld.domain.room.exception.RoomNotFoundException
import com.example.helloworld.domain.room.spi.*
import com.example.helloworld.domain.user.spi.UserPort
import com.example.helloworld.global.annotation.UseCase

@UseCase
class JoinRoomUseCase (
    private val userPort: UserPort,
    private val roomUserPort: RoomUserPort,
    private val queryRoomPort: QueryRoomPort,
    private val socketRoomPort: SocketRoomPort
) {
    fun execute(roomId: Long) {

        val user = userPort.getCurrentUser()
        val room = queryRoomPort.queryRoomById(roomId) ?: throw RoomNotFoundException.EXCEPTION

        if(roomUserPort.roomUserExists(room, user)) {
            throw AlreadyJoinedRoomException.EXCEPTION
        }

        if(roomUserPort.isFulledRoom(room)) {
            throw FulledRoomException.EXCEPTION
        }

        roomUserPort.addRoomUser(room, user)

        socketRoomPort.sendJoinMessage(room.id, user.username)
    }
}