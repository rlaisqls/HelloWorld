package com.example.helloworld.domain.chat

import com.example.helloworld.domain.chat.dto.request.SendChatRequest
import com.example.helloworld.domain.chat.dto.request.SendChatWebRequest
import com.example.helloworld.domain.chat.usecase.SocketSendChatUseCase
import com.example.helloworld.global.annotation.SocketEvent
import com.example.helloworld.global.annotation.WebSocketAdapter
import com.example.helloworld.global.property.SocketProperty
import com.example.helloworld.global.socket.SocketClient
import org.springframework.web.bind.annotation.RequestBody
import javax.validation.Valid


@WebSocketAdapter
class SocketChatWebAdapter (
    private val sendChatUseCase: SocketSendChatUseCase
) {

    @SocketEvent(SocketProperty.CHAT, SendChatWebRequest::class)
    fun sendChat(socketClient: SocketClient, request: SendChatWebRequest) {
        sendChatUseCase.execute(
            socketClient = socketClient,
            request = SendChatRequest(
                message = request.message
            )
        )
    }
}