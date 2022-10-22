package com.example.helloworld.domain.room.usecase

import com.example.helloworld.domain.room.dto.response.QueryRoomListResponse
import com.example.helloworld.domain.room.spi.QueryRoomPort
import com.example.helloworld.global.annotation.ReadOnlyUseCase


@ReadOnlyUseCase
class QueryAllRoomListUseCase(
    private val queryRoomPort: QueryRoomPort
) {
    fun execute(): QueryRoomListResponse {

        val roomList = queryRoomPort.queryAllRoomList()

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