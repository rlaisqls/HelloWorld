package com.example.helloworld.domain.room.usecase

import com.example.helloworld.domain.room.exception.AlreadyJoinedRoomException
import com.example.helloworld.domain.room.exception.RoomNotFoundException
import com.example.helloworld.domain.room.model.Room
import com.example.helloworld.domain.room.spi.QueryRoomPort
import com.example.helloworld.domain.room.spi.RoomUserPort
import com.example.helloworld.domain.room.spi.SocketRoomPort
import com.example.helloworld.domain.user.exception.UserNotFoundException
import com.example.helloworld.domain.user.model.User
import com.example.helloworld.domain.user.spi.UserPort
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.then
import java.time.LocalDateTime


@ExtendWith(MockitoExtension::class)
internal class JoinRoomUseCaseTest {

    @Mock
    private lateinit var userPort: UserPort

    @Mock
    private lateinit var roomUserPort: RoomUserPort

    @Mock
    private lateinit var queryRoomPort: QueryRoomPort

    @Mock
    private lateinit var socketRoomPort: SocketRoomPort

    @InjectMocks
    private lateinit var joinRoomUseCase: JoinRoomUseCase

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
    fun 채팅방_참가_성공() {
        //given
        given(userPort.getCurrentUser())
            .willReturn(userStub)

        given(queryRoomPort.queryRoomById(roomId))
            .willReturn(roomStub)

        given(roomUserPort.roomUserExists(roomStub, userStub))
            .willReturn(false)

        given(roomUserPort.isFulledRoom(roomStub))
            .willReturn(false)

        //when & then
        assertDoesNotThrow {
            joinRoomUseCase.execute(roomId)
        }
        then(roomUserPort).should(times(1)).addRoomUser(roomStub, userStub)
        then(socketRoomPort).should(times(1)).sendJoinMessage(roomStub.id, userStub.username)
    }

    @Test
    fun 방을_찾을_수_없음() {
        //given
        given(userPort.getCurrentUser())
            .willReturn(userStub)

        given(queryRoomPort.queryRoomById(roomId))
            .willReturn(null)

        //when & then
        assertThrows<RoomNotFoundException> {
            joinRoomUseCase.execute(roomId)
        }
        then(roomUserPort).should(times(0)).addRoomUser(roomStub, userStub)
        then(socketRoomPort).should(times(0)).sendJoinMessage(roomStub.id, userStub.username)
    }


    @Test
    fun 이미_참가중인_채팅방() {
        //given
        given(userPort.getCurrentUser())
            .willReturn(userStub)

        given(queryRoomPort.queryRoomById(roomId))
            .willReturn(roomStub)

        given(roomUserPort.roomUserExists(roomStub, userStub))
            .willReturn(true)

        //when & then
        assertThrows<AlreadyJoinedRoomException> {
            joinRoomUseCase.execute(roomId)
        }
        then(roomUserPort).should(times(0)).addRoomUser(roomStub, userStub)
        then(socketRoomPort).should(times(0)).sendJoinMessage(roomStub.id, userStub.username)
    }

    @Test
    fun 채팅방_정원초과() {
        //given
        given(userPort.getCurrentUser())
            .willReturn(userStub)

        given(queryRoomPort.queryRoomById(roomId))
            .willReturn(roomStub)

        given(roomUserPort.roomUserExists(roomStub, userStub))
            .willReturn(false)

        given(roomUserPort.isFulledRoom(roomStub))
            .willReturn(true)

        //when & then
        assertThrows<AlreadyJoinedRoomException> {
            joinRoomUseCase.execute(roomId)
        }
        then(roomUserPort).should(times(0)).addRoomUser(roomStub, userStub)
        then(socketRoomPort).should(times(0)).sendJoinMessage(roomStub.id, userStub.username)
    }

}