package com.example.helloworld.chat

import com.example.helloworld.domain.chat.dto.request.SendChatRequest
import com.example.helloworld.chat.dto.request.SendChatWebRequest
import com.example.helloworld.domain.chat.usecase.SocketSendChatUseCase
import com.example.helloworld.global.socket.SocketClient
import com.example.helloworld.socket.mapper.annotation.SocketEvent
import com.example.helloworld.socket.mapper.annotation.WebSocketAdapter
import com.example.helloworld.socket.property.SocketProperty
import org.springframework.web.bind.annotation.RequestBody
import javax.validation.Valid


@WebSocketAdapter
class SocketChatWebAdapter (
    private val sendChatUseCase: SocketSendChatUseCase
) {

    @SocketEvent(SocketProperty.CHAT, SendChatWebRequest::class)
    fun sendChat(socketClient: SocketClient, @Valid @RequestBody request: SendChatWebRequest) {
        sendChatUseCase.execute(
            socketClient = socketClient,
            request = SendChatRequest(
                message = request.message
            )
        )
    }
}