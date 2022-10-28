package com.example.helloworld.domain.room.usecase

import com.example.helloworld.domain.room.dto.response.QueryRoomListResponse
import com.example.helloworld.domain.room.spi.QueryRoomPort
import com.example.helloworld.domain.room.spi.RoomUserPort
import com.example.helloworld.domain.room.spi.SocketRoomPort
import com.example.helloworld.domain.user.spi.UserPort
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class QueryAllRoomListUseCaseTest {

    @Mock
    private lateinit var queryRoomPort: QueryRoomPort

    @InjectMocks
    private lateinit var queryAllRoomListUseCase: QueryAllRoomListUseCase

    @Test
    fun 전체_채팅방_조회_성공() {
        //given
        given(queryRoomPort.queryAllRoomList())
            .willReturn(emptyList())

        //when
        val response = queryAllRoomListUseCase.execute()

        //then
        assertEquals(response.roomList, emptyList<QueryRoomListResponse>())
    }

}