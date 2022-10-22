package com.example.helloworld.domain.room.dto.response

data class QueryRoomListResponse(
    val roomList: List<RoomResponse>
) {
    data class RoomResponse(
        val roomId: Long,
        val roomName: String,
        val maxPeople: Int,
        val currentPeople: Int
    )
}