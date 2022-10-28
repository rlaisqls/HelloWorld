package com.example.helloworld.domain.room.usecase

import com.example.helloworld.domain.room.exception.RoomNotFoundException
import com.example.helloworld.domain.room.model.Room
import com.example.helloworld.domain.room.spi.QueryRoomPort
import com.example.helloworld.domain.room.spi.RoomUserPort
import com.example.helloworld.domain.room.spi.SocketRoomPort
import com.example.helloworld.domain.user.model.User
import com.example.helloworld.domain.user.spi.QueryUserPort
import com.example.helloworld.domain.user.spi.SecurityPort
import com.example.helloworld.domain.user.spi.UserPort
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.then

@ExtendWith(MockitoExtension::class)
internal class LeaveRoomUseCaseTest {

    @Mock
    private lateinit var securityPort: SecurityPort

    @Mock
    private lateinit var queryUserPort: QueryUserPort

    @Mock
    private lateinit var queryRoomPort: QueryRoomPort

    @Mock
    private lateinit var roomUserPort: RoomUserPort

    @Mock
    private lateinit var socketRoomPort: SocketRoomPort

    @InjectMocks
    private lateinit var leaveRoomUseCase: LeaveRoomUseCase

    private val username = "rlaisqls"
    private val password = "password"

    private val userStub by lazy {
        User(
            username = username,
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

    @Test
    fun 채팅방_나가기_성공() {
        //given
        given(securityPort.getCurrentUserUsername())
            .willReturn(username)

        given(queryUserPort.queryUserByUsername(username))
            .willReturn(userStub)

        given(queryRoomPort.queryRoomById(roomId))
            .willReturn(roomStub)

        given(roomUserPort.roomUserExists(roomStub, userStub))
            .willReturn(true)

        //when & then
        assertDoesNotThrow {
            leaveRoomUseCase.execute(roomId)
        }
        then(roomUserPort).should(times(1)).deleteRoomUser(roomStub, userStub)
        then(socketRoomPort).should(times(1)).sendLeaveMessage(roomStub.id, userStub.username)
    }

    @Test
    fun 존재하지_않는_방임() {
        //given
        given(securityPort.getCurrentUserUsername())
            .willReturn(username)

        given(queryUserPort.queryUserByUsername(username))
            .willReturn(userStub)

        given(queryRoomPort.queryRoomById(roomId))
            .willReturn(null)

        //when & then
        assertThrows<RoomNotFoundException> {
            leaveRoomUseCase.execute(roomId)
        }
        then(roomUserPort).should(times(0)).deleteRoomUser(roomStub, userStub)
        then(socketRoomPort).should(times(0)).sendLeaveMessage(roomStub.id, userStub.username)
    }

    @Test
    fun 참여중인_방이_아님() {
        //given
        given(securityPort.getCurrentUserUsername())
            .willReturn(username)

        given(queryUserPort.queryUserByUsername(username))
            .willReturn(userStub)

        given(queryRoomPort.queryRoomById(roomId))
            .willReturn(roomStub)

        given(roomUserPort.roomUserExists(roomStub, userStub))
            .willReturn(false)

        //when & then
        assertThrows<RoomNotFoundException> {
            leaveRoomUseCase.execute(roomId)
        }
        then(roomUserPort).should(times(0)).deleteRoomUser(roomStub, userStub)
        then(socketRoomPort).should(times(0)).sendLeaveMessage(roomStub.id, userStub.username)
    }
}