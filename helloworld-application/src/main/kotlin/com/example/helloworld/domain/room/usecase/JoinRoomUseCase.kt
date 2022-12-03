package com.example.helloworld.domain.room.usecase

import com.example.helloworld.domain.room.exception.AlreadyJoinedRoomException
import com.example.helloworld.domain.room.exception.FulledRoomException
import com.example.helloworld.domain.room.exception.RoomNotFoundException
import com.example.helloworld.domain.room.spi.*
import com.example.helloworld.domain.user.exception.UserNotFoundException
import com.example.helloworld.domain.user.spi.QueryUserPort
import com.example.helloworld.domain.user.spi.SecurityPort
import com.example.helloworld.global.annotation.UseCase

@UseCase
class JoinRoomUseCase (
    private val queryUserPort: QueryUserPort,
    private val securityPort: SecurityPort,
    private val roomUserPort: RoomUserPort,
    private val queryRoomPort: QueryRoomPort,
    private val socketRoomPort: SocketRoomPort
) {
    fun execute(roomId: Long) {

        val currentUsername = securityPort.getCurrentUserUsername()
        val user = queryUserPort.queryUserByUsername(currentUsername) ?: throw UserNotFoundException

        val room = queryRoomPort.queryRoomById(roomId) ?: throw RoomNotFoundException

        if(roomUserPort.roomUserExists(room, user)) {
            throw AlreadyJoinedRoomException
        }

        if(roomUserPort.isFulledRoom(room)) {
            throw FulledRoomException
        }

        roomUserPort.addRoomUser(room, user)

        socketRoomPort.sendJoinMessage(room.id, user.username)
    }
}