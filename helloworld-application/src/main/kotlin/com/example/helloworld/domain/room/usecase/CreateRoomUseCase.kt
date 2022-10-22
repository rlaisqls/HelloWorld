package com.example.helloworld.domain.room.usecase

import com.example.helloworld.domain.room.dto.request.CreateRoomRequest
import com.example.helloworld.domain.room.model.Room
import com.example.helloworld.domain.room.spi.CommandRoomPort
import com.example.helloworld.domain.room.spi.RoomUserPort
import com.example.helloworld.domain.room.spi.SocketRoomPort
import com.example.helloworld.domain.user.spi.UserPort
import com.example.helloworld.global.annotation.UseCase

@UseCase
class CreateRoomUseCase (
    private val userPort: UserPort,
    private val commandRoomPort: CommandRoomPort,
    private val roomUserPort: RoomUserPort,
    private val socketRoomPort: SocketRoomPort
) {
    fun execute(request: CreateRoomRequest) {

        val user = userPort.getCurrentUser()

        val room = commandRoomPort.saveRoom(
            Room(
                roomName = request.roomName,
                maxPeople = request.maxPeople,
                roomUsers = ArrayList()
            )
        )
        roomUserPort.addRoomUser(room, user);

        socketRoomPort.sendJoinMessage(room.id, user.username)
    }
}