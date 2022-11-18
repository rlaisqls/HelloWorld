package com.example.helloworld.thirdparty.socket

import com.example.helloworld.domain.chat.dto.response.ChatResponse
import com.example.helloworld.domain.chat.model.Chat
import com.example.helloworld.domain.chat.spi.SocketPort
import com.example.helloworld.domain.room.dto.response.RoomMessageResponse
import com.example.helloworld.global.property.SocketProperty
import com.example.helloworld.global.socket.SocketClient
import com.example.helloworld.thirdparty.socket.SocketIOConnectListener.Companion.socketIOClientMap
import com.example.helloworld.thirdparty.socket.property.ClientProperty
import org.springframework.stereotype.Component
import java.time.format.DateTimeFormatter
import java.util.*

@Component
class SocketAdapter : SocketPort {

    override fun getCurrentRoomId(socketClient: SocketClient): String {
        return socketClient.getByKey(ClientProperty.ROOM_KEY)!!
    }

    override fun getCurrentemail(socketClient: SocketClient): String {
        return socketClient.getByKey(ClientProperty.USER_KEY)!!
    }

    override fun sendJoinMessage(roomId: Long, email: String) {
        sendSocket(roomId, SocketProperty.ROOM, RoomMessageResponse(email + "님이 입장하셨습니다"))
    }

    override fun sendLeaveMessage(roomId: Long, email: String) {
        sendSocket(roomId, SocketProperty.ROOM, RoomMessageResponse(email + "님이 나가셨습니다"))
    }

    override fun sendChat(roomId: Long, chat: Chat) {

        val chatResponse = ChatResponse(
            email = chat.email,
            sentAt = chat.sentAt
                .format(
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