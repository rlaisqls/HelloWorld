package com.example.helloworld.domain.chat.usecase

import com.example.helloworld.domain.chat.dto.response.ChatResponse
import com.example.helloworld.domain.chat.dto.response.QueryChatListResponse
import com.example.helloworld.domain.chat.spi.QueryChatPort
import com.example.helloworld.domain.room.exception.RoomNotFoundException
import com.example.helloworld.domain.room.exception.RoomParticipatingException
import com.example.helloworld.domain.room.spi.QueryRoomPort
import com.example.helloworld.domain.room.spi.RoomUserPort
import com.example.helloworld.domain.user.exception.UserNotFoundException
import com.example.helloworld.domain.user.spi.QueryUserPort
import com.example.helloworld.domain.user.spi.SecurityPort
import com.example.helloworld.global.annotation.ReadOnlyUseCase
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@ReadOnlyUseCase
class QueryChatUseCase (
    private val securityPort: SecurityPort,
    private val queryUserPort: QueryUserPort,
    private val roomUserPort: RoomUserPort,
    private val queryRoomPort: QueryRoomPort,
    private val queryChatPort: QueryChatPort
) {
    fun execute(roomId: Long, dateTime: LocalDateTime): QueryChatListResponse {

        val currentUserEmail = securityPort.getCurrentUserEmail()
        val user = queryUserPort.queryUserByEmail(currentUserEmail) ?: throw UserNotFoundException

        val room = queryRoomPort.queryRoomById(roomId) ?: throw RoomNotFoundException

        if (!roomUserPort.roomUserExists(room, user)) {
            throw RoomParticipatingException
        }

        return QueryChatListResponse(
            queryChatPort.queryChatByRoom(room, dateTime)
                .map {
                    ChatResponse(
                        email = it.email,
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