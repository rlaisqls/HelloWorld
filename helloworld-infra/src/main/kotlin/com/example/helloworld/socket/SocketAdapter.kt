package com.example.helloworld.socket

import com.corundumstudio.socketio.SocketIOClient
import com.corundumstudio.socketio.SocketIOServer
import com.example.helloworld.domain.chat.dto.response.ChatResponse
import com.example.helloworld.domain.chat.model.Chat
import com.example.helloworld.domain.chat.spi.SocketPort
import com.example.helloworld.domain.room.dto.response.SocketRoomMessageResponse
import com.example.helloworld.domain.user.model.User
import com.example.helloworld.socket.adapter.SocketClient
import com.example.helloworld.socket.property.ClientProperty
import com.example.helloworld.socket.property.SocketProperty
import org.springframework.stereotype.Component


@Component
class SocketAdapter (
    private val socketIOServer: SocketIOServer
) : SocketPort {

    override fun getCurrentRoomId(socketClient: SocketClient): Long {
        return socketClient.getByKey(ClientProperty.ROOM_KEY)!!.toLong()
    }

    override fun getCurrentUserId(socketClient: SocketClient): Long {
        return socketClient.getByKey(ClientProperty.ROOM_KEY)!!.toLong()
    }

    override fun sendJoinMessage(roomId: Long, username: String) {
        sendSocket(roomId, SocketProperty.ROOM, SocketRoomMessageResponse(username + "님이 입장하셨습니다"))
    }

    override fun sendLeaveMessage(roomId: Long, username: String) {
        sendSocket(roomId, SocketProperty.ROOM, SocketRoomMessageResponse(username + "님이 나가셨습니다"))
    }

    override fun sendChat(roomId: Long, chat: Chat, user: User) {

        val chatResponse = ChatResponse(
            username = user.username,
            sentAt = chat.sentAt,
            message = chat.message
        )

        sendSocket(roomId, SocketProperty.CHAT, chatResponse)
    }

    private fun sendSocket(roomId: Long, event: String, objectToSend: Any) {
        socketIOServer
            .getRoomOperations(roomId.toString())
            .sendEvent(event, objectToSend)
    }

}