package com.example.helloworld.domain.room.usecase

import com.example.helloworld.domain.room.dto.response.QueryRoomListResponse
import com.example.helloworld.domain.room.spi.QueryRoomPort
import com.example.helloworld.domain.user.exception.UserNotFoundException
import com.example.helloworld.domain.user.spi.QueryUserPort
import com.example.helloworld.domain.user.spi.SecurityPort
import com.example.helloworld.domain.user.spi.UserPort
import com.example.helloworld.global.annotation.ReadOnlyUseCase

@ReadOnlyUseCase
class QueryMyRoomListUseCase(
    private val queryUserPort: QueryUserPort,
    private val securityPort: SecurityPort,
    private val queryRoomPort: QueryRoomPort
) {
    fun execute(): QueryRoomListResponse {

        val currentUsername = securityPort.getCurrentUserUsername()
        val user = queryUserPort.queryUserByUsername(currentUsername) ?: throw UserNotFoundException

        val roomList = queryRoomPort.queryMyRoomList(user)

        return QueryRoomListResponse(
            roomList = roomList
                .map {
                    QueryRoomListResponse.RoomResponse(
                        roomId = it.id,
                        roomName = it.roomName,
                        maxPeople = it.maxPeople,
                        currentPeople = it.roomUsers.size
                    )
                }
        )
    }
}