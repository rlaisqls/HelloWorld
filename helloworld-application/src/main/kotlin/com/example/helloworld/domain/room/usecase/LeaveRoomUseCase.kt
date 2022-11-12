package com.example.helloworld.domain.room.usecase

import com.example.helloworld.domain.room.exception.RoomNotFoundException
import com.example.helloworld.domain.room.spi.QueryRoomPort
import com.example.helloworld.domain.room.spi.RoomUserPort
import com.example.helloworld.domain.room.spi.SocketRoomPort
import com.example.helloworld.domain.user.exception.UserNotFoundException
import com.example.helloworld.domain.user.spi.QueryUserPort
import com.example.helloworld.domain.user.spi.SecurityPort
import com.example.helloworld.domain.user.spi.UserPort
import com.example.helloworld.global.annotation.UseCase

@UseCase
class LeaveRoomUseCase (
    private val queryUserPort: QueryUserPort,
    private val securityPort: SecurityPort,
    private val queryRoomPort: QueryRoomPort,
    private val roomUserPort: RoomUserPort,
    private val socketRoomPort: SocketRoomPort
) {
    fun execute(roomId: Long) {

        val currentUsername = securityPort.getCurrentUserUsername()
        val user = queryUserPort.queryUserByUsername(currentUsername) ?: throw UserNotFoundException

        val room = queryRoomPort.queryRoomById(roomId) ?: throw RoomNotFoundException

        if(!roomUserPort.roomUserExists(room, user)) {
            throw RoomNotFoundException
        }

        roomUserPort.deleteRoomUser(room, user)
        socketRoomPort.sendLeaveMessage(room.id, user.username)
    }
}