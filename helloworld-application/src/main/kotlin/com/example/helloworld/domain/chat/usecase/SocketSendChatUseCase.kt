package com.example.helloworld.domain.chat.usecase

import com.example.helloworld.domain.chat.dto.request.SendChatRequest
import com.example.helloworld.domain.chat.model.Chat
import com.example.helloworld.domain.chat.spi.CommandChatPort
import com.example.helloworld.domain.chat.spi.SocketChatPort
import com.example.helloworld.domain.chat.spi.SocketUserPort
import com.example.helloworld.domain.user.spi.QueryUserPort
import com.example.helloworld.global.annotation.UseCase
import com.example.helloworld.global.socket.SocketClient
import java.time.LocalDateTime

@UseCase
class SocketSendChatUseCase (
    private val socketUserPort: SocketUserPort,
    private val commandChatPort: CommandChatPort,
    private val socketChatPort: SocketChatPort
) {
    fun execute(socketClient: SocketClient, request: SendChatRequest) {

        val email = socketUserPort.getCurrentemail(socketClient)
        val roomId = socketUserPort.getCurrentRoomId(socketClient).toLong()

        val chat = commandChatPort.save(
            Chat(
                roomId = roomId,
                email = email,
                message = request.message,
                sentAt = LocalDateTime.now()
            )
        )

        socketChatPort.sendChat(roomId, chat)
    }
}