package com.example.helloworld.domain.room.usecase

import com.example.helloworld.domain.room.exception.RoomNotFoundException
import com.example.helloworld.domain.room.spi.QueryRoomPort
import com.example.helloworld.domain.room.spi.RoomUserPort
import com.example.helloworld.domain.room.spi.SocketRoomPort
import com.example.helloworld.domain.user.spi.UserPort
import com.example.helloworld.global.annotation.UseCase

@UseCase
class LeaveRoomUseCase (
    private val userPort: UserPort,
    private val queryRoomPort: QueryRoomPort,
    private val roomUserPort: RoomUserPort,
    private val socketRoomPort: SocketRoomPort
) {
    fun execute(roomId: Long) {

        val user = userPort.getCurrentUser()
        val room = queryRoomPort.queryRoomById(roomId) ?: throw RoomNotFoundException.EXCEPTION

        if(!roomUserPort.roomUserExists(room, user)){
            throw RoomNotFoundException.EXCEPTION
        }

        roomUserPort.deleteRoomUser(room, user)
        socketRoomPort.sendLeaveMessage(room.id, user.username)
    }
}