package com.example.helloworld.socket

import com.example.helloworld.domain.chat.dto.response.ChatResponse
import com.example.helloworld.domain.chat.model.Chat
import com.example.helloworld.domain.chat.spi.SocketPort
import com.example.helloworld.domain.room.dto.response.RoomMessageResponse
import com.example.helloworld.global.socket.SocketClient
import com.example.helloworld.socket.property.ClientProperty
import com.example.helloworld.global.property.SocketProperty
import com.example.helloworld.socket.SocketIOConnectListener.Companion.socketIOClientMap
import org.springframework.stereotype.Component
import java.time.format.DateTimeFormatter
import java.util.*

@Component
class SocketAdapter : SocketPort {

    override fun getCurrentRoomId(socketClient: SocketClient): String {
        return socketClient.getByKey(ClientProperty.ROOM_KEY)!!
    }

    override fun getCurrentUsername(socketClient: SocketClient): String {
        return socketClient.getByKey(ClientProperty.USER_KEY)!!
    }

    override fun sendJoinMessage(roomId: Long, username: String) {
        sendSocket(roomId, SocketProperty.ROOM, RoomMessageResponse(username + "님이 입장하셨습니다"))
    }

    override fun sendLeaveMessage(roomId: Long, username: String) {
        sendSocket(roomId, SocketProperty.ROOM, RoomMessageResponse(username + "님이 나가셨습니다"))
    }

    override fun sendChat(roomId: Long, chat: Chat, username: String) {

        val chatResponse = ChatResponse(
            username = username,
            sentAt = chat.sentAt.format(
                DateTimeFormatter
                    .ofPattern("a HH:mm")
                    .withLocale(Locale.forLanguageTag("ko"))
            ),
            message = chat.message
        )

        sendSocket(roomId, SocketProperty.CHAT, chatResponse)
    }

    private fun sendSocket(roomId: Long, event: String, objectToSend: Any) {
        socketIOClientMap[roomId.toString()]
            ?.map { it.sendEvent(event, objectToSend) }
    }

}