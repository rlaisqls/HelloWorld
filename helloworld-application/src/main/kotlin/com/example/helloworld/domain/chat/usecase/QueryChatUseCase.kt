package com.example.helloworld.domain.chat.usecase

import com.example.helloworld.domain.chat.dto.response.ChatResponse
import com.example.helloworld.domain.chat.dto.response.QueryChatListResponse
import com.example.helloworld.domain.chat.spi.QueryChatPort
import com.example.helloworld.domain.room.exception.RoomNotFoundException
import com.example.helloworld.domain.room.exception.RoomParticipatingException
import com.example.helloworld.domain.room.spi.QueryRoomPort
import com.example.helloworld.domain.room.spi.RoomUserPort
import com.example.helloworld.domain.user.spi.UserPort
import com.example.helloworld.global.annotation.ReadOnlyUseCase
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@ReadOnlyUseCase
class QueryChatUseCase (
    private val userPort: UserPort,
    private val roomUserPort: RoomUserPort,
    private val queryRoomPort: QueryRoomPort,
    private val queryChatPort: QueryChatPort
) {
    fun execute(roomId: Long, dateTime: LocalDateTime): QueryChatListResponse {

        val user = userPort.getCurrentUser()
        val room = queryRoomPort.queryRoomById(roomId) ?: throw RoomNotFoundException.EXCEPTION

        if (!roomUserPort.roomUserExists(room, user)) {
            throw RoomParticipatingException.EXCEPTION
        }

        return QueryChatListResponse(
            queryChatPort.queryChatByRoom(room, dateTime)
                .map {
                    ChatResponse(
                        username = it.username,
                        message = it.message,
                        sentAt = it.sentAt.format(
                            DateTimeFormatter
                            .ofPattern("a HH:mm")
                            .withLocale(Locale.forLanguageTag("ko")))
                    )
                }
        )
    }
}