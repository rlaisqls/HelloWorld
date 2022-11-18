package com.example.helloworld.domain.chat.usecase

import com.example.helloworld.domain.chat.spi.QueryChatPort
import com.example.helloworld.domain.room.dto.response.QueryRoomListResponse
import com.example.helloworld.domain.room.exception.RoomNotFoundException
import com.example.helloworld.domain.room.exception.RoomParticipatingException
import com.example.helloworld.domain.room.model.Room
import com.example.helloworld.domain.room.spi.QueryRoomPort
import com.example.helloworld.domain.room.spi.RoomUserPort
import com.example.helloworld.domain.user.model.User
import com.example.helloworld.domain.user.spi.QueryUserPort
import com.example.helloworld.domain.user.spi.SecurityPort
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
internal class QueryChatUseCaseTest {

    @Mock
    private lateinit var securityPort: SecurityPort

    @Mock
    private lateinit var queryUserPort: QueryUserPort

    @Mock
    private lateinit var roomUserPort: RoomUserPort

    @Mock
    private lateinit var queryRoomPort: QueryRoomPort

    @Mock
    private lateinit var queryChatPort: QueryChatPort

    @InjectMocks
    private lateinit var queryChatUseCase: QueryChatUseCase

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

    private val roomId = 1L
    private val roomName = "roomName"
    private val maxPeople = 5

    private val roomStub by lazy {
        Room(
            id = roomId,
            roomName = roomName,
            maxPeople = maxPeople,
            roomUsers = emptyList()
        )
    }

    private val dateTime = LocalDateTime.of(2022,10,22,9,0)

    @Test
    fun 채팅_조회_성공() {
        //given
        given(securityPort.getCurrentUserEmail())
            .willReturn(email)

        given(queryUserPort.queryUserByEmail(email))
            .willReturn(userStub)

        given(queryRoomPort.queryRoomById(roomId))
            .willReturn(roomStub)

        given(roomUserPort.roomUserExists(roomStub, userStub))
            .willReturn(true)

        given(queryChatPort.queryChatByRoom(roomStub, dateTime))
            .willReturn(emptyList())

        //when
        val response = queryChatUseCase.execute(roomId, dateTime)

        //then
        assertEquals(response.chatList, emptyList<QueryRoomListResponse>())
    }

    @Test
    fun 채팅방을_찾을_수_없음() {
        //given
        given(securityPort.getCurrentUserEmail())
            .willReturn(email)

        given(queryUserPort.queryUserByEmail(email))
            .willReturn(userStub)

        given(queryRoomPort.queryRoomById(roomId))
            .willReturn(null)

        //when & then
        assertThrows<RoomNotFoundException> {
            queryChatUseCase.execute(roomId, dateTime)
        }
    }

    @Test
    fun 참여중이지_않은_방임() {
        //given
        given(securityPort.getCurrentUserEmail())
            .willReturn(email)

        given(queryUserPort.queryUserByEmail(email))
            .willReturn(userStub)

        given(queryRoomPort.queryRoomById(roomId))
            .willReturn(roomStub)

        given(roomUserPort.roomUserExists(roomStub, userStub))
            .willReturn(false)

        //when & then
        assertThrows<RoomParticipatingException> {
            queryChatUseCase.execute(roomId, dateTime)
        }
    }
}