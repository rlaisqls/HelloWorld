package com.example.helloworld.domain.chat.usecase

import com.example.helloworld.domain.chat.dto.request.SendChatRequest
import com.example.helloworld.domain.chat.model.Chat
import com.example.helloworld.domain.chat.spi.CommandChatPort
import com.example.helloworld.domain.chat.spi.SocketChatPort
import com.example.helloworld.domain.chat.spi.SocketUserPort
import com.example.helloworld.domain.user.model.User
import com.example.helloworld.global.socket.SocketClient
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.spy
import org.mockito.kotlin.then
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
internal class SocketSendChatUseCaseTest {

    @Mock
    private lateinit var socketUserPort: SocketUserPort

    @Mock
    private lateinit var commandChatPort: CommandChatPort

    @Mock
    private lateinit var socketChatPort: SocketChatPort

    @InjectMocks
    private lateinit var socketSendChatUseCase: SocketSendChatUseCase

    private val name = "김은빈"
    private val email = "rlaisqls@gmail.com"
    private val password = "password"

    private val userStub by lazy {
        User(
            name = name,
            email = email,
            password = password
        )
    }

    private val stringRoomId = "1"
    private val roomId = stringRoomId.toLong()

    private val message = "안녕하세요"
    private val dateTime = LocalDateTime.of(2022,10,22,9,0)

    private val chatStub by lazy {
        Chat(
            roomId = roomId,
            email = email,
            message = message,
            sentAt = dateTime
        )
    }

    private val socketClient: SocketClient = spy()

    @Test
    fun 채팅_전송_성공() {
        //given
        given(socketUserPort.getCurrentemail(socketClient))
            .willReturn(email)

        given(socketUserPort.getCurrentRoomId(socketClient))
            .willReturn(stringRoomId)

        given(commandChatPort.save(any()))
            .willReturn(chatStub)

        val request = SendChatRequest(message = message)

        //when
        socketSendChatUseCase.execute(request = request, socketClient = socketClient)

        //then
        then(socketChatPort).should(times(1)).sendChat(roomId, chatStub)
    }
}